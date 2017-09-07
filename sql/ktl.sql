-- Repository creation/upgrade DDL:
--
-- Nothing was created nor modified in the target repository database.
-- Hit the OK button to execute the generated SQL or Close to reject the changes.
-- Please note that it is possible to change/edit the generated SQL before execution.
--
CREATE TABLE R_DATABASE_TYPE
(
  ID_DATABASE_TYPE BIGSERIAL
, CODE VARCHAR(255)
, DESCRIPTION VARCHAR(255)
)
;

create unique index idx_databas_type_code on r_database_type using btree ( code );

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('INGRES','Ingres');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('EXASOL4','Exasol 4');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('HYPERSONIC','Hypersonic');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('AS/400','AS/400');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('ORACLERDB','Oracle RDB');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('INTERBASE','Borland Interbase');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('INFOBRIGHT','Infobright');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('DBASE','dBase III, IV or 5');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('KINGBASEES','KingbaseES');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('ORACLE9i-','ORACLE9i-');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('ORACLE9i+','ORACLE9i+');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('EXTENDB','ExtenDB');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('MSACCESS','MS Access');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('SYBASE','Sybase');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('SAPR3','SAP ERP System');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('SQLBASE','Gupta SQL Base');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('DERBY','Apache Derby');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('INFORMIX','Informix');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('LucidDB','LucidDB');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('VERTICA','Vertica');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('INFINIDB','Calpont InfiniDB');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('TERADATA','Teradata');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('UNIVERSE','UniVerse database');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('MONETDB','MonetDB');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('MYSQL','MySQL');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('MSSQLNATIVE','MS SQL Server (Native)');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('H2','H2');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('CACHE','Intersystems Cache');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('MSSQL','MS SQL Server');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('GREENPLUM','Greenplum');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('SAPDB','MaxDB (SAP DB)');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('GENERIC','Generic database');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('VECTORWISE','Ingres VectorWise');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('DB2','IBM DB2');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('SQLITE','SQLite');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('NEOVIEW','Neoview');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('REMEDY-AR-SYSTEM','Remedy Action Request System');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('MONDRIAN','Native Mondrian');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('SYBASEIQ','SybaseIQ');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('ELASTICSEARCH','elasticsearch');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('ELASTICSEARCH5','ELASTICSEARCH5');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('REDSHIFT','Redshift');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('NETEZZA','Netezza');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('VERTICA5','Vertica 5+');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('FIREBIRD','Firebird SQL');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('POSTGRESQL','PostgreSQL');

INSERT INTO R_DATABASE_TYPE(CODE, DESCRIPTION) VALUES ('HBASE','HBase');

CREATE TABLE R_DATABASE_CONTYPE
(
  ID_DATABASE_CONTYPE BIGSERIAL
, CODE VARCHAR(255)
, DESCRIPTION VARCHAR(255)
)
;

create unique index idx_databas_contype_code on R_DATABASE_CONTYPE using btree ( code );

INSERT INTO R_DATABASE_CONTYPE(ID_DATABASE_CONTYPE, CODE, DESCRIPTION) VALUES (1,E'Native',E'Native (JDBC)');

INSERT INTO R_DATABASE_CONTYPE(ID_DATABASE_CONTYPE, CODE, DESCRIPTION) VALUES (2,E'ODBC',E'ODBC');

INSERT INTO R_DATABASE_CONTYPE(ID_DATABASE_CONTYPE, CODE, DESCRIPTION) VALUES (3,E'OCI',E'OCI');

INSERT INTO R_DATABASE_CONTYPE(ID_DATABASE_CONTYPE, CODE, DESCRIPTION) VALUES (4,E'Plugin',E'Plugin specific access method');

INSERT INTO R_DATABASE_CONTYPE(ID_DATABASE_CONTYPE, CODE, DESCRIPTION) VALUES (5,E'JNDI',E'JNDI');

INSERT INTO R_DATABASE_CONTYPE(ID_DATABASE_CONTYPE, CODE, DESCRIPTION) VALUES (6,E',',E'Custom');

