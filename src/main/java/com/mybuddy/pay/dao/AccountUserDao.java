package com.mybuddy.pay.dao;

import com.mybuddy.pay.model.AccountUser;

/**
 * Interface AccountUserDao
 */

import java.util.List;

public interface AccountUserDao {
    public AccountUser getByEmail(String email);
    public AccountUser getByUserId(long userId);
    public List<AccountUser> getAll();
    public Integer getCount();
}
