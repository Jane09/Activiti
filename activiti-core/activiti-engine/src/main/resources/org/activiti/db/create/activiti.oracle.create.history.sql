create table ACT_HI_PROCINST
(
  ID_                        NVARCHAR2(64) not null,
  PROC_INST_ID_              NVARCHAR2(64) not null,
  BUSINESS_KEY_              NVARCHAR2(255),
  PROC_DEF_ID_               NVARCHAR2(64) not null,
  START_TIME_                TIMESTAMP(6) not null,
  END_TIME_                  TIMESTAMP(6),
  DURATION_                  NUMBER(19,0),
  START_USER_ID_             NVARCHAR2(255),
  START_ACT_ID_              NVARCHAR2(255),
  END_ACT_ID_                NVARCHAR2(255),
  SUPER_PROCESS_INSTANCE_ID_ NVARCHAR2(64),
  DELETE_REASON_             NVARCHAR2(2000),
  TENANT_ID_                 NVARCHAR2(255) default '',
  NAME_                      NVARCHAR2(255),
  primary key (ID_),
  unique (PROC_INST_ID_)
);

create table ACT_HI_ACTINST
(
  ID_                NVARCHAR2(64) not null,
  PROC_DEF_ID_       NVARCHAR2(64) not null,
  PROC_INST_ID_      NVARCHAR2(64) not null,
  EXECUTION_ID_      NVARCHAR2(64) not null,
  ACT_ID_            NVARCHAR2(255) not null,
  TASK_ID_           NVARCHAR2(64),
  CALL_PROC_INST_ID_ NVARCHAR2(64),
  ACT_NAME_          NVARCHAR2(255),
  ACT_TYPE_          NVARCHAR2(255) not null,
  ASSIGNEE_          NVARCHAR2(255),
  START_TIME_        TIMESTAMP(6) not null,
  END_TIME_          TIMESTAMP(6),
  DURATION_          NUMBER(19,0),
  DELETE_REASON_     NVARCHAR2(2000),
  TENANT_ID_         NVARCHAR2(255) default '',
  primary key (ID_)
);

create table ACT_HI_TASKINST
(
  ID_             NVARCHAR2(64) not null,
  PROC_DEF_ID_    NVARCHAR2(64),
  TASK_DEF_KEY_   NVARCHAR2(255),
  PROC_INST_ID_   NVARCHAR2(64),
  EXECUTION_ID_   NVARCHAR2(64),
  PARENT_TASK_ID_ NVARCHAR2(64),
  NAME_           NVARCHAR2(255),
  DESCRIPTION_    NVARCHAR2(2000),
  OWNER_          NVARCHAR2(255),
  ASSIGNEE_       NVARCHAR2(255),
  START_TIME_     TIMESTAMP(6) not null,
  CLAIM_TIME_     TIMESTAMP(6),
  END_TIME_       TIMESTAMP(6),
  DURATION_       NUMBER(19,0),
  DELETE_REASON_  NVARCHAR2(2000),
  PRIORITY_       INTEGER,
  DUE_DATE_       TIMESTAMP(6),
  FORM_KEY_       NVARCHAR2(255),
  CATEGORY_       NVARCHAR2(255),
  TENANT_ID_      NVARCHAR2(255) default '',
  primary key (ID_)
);

create table ACT_HI_VARINST
(
  ID_                NVARCHAR2(64) not null,
  PROC_INST_ID_      NVARCHAR2(64),
  EXECUTION_ID_      NVARCHAR2(64),
  TASK_ID_           NVARCHAR2(64),
  NAME_              NVARCHAR2(255) not null,
  VAR_TYPE_          NVARCHAR2(100),
  REV_               INTEGER,
  BYTEARRAY_ID_      NVARCHAR2(64),
  DOUBLE_            NUMBER(*,10),
  LONG_              NUMBER(19,0),
  TEXT_              NVARCHAR2(2000),
  TEXT2_             NVARCHAR2(2000),
  CREATE_TIME_       TIMESTAMP(6),
  LAST_UPDATED_TIME_ TIMESTAMP(6),
  primary key (ID_)
);

