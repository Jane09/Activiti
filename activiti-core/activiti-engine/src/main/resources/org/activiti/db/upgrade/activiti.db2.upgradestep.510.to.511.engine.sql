alter table ACT_RE_DEPLOYMENT
  add CATEGORY_ varchar(255);

alter table ACT_RE_PROCDEF
  add DESCRIPTION_ varchar(4000);

alter table ACT_RU_TASK
  add SUSPENSION_STATE_ integer;

Call Sysproc.admin_cmd ('REORG TABLE ACT_RU_TASK');

update ACT_RU_TASK
set SUSPENSION_STATE_ = 1;

alter table ACT_RU_EXECUTION
  add constraint ACT_FK_EXE_PROCDEF
    foreign key (PROC_DEF_ID_)
      references ACT_RE_PROCDEF (ID_);

create table ACT_RE_MODEL
(
  ID_                           varchar(64) not null,
  REV_                          integer,
  NAME_                         varchar(255),
  KEY_                          varchar(255),
  CATEGORY_                     varchar(255),
  CREATE_TIME_                  timestamp,
  LAST_UPDATE_TIME_             timestamp,
  VERSION_                      integer,
  META_INFO_                    varchar(4000),
  DEPLOYMENT_ID_                varchar(64),
  EDITOR_SOURCE_VALUE_ID_       varchar(64),
  EDITOR_SOURCE_EXTRA_VALUE_ID_ varchar(64),
  primary key (ID_)
);

alter table ACT_RE_MODEL
  add constraint ACT_FK_MODEL_SOURCE
    foreign key (EDITOR_SOURCE_VALUE_ID_)
      references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RE_MODEL
  add constraint ACT_FK_MODEL_SOURCE_EXTRA
    foreign key (EDITOR_SOURCE_EXTRA_VALUE_ID_)
      references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RE_MODEL
  add constraint ACT_FK_MODEL_DEPLOYMENT
    foreign key (DEPLOYMENT_ID_)
      references ACT_RE_DEPLOYMENT (ID_);

delete
from ACT_GE_PROPERTY
where NAME_ = 'historyLevel';

alter table ACT_RU_JOB
  add PROC_DEF_ID_ varchar(64);

update ACT_GE_PROPERTY
set VALUE_ = '5.11'
where NAME_ = 'schema.version';
