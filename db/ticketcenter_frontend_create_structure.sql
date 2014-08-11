-- Create tables
CREATE TABLE T_CITY
(
  CITY_ID bigint NOT NULL,
  NAME character varying(255),
  CONSTRAINT T_CITY_PK PRIMARY KEY (CITY_ID)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE T_CITY
  OWNER TO postgres;

  
  

CREATE TABLE T_LOCATION
(
  LOCATION_ID bigint NOT NULL,
  CAPACITY integer,
  NAME character varying(255),
  PICTURE character varying(255),
  CITY_FK bigint,
  CONSTRAINT T_LOCATION_PK PRIMARY KEY (LOCATION_ID),
  CONSTRAINT T_LOCATION_CITY_FK FOREIGN KEY (CITY_FK)
      REFERENCES T_CITY (CITY_ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE T_LOCATION
  OWNER TO postgres;
  



CREATE TABLE T_EVENT
(
  EVENT_ID bigint NOT NULL,
  EVENT_DATE date,
  NAME character varying(255),
  PRICE integer,
  PICTURE character varying(255),
  LOCATION_FK bigint,
  CONSTRAINT T_EVENT_PK PRIMARY KEY (EVENT_ID),
  CONSTRAINT T_EVENT_LOCATION_FK FOREIGN KEY (LOCATION_FK)
      REFERENCES T_LOCATION (LOCATION_ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE T_EVENT
  OWNER TO postgres;
  
  
  

CREATE TABLE T_ORDER
(
  ORDER_ID bigint NOT NULL,
  ADDRESS character varying(255),
  EMAIL character varying(255),
  NAME character varying(255),
  CONSTRAINT T_ORDER_PK PRIMARY KEY (ORDER_ID)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE T_ORDER
  OWNER TO postgres;

  


CREATE TABLE T_ORDER_ENTRY
(
  ORDER_ENTRY_ID bigint NOT NULL,
  NR_TICKETS integer,
  EVENT_FK bigint,
  ORDER_FK bigint,
  CONSTRAINT T_ORDER_ENTRY_PK PRIMARY KEY (ORDER_ENTRY_ID),
  CONSTRAINT T_ORDER_ENTRY_EVENT_FK FOREIGN KEY (EVENT_FK)
      REFERENCES T_EVENT (EVENT_ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT T_ORDER_ENTRY_ORDER_FK FOREIGN KEY (ORDER_FK)
      REFERENCES T_ORDER (ORDER_ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE T_ORDER_ENTRY
  OWNER TO postgres;
  
  
-- Create sequences
CREATE SEQUENCE SQ_CITY CYCLE
   INCREMENT 1
   START 1
   MINVALUE 1
   MAXVALUE 9999999
   CACHE 20;
ALTER SEQUENCE SQ_CITY
  OWNER TO postgres;
  
CREATE SEQUENCE SQ_EVENT CYCLE
   INCREMENT 1
   START 1
   MINVALUE 1
   MAXVALUE 9999999
   CACHE 20;
ALTER SEQUENCE SQ_EVENT
  OWNER TO postgres;
 
CREATE SEQUENCE SQ_LOCATION CYCLE
   INCREMENT 1
   START 1
   MINVALUE 1
   MAXVALUE 9999999
   CACHE 20;
ALTER SEQUENCE SQ_LOCATION
  OWNER TO postgres;
  
CREATE SEQUENCE SQ_ORDER CYCLE
   INCREMENT 1
   START 1
   MINVALUE 1
   MAXVALUE 9999999
   CACHE 20;
ALTER SEQUENCE SQ_ORDER
  OWNER TO postgres;

CREATE SEQUENCE SQ_ORDER_ENTRY CYCLE
   INCREMENT 1
   START 1
   MINVALUE 1
   MAXVALUE 9999999
   CACHE 20;
ALTER SEQUENCE SQ_ORDER_ENTRY
  OWNER TO postgres;