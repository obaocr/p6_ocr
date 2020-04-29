package com.mybuddy.pay.dao;

import com.mybuddy.pay.AppConfigTest;
import com.mybuddy.pay.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO remplacer les 2 annotations @ExtendWith et @ContextConfiguration par @SpringJUnitConfig(AppConfigTest.class) ?

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfigTest.class)
class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Test
    void getByEmail() {
        User user = userDao.getByEmail("test1@gmail.com");
        System.out.println("person.getLastName(): "+ user.getLastName());
        assertTrue(user.getLastName().equals("Martin"));
    }
}