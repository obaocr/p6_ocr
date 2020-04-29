package com.mybuddy.pay.dao;

import com.mybuddy.pay.AppConfigTest;
import com.mybuddy.pay.model.AccountUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO remplacer les 2 annotations @ExtendWith et @ContextConfiguration par @SpringJUnitConfig(AppConfigTest.class) ?

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfigTest.class)
class AccountDaoImplTest {

    @Autowired
    AccountUserDao accountUserDao;

    @Autowired
    AccountDao accountDao;

    @Test
    void updateBalance() {
        AccountUser accountUser = accountUserDao.getByEmail("test1@gmail.com");
        accountDao.updateBalance(accountUser.getId(), 999.00);
        assertTrue(accountDao.updateBalance(accountUser.getId(), 999.00) > 0);
        assertTrue(accountUserDao.getByEmail("test1@gmail.com").getBalance() == 999.00);
    }

}