package com.mybuddy.pay.dao;

import com.mybuddy.pay.model.RateFee;

public interface RateDao {
    public RateFee getRate (String rateCode);
}
