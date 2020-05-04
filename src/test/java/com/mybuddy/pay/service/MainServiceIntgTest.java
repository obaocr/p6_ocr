package com.mybuddy.pay.service;

import com.mybuddy.pay.AppConfigTest;
import com.mybuddy.pay.constants.Message;
import com.mybuddy.pay.dao.AccountUserDao;
import com.mybuddy.pay.dao.OperationDao;
import com.mybuddy.pay.model.ServiceResponse;
import com.mybuddy.pay.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(AppConfigTest.class)
@Transactional
class MainServiceIntgTest {

    @Autowired
    MainService mainService;

    @Autowired
    OperationDao operationDao;

    @Autowired
    AccountUserDao accountUserDao;

    @Rollback
    @Test
    void login() {
        assertTrue(mainService.login("test1@gmail.com", "A123").getLastName().equals("Martin"));
    }

    @Rollback
    @Test
    void loginShouldReturnNull() {
        assertTrue(mainService.login("test1@gmail.com", "**") == null);
    }

    @Rollback
    @Test
    void addRelationByEmail() {
        User user = mainService.login("test1@gmail.com", "A123");
        ServiceResponse serviceResponse = mainService.addRelationByEmail(user.getId(), "test2@gmail.com");
        assertTrue(serviceResponse.isResult() == true);
    }

    @Rollback
    @Test
    void addRelationByEmailShouldReturnException() {
        try {
            ServiceResponse serviceResponse = mainService.addRelationByEmail(0, "test2@gmail.com");
        } catch (Exception e) {
            assertTrue(e.getMessage() != null);
        }
    }

    @Rollback
    @Test
    void creditAcccount() {
        User user = mainService.login("test1@gmail.com", "A123");
        ServiceResponse serviceResponse = mainService.creditAccount(user.getId(), 888.00, "test creditAcccount");
        assertTrue(serviceResponse.isResult() == true);
        assertTrue(accountUserDao.getByUserId(user.getId()).getBalance() == 888.00);

    }

    @Rollback
    @Test
    void creditAcccountShouldReturnException() {
        try {
            ServiceResponse serviceResponse = mainService.creditAccount(0, 888.00, "test creditAcccount");
        } catch (Exception e) {
            assertTrue(e.getMessage() != null);
        }
    }

    @Rollback
    @Test
    void externalTransferAccount() {
        User user = mainService.login("test6@gmail.com", "A123");
        ServiceResponse serviceResponse = mainService.externalTransferAccount(user.getId(), 1000.00, "test externalTransferAccount");
        assertTrue(serviceResponse.isResult() == true);

    }

    @Rollback
    @Test
    void externalTransferAccountInsufficientFund() {
        int nbOpe = operationDao.getOperationForBilling().size();
        User user = mainService.login("test1@gmail.com", "A123");
        Double balance = accountUserDao.getByUserId(user.getId()).getBalance();
        ServiceResponse serviceResponse = mainService.externalTransferAccount(user.getId(), 1000.00, "test externalTransferAccount");
        assertTrue(serviceResponse.isResult() == false);
        assertTrue(serviceResponse.getMessage().contains(Message.MSG_ERR_002));
        assertTrue(nbOpe == operationDao.getOperationForBilling().size());
        assertTrue(balance == accountUserDao.getByUserId(user.getId()).getBalance());

    }

    @Rollback
    @Test
    void externalTransferAccountShouldReturnException() {
        int nbOpe = operationDao.getOperationForBilling().size();
        try {
            ServiceResponse serviceResponse = mainService.externalTransferAccount(0, 1000.00, "test externalTransferAccount");
        } catch (Exception e) {
            assertTrue(e.getMessage() != null);
            assertTrue(nbOpe == operationDao.getOperationForBilling().size());
        }
    }

    @Rollback
    @Test
    void transferToAnotherAccount() {
        User user = mainService.login("test1@gmail.com", "A123");
        ServiceResponse serviceResponse1 = mainService.creditAccount(user.getId(), 888.00, "test creditAcccount");
        ServiceResponse serviceResponse2 = mainService.addRelationByEmail(user.getId(), "test2@gmail.com");
        ServiceResponse serviceResponse3 = mainService.transferToAnotherAccount(user.getId(), "test2@gmail.com", 100.00, "test externalTransferAccount");
        System.out.println("serviceResponse2.getMessage():" + serviceResponse3.getMessage());
        assertTrue(serviceResponse3.isResult() == true);
        assertTrue(serviceResponse3.getMessage().contains(Message.MSG_INFO_004));
    }

    @Rollback
    @Test
    void transferToAnotherAccountKORelationNotExists() {

        User user = mainService.login("test1@gmail.com", "A123");
        ServiceResponse serviceResponse1 = mainService.creditAccount(user.getId(), 888.00, "test creditAcccount");
        Double balance = accountUserDao.getByUserId(user.getId()).getBalance();
        ServiceResponse serviceResponse3 = mainService.transferToAnotherAccount(user.getId(), "test2@gmail.com", 100.00, "test externalTransferAccount");
        System.out.println("serviceResponse2.getMessage():" + serviceResponse3.getMessage());
        assertTrue(serviceResponse3.isResult() == false);
        assertTrue(serviceResponse3.getMessage().contains(Message.MSG_ERR_003));
        assertTrue(balance == accountUserDao.getByUserId(user.getId()).getBalance());
    }

    @Rollback
    @Test
    void transferToAnotherAccountKOInsufficientFund() {
        User user = mainService.login("test1@gmail.com", "A123");
        ServiceResponse serviceResponse1 = mainService.creditAccount(user.getId(), 10.00, "test creditAcccount");
        int nbOpe = operationDao.getOperationForBilling().size();
        Double balance = accountUserDao.getByUserId(user.getId()).getBalance();
        ServiceResponse serviceResponse3 = mainService.transferToAnotherAccount(user.getId(), "test2@gmail.com", 100.00, "test externalTransferAccount");
        System.out.println("serviceResponse2.getMessage():" + serviceResponse3.getMessage());
        assertTrue(serviceResponse3.isResult() == false);
        assertTrue(serviceResponse3.getMessage().contains(Message.MSG_ERR_002));
        System.out.println("nb..." + nbOpe + "/" + operationDao.getOperationForBilling().size());
        assertTrue(nbOpe == operationDao.getOperationForBilling().size());
        assertTrue(balance == accountUserDao.getByUserId(user.getId()).getBalance());
    }

    @Rollback
    @Test
    void transferToAnotherAccountShouldReturnException() {
        try {
            ServiceResponse serviceResponse = mainService.transferToAnotherAccount(0, "test2@gmail.com", 100.00, "test externalTransferAccount");
        } catch (Exception e) {
            assertTrue(e.getMessage() != null);
        }
    }

}