create table ACT_HI_DETAIL
(
  ID_           NVARCHAR2(64) not null,
  TYPE_         NVARCHAR2(255) not null,
  PROC_INST_ID_ NVARCHAR2(64),
  EXECUTION_ID_ NVARCHAR2(64),
  TASK_ID_      NVARCHAR2(64),
  ACT_INST_ID_  NVARCHAR2(64),
  NAME_         NVARCHAR2(255) not null,
  VAR_TYPE_     NVARCHAR2(64),
  REV_          INTEGER,
  TIME_         TIMESTAMP(6) not null,
  BYTEARRAY_ID_ NVARCHAR2(64),
  DOUBLE_       NUMBER(*,10),
  LONG_         NUMBER(19,0),
  TEXT_         NVARCHAR2(2000),
  TEXT2_        NVARCHAR2(2000),
  primary key (ID_)
);

create table ACT_HI_COMMENT
(
  ID_           NVARCHAR2(64) not null,
  TYPE_         NVARCHAR2(255),
  TIME_         TIMESTAMP(6) not null,
  USER_ID_      NVARCHAR2(255),
  TASK_ID_      NVARCHAR2(64),
  PROC_INST_ID_ NVARCHAR2(64),
  ACTION_       NVARCHAR2(255),
  MESSAGE_      NVARCHAR2(2000),
  FULL_MSG_     BLOB,
  primary key (ID_)
);

create table ACT_HI_ATTACHMENT
(
  ID_           NVARCHAR2(64) not null,
  REV_          INTEGER,
  USER_ID_      NVARCHAR2(255),
  NAME_         NVARCHAR2(255),
  DESCRIPTION_  NVARCHAR2(2000),
  TYPE_         NVARCHAR2(255),
  TASK_ID_      NVARCHAR2(64),
  PROC_INST_ID_ NVARCHAR2(64),
  URL_          NVARCHAR2(2000),
  CONTENT_ID_   NVARCHAR2(64),
  TIME_         TIMESTAMP(6),
  primary key (ID_)
);

create table ACT_HI_IDENTITYLINK
(
  ID_           NVARCHAR2(64),
  GROUP_ID_     NVARCHAR2(255),
  TYPE_         NVARCHAR2(255),
  USER_ID_      NVARCHAR2(255),
  TASK_ID_      NVARCHAR2(64),
  PROC_INST_ID_ NVARCHAR2(64),
  primary key (ID_)
);

create index ACT_IDX_HI_PRO_INST_END on ACT_HI_PROCINST (END_TIME_);
create index ACT_IDX_HI_PRO_I_BUSKEY on ACT_HI_PROCINST (BUSINESS_KEY_);
create index ACT_IDX_HI_ACT_INST_START on ACT_HI_ACTINST (START_TIME_);
create index ACT_IDX_HI_ACT_INST_END on ACT_HI_ACTINST (END_TIME_);
create index ACT_IDX_HI_DETAIL_PROC_INST on ACT_HI_DETAIL (PROC_INST_ID_);
create index ACT_IDX_HI_DETAIL_ACT_INST on ACT_HI_DETAIL (ACT_INST_ID_);
create index ACT_IDX_HI_DETAIL_TIME on ACT_HI_DETAIL (TIME_);
create index ACT_IDX_HI_DETAIL_NAME on ACT_HI_DETAIL (NAME_);
create index ACT_IDX_HI_DETAIL_TASK_ID on ACT_HI_DETAIL (TASK_ID_);
create index ACT_IDX_HI_PROCVAR_PROC_INST on ACT_HI_VARINST (PROC_INST_ID_);
create index ACT_IDX_HI_PROCVAR_NAME_TYPE on ACT_HI_VARINST (NAME_, VAR_TYPE_);
create index ACT_IDX_HI_PROCVAR_TASK_ID on ACT_HI_VARINST (TASK_ID_);
create index ACT_IDX_HI_IDENT_LNK_USER on ACT_HI_IDENTITYLINK (USER_ID_);
create index ACT_IDX_HI_IDENT_LNK_TASK on ACT_HI_IDENTITYLINK (TASK_ID_);
create index ACT_IDX_HI_IDENT_LNK_PROCINST on ACT_HI_IDENTITYLINK (PROC_INST_ID_);

create index ACT_IDX_HI_ACT_INST_PROCINST on ACT_HI_ACTINST (PROC_INST_ID_, ACT_ID_);
create index ACT_IDX_HI_ACT_INST_EXEC on ACT_HI_ACTINST (EXECUTION_ID_, ACT_ID_);
create index ACT_IDX_HI_TASK_INST_PROCINST on ACT_HI_TASKINST (PROC_INST_ID_);
