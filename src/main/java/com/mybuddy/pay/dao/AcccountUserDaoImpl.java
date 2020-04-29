package com.mybuddy.pay.dao;

import com.mybuddy.pay.mapper.AccountUserRowMapper;
import com.mybuddy.pay.model.AccountUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AcccountUserDaoImpl implements AccountUserDao {

    private static final Logger log = LogManager.getLogger(AcccountUserDaoImpl.class);

    private final String GET_ACC_BY_EMAIL = "SELECT T2.ID,T2.PERSON_ID,T2.ACCOUNT_NUMBER,T2.BALANCE,T2.PERSON_ID,T1.EMAIL,PERSON_ID,T1.LASTNAME,T1.FIRSTNAME,T1.IBAN,T1.BIC" +
            " FROM P6_PERSON T1 JOIN P6_ACCOUNT T2 ON T2.PERSON_ID = T1.ID  WHERE T1.EMAIL = ?";

    private final String GET_ACC_BY_ID = "SELECT T2.ID,T2.PERSON_ID,T2.ACCOUNT_NUMBER,T2.BALANCE,T2.PERSON_ID,T1.EMAIL,PERSON_ID,T1.LASTNAME,T1.FIRSTNAME,T1.IBAN,T1.BIC" +
            " FROM P6_PERSON T1 JOIN P6_ACCOUNT T2 ON T2.PERSON_ID = T1.ID  WHERE T1.ID = ?";

    private final String GET_ACC = "SELECT T2.ID,T2.PERSON_ID,T2.ACCOUNT_NUMBER,T2.BALANCE,T2.PERSON_ID,T1.EMAIL,PERSON_ID,T1.LASTNAME,T1.FIRSTNAME,T1.IBAN,T1.BIC" +
            " FROM P6_PERSON T1 " +
            " JOIN P6_ACCOUNT T2 ON T2.PERSON_ID = T1.ID";

    private final String GET_ACC_COUNT = "SELECT count(*) FROM P6_PERSON T1 JOIN P6_ACCOUNT T2 ON T2.PERSON_ID = T1.ID";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public AccountUser getByEmail(String email) {
        log.info("getByEmail");
        try {
            AccountUser accountUser = jdbcTemplate.queryForObject(
                    GET_ACC_BY_EMAIL, new Object[]{email}, new AccountUserRowMapper());
            return accountUser;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public AccountUser getByUserId(long userId) {
        log.info("getByUserId");
        try {
            AccountUser accountUser = jdbcTemplate.queryForObject(
                    GET_ACC_BY_ID, new Object[]{userId}, new AccountUserRowMapper());
            return accountUser;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<AccountUser> getAll() {
        log.info("getAll");
        try {
            List<AccountUser> accountUsers = jdbcTemplate.query(
                    GET_ACC, new AccountUserRowMapper());
            return accountUsers;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int getCount() {
        log.info("getCount");
        return jdbcTemplate.queryForObject(GET_ACC_COUNT, Integer.class);

    }

}

