package com.mybuddy.pay.dao;

import com.mybuddy.pay.constants.Query;
import com.mybuddy.pay.mapper.UserRowMapper;
import com.mybuddy.pay.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Class UserDaoImpl
 */

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail");
        try {
            User user = jdbcTemplate.queryForObject(
                    Query.GET_BY_EMAIL, new Object[]{email}, new UserRowMapper());
            return user;
        } catch (EmptyResultDataAccessException e) {
            log.info("getByEmail : EmptyResultDataAccessException");
            throw e;
        }
    }
}
