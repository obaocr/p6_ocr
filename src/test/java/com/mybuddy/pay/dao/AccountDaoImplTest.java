package com.mybuddy.pay.dao;

import com.mybuddy.pay.AppConfigTest;
import com.mybuddy.pay.model.AccountUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(AppConfigTest.class)
@Transactional
class AccountDaoImplTest {

    @Autowired
    AccountUserDao accountUserDao;

    @Autowired
    AccountDao accountDao;

    @Test
    @Rollback
    void updateBalance() {
        try {
            AccountUser accountUser = accountUserDao.getByEmail("test1@gmail.com");
            accountDao.updateBalance(accountUser.getId(), 999.00);
            assertTrue(accountDao.updateBalance(accountUser.getId(), 999.00) > 0);
            assertTrue(accountUserDao.getByEmail("test1@gmail.com").getBalance() == 999.00);
        } catch (Exception e) {

        }

    }

    @Test
    @Rollback
    void updateBalanceShouldReturnException() {
        try {
            // id 0 does not exists
            long id = 0;
            accountDao.updateBalance(id, 999.00);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("updateBalance : no row updated !"));
        }

    }
}