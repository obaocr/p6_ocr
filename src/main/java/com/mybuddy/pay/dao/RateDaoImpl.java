package com.mybuddy.pay.dao;

import com.mybuddy.pay.constants.Query;
import com.mybuddy.pay.mapper.RateFeeRowMapper;
import com.mybuddy.pay.model.RateFee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Class RateDaoImpl
 */

@Repository
public class RateDaoImpl implements RateDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LogManager.getLogger(RateDaoImpl.class);

    @Override
    public RateFee getRate(String rateCode) {

        log.info("getRate");
        try {
            RateFee rateFee = jdbcTemplate.queryForObject(
                    Query.GET_RATE, new Object[]{rateCode}, new RateFeeRowMapper());
            return rateFee;
        } catch (EmptyResultDataAccessException e) {
            log.error("Taux non trouvé pour : " + rateCode);
            throw e;
        }
    }
}
