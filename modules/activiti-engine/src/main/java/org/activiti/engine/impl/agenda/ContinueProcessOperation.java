package org.activiti.engine.impl.agenda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.activiti.bpmn.model.Activity;
import org.activiti.bpmn.model.BoundaryEvent;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.Gateway;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.jobexecutor.AsyncContinuationJobHandler;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.MessageEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.util.ProcessDefinitionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Joram Barrez
 * @author Tijs Rademakers
 */
public class ContinueProcessOperation extends AbstractOperation {

  private static Logger logger = LoggerFactory.getLogger(ContinueProcessOperation.class);

  protected boolean forceSynchronousOperation;

  public ContinueProcessOperation(CommandContext commandContext, ActivityExecution execution, boolean forceSynchronousOperation) {
    super(commandContext, execution);
    this.forceSynchronousOperation = forceSynchronousOperation;
  }

  public ContinueProcessOperation(CommandContext commandContext, ActivityExecution execution) {
    this(commandContext, execution, false);
  }

  @Override
  public void run() {

    FlowElement currentFlowElement = execution.getCurrentFlowElement();

    if (currentFlowElement == null) {
      currentFlowElement = findCurrentFlowElement(execution);
      execution.setCurrentFlowElement(currentFlowElement);
    }

    if (currentFlowElement instanceof FlowNode) {
      continueThroughFlowNode((FlowNode) currentFlowElement);
    } else if (currentFlowElement instanceof SequenceFlow) {
      continueThroughSequenceFlow((SequenceFlow) currentFlowElement);
    } else {
      throw new RuntimeException("Programmatic error: no current flow element found or invalid type: " + currentFlowElement + ". Halting.");
    }

  }

  private void continueThroughFlowNode(FlowNode flowNode) {
    // See if flowNode is an async activity and schedule as a job if that evaluates to true
    if (!forceSynchronousOperation) {
      boolean isAsynchronous = false;
      boolean isExclusive = false;
      if (flowNode instanceof Activity) {
        Activity activity = (Activity) flowNode;
        if (activity.isAsynchronous()) {
          isAsynchronous = true;
          isExclusive = !activity.isNotExclusive();
        }
      } else if (flowNode instanceof Gateway) {
        Gateway gateway = (Gateway) flowNode;
        if (gateway.isAsynchronous()) {
          isAsynchronous = true;
          isExclusive = !gateway.isNotExclusive();
        }
      }

      if (isAsynchronous) {
        scheduleJob(isExclusive);
        return;
      }
    }

    // Synchronous execution

    // Execution listener
    if (CollectionUtils.isNotEmpty(flowNode.getExecutionListeners())) {
      executeExecutionListeners(flowNode, ExecutionListener.EVENTNAME_START);
    }

    // Execute any boundary events
    Collection<BoundaryEvent> boundaryEvents = findBoundaryEventsForFlowNode(execution.getProcessDefinitionId(), flowNode);
    if (CollectionUtils.isNotEmpty(boundaryEvents)) {
      executeBoundaryEvents(boundaryEvents, execution);
    }

    // Execute actual behavior
    ActivityBehavior activityBehavior = (ActivityBehavior) flowNode.getBehavior();
    if (activityBehavior != null) {
      logger.debug("Executing activityBehavior {} on activity '{}' with execution {}", activityBehavior.getClass(), flowNode.getId(), execution.getId());
      activityBehavior.execute(execution);
    } else {
      logger.debug("No activityBehavior on activity '{}' with execution {}", flowNode.getId(), execution.getId());
      Context.getAgenda().planTakeOutgoingSequenceFlowsOperation(execution, true);
    }
  }

  protected void continueThroughSequenceFlow(SequenceFlow sequenceFlow) {

    // Execution listener
    if (CollectionUtils.isNotEmpty(sequenceFlow.getExecutionListeners())) {
      executeExecutionListeners(sequenceFlow, ExecutionListener.EVENTNAME_TAKE);
    }

    FlowElement targetFlowElement = sequenceFlow.getTargetFlowElement();
    execution.setCurrentFlowElement(targetFlowElement);
    logger.debug("Sequence flow '{}' encountered. Continuing process by following it using execution {}", sequenceFlow.getId(), execution.getId());
    agenda.planContinueProcessOperation(execution);
  }

  protected void scheduleJob(boolean exclusive) {
    MessageEntity message = new MessageEntity();
    message.setExecutionId(execution.getId());
    message.setProcessInstanceId(execution.getProcessInstanceId());
    message.setProcessDefinitionId(execution.getProcessDefinitionId());
    message.setExclusive(exclusive);
    message.setJobHandlerType(AsyncContinuationJobHandler.TYPE);

    // Inherit tenant id (if applicable)
    if (execution.getTenantId() != null) {
      message.setTenantId(execution.getTenantId());
    }

    commandContext.getJobEntityManager().send(message);
  }

  protected void executeBoundaryEvents(Collection<BoundaryEvent> boundaryEvents, ActivityExecution execution) {

    // The parent execution becomes a scope, and a child execution iscreated
    // for each of the boundary events
    execution.setScope(true);

    for (BoundaryEvent boundaryEvent : boundaryEvents) {

      if (CollectionUtils.isEmpty(boundaryEvent.getEventDefinitions())) {
        continue;
      }

      ExecutionEntity childExecutionEntity = (ExecutionEntity) execution.createExecution();
      childExecutionEntity.setParentId(execution.getId());
      childExecutionEntity.setCurrentFlowElement(boundaryEvent);
      childExecutionEntity.setScope(false);

      ActivityBehavior boundaryEventBehavior = ((ActivityBehavior) boundaryEvent.getBehavior());
      logger.debug("Executing boundary event activityBehavior {} with execution {}", boundaryEventBehavior.getClass(), childExecutionEntity.getId());
      boundaryEventBehavior.execute(childExecutionEntity);
    }

  }

  protected Collection<BoundaryEvent> findBoundaryEventsForFlowNode(final String processDefinitionId, final FlowNode flowNode) {
    org.activiti.bpmn.model.Process process = getProcessDefinition(processDefinitionId);

    // This could be cached or could be done at parsing time
    List<BoundaryEvent> results = new ArrayList<BoundaryEvent>(1);
    Collection<BoundaryEvent> boundaryEvents = process.findFlowElementsOfType(BoundaryEvent.class, true);
    for (BoundaryEvent boundaryEvent : boundaryEvents) {
      if (boundaryEvent.getAttachedToRefId() != null && boundaryEvent.getAttachedToRefId().equals(flowNode.getId())) {
        results.add(boundaryEvent);
      }
    }
    return results;
  }

  protected org.activiti.bpmn.model.Process getProcessDefinition(String processDefinitionId) {
    // TODO: must be extracted / cache should be accessed in another way
    return ProcessDefinitionUtil.getProcess(processDefinitionId);
  }

}
