package com.mybuddy.pay.dao;

import com.mybuddy.pay.constants.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Class AcccountDaoImpl for Banking Account
 */

@Repository
public class AcccountDaoImpl implements  AccountDao {

    private static final Logger log = LogManager.getLogger(AcccountDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Integer updateBalance (Long Id, Double amount) throws SQLException {
            log.info("updateBalance");
            Integer nbUpd = jdbcTemplate.update(Query.UPD_ACC_BALANCE, amount, Id);
            if(nbUpd == 0 ) {
                log.error("updateBalance : no row updated !");
                throw new SQLException("updateBalance no row affected");
            }
            return nbUpd;
    }
}
