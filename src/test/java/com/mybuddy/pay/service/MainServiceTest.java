package com.mybuddy.pay.service;

import com.mybuddy.pay.dao.*;
import com.mybuddy.pay.model.AccountUser;
import com.mybuddy.pay.model.Operation;
import com.mybuddy.pay.model.ServiceResponse;
import com.mybuddy.pay.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainServiceTest {

    @Autowired
    private MainService mainService;

    @Mock
    private static AccountUserDao accountUserDao;

    @Mock
    private UserDao userDao;

    @Mock
    private static OperationDao operationDao;

    @Mock
    private static AccountDao accountDao;

    @Mock
    private static RelationDao relationDao;

    /*@Test
    void addRelationByEmailShouldReturnTrue() {
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("test1@gmail.com");
        user1.setLastName("Martin");
        user1.setFirstName("Olivier");
        user1.setPassword("A123");
        ServiceResponse serviceResponse = mainService.addRelationByEmail(1, "test@gmail.com");
        Mockito.when(userDao.getByEmail("test@gmail.com")).thenReturn(user1);
        Mockito.when(relationDao.countRelationByEmail(user1.getId(), "test@gmail.com")).thenReturn(1);
        Mockito.when(relationDao.addRelationById(1, user1.getId())).thenReturn(1);
        assertTrue(serviceResponse.isResult() == true);
    }*/

    @Test
    void creditAccountShouldReturnTrue() {
        AccountUser accountUser1 = new AccountUser();
        accountUser1.setId(1);
        accountUser1.setBalance(1000.00);
        Operation operation = new Operation(1, null, 100.00, 0.0, "PAYMENT", "C", "Test", null, null);
        //
        Mockito.when(accountUserDao.getByUserId(1)).thenReturn(accountUser1);
        Mockito.when(operationDao.createOperation(operation)).thenReturn(1);
        Mockito.when(accountDao.updateBalance(accountUser1.getId(), 110.00)).thenReturn(1);
        //
        ServiceResponse serviceResponse = mainService.creditAccount(1, 10.00, "Test");
        assertTrue(serviceResponse.isResult() == true);
    }



}