CREATE TABLE R_DATABASE
(
  ID_DATABASE BIGSERIAL
, "NAME" VARCHAR(255)
, ID_DATABASE_TYPE INTEGER
, ID_DATABASE_CONTYPE INTEGER
, HOST_NAME VARCHAR(255)
, DATABASE_NAME VARCHAR(2000000)
, PORT INTEGER
, USERNAME VARCHAR(255)
, "PASSWORD" VARCHAR(255)
, SERVERNAME VARCHAR(255)
, DATA_TBS VARCHAR(255)
, INDEX_TBS VARCHAR(255)
, SHIM_ID VARCHAR(255)
)
;

CREATE TABLE R_DIRECTORY(
	ID_DIRECTORY BIGSERIAL,
	ID_DIRECTORY_PARENT INTEGER,
	DIRECTORY_NAME VARCHAR(255),
	SWHETHER BOOLEAN,
	SORT INTEGER,
	TYPE INTEGER,
	CREATE_DATE TIMESTAMP,
	UPDATE_DATE TIMESTAMP
)
;

CREATE UNIQUE INDEX IDX_RDIR ON R_DIRECTORY(ID_DIRECTORY_PARENT, DIRECTORY_NAME,TYPE);

CREATE TABLE R_TRANSFORMATION
(
    ID_TRANSFORMATION BIGSERIAL
  , ID_DIRECTORY INTEGER
  , "NAME" VARCHAR(255)
  , TRANS_STATUS INTEGER
  , START_COUNT BIGINT
  , CREATED_USER VARCHAR(255)
  , CREATED_DATE TIMESTAMP
  , MODIFIED_USER VARCHAR(255)
  , MODIFIED_DATE TIMESTAMP
  , disbale boolean
)
;

CREATE INDEX "r_transformation_INDEX_DATE" ON "public"."r_transformation" USING btree (modified_date);
CREATE INDEX "r_transformation_INDEX_ID" ON "public"."r_transformation" USING btree (id_transformation);
CREATE INDEX "r_transformation_INDEX_NAME" ON "public"."r_transformation" USING btree (modified_user);

CREATE TABLE R_STEP
(
    ID_STEP BIGSERIAL
  , ID_TRANSFORMATION INTEGER
  , "NAME" VARCHAR(255)
  , DESCRIPTION VARCHAR(2000000)
  , ID_STEP_TYPE INTEGER
  , DISTRIBUTE BOOLEAN
  , COPIES SMALLINT
  , GUI_LOCATION_X INTEGER
  , GUI_LOCATION_Y INTEGER
  , GUI_DRAW BOOLEAN
  , COPIES_STRING VARCHAR(255)
)
;

CREATE TABLE R_JOB (
	ID_JOB BIGSERIAL,
	ID_DIRECTORY INTEGER,
	"NAME" VARCHAR (255),
	JOB_STATUS INTEGER,
	START_COUNT BIGINT,
	TRANS_COUNT BIGINT,
	CREATED_USER VARCHAR (255),
	CREATED_DATE TIMESTAMP,
	MODIFIED_USER VARCHAR (255),
	MODIFIED_DATE TIMESTAMP,
	IP_ADDRESS VARCHAR (40),
	PORT INTEGER,
	LOG_PATH VARCHAR (500),
	SYNC_ID INTEGER,
	NODE VARCHAR (100) COLLATE "default",
	LOG_EDIT_PATH VARCHAR (500) COLLATE "default",
	LOG_LEVEL VARCHAR (10) COLLATE "default",
	MEM INTEGER,
	SYNC_MODE VARCHAR (10) COLLATE "default",
	IMMEDIATE BOOLEAN,
	REPEAT BOOLEAN,
	OCCUPY BOOLEAN,
	CYCLE VARCHAR (500) COLLATE "default",
	SLEEP_TIME INTEGER,
	SCHEDULE_TYPE varchar(10),
	INTERVAL_SECOND INTEGER,
	INTERVAL_MINUTE INTEGER,
	"hour" INTEGER,
	"minute" INTEGER,
	DAY_OF_WEEK INTEGER,
	DAY_OF_MONTH INTEGER
);

