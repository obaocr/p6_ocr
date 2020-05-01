package com.mybuddy.pay.model;

import com.mybuddy.pay.AppConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(AppConfigTest.class)
@Transactional
class ModelTest {

    @Test
    void operation() throws ParseException {
        long id = 3;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "2020-05-01 12:00:00";
        Date dateTest = simpleDateFormat.parse(dateString);
        Operation operation = new Operation();
        operation.setId(1);
        operation.setAccountId(2);
        operation.setBeneficiaryId(id);
        operation.setIbanBenef("123456");
        operation.setBicBenef("BNPP");
        operation.setDescription("test");
        operation.setFlow("C");
        operation.setAmount(1000.00);
        operation.setFee(5.0);
        operation.setType("WITHDRAWAL");
        operation.setUpdateDate(dateTest);
        operation.setCreateDate(dateTest);
        operation.setOperationDate(dateTest);
        assertTrue(operation.getId() == 1);
        assertTrue(operation.getAccountId() == 2);
        assertTrue(operation.getBeneficiaryId() == id);
        assertTrue(operation.getAmount() == 1000.00);
        assertTrue(operation.getFee() == 5.00);
        assertTrue(operation.getBicBenef().equals("BNPP"));
        assertTrue(operation.getIbanBenef().equals("123456"));
        assertTrue(operation.getFlow().equals("C"));
        assertTrue(operation.getType().equals("WITHDRAWAL"));
        assertTrue(simpleDateFormat.format(operation.getUpdateDate()).equals(dateString));
        assertTrue(simpleDateFormat.format(operation.getCreateDate()).equals(dateString));
        assertTrue(simpleDateFormat.format(operation.getOperationDate()).equals(dateString));
    }

    @Test
    void user() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "2020-05-01 12:00:00";
        Date dateTest = simpleDateFormat.parse(dateString);
        User user = new User();
        user.setId(1);
        user.setPassword("***");
        user.setEmail("a@gmail.com");
        user.setFirstName("Olivier");
        user.setLastName("Martin");
        user.setActiveFlg("1");
        user.setBic("BNPP");
        user.setIban("123456");
        user.setUpdateDate(dateTest);
        user.setCreateDate(dateTest);
        assertTrue(user.getId() == 1);
        assertTrue(user.getBic().equals("BNPP"));
        assertTrue(user.getIban().equals("123456"));
        assertTrue(user.getPassword().equals("***"));
        assertTrue(user.getEmail().equals("a@gmail.com"));
        assertTrue(user.getFirstName().equals("Olivier"));
        assertTrue(user.getLastName().equals("Martin"));
        assertTrue(user.getActiveFlg().equals("1"));
        assertTrue(simpleDateFormat.format(user.getUpdateDate()).equals(dateString));
        assertTrue(simpleDateFormat.format(user.getCreateDate()).equals(dateString));
    }

    @Test
    void account() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "2020-05-01 12:00:00";
        Date dateTest = simpleDateFormat.parse(dateString);
        Account account = new Account();
        account.setId(1);
        account.setPersonId(2);
        account.setAccountNumber("C01");
        account.setBalance(1000.00);
        account.setActiveflg("1");
        account.setUpdateDate(dateTest);
        account.setCreateDate(dateTest);
        assertTrue(account.getId() == 1);
        assertTrue(account.getPersonId() == 2);
        assertTrue(account.getBalance() == (1000.00));
        assertTrue(account.getActiveflg().equals("1"));
        assertTrue(account.getAccountNumber().equals("C01"));
        assertTrue(simpleDateFormat.format(account.getUpdateDate()).equals(dateString));
        assertTrue(simpleDateFormat.format(account.getCreateDate()).equals(dateString));
    }

    @Test
    void relation() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "2020-05-01 12:00:00";
        Date dateTest = simpleDateFormat.parse(dateString);
        Relation relation = new Relation();
        relation.setId(1);
        relation.setUserId(2);
        relation.setRelationId(3);
        relation.setUpdateDate(dateTest);
        relation.setCreateDate(dateTest);
        assertTrue(relation.getId() == 1);
        assertTrue(relation.getUserId() == 2);
        assertTrue(relation.getRelationId() == 3);
        assertTrue(simpleDateFormat.format(relation.getUpdateDate()).equals(dateString));
        assertTrue(simpleDateFormat.format(relation.getCreateDate()).equals(dateString));
    }

    @Test
    void rateFee() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "2020-05-01 12:00:00";
        Date dateTest = simpleDateFormat.parse(dateString);
        RateFee rateFee = new RateFee();
        rateFee.setId(1);
        rateFee.setRateCode("TX01");
        rateFee.setRate(0.5);
        rateFee.setUpdateDate(dateTest);
        rateFee.setCreateDate(dateTest);
        assertTrue(rateFee.getId() == 1);
        assertTrue(rateFee.getRateCode() == "TX01");
        assertTrue(rateFee.getRate() == 0.5);
        assertTrue(simpleDateFormat.format(rateFee.getUpdateDate()).equals(dateString));
        assertTrue(simpleDateFormat.format(rateFee.getCreateDate()).equals(dateString));
    }

    @Test
    void accountUser() throws ParseException {
        AccountUser accountUser = new AccountUser();
        accountUser.setId(1);
        accountUser.setPersonId(2);
        accountUser.setAccountNumber("C01");
        accountUser.setEmail("a@gmail.com");
        accountUser.setFirstName("Olivier");
        accountUser.setLastName("Martin");
        accountUser.setActiveflg("1");
        accountUser.setBic("BNPP");
        accountUser.setIban("123456");
        assertTrue(accountUser.getId() == 1);
        assertTrue(accountUser.getPersonId() == 2);
        assertTrue(accountUser.getEmail().equals("a@gmail.com"));
        assertTrue(accountUser.getFirstName().equals("Olivier"));
        assertTrue(accountUser.getLastName().equals("Martin"));
        assertTrue(accountUser.getActiveflg().equals("1"));
    }

}
