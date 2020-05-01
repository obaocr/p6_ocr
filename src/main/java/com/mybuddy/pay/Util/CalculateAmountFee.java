package com.mybuddy.pay.Util;

import com.mybuddy.pay.service.MainServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Calculate Fee for operation
 */

public class CalculateAmountFee {

    private static final Logger log = LogManager.getLogger(MainServiceImpl.class);

    public static Double calculateFee(Double rate, Double amount) {
        double feeAmount = (amount * rate) / 100.0;
        return Math.round(feeAmount * 100) / 100.0;
    }

}