CREATE UNIQUE INDEX "R_JOB_PK_ID" ON "public"."r_job" USING btree (id_job);

CREATE TABLE R_JOBENTRY
(
    ID_JOBENTRY BIGSERIAL
  , ID_JOB INTEGER
  , ID_JOBENTRY_TYPE INTEGER
  , TRANS_ID INTEGER
  , "NAME" VARCHAR(255)
  , DESCRIPTION VARCHAR(2000000)
)
;

CREATE TABLE "public"."r_job_log" (
  "id" BIGSERIAL,
	"job_metric_id" BIGINT,
  "job_id" BIGINT,
  "job_name" varchar(255) COLLATE "default",
	"trans_metric_id" BIGINT,
  "trans_id" BIGINT,
  "trans_name" varchar(255) COLLATE "default",
  "step_name" varchar(255) COLLATE "default",
  "status_code" varchar(50) COLLATE "default",
	"status_name" varchar(50) COLLATE "default",
  "log" varchar(20000) COLLATE "default",
  "current_date" timestamp(6),
  "type" varchar(10) COLLATE "default"
)
WITH (OIDS=FALSE);
CREATE INDEX "idx_job_log_jmd" ON "public"."r_job_log" USING btree (job_metric_id);
CREATE INDEX "idx_job_log_tmd" ON "public"."r_job_log" USING btree (trans_metric_id);
CREATE INDEX "idx_job_log_status" ON "public"."r_job_log" USING btree (status_code);
CREATE INDEX "idx_job_log_type" ON "public"."r_job_log" USING btree ("type");
CREATE INDEX "idx_job_log_date" ON "public"."r_job_log" USING btree ("current_date");

CREATE TABLE "public"."r_job_metric_relation" (
  "job_id" BIGINT,
	"job_metric_id" BIGINT
)
WITH (OIDS=FALSE);

CREATE TABLE "public"."r_job_metric_history" (
	"id" BIGSERIAL,
  "job_id" BIGINT,
	"job_name" VARCHAR(100),
	"start_count" BIGINT,
  "directory_name" varchar(100),
  "status_code" varchar(10),
  "status_name" varchar(50),
	"trans_count" INTEGER,
  "ip_address" VARCHAR(100),
	"port" INTEGER,
	"node" VARCHAR(100),
	"log_file" VARCHAR(100),
	"log_edit_path" VARCHAR(100),
	"log_level" VARCHAR(30),
	"sync_mode" VARCHAR(30),
	"cycle" VARCHAR(100),
	"lines_read" BIGINT,
	"lines_written" BIGINT,
	"lines_input" BIGINT,
	"lines_output" BIGINT,
	"lines_updated" BIGINT,
	"lines_rejected" BIGINT,
	"speed" BIGINT,
	"error" BIGINT,
	"start_date" timestamp,
	"end_date" timestamp,
	"current_date" timestamp,
  "create_date" timestamp,
  "user_name" varchar(200)
)
WITH (OIDS=FALSE);

CREATE INDEX "idx_jm_jobid" ON "public"."r_job_metric_history" USING btree (job_id);
CREATE INDEX "idx_jm_status" ON "public"."r_job_metric_history" USING btree (status_code);
CREATE INDEX "idx_jm_time" ON "public"."r_job_metric_history" USING btree ("current_date");

CREATE TABLE "public"."r_trans_metric" (
  "id" BIGSERIAL,
	"job_metric_id" BIGINT,
  "job_id" BIGINT,
	"job_name" VARCHAR(100),
	"trans_id" BIGINT,
	"trans_name" VARCHAR(100),
  "directory_name" varchar(100),
	"start_count" BIGINT,
  "status_code" varchar(10),
  "status_name" varchar(50),
	"step_count" INTEGER,
	"lines_read" BIGINT,
	"lines_written" BIGINT,
	"lines_input" BIGINT,
	"lines_output" BIGINT,
	"lines_updated" BIGINT,
	"lines_rejected" BIGINT,
	"speed" BIGINT,
	"error" BIGINT,
	"start_date" timestamp,
	"end_date" timestamp,
	"current_date" timestamp,
  "create_date" timestamp
)
WITH (OIDS=FALSE);

