package com.mybuddy.pay.dao;

import com.mybuddy.pay.model.User;

public interface UserDao {
    public User getByEmail(String email);
}
