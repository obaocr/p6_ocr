--alter table p6_OPERATION DISABLE constraint FK_OPERATION_ACCOUNT;
--alter table p6_OPERATION DISABLE constraint FK_OPERATION_BENEFICIARY;
--alter table p6_ACCOUNT DISABLE constraint FK_ACCOUNT_PERSON;
--alter table p6_RELATION  DISABLE constraint FK_RELATION_RELATION;
--alter table p6_RELATION  DISABLE constraint FK_RELATION_PERSON;

--truncate table P6_RATE;
--truncate table p6_OPERATION;
--truncate table P6_PERSON;
--truncate table P6_ACCOUNT;
--truncate table P6_RELATION;

insert into P6_RATE (RATE_CODE,RATE) values ('RTFEE',0.5);

insert into  P6_PERSON (EMAIL,PASSWORD,FIRSTNAME,LASTNAME,BIC,IBAN,ACTIVE_FLG) values ('test1@gmail.com','A123','Olivier','Martin','SOGEFRPP','600030407351233388889','1');
insert into  P6_PERSON (EMAIL,PASSWORD,FIRSTNAME,LASTNAME,BIC,IBAN,ACTIVE_FLG) values ('test2@gmail.com','A123','Jean','Martin','SOGEFRPP','600030407351233386669','1');
insert into  P6_PERSON (EMAIL,PASSWORD,FIRSTNAME,LASTNAME,BIC,IBAN,ACTIVE_FLG) values ('test3@gmail.com','A123','Arthur','Dardarin','SOGEFRPP','600030407351233377789','1');
insert into  P6_PERSON (EMAIL,PASSWORD,FIRSTNAME,LASTNAME,BIC,IBAN,ACTIVE_FLG) values ('test4@gmail.com','A123','Virginie','Massot','SOGEFRPP','600030407351233311191','1');
insert into  P6_PERSON (EMAIL,PASSWORD,FIRSTNAME,LASTNAME,BIC,IBAN,ACTIVE_FLG) values ('test5@gmail.com','A123','Maxime','Calice','SOGEFRPP','600030407351233344489','1');
insert into  P6_PERSON (EMAIL,PASSWORD,FIRSTNAME,LASTNAME,BIC,IBAN,ACTIVE_FLG) values ('test6@gmail.com','A123','Claudia','Bozon','SOGEFRPP','600030407351233322284','1');

insert into P6_ACCOUNT (account_number,active_flg,balance) values ('C000000001','1',0);
insert into P6_ACCOUNT (account_number,active_flg,balance) values ('C000000002','1',0);
insert into P6_ACCOUNT (account_number,active_flg,balance) values ('C000000003','1',0);
insert into P6_ACCOUNT (account_number,active_flg,balance) values ('C000000004','1',0);
insert into P6_ACCOUNT (account_number,active_flg,balance) values ('C000000005','1',0);
insert into P6_ACCOUNT (account_number,active_flg,balance) values ('C000000006','1',10000);

update P6_ACCOUNT set PERSON_ID = (select ID from P6_PERSON where email = 'test1@gmail.com') where account_number = 'C000000001';
update P6_ACCOUNT set PERSON_ID = (select ID from P6_PERSON where email = 'test2@gmail.com') where account_number = 'C000000002';
update P6_ACCOUNT set PERSON_ID = (select ID from P6_PERSON where email = 'test3@gmail.com') where account_number = 'C000000003';
update P6_ACCOUNT set PERSON_ID = (select ID from P6_PERSON where email = 'test4@gmail.com') where account_number = 'C000000004';
update P6_ACCOUNT set PERSON_ID = (select ID from P6_PERSON where email = 'test5@gmail.com') where account_number = 'C000000005';
update P6_ACCOUNT set PERSON_ID = (select ID from P6_PERSON where email = 'test6@gmail.com') where account_number = 'C000000006';

commit;

--alter table p6_OPERATION ENABLE constraint FK_OPERATION_ACCOUNT;
--alter table p6_OPERATION ENABLE constraint FK_OPERATION_BENEFICIARY;
--alter table p6_ACCOUNT ENABLE constraint FK_ACCOUNT_PERSON;
--alter table p6_RELATION  ENABLE constraint FK_RELATION_RELATION;
--alter table p6_RELATION  ENABLE constraint FK_RELATION_PERSON;