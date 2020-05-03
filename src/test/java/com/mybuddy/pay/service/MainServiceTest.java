package com.mybuddy.pay.service;

import com.mybuddy.pay.dao.AccountDao;
import com.mybuddy.pay.dao.AccountUserDao;
import com.mybuddy.pay.dao.OperationDao;
import com.mybuddy.pay.model.AccountUser;
import com.mybuddy.pay.model.Operation;
import com.mybuddy.pay.model.ServiceResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainServiceTest {

    @Autowired
    MainService mainService;

    @Mock
    private static AccountUserDao accountUserDao;

    @Mock
    private static OperationDao operationDao;

    @Mock
    private static AccountDao accountDao;

    @Test
    void creditAccount() {
        try {
            AccountUser accountUser1 = new AccountUser();
            accountUser1.setId(1);
            accountUser1.setBalance(1000.00);
            Mockito.when(accountUserDao.getByUserId(1)).thenReturn(accountUser1);
            //
            Operation operation = new Operation(1, null, 100.00, 0.0, "PAYMENT", "C", "Test", null, null);
            Mockito.when(operationDao.createOperation(operation)).thenReturn(1);
            //
            long id=1;
            Mockito.when(accountDao.updateBalance(id,110.00)).thenReturn(1);
            //
            ServiceResponse serviceResponse = mainService.creditAccount(1, 10.00, "Test");
            assertTrue(serviceResponse.isResult() == true);
        } catch (Exception e) {

        }
    }
}
