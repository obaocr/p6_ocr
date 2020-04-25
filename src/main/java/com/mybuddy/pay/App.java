package com.mybuddy.pay;

import com.mybuddy.pay.dao.AccountPersonDao;
import com.mybuddy.pay.dao.RelationDao;
import com.mybuddy.pay.model.ServiceResult;
import com.mybuddy.pay.service.MainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        Logger log = LogManager.getLogger(App.class);
        log.info("Application Main");

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        AccountPersonDao accountPersonDao = context.getBean(AccountPersonDao.class);
        RelationDao relationDao = context.getBean(RelationDao.class);
        MainService mainService = context.getBean(MainService.class);

        System.out.println("Main => LastName pour test1@gmail.com = " + accountPersonDao.getByEmail("test1@gmail.com").getAccountNumber());
        System.out.println("Main => nb acc = " + accountPersonDao.getCount());
        System.out.println("Main => nb list acc = " + accountPersonDao.getAll().size());
        System.out.println("Main => nb relation 1  = " + relationDao.countRelationByEmail("test1@gmail.com","test2@gmail.com"));
        //
        ServiceResult serviceResult = mainService.addRelationByEmail("test1@gmail.com","test2@gmail.com");
        System.out.println("Main => Insert relation = " + serviceResult.isResult() + "/" + serviceResult.getMessage());
    }

}