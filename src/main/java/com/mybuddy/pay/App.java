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

public class App {
    public static void main(String[] args) {

        // TODO tracer les mises  à jour avec le UserId ..ajout UserId dans les tables en mise  à jour
        // TODO Password de la BDD en sécurisé ... à voir ... env ou vault ?
        // TODO Gestion transaction / rollback
        // TODO  !!! refacto code
        // TODO  H2 : mettre les FK
        // TODO Test + H2 + Test des exceptions
        // TODO Gestion code+ message au lieu de mesage en dur
        // TODO Gestion throw qd il le faut, avec gestion transaction pour rollback
        // TODO Classe pour calcul de la commission
        // TODO Accès BDD sécuris&é
        // TODO JAVADOC
        // TODO SPOTBUG à vérifier
        Logger log = LogManager.getLogger(App.class);
        log.info("Application Main");

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        AccountUserDao accountUserDao = context.getBean(AccountUserDao.class);
        RelationDao relationDao = context.getBean(RelationDao.class);
        MainService mainService = context.getBean(MainService.class);

        User user = mainService.login("test1@gmail.com","A123");
        if(user != null) {
            System.out.println("login OK :" + user.getId());
        } else {
            System.out.println("login KO ");
        }
        //
        System.out.println("Main => nb acc = " + accountUserDao.getCount());
        System.out.println("Main => nb list acc = " + accountUserDao.getAll().size());
        System.out.println("Main => nb relation 1  = " + relationDao.countRelationByEmail(user.getId(),"test2@gmail.com"));
        //
        ServiceResponse serviceResponse = mainService.addRelationByEmail(user.getId(),"test2@gmail.com");
        System.out.println("Main => Insert relation = " + serviceResponse.isResult() + "/" + serviceResponse.getMessage());
        //
        ServiceResponse serviceResponse2 = mainService.creditAccount(user.getId(),100.00,"Test 1");
        System.out.println("Main => versement = " + serviceResponse2.isResult() + "/" + serviceResponse2.getMessage());
        serviceResponse2 = mainService.externalTransferAccount(user.getId(),1000.00,"Test 2");
        System.out.println("Main => virement = " + serviceResponse2.isResult() + "/" + serviceResponse2.getMessage());
        serviceResponse2 = mainService.externalTransferAccount(user.getId(),25.00,"Test 3");
        System.out.println("Main => virement = " + serviceResponse2.isResult() + "/" + serviceResponse2.getMessage());
        //
        ServiceResponse serviceResponse3 = mainService.transferToAnotherAccount(user.getId(),"test2@gmail.com",10.00,"Test 4");
        System.out.println("Main => transfert = " + serviceResponse3.isResult() + "/" + serviceResponse3.getMessage());
    }

}