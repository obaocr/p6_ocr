package com.mybuddy.pay.service;

import com.mybuddy.pay.dao.*;
import com.mybuddy.pay.model.AccountUser;
import com.mybuddy.pay.model.Operation;
import com.mybuddy.pay.model.ServiceResponse;
import com.mybuddy.pay.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainServiceTest {

    @InjectMocks
    private MainServiceImpl mainServiceImpl;

    @Mock
    private static AccountUserDao accountUserDao;

    @Mock
    private static UserDao userDao;

    @Mock
    private static OperationDao operationDao;

    @Mock
    private static AccountDao accountDao;

    @Mock
    private static RelationDao relationDao;

    @Test
    void addRelationByEmailShouldReturnTrue() {
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("test1@gmail.com");
        user1.setLastName("Martin");
        user1.setFirstName("Olivier");
        user1.setPassword("A123");
        when(userDao.getByEmail(anyString())).thenReturn(user1);
        when(relationDao.countRelationByEmail(anyLong(), anyString())).thenReturn(0);
        when(relationDao.addRelationById(anyLong(),anyLong())).thenReturn(1);
        ServiceResponse serviceResponse = mainServiceImpl.addRelationByEmail(1, "test@gmail.com");
        assertTrue(serviceResponse.isResult() == true);
    }

    @Test
    void addRelationByEmailShouldReturnFalse() {
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("test1@gmail.com");
        user1.setLastName("Martin");
        user1.setFirstName("Olivier");
        user1.setPassword("A123");
        when(userDao.getByEmail(anyString())).thenReturn(user1);
        when(relationDao.countRelationByEmail(anyLong(), anyString())).thenReturn(1);
        ServiceResponse serviceResponse = mainServiceImpl.addRelationByEmail(1, "test@gmail.com");
        assertTrue(serviceResponse.isResult() == false);
        assertTrue(serviceResponse.getMessage().contains("Relation already exists for"));
    }

    @Test
    void creditAccountShouldReturnTrue() {
            AccountUser accountUser1 = new AccountUser();
            accountUser1.setId(1);
            accountUser1.setBalance(100.00);
            //
            when(accountUserDao.getByUserId(accountUser1.getId())).thenReturn(accountUser1);
            when(operationDao.createOperation(any(Operation.class))).thenReturn(1);
            when(accountDao.updateBalance(anyLong(), anyDouble())).thenReturn(1);
            //
            ServiceResponse serviceResponse = mainServiceImpl.creditAccount(1, 10.00, "Test");
            assertTrue(serviceResponse.isResult() == true);
    }

    @Test
    void externalTransferAccountShouldReturnFalse() {
        AccountUser accountUser1 = new AccountUser();
        accountUser1.setId(1);
        accountUser1.setBalance(100.00);
        //
        when(accountUserDao.getByUserId(accountUser1.getId())).thenReturn(accountUser1);
        //
        ServiceResponse serviceResponse = mainServiceImpl.externalTransferAccount(1, 1000.00, "Test fond insuffisant");
        assertTrue(serviceResponse.isResult() == false);
        assertTrue(serviceResponse.getMessage().contains("Operation impossible, insufficient fund"));
    }

}
