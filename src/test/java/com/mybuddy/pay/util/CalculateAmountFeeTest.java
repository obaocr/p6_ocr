package com.mybuddy.pay.util;

import com.mybuddy.pay.Util.CalculateAmountFee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculateAmountFeeTest {

    @Test
    void CalculateAmountFee() {
        assertTrue(CalculateAmountFee.calculateFee(0.5, 200.0) == 1.0);
    }
}
