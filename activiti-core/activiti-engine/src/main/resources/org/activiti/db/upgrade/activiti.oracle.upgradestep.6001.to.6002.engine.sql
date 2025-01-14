create table ACT_RU_TIMER_JOB
(
  ID_                  NVARCHAR2(64) NOT NULL,
  REV_                 INTEGER,
  TYPE_                NVARCHAR2(255) NOT NULL,
  LOCK_EXP_TIME_       TIMESTAMP(6),
  LOCK_OWNER_          NVARCHAR2(255),
  EXCLUSIVE_           NUMBER(1,0) CHECK (EXCLUSIVE_ IN (1,0)),
  EXECUTION_ID_        NVARCHAR2(64),
  PROCESS_INSTANCE_ID_ NVARCHAR2(64),
  PROC_DEF_ID_         NVARCHAR2(64),
  RETRIES_             INTEGER,
  EXCEPTION_STACK_ID_  NVARCHAR2(64),
  EXCEPTION_MSG_       NVARCHAR2(2000),
  DUEDATE_             TIMESTAMP(6),
  REPEAT_              NVARCHAR2(255),
  HANDLER_TYPE_        NVARCHAR2(255),
  HANDLER_CFG_         NVARCHAR2(2000),
  TENANT_ID_           NVARCHAR2(255) DEFAULT '',
  primary key (ID_)
);

create table ACT_RU_SUSPENDED_JOB
(
  ID_                  NVARCHAR2(64) NOT NULL,
  REV_                 INTEGER,
  TYPE_                NVARCHAR2(255) NOT NULL,
  EXCLUSIVE_           NUMBER(1,0) CHECK (EXCLUSIVE_ IN (1,0)),
  EXECUTION_ID_        NVARCHAR2(64),
  PROCESS_INSTANCE_ID_ NVARCHAR2(64),
  PROC_DEF_ID_         NVARCHAR2(64),
  RETRIES_             INTEGER,
  EXCEPTION_STACK_ID_  NVARCHAR2(64),
  EXCEPTION_MSG_       NVARCHAR2(2000),
  DUEDATE_             TIMESTAMP(6),
  REPEAT_              NVARCHAR2(255),
  HANDLER_TYPE_        NVARCHAR2(255),
  HANDLER_CFG_         NVARCHAR2(2000),
  TENANT_ID_           NVARCHAR2(255) DEFAULT '',
  primary key (ID_)
);

create table ACT_RU_DEADLETTER_JOB
(
  ID_                  NVARCHAR2(64) NOT NULL,
  REV_                 INTEGER,
  TYPE_                NVARCHAR2(255) NOT NULL,
  EXCLUSIVE_           NUMBER(1,0) CHECK (EXCLUSIVE_ IN (1,0)),
  EXECUTION_ID_        NVARCHAR2(64),
  PROCESS_INSTANCE_ID_ NVARCHAR2(64),
  PROC_DEF_ID_         NVARCHAR2(64),
  EXCEPTION_STACK_ID_  NVARCHAR2(64),
  EXCEPTION_MSG_       NVARCHAR2(2000),
  DUEDATE_             TIMESTAMP(6),
  REPEAT_              NVARCHAR2(255),
  HANDLER_TYPE_        NVARCHAR2(255),
  HANDLER_CFG_         NVARCHAR2(2000),
  TENANT_ID_           NVARCHAR2(255) DEFAULT '',
  primary key (ID_)
);

create index ACT_IDX_JOB_EXECUTION_ID on ACT_RU_JOB (EXECUTION_ID_);
alter table ACT_RU_JOB
  add constraint ACT_FK_JOB_EXECUTION
    foreign key (EXECUTION_ID_)
      references ACT_RU_EXECUTION (ID_);

create index ACT_IDX_JOB_PROC_INST_ID on ACT_RU_JOB (PROCESS_INSTANCE_ID_);
alter table ACT_RU_JOB
  add constraint ACT_FK_JOB_PROCESS_INSTANCE
    foreign key (PROCESS_INSTANCE_ID_)
      references ACT_RU_EXECUTION (ID_);

create index ACT_IDX_JOB_PROC_DEF_ID on ACT_RU_JOB (PROC_DEF_ID_);
alter table ACT_RU_JOB
  add constraint ACT_FK_JOB_PROC_DEF
    foreign key (PROC_DEF_ID_)
      references ACT_RE_PROCDEF (ID_);

create index ACT_IDX_TJOB_EXECUTION_ID on ACT_RU_TIMER_JOB (EXECUTION_ID_);
alter table ACT_RU_TIMER_JOB
  add constraint ACT_FK_TJOB_EXECUTION
    foreign key (EXECUTION_ID_)
      references ACT_RU_EXECUTION (ID_);

create index ACT_IDX_TJOB_PROC_INST_ID on ACT_RU_TIMER_JOB (PROCESS_INSTANCE_ID_);
alter table ACT_RU_TIMER_JOB
  add constraint ACT_FK_TJOB_PROCESS_INSTANCE
    foreign key (PROCESS_INSTANCE_ID_)
      references ACT_RU_EXECUTION (ID_);