CREATE INDEX "idx_tm_jmd" ON "public"."r_trans_metric" USING btree (job_metric_id);
CREATE INDEX "idx_tm_transid" ON "public"."r_trans_metric" USING btree (trans_id);
CREATE INDEX "idx_tm_status" ON "public"."r_trans_metric" USING btree (status_code);
CREATE INDEX "idx_tm_time" ON "public"."r_trans_metric" USING btree ("current_date");

CREATE TABLE "public"."r_step_metric" (
  "id" BIGSERIAL,
	"trans_metric_id" BIGINT,
	"trans_id" BIGINT,
	"trans_name" VARCHAR(100),
	"step_id" BIGINT,
	"step_name" VARCHAR(100),
  "copy" INTEGER,
  "status_code" varchar(10),
  "status_name" varchar(50),
	"lines_read" BIGINT,
	"lines_written" BIGINT,
	"lines_input" BIGINT,
	"lines_output" BIGINT,
	"lines_updated" BIGINT,
	"lines_rejected" BIGINT,
	"speed" VARCHAR(30),
	"error" VARCHAR(30),
	"priority" VARCHAR(30),
	"seconds" VARCHAR(30),
  "create_date" timestamp
)
WITH (OIDS=FALSE);

CREATE INDEX "idx_rsm_tmd" ON "public"."r_step_metric" USING btree (trans_metric_id);
CREATE INDEX "idx_rsm_transid" ON "public"."r_step_metric" USING btree (trans_id);

CREATE TABLE "public"."r_dictionary" (
  "id" int4,
  "key" varchar(50) COLLATE "default",
  "value" varchar(100) COLLATE "default",
  "area" varchar(50) COLLATE "default" NOT NULL,
  "type" varchar(50) COLLATE "default" NOT NULL,
  "update_date" timestamp(6)
)
WITH (OIDS=FALSE)
;
CREATE INDEX IDX_TYPE ON r_dictionary( type );
CREATE INDEX IDX_AREA ON r_dictionary( area );
CREATE INDEX IDX_KEY ON r_dictionary( key );

