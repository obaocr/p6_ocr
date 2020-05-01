package com.mybuddy.pay.dao;

import com.mybuddy.pay.AppConfigTest;
import com.mybuddy.pay.model.AccountUser;
import com.mybuddy.pay.model.RateFee;
import com.mybuddy.pay.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(AppConfigTest.class)
@Transactional
public class RelationDaoImplTest {

    @Autowired
    RelationDao relationDao;

    @Autowired
    UserDao userDao;

    @Test
    @Rollback
    void countRelationsShoudReturnZero() {
        User user = userDao.getByEmail("test1@gmail.com");
        assertTrue(user != null);
        assertTrue(relationDao.countRelationByEmail(user.getId(), "test2@gmail.com") == 0);
    }

    @Test
    @Rollback
    void addRelation() {
        User user = userDao.getByEmail("test1@gmail.com");
        User user2 = userDao.getByEmail("test2@gmail.com");
        assertTrue(user != null);
        assertTrue(user2 != null);
        assertTrue(relationDao.addRelationById(user.getId(), user2.getId()) == 1);
    }

    @Test
    @Rollback
    void countRelations() {
        User user = userDao.getByEmail("test1@gmail.com");
        User user2 = userDao.getByEmail("test2@gmail.com");
        assertTrue(user != null);
        assertTrue(user2 != null);
        assertTrue(relationDao.addRelationById(user.getId(), user2.getId()) == 1);
        assertTrue(relationDao.countRelationByEmail(user.getId(), "test2@gmail.com") == 1);
    }

    @Test
    @Rollback
    void getRelationsShoudReturnZero() {
        User user = userDao.getByEmail("test1@gmail.com");
        User user2 = userDao.getByEmail("test2@gmail.com");
        assertTrue(user != null);
        assertTrue(user2 != null);
        assertTrue(relationDao.getRelations(user.getId()).size() == 0);
    }

    @Test
    @Rollback
    void getRelations() {
        User user = userDao.getByEmail("test1@gmail.com");
        User user2 = userDao.getByEmail("test2@gmail.com");
        assertTrue(user != null);
        assertTrue(user2 != null);
        relationDao.addRelationById(user.getId(), user2.getId());
        assertTrue(relationDao.getRelations(user.getId()).size() > 0);
    }

}
