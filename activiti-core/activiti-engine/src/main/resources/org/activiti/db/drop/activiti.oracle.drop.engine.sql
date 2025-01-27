drop index ACT_IDX_BYTEAR_DEPL;
drop index ACT_IDX_EXE_PROCINST;
drop index ACT_IDX_EXE_PARENT;
drop index ACT_IDX_EXE_SUPER;
drop index ACT_IDX_TSKASS_TASK;
drop index ACT_IDX_TASK_EXEC;
drop index ACT_IDX_TASK_PROCINST;
drop index ACT_IDX_TASK_PROCDEF;
drop index ACT_IDX_VAR_EXE;
drop index ACT_IDX_VAR_PROCINST;
drop index ACT_IDX_VAR_BYTEARRAY;
drop index ACT_IDX_JOB_EXECUTION_ID;
drop index ACT_IDX_JOB_PROC_INST_ID;
drop index ACT_IDX_JOB_PROC_DEF_ID;
drop index ACT_IDX_JOB_EXCEPTION;
drop index ACT_IDX_TJOB_EXECUTION_ID;
drop index ACT_IDX_TJOB_PROC_INST_ID;
drop index ACT_IDX_TJOB_PROC_DEF_ID;
drop index ACT_IDX_TJOB_EXCEPTION;
drop index ACT_IDX_SJOB_EXECUTION_ID;
drop index ACT_IDX_SJOB_PROC_INST_ID;
drop index ACT_IDX_SJOB_PROC_DEF_ID;
drop index ACT_IDX_SJOB_EXCEPTION;
drop index ACT_IDX_DJOB_EXECUTION_ID;
drop index ACT_IDX_DJOB_PROC_INST_ID;
drop index ACT_IDX_DJOB_PROC_DEF_ID;
drop index ACT_IDX_DJOB_EXCEPTION;
drop index ACT_IDX_MODEL_SOURCE;
drop index ACT_IDX_MODEL_SOURCE_EXTRA;
drop index ACT_IDX_MODEL_DEPLOYMENT;
drop index ACT_IDX_PROCDEF_INFO_JSON;

drop index ACT_IDX_EXEC_BUSKEY;
drop index ACT_IDX_TASK_CREATE;
drop index ACT_IDX_IDENT_LNK_USER;
drop index ACT_IDX_IDENT_LNK_GROUP;
drop index ACT_IDX_VARIABLE_TASK_ID;

alter table ACT_GE_BYTEARRAY
drop
CONSTRAINT ACT_FK_BYTEARR_DEPL;

alter table ACT_RU_EXECUTION
drop
CONSTRAINT ACT_FK_EXE_PROCINST;

alter table ACT_RU_EXECUTION
drop
CONSTRAINT ACT_FK_EXE_PARENT;

alter table ACT_RU_EXECUTION
drop
CONSTRAINT ACT_FK_EXE_SUPER;

alter table ACT_RU_EXECUTION
drop
CONSTRAINT ACT_FK_EXE_PROCDEF;

alter table ACT_RU_IDENTITYLINK
drop
CONSTRAINT ACT_FK_TSKASS_TASK;

alter table ACT_RU_IDENTITYLINK
drop
CONSTRAINT ACT_FK_ATHRZ_PROCEDEF;

alter table ACT_RU_TASK
drop
CONSTRAINT ACT_FK_TASK_EXE;

alter table ACT_RU_TASK
drop
CONSTRAINT ACT_FK_TASK_PROCINST;

alter table ACT_RU_TASK
drop
CONSTRAINT ACT_FK_TASK_PROCDEF;

alter table ACT_RU_VARIABLE
drop
CONSTRAINT ACT_FK_VAR_EXE;

alter table ACT_RU_VARIABLE
drop
CONSTRAINT ACT_FK_VAR_PROCINST;

alter table ACT_RU_VARIABLE
drop
CONSTRAINT ACT_FK_VAR_BYTEARRAY;

alter table ACT_RU_JOB
drop
CONSTRAINT ACT_FK_JOB_EXECUTION;

alter table ACT_RU_JOB
drop
CONSTRAINT ACT_FK_JOB_PROCESS_INSTANCE;

alter table ACT_RU_JOB
drop
CONSTRAINT ACT_FK_JOB_PROC_DEF;

