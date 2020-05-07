package com.mybuddy.pay.dao;

import com.mybuddy.pay.AppConfigTest;
import com.mybuddy.pay.model.AccountUser;
import com.mybuddy.pay.model.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(AppConfigTest.class)
@Transactional
public class OperationDaoImplTest {

    @Autowired
    private OperationDao operationDao;
    @Autowired
    private AccountUserDao accountUserDao;

    @Test
    @Rollback
    void createOperation() {
        AccountUser accountUser =  accountUserDao.getByEmail("test1@gmail.com");
        assertTrue(accountUser != null);
        Operation operation = new Operation(accountUser.getId(), null, 100.00, 0.0, "PAYMENT","C", "description",null,null);
        assertTrue(operationDao.createOperation(operation) == 1);
    }

    @Test
    @Rollback
    void getOperationForBillingShouldReturn1Operation() {
        AccountUser accountUser =  accountUserDao.getByEmail("test1@gmail.com");
        assertTrue(accountUser != null);
        Operation operation = new Operation(accountUser.getId(), null, 100.00, 0.0, "PAYMENT","C", "description",null,null);
        assertTrue(operationDao.createOperation(operation) == 1);
        assertTrue(operationDao.getOperationForBilling().size() == 1);
    }

    @Test
    @Rollback
    void getOperationForBillingShouldReturnException() {
        try {
            AccountUser accountUser =  accountUserDao.getByEmail("test1@gmail.com");
            assertTrue(accountUser != null);
            // Exception car FK inexistante !
            long id = 0;
            Operation operation = new Operation(accountUser.getId(), id, 100.00, 0.0, "PAYMENT","C", "description",null,null);
            assertTrue(operationDao.createOperation(operation) == 1);
        } catch (Exception e) {
            assertTrue(e.getMessage() != null);
        }

    }

}
