package com.mybuddy.pay.dao;

import com.mybuddy.pay.constants.Query;
import com.mybuddy.pay.mapper.AccountUserRowMapper;
import com.mybuddy.pay.model.AccountUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class AcccountUserDaoImpl for User and account join
 */

@Repository
public class AcccountUserDaoImpl implements AccountUserDao {

    private static final Logger log = LogManager.getLogger(AcccountUserDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public AccountUser getByEmail(String email) {
        log.info("getByEmail");
        try {
            AccountUser accountUser = jdbcTemplate.queryForObject(
                    Query.GET_ACC_BY_EMAIL, new Object[]{email}, new AccountUserRowMapper());
            return accountUser;
        } catch (EmptyResultDataAccessException e) {
            log.info("getByEmail : EmptyResultDataAccessException");
            throw e;
        }
    }

    @Override
    public AccountUser getByUserId(long userId) {
        log.info("getByUserId");
        try {
            AccountUser accountUser = jdbcTemplate.queryForObject(
                    Query.GET_ACC_BY_ID, new Object[]{userId}, new AccountUserRowMapper());
            return accountUser;
        } catch (EmptyResultDataAccessException e) {
            log.info("getByUserId : EmptyResultDataAccessException");
            throw e;
        }
    }

    @Override
    public List<AccountUser> getAll() {
        log.info("getAll");
            List<AccountUser> accountUsers = jdbcTemplate.query(
                    Query.GET_ACC, new AccountUserRowMapper());
            return accountUsers;
    }

    @Override
    public Integer getCount() {
        log.info("getCount");
        return jdbcTemplate.queryForObject(Query.GET_ACC_COUNT, Integer.class);
    }

}

