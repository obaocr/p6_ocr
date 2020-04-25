package com.mybuddy.pay.dao;

import com.mybuddy.pay.model.AccountPerson;

import java.util.List;

public interface AccountPersonDao {
    public AccountPerson getByEmail(String email);
    public List<AccountPerson> getAll();
    public int getCount();
}
