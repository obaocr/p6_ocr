-- *****************************
-- Init de la BDD
-- *****************************

drop table P6_RATE CASCADE CONSTRAINTS;
drop table p6_OPERATION CASCADE CONSTRAINTS;
drop table P6_PERSON CASCADE CONSTRAINTS;
drop table P6_ACCOUNT CASCADE CONSTRAINTS;
drop table P6_RELATION CASCADE CONSTRAINTS;

-- P6_RATE
create table  P6_RATE (
                          ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
                          RATE_CODE VARCHAR2(5),
                          RATE NUMBER(2,2) NOT NULL,
                          CREATE_DATE DATE DEFAULT sysdate NOT NULL,
                          UPDATE_DATE DATE DEFAULT sysdate NOT NULL
)
;

-- P6_PERSON
create table  P6_PERSON (
                            ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
                            EMAIL VARCHAR2(255) NOT NULL,
                            PASSWORD VARCHAR2(255) ,
                            LASTNAME VARCHAR2(50) NOT NULL,
                            FIRSTNAME VARCHAR2(50) NOT NULL,
                            BIC VARCHAR2(10) NOT NULL,
                            IBAN VARCHAR2(23) NOT NULL,
                            ACTIVE_FLG CHAR(1) NOT NULL,
                            CREATE_DATE DATE DEFAULT sysdate NOT NULL,
                            UPDATE_DATE DATE DEFAULT sysdate NOT NULL
)
;

-- P6_ACCOUNT
create table  P6_ACCOUNT (
                             ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
                             ACCOUNT_NUMBER VARCHAR2(10) NOT NULL,
                             BALANCE NUMBER(9,2),
                             ACTIVE_FLG CHAR(1) NOT NULL,
                             PERSON_ID NUMBER,
                             CURRENCY VARCHAR2(3) DEFAULT 'EUR' NOT NULL,
                             CREATE_DATE DATE DEFAULT sysdate NOT NULL,
                             UPDATE_DATE DATE DEFAULT sysdate NOT NULL,
                             CONSTRAINT FK_ACCOUNT_PERSON  FOREIGN KEY(PERSON_ID) REFERENCES P6_PERSON(ID)
)
;

create table  P6_OPERATION (
                               ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
                               OPERATION_DATE DATE DEFAULT sysdate NOT NULL,
                               AMOUNT NUMBER(9,2),
                               FEE NUMBER(9,2) DEFAULT 0,
                               TYPE VARCHAR2(10) NOT NULL,
                               FLOW CHAR(1) NOT NULL,
                               DESCRIPTION VARCHAR2(255),
                               ACCOUNT_ID NUMBER NOT NULL,
                               BENEFICIARY_ID NUMBER,
                               BIC_BENEF VARCHAR2(10),
                               IBAN_BENEF VARCHAR2(23),
                               BILLING_FLG CHAR(1) DEFAULT '0' NOT NULL,
                               CURRENCY VARCHAR2(3) DEFAULT 'EUR' NOT NULL,
                               CREATE_DATE DATE DEFAULT sysdate NOT NULL,
                               UPDATE_DATE DATE DEFAULT sysdate NOT NULL,
                               CONSTRAINT FK_OPERATION_ACCOUNT  FOREIGN KEY(ACCOUNT_ID) REFERENCES P6_ACCOUNT(ID),
                               CONSTRAINT FK_OPERATION_BENEFICIARY FOREIGN KEY(BENEFICIARY_ID) REFERENCES P6_ACCOUNT(ID)
)
;

-- P6_ACCOUNT
create table  P6_RELATION (
                              ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
                              PERSON_ID NUMBER NOT NULL,
                              RELATION_ID NUMBER NOT NULL,
                              CREATE_DATE DATE DEFAULT sysdate NOT NULL,
                              UPDATE_DATE DATE DEFAULT sysdate NOT NULL,
                              CONSTRAINT FK_RELATION_PERSON  FOREIGN KEY(PERSON_ID) REFERENCES P6_PERSON(ID),
                              CONSTRAINT FK_RELATION_RELATION FOREIGN KEY(RELATION_ID) REFERENCES P6_PERSON(ID)
)
;

create unique index IDX_RATE_U1 on P6_RATE (RATE_CODE);
create unique index IDX_PERSON_U1 on P6_PERSON (EMAIL);
create unique index IDX_ACCOUNT_U1 on P6_ACCOUNT (ACCOUNT_NUMBER);
create unique index IDX_RELATION_U1 on P6_RELATION (PERSON_ID,RELATION_ID);