create index ACT_IDX_TJOB_PROC_DEF_ID on ACT_RU_TIMER_JOB (PROC_DEF_ID_);
alter table ACT_RU_TIMER_JOB
  add constraint ACT_FK_TJOB_PROC_DEF
    foreign key (PROC_DEF_ID_)
      references ACT_RE_PROCDEF (ID_);

create index ACT_IDX_TJOB_EXCEPTION on ACT_RU_TIMER_JOB (EXCEPTION_STACK_ID_);
alter table ACT_RU_TIMER_JOB
  add constraint ACT_FK_TJOB_EXCEPTION
    foreign key (EXCEPTION_STACK_ID_)
      references ACT_GE_BYTEARRAY (ID_);

create index ACT_IDX_SJOB_EXECUTION_ID on ACT_RU_SUSPENDED_JOB (EXECUTION_ID_);
alter table ACT_RU_SUSPENDED_JOB
  add constraint ACT_FK_SJOB_EXECUTION
    foreign key (EXECUTION_ID_)
      references ACT_RU_EXECUTION (ID_);

create index ACT_IDX_SJOB_PROC_INST_ID on ACT_RU_SUSPENDED_JOB (PROCESS_INSTANCE_ID_);
alter table ACT_RU_SUSPENDED_JOB
  add constraint ACT_FK_SJOB_PROCESS_INSTANCE
    foreign key (PROCESS_INSTANCE_ID_)
      references ACT_RU_EXECUTION (ID_);

create index ACT_IDX_SJOB_PROC_DEF_ID on ACT_RU_SUSPENDED_JOB (PROC_DEF_ID_);
alter table ACT_RU_SUSPENDED_JOB
  add constraint ACT_FK_SJOB_PROC_DEF
    foreign key (PROC_DEF_ID_)
      references ACT_RE_PROCDEF (ID_);

create index ACT_IDX_SJOB_EXCEPTION on ACT_RU_SUSPENDED_JOB (EXCEPTION_STACK_ID_);
alter table ACT_RU_SUSPENDED_JOB
  add constraint ACT_FK_SJOB_EXCEPTION
    foreign key (EXCEPTION_STACK_ID_)
      references ACT_GE_BYTEARRAY (ID_);

create index ACT_IDX_DJOB_EXECUTION_ID on ACT_RU_DEADLETTER_JOB (EXECUTION_ID_);
alter table ACT_RU_DEADLETTER_JOB
  add constraint ACT_FK_DJOB_EXECUTION
    foreign key (EXECUTION_ID_)
      references ACT_RU_EXECUTION (ID_);

create index ACT_IDX_DJOB_PROC_INST_ID on ACT_RU_DEADLETTER_JOB (PROCESS_INSTANCE_ID_);
alter table ACT_RU_DEADLETTER_JOB
  add constraint ACT_FK_DJOB_PROCESS_INSTANCE
    foreign key (PROCESS_INSTANCE_ID_)
      references ACT_RU_EXECUTION (ID_);

create index ACT_IDX_DJOB_PROC_DEF_ID on ACT_RU_DEADLETTER_JOB (PROC_DEF_ID_);
alter table ACT_RU_DEADLETTER_JOB
  add constraint ACT_FK_DJOB_PROC_DEF
    foreign key (PROC_DEF_ID_)
      references ACT_RE_PROCDEF (ID_);

create index ACT_IDX_DJOB_EXCEPTION on ACT_RU_DEADLETTER_JOB (EXCEPTION_STACK_ID_);
alter table ACT_RU_DEADLETTER_JOB
  add constraint ACT_FK_DJOB_EXCEPTION
    foreign key (EXCEPTION_STACK_ID_)
      references ACT_GE_BYTEARRAY (ID_);

-- Moving jobs with retries <= 0 to ACT_RU_DEADLETTER_JOB

INSERT INTO ACT_RU_DEADLETTER_JOB (ID_, REV_, TYPE_, EXCLUSIVE_, EXECUTION_ID_, PROCESS_INSTANCE_ID_, PROC_DEF_ID_,
                                   EXCEPTION_STACK_ID_, EXCEPTION_MSG_, DUEDATE_, REPEAT_, HANDLER_TYPE_, HANDLER_CFG_,
                                   TENANT_ID_)
  (SELECT ID_,
          REV_,
          TYPE_,
          EXCLUSIVE_,
          EXECUTION_ID_,
          PROCESS_INSTANCE_ID_,
          PROC_DEF_ID_,
          EXCEPTION_STACK_ID_,
          EXCEPTION_MSG_,
          DUEDATE_,
          REPEAT_,
          HANDLER_TYPE_,
          HANDLER_CFG_,
          TENANT_ID_
   from ACT_RU_JOB
   WHERE RETRIES_ <= 0);

DELETE
FROM ACT_RU_JOB
WHERE RETRIES_ <= 0;


-- Moving suspended jobs to ACT_RU_SUSPENDED_JOB

