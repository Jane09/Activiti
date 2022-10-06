create table ACT_HI_TASKINST
(
  ID_            NVARCHAR2(64) not null,
  PROC_DEF_ID_   NVARCHAR2(64),
  TASK_DEF_KEY_  NVARCHAR2(255),
  PROC_INST_ID_  NVARCHAR2(64),
  EXECUTION_ID_  NVARCHAR2(64),
  NAME_          NVARCHAR2(255),
  DESCRIPTION_   NVARCHAR2(255),
  ASSIGNEE_      NVARCHAR2(64),
  START_TIME_    TIMESTAMP(6) not null,
  END_TIME_      TIMESTAMP(6),
  DURATION_      NUMBER(19,0),
  DELETE_REASON_ NVARCHAR2(255),
  primary key (ID_)
);

alter table ACT_HI_DETAIL
  add TASK_ID_ NVARCHAR2(64);
