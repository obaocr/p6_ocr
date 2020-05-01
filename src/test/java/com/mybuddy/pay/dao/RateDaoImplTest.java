package com.mybuddy.pay.dao;

import com.mybuddy.pay.AppConfigTest;
import com.mybuddy.pay.model.AccountUser;
import com.mybuddy.pay.model.RateFee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(AppConfigTest.class)
@Transactional
public class RateDaoImplTest {

    @Autowired
    RateDao rateDao;

    @Test
    @Rollback
    void getRate() {
        RateFee rateFee = rateDao.getRate("RTFEE");
        assertTrue(rateFee != null);
        assertTrue(rateFee.getRate() > 0.0);
    }


    @Test
    @Rollback
    void getRateShouldReturnException() {
        try {
            RateFee rateFee = rateDao.getRate("x");
        } catch (EmptyResultDataAccessException e) {
            assertTrue(e.getMessage() != null);
        }
    }

}
