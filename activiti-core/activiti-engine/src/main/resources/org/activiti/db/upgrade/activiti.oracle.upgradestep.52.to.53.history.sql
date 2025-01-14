execute java org.activiti.engine.impl.db.upgrade.DbUpgradeStep52To53InsertPropertyHistoryLevel

-- removing not null constraint from ACT_HI_DETAIL.PROC_INST_ID_ and ACT_HI_DETAIL.EXECUTION_ID_

create table ACT_HI_DETAIL_TMP
(
  ID_           NVARCHAR2(64) not null,
  TYPE_         NVARCHAR2(255) not null,
  PROC_INST_ID_ NVARCHAR2(64),
  EXECUTION_ID_ NVARCHAR2(64),
  TASK_ID_      NVARCHAR2(64),
  ACT_INST_ID_  NVARCHAR2(64),
  NAME_         NVARCHAR2(255),
  VAR_TYPE_     NVARCHAR2(64),
  REV_          INTEGER,
  TIME_         TIMESTAMP(6) not null,
  BYTEARRAY_ID_ NVARCHAR2(64),
  DOUBLE_       NUMBER(*,10),
  LONG_         NUMBER(19,0),
  TEXT_         NVARCHAR2(255),
  TEXT2_        NVARCHAR2(255),
  primary key (ID_)
);

insert into ACT_HI_DETAIL_TMP (ID_, TYPE_, PROC_INST_ID_, EXECUTION_ID_, TASK_ID_, ACT_INST_ID_, NAME_, VAR_TYPE_, REV_,
                               TIME_, BYTEARRAY_ID_, DOUBLE_, LONG_, TEXT_, TEXT2_)
select ID_,
       TYPE_,
       PROC_INST_ID_,
       EXECUTION_ID_,
       TASK_ID_,
       ACT_INST_ID_,
       NAME_,
       VAR_TYPE_,
       REV_,
       TIME_,
       BYTEARRAY_ID_,
       DOUBLE_,
       LONG_,
       TEXT_,
       TEXT2_
from ACT_HI_DETAIL;

drop table ACT_HI_DETAIL;

create table ACT_HI_DETAIL
(
  ID_           NVARCHAR2(64) not null,
  TYPE_         NVARCHAR2(255) not null,
  PROC_INST_ID_ NVARCHAR2(64),
  EXECUTION_ID_ NVARCHAR2(64),
  TASK_ID_      NVARCHAR2(64),
  ACT_INST_ID_  NVARCHAR2(64),
  NAME_         NVARCHAR2(255),
  VAR_TYPE_     NVARCHAR2(64),
  REV_          INTEGER,
  TIME_         TIMESTAMP(6) not null,
  BYTEARRAY_ID_ NVARCHAR2(64),
  DOUBLE_       NUMBER(*,10),
  LONG_         NUMBER(19,0),
  TEXT_         NVARCHAR2(255),
  TEXT2_        NVARCHAR2(255),
  primary key (ID_)
);

create index ACT_IDX_HI_DETAIL_PROC_INST on ACT_HI_DETAIL (PROC_INST_ID_);
create index ACT_IDX_HI_DETAIL_ACT_INST on ACT_HI_DETAIL (ACT_INST_ID_);
create index ACT_IDX_HI_DETAIL_TIME on ACT_HI_DETAIL (TIME_);
create index ACT_IDX_HI_DETAIL_NAME on ACT_HI_DETAIL (NAME_);

insert into ACT_HI_DETAIL (ID_, TYPE_, PROC_INST_ID_, EXECUTION_ID_, TASK_ID_, ACT_INST_ID_, NAME_, VAR_TYPE_, REV_,
                           TIME_, BYTEARRAY_ID_, DOUBLE_, LONG_, TEXT_, TEXT2_)
select ID_,
       TYPE_,
       PROC_INST_ID_,
       EXECUTION_ID_,
       TASK_ID_,
       ACT_INST_ID_,
       NAME_,
       VAR_TYPE_,
       REV_,
       TIME_,
       BYTEARRAY_ID_,
       DOUBLE_,
       LONG_,
       TEXT_,
       TEXT2_
from ACT_HI_DETAIL_TMP;

drop table ACT_HI_DETAIL_TMP;

-- Add column PRIORITY_ to ACT_HI_TASKINST and set to default priority (ACT-484)
alter table ACT_HI_TASKINST
  add PRIORITY_ INTEGER;
update ACT_HI_TASKINST
set PRIORITY_ = 50;
