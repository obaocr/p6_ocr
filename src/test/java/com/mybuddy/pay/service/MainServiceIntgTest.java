package com.mybuddy.pay.service;

import com.mybuddy.pay.AppConfigTest;
import com.mybuddy.pay.constants.Message;
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
        try {
            User user = mainService.login("test1@gmail.com", "A123");
            ServiceResponse serviceResponse = mainService.addRelationByEmail(user.getId(), "test2@gmail.com");
            assertTrue(serviceResponse.isResult() == true);
        } catch (Exception e) {

        }
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
        try {
            User user = mainService.login("test1@gmail.com", "A123");
            ServiceResponse serviceResponse = mainService.creditAccount(user.getId(), 888.00, "test creditAcccount");
            assertTrue(serviceResponse.isResult() == true);
        } catch (Exception e) {

        }
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
        try {
            User user = mainService.login("test6@gmail.com", "A123");
            ServiceResponse serviceResponse = mainService.externalTransferAccount(user.getId(), 1000.00, "test externalTransferAccount");
            assertTrue(serviceResponse.isResult() == true);

        } catch (Exception e) {

        }
    }

    @Rollback
    @Test
    void externalTransferAccountInsufficientFund() {
        try {
            User user = mainService.login("test1@gmail.com", "A123");
            ServiceResponse serviceResponse = mainService.externalTransferAccount(user.getId(), 1000.00, "test externalTransferAccount");
            assertTrue(serviceResponse.isResult() == false);
            assertTrue(serviceResponse.getMessage().contains(Message.MSG_ERR_002));

        } catch (Exception e) {

        }
    }

    @Rollback
    @Test
    void externalTransferAccountShouldReturnException() {
        try {
            ServiceResponse serviceResponse = mainService.externalTransferAccount(0, 1000.00, "test externalTransferAccount");
        } catch (Exception e) {
            assertTrue(e.getMessage() != null);
        }
    }

    @Rollback
    @Test
    void transferToAnotherAccount() {
        try {
            User user = mainService.login("test1@gmail.com", "A123");
            ServiceResponse serviceResponse1 = mainService.creditAccount(user.getId(), 888.00, "test creditAcccount");
            ServiceResponse serviceResponse2 = mainService.addRelationByEmail(user.getId(), "test2@gmail.com");
            ServiceResponse serviceResponse3 = mainService.transferToAnotherAccount(user.getId(), "test2@gmail.com", 100.00, "test externalTransferAccount");
            System.out.println("serviceResponse2.getMessage():" + serviceResponse3.getMessage());
            assertTrue(serviceResponse3.isResult() == true);
            assertTrue(serviceResponse3.getMessage().contains(Message.MSG_INFO_004));
        } catch (Exception e) {

        }
    }

    @Rollback
    @Test
    void transferToAnotherAccountKORelationNotExists() {
        try {
            User user = mainService.login("test1@gmail.com", "A123");
            ServiceResponse serviceResponse1 = mainService.creditAccount(user.getId(), 888.00, "test creditAcccount");
            ServiceResponse serviceResponse3 = mainService.transferToAnotherAccount(user.getId(), "test2@gmail.com", 100.00, "test externalTransferAccount");
            System.out.println("serviceResponse2.getMessage():" + serviceResponse3.getMessage());
            assertTrue(serviceResponse3.isResult() == false);
            assertTrue(serviceResponse3.getMessage().contains(Message.MSG_ERR_003));
        } catch (Exception e) {

        }
    }

    @Rollback
    @Test
    void transferToAnotherAccountKOInsufficientFund() {
        try {
            User user = mainService.login("test1@gmail.com", "A123");
            ServiceResponse serviceResponse1 = mainService.creditAccount(user.getId(), 10.00, "test creditAcccount");
            ServiceResponse serviceResponse3 = mainService.transferToAnotherAccount(user.getId(), "test2@gmail.com", 100.00, "test externalTransferAccount");
            System.out.println("serviceResponse2.getMessage():" + serviceResponse3.getMessage());
            assertTrue(serviceResponse3.isResult() == false);
            assertTrue(serviceResponse3.getMessage().contains(Message.MSG_ERR_002));
        } catch (Exception e) {

        }
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
