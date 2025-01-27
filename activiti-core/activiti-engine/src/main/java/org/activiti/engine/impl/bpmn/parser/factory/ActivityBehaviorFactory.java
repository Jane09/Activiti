/*
 * Copyright 2010-2020 Alfresco Software, Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.engine.impl.bpmn.parser.factory;

import org.activiti.bpmn.model.*;
import org.activiti.engine.api.internal.Internal;
import org.activiti.engine.impl.bpmn.behavior.*;
import org.activiti.engine.impl.bpmn.helper.ClassDelegate;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.BpmnParser;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.delegate.ActivityBehavior;

/**
 * Factory class used by the {@link BpmnParser} and {@link BpmnParse} to instantiate the behaviour classes. For example when parsing an exclusive gateway, this factory will be requested to create a
 * new {@link ActivityBehavior} that will be set on the {@link ActivityImpl} of that step of the process and will implement the spec-compliant behavior of the exclusive gateway.
 * <p>
 * You can provide your own implementation of this class. This way, you can give different execution semantics to a standard bpmn xml construct. Eg. you could tweak the exclusive gateway to do
 * something completely different if you would want that. Creating your own {@link ActivityBehaviorFactory} is only advisable if you want to change the default behavior of any BPMN default construct.
 * And even then, think twice, because it won't be spec compliant bpmn anymore.
 * <p>
 * Note that you can always express any custom step as a service task with a class delegation.
 * <p>
 * The easiest and advisable way to implement your own {@link ActivityBehaviorFactory} is to extend the {@link DefaultActivityBehaviorFactory} class and override the method specific to the
 * {@link ActivityBehavior} you want to change.
 * <p>
 * An instance of this interface can be injected in the {@link ProcessEngineConfigurationImpl} and its subclasses.
 */
@Internal
public interface ActivityBehaviorFactory {

    public abstract NoneStartEventActivityBehavior createNoneStartEventActivityBehavior(StartEvent startEvent);

    public abstract TaskActivityBehavior createTaskActivityBehavior(Task task);

    public abstract ManualTaskActivityBehavior createManualTaskActivityBehavior(ManualTask manualTask);

    public abstract ReceiveTaskActivityBehavior createReceiveTaskActivityBehavior(ReceiveTask receiveTask);

    public abstract UserTaskActivityBehavior createUserTaskActivityBehavior(UserTask userTask);

    public abstract ClassDelegate createClassDelegateServiceTask(ServiceTask serviceTask);

    public abstract ServiceTaskDelegateExpressionActivityBehavior createServiceTaskDelegateExpressionActivityBehavior(ServiceTask serviceTask);

    public abstract ActivityBehavior createDefaultServiceTaskBehavior(ServiceTask serviceTask);

    public abstract ServiceTaskExpressionActivityBehavior createServiceTaskExpressionActivityBehavior(ServiceTask serviceTask);

    public abstract WebServiceActivityBehavior createWebServiceActivityBehavior(ServiceTask serviceTask);

    public abstract WebServiceActivityBehavior createWebServiceActivityBehavior(SendTask sendTask);

    public abstract MailActivityBehavior createMailActivityBehavior(ServiceTask serviceTask);

    public abstract MailActivityBehavior createMailActivityBehavior(SendTask sendTask);

    // We do not want a hard dependency on the Mule module, hence we return
    // ActivityBehavior and instantiate the delegate instance using a string instead of the Class itself.
    public abstract ActivityBehavior createMuleActivityBehavior(ServiceTask serviceTask);

    public abstract ActivityBehavior createMuleActivityBehavior(SendTask sendTask);

    public abstract ActivityBehavior createCamelActivityBehavior(ServiceTask serviceTask);

    public abstract ActivityBehavior createCamelActivityBehavior(SendTask sendTask);

    public abstract ShellActivityBehavior createShellActivityBehavior(ServiceTask serviceTask);

    public abstract ActivityBehavior createBusinessRuleTaskActivityBehavior(BusinessRuleTask businessRuleTask);

    public abstract ScriptTaskActivityBehavior createScriptTaskActivityBehavior(ScriptTask scriptTask);

    public abstract ExclusiveGatewayActivityBehavior createExclusiveGatewayActivityBehavior(ExclusiveGateway exclusiveGateway);

    public abstract ParallelGatewayActivityBehavior createParallelGatewayActivityBehavior(ParallelGateway parallelGateway);

    public abstract InclusiveGatewayActivityBehavior createInclusiveGatewayActivityBehavior(InclusiveGateway inclusiveGateway);

    public abstract EventBasedGatewayActivityBehavior createEventBasedGatewayActivityBehavior(EventGateway eventGateway);

