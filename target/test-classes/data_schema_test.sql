--ALTER TABLE P6_ACCOUNT DROP CONSTRAINT FK_ACCOUNT_PERSON;
--ALTER TABLE P6_RELATION DROP CONSTRAINT FK_RELATION_PERSON;
--ALTER TABLE P6_RELATION DROP CONSTRAINT FK_RELATION_RELATION;
--ALTER TABLE P6_OPERATION DROP CONSTRAINT FK_OPERATION_ACCOUNT;
--ALTER TABLE P6_OPERATION DROP CONSTRAINT FK_OPERATION_BENEFICIARY;

--drop  table P6_RATE;
--drop  table P6_ACCOUNT;
--drop  table P6_PERSON;
--drop  table P6_RELATION;
--drop  table P6_OPERATION;


create table  P6_RATE (
                          ID BIGINT auto_increment primary key,
                          RATE_CODE VARCHAR(5),
                          RATE DECIMAL(2,2) NOT NULL,
                          CREATE_DATE TIMESTAMP DEFAULT NOW(),
                          UPDATE_DATE TIMESTAMP DEFAULT NOW()
)
;

create table  P6_PERSON (
                            ID BIGINT auto_increment primary key,
                            EMAIL VARCHAR(255) NOT NULL,
                            PASSWORD VARCHAR2(255) ,
                            LASTNAME VARCHAR(50) NOT NULL,
                            FIRSTNAME VARCHAR(50) NOT NULL,
                            BIC VARCHAR(10) NOT NULL,
                            IBAN VARCHAR(23) NOT NULL,
                            ACTIVE_FLG CHAR(1) NOT NULL,
                            CREATE_DATE TIMESTAMP DEFAULT NOW(),
                            UPDATE_DATE TIMESTAMP DEFAULT NOW()
)
;

create table  P6_ACCOUNT (
                             ID BIGINT auto_increment primary key,
                             ACCOUNT_NUMBER VARCHAR(10) NOT NULL,
                             BALANCE DECIMAL(9,2),
                             ACTIVE_FLG CHAR(1) NOT NULL,
                             PERSON_ID BIGINT ,
                             CREATE_DATE TIMESTAMP DEFAULT NOW(),
                             UPDATE_DATE TIMESTAMP DEFAULT NOW(),
                             CONSTRAINT FK_ACCOUNT_PERSON  FOREIGN KEY(PERSON_ID) REFERENCES P6_PERSON(ID)
)
;

create table  P6_RELATION (
                              ID BIGINT auto_increment primary key,
                              PERSON_ID BIGINT NOT NULL,
                              RELATION_ID BIGINT NOT NULL,
                              CREATE_DATE TIMESTAMP DEFAULT NOW(),
                              UPDATE_DATE TIMESTAMP DEFAULT NOW(),
                              CONSTRAINT FK_RELATION_PERSON  FOREIGN KEY(PERSON_ID) REFERENCES P6_PERSON(ID),
                              CONSTRAINT FK_RELATION_RELATION FOREIGN KEY(RELATION_ID) REFERENCES P6_PERSON(ID)
)
;

create table  P6_OPERATION (
                               ID BIGINT auto_increment primary key,
                               OPERATION_DATE TIMESTAMP DEFAULT NOW(),
                               AMOUNT DECIMAL(9,2),
                               FEE DECIMAL(9,2) DEFAULT 0,
                               TYPE VARCHAR(10) NOT NULL,
                               FLOW CHAR(1) NOT NULL,
                               DESCRIPTION VARCHAR(255),
                               ACCOUNT_ID BIGINT NOT NULL,
                               BENEFICIARY_ID BIGINT ,
                               BIC_BENEF VARCHAR(10),
                               IBAN_BENEF VARCHAR(23),
                               BILLING_FLG CHAR(1) DEFAULT '0' NOT NULL,
                               CREATE_DATE TIMESTAMP DEFAULT NOW(),
                               UPDATE_DATE TIMESTAMP DEFAULT NOW(),
                               CONSTRAINT FK_OPERATION_ACCOUNT  FOREIGN KEY(ACCOUNT_ID) REFERENCES P6_ACCOUNT(ID),
                               CONSTRAINT FK_OPERATION_BENEFICIARY FOREIGN KEY(BENEFICIARY_ID) REFERENCES P6_ACCOUNT(ID)
)
;

create unique index IDX_RATE_U1 on P6_RATE (RATE_CODE);
create unique index IDX_PERSON_U1 on P6_PERSON (EMAIL);
create unique index IDX_ACCOUNT_U1 on P6_ACCOUNT (ACCOUNT_NUMBER);
create unique index IDX_RELATION_U1 on P6_RELATION (PERSON_ID,RELATION_ID);
