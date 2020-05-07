package com.mybuddy.pay.dao;

import com.mybuddy.pay.AppConfigTest;
import com.mybuddy.pay.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(AppConfigTest.class)
class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Rollback
    void getByEmail() {
        User user = userDao.getByEmail("test1@gmail.com");
        System.out.println("person.getLastName(): "+ user.getLastName());
        assertTrue(user.getLastName().equals("Martin"));
    }

    @Test
    @Rollback
    void getByEmailShouldReturnException() {
        try {
            User user = userDao.getByEmail("xx@xx.xx");
        } catch (EmptyResultDataAccessException e) {
            assertTrue(e.getMessage() != null);
        }

    }
}