alter table ACT_RU_JOB
drop
CONSTRAINT ACT_FK_JOB_EXCEPTION;

alter table ACT_RU_TIMER_JOB
drop
CONSTRAINT ACT_FK_TJOB_EXECUTION;

alter table ACT_RU_TIMER_JOB
drop
CONSTRAINT ACT_FK_TJOB_PROCESS_INSTANCE;

alter table ACT_RU_TIMER_JOB
drop
CONSTRAINT ACT_FK_TJOB_PROC_DEF;

alter table ACT_RU_TIMER_JOB
drop
CONSTRAINT ACT_FK_TJOB_EXCEPTION;

alter table ACT_RU_SUSPENDED_JOB
drop
CONSTRAINT ACT_FK_SJOB_EXECUTION;

alter table ACT_RU_SUSPENDED_JOB
drop
CONSTRAINT ACT_FK_SJOB_PROCESS_INSTANCE;

alter table ACT_RU_SUSPENDED_JOB
drop
CONSTRAINT ACT_FK_SJOB_PROC_DEF;

alter table ACT_RU_SUSPENDED_JOB
drop
CONSTRAINT ACT_FK_SJOB_EXCEPTION;

alter table ACT_RU_DEADLETTER_JOB
drop
CONSTRAINT ACT_FK_DJOB_EXECUTION;

alter table ACT_RU_DEADLETTER_JOB
drop
CONSTRAINT ACT_FK_DJOB_PROCESS_INSTANCE;

alter table ACT_RU_DEADLETTER_JOB
drop
CONSTRAINT ACT_FK_DJOB_PROC_DEF;

alter table ACT_RU_DEADLETTER_JOB
drop
CONSTRAINT ACT_FK_DJOB_EXCEPTION;

alter table ACT_RU_EVENT_SUBSCR
drop
CONSTRAINT ACT_FK_EVENT_EXEC;

alter table ACT_RE_PROCDEF
drop
CONSTRAINT ACT_UNIQ_PROCDEF;

alter table ACT_RE_MODEL
drop
CONSTRAINT ACT_FK_MODEL_SOURCE;

alter table ACT_RE_MODEL
drop
CONSTRAINT ACT_FK_MODEL_SOURCE_EXTRA;

alter table ACT_RE_MODEL
drop
CONSTRAINT ACT_FK_MODEL_DEPLOYMENT;

alter table ACT_PROCDEF_INFO
drop
CONSTRAINT ACT_UNIQ_INFO_PROCDEF;

alter table ACT_PROCDEF_INFO
drop
CONSTRAINT ACT_FK_INFO_JSON_BA;

alter table ACT_PROCDEF_INFO
drop
CONSTRAINT ACT_FK_INFO_PROCDEF;

alter table ACT_RU_INTEGRATION
drop
CONSTRAINT ACT_FK_INT_EXECUTION;

alter table ACT_RU_INTEGRATION
drop
CONSTRAINT ACT_FK_INT_PROC_INST;

alter table ACT_RU_INTEGRATION
drop
CONSTRAINT ACT_FK_INT_PROC_DEF;

drop index ACT_IDX_EVENT_SUBSCR_CONFIG_;
drop index ACT_IDX_EVENT_SUBSCR;
drop index ACT_IDX_ATHRZ_PROCEDEF;
drop index ACT_IDX_PROCDEF_INFO_PROC;

drop table ACT_GE_PROPERTY;
drop table ACT_GE_BYTEARRAY;
drop table ACT_RE_DEPLOYMENT;
drop table ACT_RE_MODEL;
drop table ACT_RE_PROCDEF;
drop table ACT_RU_IDENTITYLINK;
drop table ACT_RU_VARIABLE;
drop table ACT_RU_TASK;
drop table ACT_RU_EXECUTION;
drop table ACT_RU_JOB;
drop table ACT_RU_TIMER_JOB;
drop table ACT_RU_SUSPENDED_JOB;
drop table ACT_RU_DEADLETTER_JOB;
drop table ACT_RU_EVENT_SUBSCR;

drop sequence act_evt_log_seq;
drop table ACT_EVT_LOG;
drop table ACT_PROCDEF_INFO;
drop table ACT_RU_INTEGRATION;
