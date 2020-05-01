package com.mybuddy.pay.dao;

import com.mybuddy.pay.AppConfigTest;
import com.mybuddy.pay.model.AccountUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(AppConfigTest.class)
@Transactional
public class AcccountUserDaoImplTest {

    @Autowired
    AccountUserDao accountUserDao;

    @Test
    @Rollback
    void getByEmail() {
        AccountUser accountUser = accountUserDao.getByEmail("test1@gmail.com");
        assertTrue(accountUser.getLastName().equals("Martin"));
    }

    @Test
    @Rollback
    void getById() {
        AccountUser accountUser = accountUserDao.getByEmail("test1@gmail.com");
        AccountUser accountUser2 = accountUserDao.getByUserId(accountUser.getId());
        assertTrue(accountUser2.getLastName().equals("Martin"));
    }

    @Test
    @Rollback
    void getCount() {
        assertTrue(accountUserDao.getCount() > 0);
    }

    @Test
    @Rollback
    void getAll() {
        assertTrue(accountUserDao.getAll().size() > 0);
    }

    @Test
    @Rollback
    void getByEmailShoudReturnException() {
        try {
            AccountUser accountUser = accountUserDao.getByEmail("xxxxx");
        } catch (EmptyResultDataAccessException e) {
            assertTrue(e.getMessage() != null);
        }
    }

    @Test
    @Rollback
    void getByIdReturnException() {

        try {
            accountUserDao.getByUserId(0);
        } catch (EmptyResultDataAccessException e) {
            assertTrue(e.getMessage() != null);
        }

    }
}
