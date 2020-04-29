package com.mybuddy.pay.dao;

import com.mybuddy.pay.mapper.RateFeeRowMapper;
import com.mybuddy.pay.model.RateFee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RateDaoImpl implements RateDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LogManager.getLogger(RelationDaoImpl.class);
    private final String GET_RATE = "SELECT ID,RATE_CODE,RATE FROM P6_RATE WHERE RATE_CODE = ?";

    @Override
    public RateFee getRate(String rateCode) {

        log.info("getRate");
        try {
            RateFee rateFee = jdbcTemplate.queryForObject(
                    GET_RATE, new Object[]{rateCode}, new RateFeeRowMapper());
            return rateFee;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
