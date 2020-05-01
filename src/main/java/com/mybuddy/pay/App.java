package com.mybuddy.pay;

import com.mybuddy.pay.dao.AccountUserDao;
import com.mybuddy.pay.dao.RelationDao;
import com.mybuddy.pay.model.ServiceResponse;
import com.mybuddy.pay.model.User;
import com.mybuddy.pay.service.MainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * App Main  class for Pay myBuddy application
 */

public class App {
    public static void main(String[] args) {
        // TODO : exceptions custo, pour fons insuffisants
        // TODO Gestion transaction / rollback à tester
        // TODO Test + H2 + Test des exceptions
        // TODO Gestion throw qd il le faut, avec gestion transaction pour rollback
        // TODO JAVADOC
        // TODO SPOTBUG à vérifier
        // TODO Readme
        // TODO probleme de log4j2 ?
        Logger log = LogManager.getLogger(App.class);
        log.info("Application Main");

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MainService mainService = context.getBean(MainService.class);

        User user = mainService.login("test1@gmail.com", "A123");
        if (user != null) {
            System.out.println("login OK :" + user.getId());
        } else {
            System.out.println("login KO ");
        }

        try {
            ServiceResponse serviceResponse = mainService.addRelationByEmail(user.getId(), "test2@gmail.com");
            System.out.println("Main => Insert relation = " + serviceResponse.isResult() + "/" + serviceResponse.getMessage());

            ServiceResponse serviceResponse2 = mainService.creditAccount(user.getId(), 100.00, "Test 1");
            System.out.println("Main => versement = " + serviceResponse2.isResult() + "/" + serviceResponse2.getMessage());
            serviceResponse2 = mainService.externalTransferAccount(user.getId(), 1000.00, "Test 2");
            System.out.println("Main => virement = " + serviceResponse2.isResult() + "/" + serviceResponse2.getMessage());
            serviceResponse2 = mainService.externalTransferAccount(user.getId(), 25.00, "Test 3");
            System.out.println("Main => virement = " + serviceResponse2.isResult() + "/" + serviceResponse2.getMessage());

            ServiceResponse serviceResponse3 = mainService.transferToAnotherAccount(user.getId(), "test2@gmail.com", 10.00, "Test 4");
            System.out.println("Main => transfert = " + serviceResponse3.isResult() + "/" + serviceResponse3.getMessage());

        } catch (Exception e) {
            System.out.println("==> !!!! Main.....Exception " + e.toString());
        }


    }

}