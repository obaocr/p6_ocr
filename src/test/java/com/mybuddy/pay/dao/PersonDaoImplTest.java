package com.mybuddy.pay.dao;

import com.mybuddy.pay.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDaoImplTest {

    @Test
    void getByEmail() {
        PersonDao personDao = new PersonDaoImpl();
        Person person = personDao.getByEmail("test1@gmail.com");
        System.out.println("person.getLastName(): "+ person.getLastName());
        assertTrue(person.getLastName() == "Martin");
    }

}