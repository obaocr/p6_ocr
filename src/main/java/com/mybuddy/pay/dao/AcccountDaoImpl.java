package com.mybuddy.pay.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AcccountDaoImpl implements  AccountDao {

    private static final Logger log = LogManager.getLogger(AcccountUserDaoImpl.class);
    // TODO mettre jour date / time en local ...
    private final String UPD_ACC_BALANCE = "UPDATE P6_ACCOUNT SET UPDATE_DATE = SYSDATE, BALANCE = ? WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int updateBalance (Long Id, Double amount) {
        try {
            log.info("updateBalance");
            return jdbcTemplate.update(UPD_ACC_BALANCE, amount, Id);
        } catch (Exception e) {
            log.error("Erreur update acccount balance :" + e.toString());
            return 0;
        }
    }
}
