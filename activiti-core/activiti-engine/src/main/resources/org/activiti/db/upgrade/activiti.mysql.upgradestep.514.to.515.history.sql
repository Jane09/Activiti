alter table ACT_HI_TASKINST
  add CATEGORY_ varchar(255);

alter table ACT_HI_PROCINST drop index ACT_UNIQ_HI_BUS_KEY;

#
# ACT-1867: MySQL DATETIME and TIMESTAMP precision
# The way this is done, is by creating a new column, pumping over all data
# and then removing the old column.
#

# START_TIME_ in ACT_HI_PROCINST

ALTER TABLE ACT_HI_PROCINST
  ADD START_TIME_TEMP_ datetime(3) not null;
UPDATE ACT_HI_PROCINST
SET START_TIME_TEMP_ = START_TIME_;
ALTER TABLE ACT_HI_PROCINST DROP COLUMN START_TIME_;
ALTER TABLE ACT_HI_PROCINST CHANGE START_TIME_TEMP_ START_TIME_ datetime(3) not null;


#
END_TIME_ in ACT_HI_PROCINST

ALTER TABLE ACT_HI_PROCINST
  ADD END_TIME_TEMP_ datetime(3);
UPDATE ACT_HI_PROCINST
SET END_TIME_TEMP_ = END_TIME_;
ALTER TABLE ACT_HI_PROCINST DROP COLUMN END_TIME_;
ALTER TABLE ACT_HI_PROCINST CHANGE END_TIME_TEMP_ END_TIME_ datetime(3);


#
START_TIME_ in ACT_HI_ACTINST

ALTER TABLE ACT_HI_ACTINST
  ADD START_TIME_TEMP_ datetime(3) not null;
UPDATE ACT_HI_ACTINST
SET START_TIME_TEMP_ = START_TIME_;
ALTER TABLE ACT_HI_ACTINST DROP COLUMN START_TIME_;
ALTER TABLE ACT_HI_ACTINST CHANGE START_TIME_TEMP_ START_TIME_ datetime(3) not null;


#
END_TIME_ in ACT_HI_ACTINST

ALTER TABLE ACT_HI_ACTINST
  ADD END_TIME_TEMP_ datetime(3);
UPDATE ACT_HI_ACTINST
SET END_TIME_TEMP_ = END_TIME_;
ALTER TABLE ACT_HI_ACTINST DROP COLUMN END_TIME_;
ALTER TABLE ACT_HI_ACTINST CHANGE END_TIME_TEMP_ END_TIME_ datetime(3);


#
START_TIME_ in ACT_HI_TASKINST

ALTER TABLE ACT_HI_TASKINST
  ADD START_TIME_TEMP_ datetime(3) not null;
UPDATE ACT_HI_TASKINST
SET START_TIME_TEMP_ = START_TIME_;
ALTER TABLE ACT_HI_TASKINST DROP COLUMN START_TIME_;
ALTER TABLE ACT_HI_TASKINST CHANGE START_TIME_TEMP_ START_TIME_ datetime(3) not null;

#
CLAIM_TIME_ in ACT_HI_TASKINST

ALTER TABLE ACT_HI_TASKINST
  ADD CLAIM_TIME_TEMP_ datetime(3);
UPDATE ACT_HI_TASKINST
SET CLAIM_TIME_TEMP_ = CLAIM_TIME_;
ALTER TABLE ACT_HI_TASKINST DROP COLUMN CLAIM_TIME_;
ALTER TABLE ACT_HI_TASKINST CHANGE CLAIM_TIME_TEMP_ CLAIM_TIME_ datetime(3);

#
END_TIME_ in ACT_HI_TASKINST

ALTER TABLE ACT_HI_TASKINST
  ADD END_TIME_TEMP_ datetime(3);
UPDATE ACT_HI_TASKINST
SET END_TIME_TEMP_ = END_TIME_;
ALTER TABLE ACT_HI_TASKINST DROP COLUMN END_TIME_;
ALTER TABLE ACT_HI_TASKINST CHANGE END_TIME_TEMP_ END_TIME_ datetime(3);

#
DUE_DATE_ in ACT_HI_TASKINST

ALTER TABLE ACT_HI_TASKINST
  ADD DUE_DATE_TEMP_ datetime(3);
UPDATE ACT_HI_TASKINST
SET DUE_DATE_TEMP_ = DUE_DATE_;
ALTER TABLE ACT_HI_TASKINST DROP COLUMN DUE_DATE_;
ALTER TABLE ACT_HI_TASKINST CHANGE DUE_DATE_TEMP_ DUE_DATE_ datetime(3);


#
TIME_ in ACT_HI_DETAIL

ALTER TABLE ACT_HI_DETAIL
  ADD TIME_TEMP_ datetime(3) not null;
UPDATE ACT_HI_DETAIL
SET TIME_TEMP_ = TIME_;
ALTER TABLE ACT_HI_DETAIL DROP COLUMN TIME_;
ALTER TABLE ACT_HI_DETAIL CHANGE TIME_TEMP_ TIME_ datetime(3) not null;

#
TIME_ in ACT_HI_COMMENT

ALTER TABLE ACT_HI_COMMENT
  ADD TIME_TEMP_ datetime(3) not null;
UPDATE ACT_HI_COMMENT
SET TIME_TEMP_ = TIME_;
ALTER TABLE ACT_HI_COMMENT DROP COLUMN TIME_;
ALTER TABLE ACT_HI_COMMENT CHANGE TIME_TEMP_ TIME_ datetime(3) not null;


alter table ACT_HI_VARINST
  add CREATE_TIME_ datetime(3);

alter table ACT_HI_VARINST
  add LAST_UPDATED_TIME_ datetime(3);

alter table ACT_HI_PROCINST
  add TENANT_ID_ varchar(255) default '';

alter table ACT_HI_ACTINST
  add TENANT_ID_ varchar(255) default '';

alter table ACT_HI_TASKINST
  add TENANT_ID_ varchar(255) default '';

alter table ACT_HI_ACTINST
  MODIFY ASSIGNEE_ varchar (255);
