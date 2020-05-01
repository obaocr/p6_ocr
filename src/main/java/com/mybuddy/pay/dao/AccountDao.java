package com.mybuddy.pay.dao;

import com.mybuddy.pay.model.Account;

import java.sql.SQLException;

/**
 * Interface AccountDao
 */

public interface AccountDao {
         public Integer updateBalance (Long Id, Double amount) throws IllegalAccessException, SQLException;
}
