CREATE DATABASE mosip_curp
	ENCODING = 'UTF8'
	LC_COLLATE = 'en_US.UTF-8'
	LC_CTYPE = 'en_US.UTF-8'
	TABLESPACE = pg_default
	OWNER = postgres
	TEMPLATE  = template0;
-- ddl-end --
COMMENT ON DATABASE mosip_curp IS 'Migration data from Mexico env. and status of the curp in Mosip';
-- ddl-end --

\c mosip_curp postgres

-- object: regprc | type: SCHEMA --
DROP SCHEMA IF EXISTS curp CASCADE;
CREATE SCHEMA curp;
-- ddl-end --
ALTER SCHEMA curp OWNER TO postgres;
-- ddl-end --

ALTER DATABASE mosip_curp SET search_path TO curp,pg_catalog,public;
-- ddl-end --

CREATE ROLE curpuser WITH
	INHERIT
	LOGIN
	PASSWORD :dbuserpwd;
-- ddl-end --

GRANT CONNECT
   ON DATABASE mosip_curp
   TO curpuser;
-- ddl-end --

-- object: grant_3543fb6cf7 | type: PERMISSION --
GRANT USAGE
   ON SCHEMA curp
   TO curpuser;
-- ddl-end --

-- object: grant_8e1a2559ed | type: PERMISSION --
GRANT SELECT,UPDATE
   ON ALL TABLES IN SCHEMA curp
   TO curpuser;
-- ddl-end --

ALTER DEFAULT PRIVILEGES IN SCHEMA curp
	GRANT SELECT,UPDATE ON TABLES TO curpuser;


CREATE TABLE curp.curp_bio_data(
	id character varying(36) NOT NULL,
	curp_id character varying(18) NOT NULL,
	curp_type character varying(20),
	curp_cr_dtimes timestamp NOT NULL,
	bio_filepath text,	
	status character varying(20) NOT NULL,
	status_comment character varying(256),
	cr_by character varying(256) NOT NULL,
	cr_dtimes timestamp NOT NULL,
	upd_by character varying(256),
	upd_dtimes timestamp,
	CONSTRAINT pk_curp_data_id PRIMARY KEY (id),
	CONSTRAINT uni_curp_key UNIQUE(curp_id,curp_type)
);
-- ddl-end --
COMMENT ON TABLE curp.curp_bio_data IS 'Card Print : To store demographic and biometric(only face) details in encrypted form which further to be used for card printing';
-- ddl-end --
COMMENT ON COLUMN curp.curp_bio_data.curp_id IS 'Nation Id of Mexico.';
-- ddl-end --
COMMENT ON COLUMN curp.curp_bio_data.curp_type IS 'Possible values: NEW,UPDATE,REVOKE & DEACTIVATE';
-- ddl-end --
COMMENT ON COLUMN curp.curp_bio_data.curp_cr_dtimes IS 'Actual creation datetime of curp.';
COMMENT ON COLUMN curp.curp_bio_data.bio_filepath IS 'Biometric file path';
COMMENT ON COLUMN curp.curp_bio_data.status_code IS 'Flag to mark the status of curp';
COMMENT ON COLUMN curp.curp_bio_data.status_comment IS 'Additional information of status';
-- ddl-end --
COMMENT ON COLUMN curp.curp_bio_data.cr_by IS 'Created By : ID or name of the user who create / insert record';
-- ddl-end --
COMMENT ON COLUMN curp.curp_bio_data.cr_dtimes IS 'Created DateTimestamp : Date and Timestamp when the record is created/inserted';
-- ddl-end --
COMMENT ON COLUMN curp.curp_bio_data.upd_by IS 'Updated By : ID or name of the user who update the record with new values';
-- ddl-end --
COMMENT ON COLUMN curp.curp_bio_data.upd_dtimes IS 'Updated DateTimestamp : Date and Timestamp when any of the fields in the record is updated with new values.';
-- ddl-end --

CREATE TABLE curp.matched_curp(
	id character varying(36) NOT NULL,
	curp_id character varying(18) NOT NULL,
	matched_curp_ids text NOT NULL,
	status_code character varying(20) NOT NULL,
	status_comment character varying(256),
	cr_by character varying(256) NOT NULL,
	cr_dtimes timestamp NOT NULL,
	upd_by character varying(256),
	upd_dtimes timestamp,
	is_latest_bio boolean,
	CONSTRAINT pk_mat_curp_id PRIMARY KEY (id)
);


CREATE INDEX IF NOT EXISTS idx_curp_type ON curp.curp_bio_data USING btree (curp_type);
CREATE INDEX IF NOT EXISTS idx_curp_status ON curp.curp_bio_data USING btree (status);

