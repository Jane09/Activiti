alter table ACT_HI_DETAIL
  add DUE_DATE_ TIMESTAMP(6);

alter table ACT_HI_TASKINST
  add DUE_DATE_ TIMESTAMP(6);

create table ACT_HI_COMMENT
(
  ID_           NVARCHAR2(64) not null,
  TIME_         TIMESTAMP(6) not null,
  USER_ID_      NVARCHAR2(255),
  TASK_ID_      NVARCHAR2(64),
  PROC_INST_ID_ NVARCHAR2(64),
  MESSAGE_      NVARCHAR2(255),
  primary key (ID_)
);

create table ACT_HI_ATTACHMENT
(
  ID_           NVARCHAR2(64) not null,
  REV_          integer,
  NAME_         NVARCHAR2(255),
  DESCRIPTION_  NVARCHAR2(255),
  TYPE_         NVARCHAR2(255),
  TASK_ID_      NVARCHAR2(64),
  PROC_INST_ID_ NVARCHAR2(64),
  URL_          NVARCHAR2(255),
  CONTENT_ID_   NVARCHAR2(64),
  primary key (ID_)
);
