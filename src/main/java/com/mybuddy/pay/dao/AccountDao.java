package com.mybuddy.pay.dao;

import com.mybuddy.pay.model.Account;

public interface AccountDao {
         public int updateBalance (Long Id, Double amount);
}
