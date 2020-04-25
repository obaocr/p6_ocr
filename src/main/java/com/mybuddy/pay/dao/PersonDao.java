package com.mybuddy.pay.dao;

import com.mybuddy.pay.model.Person;

import java.util.List;

public interface PersonDao {
    public Person getByEmail(String email);
}