CREATE TABLE "public"."r_param" (
  "job_id" bigint,
  "trans_id" bigint,
  "param" varchar(10000) COLLATE "default"
)
WITH (OIDS=FALSE)
;
CREATE INDEX IDX_PARAM_JI ON r_param( job_id );

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
CREATE TABLE "public"."u_user" (
  "id" BIGSERIAL,
  "user_name" varchar(50) COLLATE "default" NOT NULL,
  "password" varchar(200) COLLATE "default" NOT NULL,
  "salt" varchar(200) COLLATE "default",
  "islogin" bool DEFAULT true NOT NULL,
  "islock" bool DEFAULT false NOT NULL,
  "real_name" varchar(100) COLLATE "default",
  "phone_number" varchar(20) COLLATE "default",
  "email" varchar(50) COLLATE "default",
  "position" varchar(50) COLLATE "default",
  "create_time" timestamp(6),
  "update_time" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Primary Key structure for table u_user
-- ----------------------------
ALTER TABLE "public"."u_user" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table u_user
-- ----------------------------
create unique index idx_user_name on u_user using btree (user_name);

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO "public"."u_user" ( user_name,password,salt,islogin,islock,real_name,phone_number,email,position,create_time,update_time  )
VALUES ('admin', '811f1e87328676849d4f1ad981c8bbf9', '58defed4bfc32199a7b2bdc9ac0fb7c9', 't', 'f', '超级管理员', '123455678','', '超级管理员',now(),now());

-- ----------------------------
-- Table structure for u_user_session
-- ----------------------------
CREATE TABLE "public"."u_user_session" (
  "session_id" varchar(200),
  "user_id" BIGINT,
  "login_count" INTEGER,
  "create_time" timestamp(6)
)
WITH (OIDS=FALSE);

CREATE INDEX "idx_uus_sd" ON "public"."u_user_session" USING btree (session_id);


-- ----------------------------
-- Table structure for u_role
-- ----------------------------
CREATE TABLE "public"."u_role" (
  "id" BIGSERIAL,
  "name" varchar(50) COLLATE "default" NOT NULL,
  "disable" bool DEFAULT true NOT NULL,
  "description" varchar(200) COLLATE "default",
  "create_time" timestamp(6),
  "update_time" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Primary Key structure for table u_role
-- ----------------------------
ALTER TABLE "public"."u_role" ADD PRIMARY KEY ("id");

create unique index idx_role_name on u_role using btree (NAME);

-- ----------------------------
-- Records of u_role
-- ----------------------------
INSERT INTO "public"."u_role"( name,disable,description,create_time,update_time ) VALUES ('admin', 't', '统计', now(), now() );
INSERT INTO "public"."u_role"( name,disable,description,create_time,update_time ) VALUES ('guest', 't', '统计', now(), now() );

-- ----------------------------
-- Table structure for u_user_role
-- ----------------------------
CREATE TABLE "public"."u_user_role" (
  "user_id" int4 NOT NULL,
  "role_id" int4 NOT NULL
)
WITH (OIDS=FALSE)
;


-- ----------------------------
-- Indexes structure for table u_user_role
-- ----------------------------
CREATE INDEX "index_user_role_id" ON "public"."u_user_role" USING btree (user_id);

create unique index idx_user_role_id on u_user_role using btree (user_id, role_id);

-- ----------------------------
-- Records of u_user_role
-- ----------------------------
INSERT INTO "public"."u_user_role" VALUES ('1', '1');


-- ----------------------------
-- Table structure for u_menu
-- ----------------------------
CREATE TABLE "public"."u_menu" (
  "id" BIGSERIAL,
  "code" varchar(100) COLLATE "default" NOT NULL,
  "name" varchar(100) COLLATE "default" NOT NULL,
  "url" varchar(200) COLLATE "default",
  "sort" int4 NOT NULL,
  "is_parent" bool DEFAULT true NOT NULL,
  "disable" bool DEFAULT true NOT NULL,
  "is_default" bool DEFAULT false,
  "parent_code" varchar(20) COLLATE "default",
  "description" varchar(100) COLLATE "default",
  "create_time" timestamp(6),
  "update_time" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Primary Key structure for table u_menu
-- ----------------------------
ALTER TABLE "public"."u_menu" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table u_menu
-- ----------------------------
CREATE INDEX "index_u_menu_parent" ON "public"."u_menu" USING btree (is_parent);

create unique index idx_menu_code on u_menu using btree ( code );

-- ----------------------------
-- Records of u_menu
-- ----------------------------
INSERT INTO "public"."u_menu"(code,name,url,sort,is_parent,disable,is_default,parent_code,description,create_time,update_time) VALUES ('develop', '研发中心', '', '1', 't', 't', 'f', 'ROOT_MENU', '研发中心', now(),now());
INSERT INTO "public"."u_menu"(code,name,url,sort,is_parent,disable,is_default,parent_code,description,create_time,update_time) VALUES ('schedule', '调度中心', '', '2', 't', 't', 'f', 'ROOT_MENU', '调度中心', now(),now());
INSERT INTO "public"."u_menu"(code,name,url,sort,is_parent,disable,is_default,parent_code,description,create_time,update_time) VALUES ('statistics', '统计中心', '', '3', 't', 't', 'f', 'ROOT_MENU', '统计中心', now(),now());

-- ----------------------------
-- Table structure for u_role_menu
-- ----------------------------
CREATE TABLE "public"."u_role_menu" (
  "role_id" int4 NOT NULL,
  "menu_id" int4 NOT NULL
)
WITH (OIDS=FALSE)

;

create unique index idx_role_menu_id on u_role_menu using btree ( role_id,menu_id );

-- ----------------------------
-- Records of u_role_menu
-- ----------------------------
INSERT INTO "public"."u_role_menu" VALUES ('1', '1');


CREATE TABLE "public"."u_dictionary" (
"id" BIGSERIAL,
"code" varchar(100) COLLATE "default" NOT NULL,
"name" varchar(100) COLLATE "default" NOT NULL,
"disable" bool DEFAULT true NOT NULL,
"description" varchar(200) COLLATE "default",
"sort" int4,
"create_time" timestamp(6),
"update_time" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Primary Key structure for table u_dictionary
-- ----------------------------
ALTER TABLE "public"."u_dictionary" ADD PRIMARY KEY ("id");

create unique index idx_dictionary_code on u_dictionary using btree ( code );

INSERT INTO "public"."u_dictionary"( code,name,disable,description,sort,create_time,update_time ) VALUES ('IS_DEFAULT', '是否默认', 't', null, '1', now(), now());
INSERT INTO "public"."u_dictionary"( code,name,disable,description,sort,create_time,update_time ) VALUES ('DISABLE', '可用', 't', '可用', '2',  now(), now());
INSERT INTO "public"."u_dictionary"( code,name,disable,description,sort,create_time,update_time ) VALUES ('STATUS', '转换作业状态', 't', '转换作业状态', '3',  now(), now());
INSERT INTO "public"."u_dictionary"( code,name,disable,description,sort,create_time,update_time ) VALUES ('SchedulerType', '调度类型', 't', '作业开始控件中类型参数', '4',  now(), now());
INSERT INTO "public"."u_dictionary"( code,name,disable,description,sort,create_time,update_time ) VALUES ('WEEK', '星期', 't', '作业开始控件中星期参数', '5',  now(), now());
INSERT INTO "public"."u_dictionary"( code,name,disable,description,sort,create_time,update_time ) VALUES ('SYNC_WAY', '同步方式', 't', '作业开始进入的同步方式', '6',  now(), now());
INSERT INTO "public"."u_dictionary"( code,name,disable,description,sort,create_time,update_time ) VALUES ('PARTICIPLE', '分词', 't', 'ElasticSearch分词字典', '7',  now(), now());
INSERT INTO "public"."u_dictionary"( code,name,disable,description,sort,create_time,update_time ) VALUES ('LOG_LEVEL', '日志级别', 't', '作业日志级别', '8',  now(), now());
INSERT INTO "public"."u_dictionary"( code,name,disable,description,sort,create_time,update_time ) VALUES ('STEP_STATUS', '步骤状态', 't', '步骤状态', '9',  now(), now());
INSERT INTO "public"."u_dictionary"( code,name,disable,description,sort,create_time,update_time ) VALUES ('TRANS_STATUS', '转换状态', 't', '转换状态', '9',  now(), now());

-- Table structure for u_dictionary_data
-- ----------------------------
CREATE TABLE "public"."u_dictionary_data" (
"id" BIGSERIAL,
"code" varchar(100) COLLATE "default" NOT NULL,
"name" varchar(100) COLLATE "default" NOT NULL,
"disable" bool DEFAULT true NOT NULL,
"parent_id" int4,
"parent_code" varchar(100) COLLATE "default",
"description" varchar(200) COLLATE "default",
"sort" int4,
"create_time" timestamp(6),
"update_time" timestamp(6)
)
WITH (OIDS=FALSE)
;

-- ----------------------------
-- Primary Key structure for table u_dictionary_data
-- ----------------------------
ALTER TABLE "public"."u_dictionary_data" ADD PRIMARY KEY ("id");

create unique index idx_dictionary_data_code on u_dictionary_data using btree ( code,parent_code );

-- ----------------------------
-- Records of u_dictionary_data
-- ----------------------------
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('false', '否', 't', '1', 'IS_DEFAULT', '', '1', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('true', '是', 't', '1', 'IS_DEFAULT', '', '2', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('true', '可用', 't', '2', 'DISABLE', '', '1', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('false', '不可用', 't', '2', 'DISABLE', '', '2',  now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('1', '设计中', 't', '3', 'STATUS', '', '1',  now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('2', '未部署', 't', '3', 'STATUS', '', '2',  now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('3', '已部署', 't', '3', 'STATUS', '', '3',  now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('4', '等待启动', 't', '3', 'STATUS', '', '4', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('5', '执行中', 't', '3', 'STATUS', '', '5', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('6', '完成', 't', '3', 'STATUS', '', '6', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('7', '等待调度', 't', '3', 'STATUS', '', '7', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('8 ', '手动停止', 't', '3', 'STATUS', '', '8', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('9', '异常停止', 't', '3', 'STATUS', '', '9', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('0', '不需要定时', 't', '4', 'SchedulerType', '', '1', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('1', '时间间隔', 't', '4', 'SchedulerType', '', '2', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('2', '天', 't', '4', 'SchedulerType', '', '3', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('3', '周', 't', '4', 'SchedulerType', '', '4', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('4', '月', 't', '4', 'SchedulerType', '', '5', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('0', '星期天', 't', '5', 'WEEK', '', '1', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('1', '星期一', 't', '5', 'WEEK', '', '2', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('2', '星期二', 't', '5', 'WEEK', '', '3', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('3', '星期三', 't', '5', 'WEEK', '', '4', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('4', '星期四', 't', '5', 'WEEK', '', '5', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('5', '星期五', 't', '5', 'WEEK', '', '6', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('6', '星期六', 't', '5', 'WEEK', '', '7', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('1', '不需要定时', 't', '6', 'SYNC_WAY', '', '1', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('2', '定时', 't', '6', 'SYNC_WAY', '', '2', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('3', '周期', 't', '6', 'SYNC_WAY', '', '3', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('整词', '整词', 't', '7', 'PARTICIPLE', '', '1', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('拼音', '拼音', 't', '7', 'PARTICIPLE', '', '2', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('车牌号', '车牌号', 't', '7', 'PARTICIPLE', '', '3', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('整词,分词', '整词,分词', 't', '7', 'PARTICIPLE', '', '4', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('整词,拼音', '整词,拼音', 't', '7', 'PARTICIPLE', '', '5', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('Nothing', 'Nothing', 't', '8', 'LOG_LEVEL', '', '1', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('Error', 'Error', 't', '8', 'LOG_LEVEL', '', '2', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('Minimal', 'Minimal', 't', '8', 'LOG_LEVEL', '', '3', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('Basic', 'Basic', 't', '8', 'LOG_LEVEL', '', '4', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('Detailed', 'Detailed', 't', '8', 'LOG_LEVEL', '', '5', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('Debug', 'Debug', 't', '8', 'LOG_LEVEL', '', '6', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('空', '空', 't', '8', 'LOG_LEVEL', '', '7', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('初始化', '初始化', 't', '9', 'STEP_STATUS', 'Initializing', '1', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('在运行', '在运行', 't', '9', 'STEP_STATUS', 'Running', '2', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('空闲', '空闲', 't', '9', 'STEP_STATUS', 'Idle', '3', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('已完成', '完成', 't', '9', 'STEP_STATUS', 'Finished', '4', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('已停止', '完成', 't', '9', 'STEP_STATUS', 'Stopped', '5', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('已处理', '已处理', 't', '9', 'STEP_STATUS', 'Disposed', '6', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('终止', '终止', 't', '9', 'STEP_STATUS', 'Halted', '7', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('Finished', '完成', 't', '10', 'TRANS_STATUS', 'Paused', '8', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('Finished (with errors)', '异常停止', 't', '10', 'TRANS_STATUS', '', '1', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('Running', '执行中', 't', '10', 'TRANS_STATUS', '', '2', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('Paused', '暂停', 't', '10', 'TRANS_STATUS', '', '3', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('Initializing', '初始化', 't', '10', 'TRANS_STATUS', '', '4', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('Waiting', '等待中', 't', '10', 'TRANS_STATUS', '', '5', now(), now() );
INSERT INTO "public"."u_dictionary_data"(code,name,disable,parent_id,parent_code,description,sort,create_time,update_time) VALUES ('Stopped', '完成', 't', '10', 'TRANS_STATUS', '', '6', now(), now() );

CREATE TABLE r_data_hour (
"id" BIGSERIAL,
"job_id" int4,
"name" varchar(255) NOT NULL,
"ip" varchar(100) NOT NULL,
"port" int4,
"log_file" varchar(500),
"start_date" timestamp,
"end_date" timestamp,
"current_date" timestamp,
"hour" int4,
"input" bigint,
"output" bigint,
"time_length" bigint,
"speed" bigint,
"mark" int4
)
WITH (OIDS=FALSE);

CREATE INDEX "index_dh_jobid" ON "public"."r_data_hour" USING btree (job_id);
CREATE INDEX "index_dh_date" ON "public"."r_data_hour" USING btree ("current_date");

CREATE TABLE "public"."r_sync" (
"id" BIGSERIAL,
"name" varchar(20) COLLATE "default" NOT NULL,
"node" varchar(100) COLLATE "default",
"log_edit_path" varchar(500) COLLATE "default",
"log_level" varchar(10) COLLATE "default",
"mem" INTEGER,
"sync_mode" varchar(10) COLLATE "default",
"immediate" BOOLEAN,
"repeat" BOOLEAN,
"occupy" BOOLEAN,
"cycle" varchar(500) COLLATE "default",
"schedule_type" varchar(10),
"interval_second" INTEGER,
"interval_minute" INTEGER,
"hour" INTEGER,
"minute" INTEGER,
"day_of_week" INTEGER,
"day_of_month" INTEGER
)
WITH (OIDS=FALSE)
;

delete from r_sync where id in(1,2,3,4,5);
INSERT INTO "r_sync" VALUES (1, '全量非周期', '系统匹配', NULL, 'Basic', 1, '1', 't', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "r_sync" VALUES (2, '全量周期', '系统匹配', NULL, 'Basic', 1, '3', 't', 't', 'f', '3600', '1', 0, 60, NULL, NULL, NULL, NULL);
INSERT INTO "r_sync" VALUES (5, '独占资源', '系统匹配', NULL, 'Basic', 1, '3', 't', 't', 't', '600', '1', 0, 10, NULL, NULL, NULL, NULL);
INSERT INTO "r_sync" VALUES (3, '增量实时性高', '系统匹配', NULL, 'Basic', 1, '3', 't', 't', 'f', '600', '1', 0, 10, NULL, NULL, NULL, NULL);
INSERT INTO "r_sync" VALUES (4, '增量实时性低', '系统匹配', NULL, 'Basic', 1, '2', 'f', 't', 'f', '0 0 23 * * ? *', '2', NULL, NULL, 23, 0, NULL, NULL);

--- 作业和转换json数据
CREATE TABLE "public"."r_transformation_text" (
	id BIGINT,
	transtext TEXT,
	PRIMARY KEY(id)
)
WITH (OIDS=FALSE);

CREATE TABLE "public"."r_job_text" (
	id BIGINT,
	jobtext TEXT,
	PRIMARY KEY(id)
)
WITH (OIDS=FALSE);

--- 导出模板
CREATE TABLE "public"."r_user_temp" (
	id BIGINT,
	temp_name  VARCHAR(255),
	temp_type INTEGER,
	create_date timestamp,
	create_user VARCHAR(255),
	content TEXT,
	PRIMARY KEY(id)
)
WITH (OIDS=FALSE);

ALTER TABLE "public"."r_user_temp" ADD column tags varchar(255);
update u_dictionary_data set name='不需要定时' where code='1' and parent_code='SYNC_WAY';

--- yingjie yao 2017-8-18
alter table r_data_hour add column "job_metric_id" bigint;
update u_dictionary_data set name = '没有日志' where code = 'Nothing' and parent_code = 'LOG_LEVEL';
update u_dictionary_data set name = '最小日志' where code = 'Minimal' and parent_code = 'LOG_LEVEL';
update u_dictionary_data set name = '基本日志' where code = 'Basic' and parent_code = 'LOG_LEVEL';
update u_dictionary_data set name = '详细日志' where code = 'Detailed' and parent_code = 'LOG_LEVEL';
update u_dictionary_data set name = '错误日志' where code = 'Error' and parent_code = 'LOG_LEVEL';