INSERT INTO ACT_RU_SUSPENDED_JOB (ID_, REV_, TYPE_, EXCLUSIVE_, EXECUTION_ID_, PROCESS_INSTANCE_ID_, PROC_DEF_ID_,
                                  RETRIES_, EXCEPTION_STACK_ID_, EXCEPTION_MSG_, DUEDATE_, REPEAT_, HANDLER_TYPE_,
                                  HANDLER_CFG_, TENANT_ID_)
  (SELECT job.ID_,
          job.REV_,
          job.TYPE_,
          job.EXCLUSIVE_,
          job.EXECUTION_ID_,
          job.PROCESS_INSTANCE_ID_,
          job.PROC_DEF_ID_,
          job.RETRIES_,
          job.EXCEPTION_STACK_ID_,
          job.EXCEPTION_MSG_,
          job.DUEDATE_,
          job.REPEAT_,
          job.HANDLER_TYPE_,
          job.HANDLER_CFG_,
          job.TENANT_ID_
   from ACT_RU_JOB job
          INNER JOIN ACT_RU_EXECUTION execution on execution.ID_ = job.PROCESS_INSTANCE_ID_
   where execution.SUSPENSION_STATE_ = 2);

DELETE
FROM ACT_RU_JOB
WHERE PROCESS_INSTANCE_ID_ IN (SELECT ID_
                               FROM ACT_RU_EXECUTION execution
                               WHERE execution.PARENT_ID_ is null and execution.SUSPENSION_STATE_ = 2);


-- Moving timer jobs to ACT_RU_TIMER_JOB

INSERT INTO ACT_RU_TIMER_JOB (ID_, REV_, TYPE_, LOCK_EXP_TIME_, LOCK_OWNER_, EXCLUSIVE_, EXECUTION_ID_,
                              PROCESS_INSTANCE_ID_,
                              PROC_DEF_ID_, RETRIES_, EXCEPTION_STACK_ID_, EXCEPTION_MSG_, DUEDATE_, REPEAT_,
                              HANDLER_TYPE_, HANDLER_CFG_, TENANT_ID_)
  (SELECT ID_,
          REV_,
          TYPE_,
          LOCK_EXP_TIME_,
          LOCK_OWNER_,
          EXCLUSIVE_,
          EXECUTION_ID_,
          PROCESS_INSTANCE_ID_,
          PROC_DEF_ID_,
          RETRIES_,
          EXCEPTION_STACK_ID_,
          EXCEPTION_MSG_,
          DUEDATE_,
          REPEAT_,
          HANDLER_TYPE_,
          HANDLER_CFG_,
          TENANT_ID_
   from ACT_RU_JOB
   WHERE (HANDLER_TYPE_ = 'activate-processdefinition' or HANDLER_TYPE_ = 'suspend-processdefinition'
     or HANDLER_TYPE_ = 'timer-intermediate-transition' or HANDLER_TYPE_ = 'timer-start-event' or
          HANDLER_TYPE_ = 'timer-transition')
     and LOCK_EXP_TIME_ is null);

DELETE
FROM ACT_RU_JOB
WHERE (HANDLER_TYPE_ = 'activate-processdefinition'
  or HANDLER_TYPE_ = 'suspend-processdefinition'
  or HANDLER_TYPE_ = 'timer-intermediate-transition'
  or HANDLER_TYPE_ = 'timer-start-event'
  or HANDLER_TYPE_ = 'timer-transition')
  and LOCK_EXP_TIME_ is null;


alter table ACT_RU_EXECUTION
  add START_TIME_ TIMESTAMP(6);
alter table ACT_RU_EXECUTION
  add START_USER_ID_ NVARCHAR2(255);
alter table ACT_RU_TASK
  add CLAIM_TIME_ TIMESTAMP(6);

alter table ACT_RE_DEPLOYMENT
  add KEY_ NVARCHAR2(255);

-- Upgrade added in upgradestep.52001.to.52002.engine, which is not applied when already on beta2
update ACT_RU_EVENT_SUBSCR
set PROC_DEF_ID_ = CONFIGURATION_
where EVENT_TYPE_ = 'message'
  and PROC_INST_ID_ is null
  and EXECUTION_ID_ is null
  and PROC_DEF_ID_ is null;

-- Adding count columns for execution relationship count feature
alter table ACT_RU_EXECUTION
  add IS_COUNT_ENABLED_ NUMBER(1,0) CHECK (IS_COUNT_ENABLED_ IN (1,0));
alter table ACT_RU_EXECUTION
  add EVT_SUBSCR_COUNT_ INTEGER;
alter table ACT_RU_EXECUTION
  add TASK_COUNT_ INTEGER;
alter table ACT_RU_EXECUTION
  add JOB_COUNT_ INTEGER;
alter table ACT_RU_EXECUTION
  add TIMER_JOB_COUNT_ INTEGER;
alter table ACT_RU_EXECUTION
  add SUSP_JOB_COUNT_ INTEGER;
alter table ACT_RU_EXECUTION
  add DEADLETTER_JOB_COUNT_ INTEGER;
alter table ACT_RU_EXECUTION
  add VAR_COUNT_ INTEGER;
alter table ACT_RU_EXECUTION
  add ID_LINK_COUNT_ INTEGER;

update ACT_GE_PROPERTY
set VALUE_ = '6.0.0.2'
where NAME_ = 'schema.version';
