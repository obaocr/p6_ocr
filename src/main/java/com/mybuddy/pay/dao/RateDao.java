package com.mybuddy.pay.dao;

import com.mybuddy.pay.model.RateFee;

/**
 * Interface RateDao
 */

public interface RateDao {
    public RateFee getRate (String rateCode);
}