    public abstract SequentialMultiInstanceBehavior createSequentialMultiInstanceBehavior(Activity activity, AbstractBpmnActivityBehavior innerActivityBehavior);

    public abstract ParallelMultiInstanceBehavior createParallelMultiInstanceBehavior(Activity activity, AbstractBpmnActivityBehavior innerActivityBehavior);

    public abstract SubProcessActivityBehavior createSubprocessActivityBehavior(SubProcess subProcess);

    public abstract EventSubProcessErrorStartEventActivityBehavior createEventSubProcessErrorStartEventActivityBehavior(StartEvent startEvent);

    public abstract EventSubProcessMessageStartEventActivityBehavior createEventSubProcessMessageStartEventActivityBehavior(StartEvent startEvent, MessageEventDefinition messageEventDefinition);

    public abstract AdhocSubProcessActivityBehavior createAdhocSubprocessActivityBehavior(SubProcess subProcess);

    public abstract CallActivityBehavior createCallActivityBehavior(CallActivity callActivity);

    public abstract TransactionActivityBehavior createTransactionActivityBehavior(Transaction transaction);

    public abstract IntermediateCatchEventActivityBehavior createIntermediateCatchEventActivityBehavior(IntermediateCatchEvent intermediateCatchEvent);

    public abstract IntermediateCatchMessageEventActivityBehavior createIntermediateCatchMessageEventActivityBehavior(IntermediateCatchEvent intermediateCatchEvent,
                                                                                                                      MessageEventDefinition messageEventDefinition);

    public abstract IntermediateCatchTimerEventActivityBehavior createIntermediateCatchTimerEventActivityBehavior(IntermediateCatchEvent intermediateCatchEvent, TimerEventDefinition timerEventDefinition);

    public abstract IntermediateCatchSignalEventActivityBehavior createIntermediateCatchSignalEventActivityBehavior(IntermediateCatchEvent intermediateCatchEvent,
                                                                                                                    SignalEventDefinition signalEventDefinition, Signal signal);

    public abstract IntermediateThrowNoneEventActivityBehavior createIntermediateThrowNoneEventActivityBehavior(ThrowEvent throwEvent);

    public abstract IntermediateThrowSignalEventActivityBehavior createIntermediateThrowSignalEventActivityBehavior(ThrowEvent throwEvent, SignalEventDefinition signalEventDefinition, Signal signal);

    public abstract IntermediateThrowCompensationEventActivityBehavior createIntermediateThrowCompensationEventActivityBehavior(ThrowEvent throwEvent, CompensateEventDefinition compensateEventDefinition);

    public abstract IntermediateThrowMessageEventActivityBehavior createThrowMessageEventActivityBehavior(ThrowEvent throwEvent,
                                                                                                          MessageEventDefinition messageEventDefinition,
                                                                                                          Message message);

    public abstract NoneEndEventActivityBehavior createNoneEndEventActivityBehavior(EndEvent endEvent);

    public abstract ErrorEndEventActivityBehavior createErrorEndEventActivityBehavior(EndEvent endEvent, ErrorEventDefinition errorEventDefinition);

    public abstract CancelEndEventActivityBehavior createCancelEndEventActivityBehavior(EndEvent endEvent);

    public abstract TerminateEndEventActivityBehavior createTerminateEndEventActivityBehavior(EndEvent endEvent);

    public abstract BoundaryEventActivityBehavior createBoundaryEventActivityBehavior(BoundaryEvent boundaryEvent, boolean interrupting);

    public abstract BoundaryCancelEventActivityBehavior createBoundaryCancelEventActivityBehavior(CancelEventDefinition cancelEventDefinition);

    public abstract BoundaryTimerEventActivityBehavior createBoundaryTimerEventActivityBehavior(BoundaryEvent boundaryEvent, TimerEventDefinition timerEventDefinition, boolean interrupting);

    public abstract BoundarySignalEventActivityBehavior createBoundarySignalEventActivityBehavior(BoundaryEvent boundaryEvent, SignalEventDefinition signalEventDefinition, Signal signal, boolean interrupting);

    public abstract BoundaryMessageEventActivityBehavior createBoundaryMessageEventActivityBehavior(BoundaryEvent boundaryEvent, MessageEventDefinition messageEventDefinition, boolean interrupting);

    public abstract BoundaryCompensateEventActivityBehavior createBoundaryCompensateEventActivityBehavior(BoundaryEvent boundaryEvent, CompensateEventDefinition compensateEventDefinition, boolean interrupting);

    public abstract ThrowMessageEndEventActivityBehavior createThrowMessageEndEventActivityBehavior(EndEvent endEvent,
                                                                                                    MessageEventDefinition messageEventDefinition,
                                                                                                    Message message);
}
