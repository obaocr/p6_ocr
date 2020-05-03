package com.mybuddy.pay.dao;

import com.mybuddy.pay.constants.Query;
import com.mybuddy.pay.mapper.AccountUserRowMapper;
import com.mybuddy.pay.mapper.OperationRowMapper;
import com.mybuddy.pay.model.AccountUser;
import com.mybuddy.pay.model.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Class OperationDaoImpl
 */

@Repository
public class OperationDaoImpl implements OperationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LogManager.getLogger(OperationDaoImpl.class);

    public int createOperation(Operation operation) {
        log.info("createOperation");
        return jdbcTemplate.update(Query.INS_CREDIT_OPERATION, operation.getAccountId(), operation.getAmount(), operation.getFee()
                    , operation.getType(), operation.getFlow() , operation.getDescription(),operation.getBicBenef(),operation.getIbanBenef(),operation.getBeneficiaryId());
    }

    public List<Operation> getOperationForBilling() {
        log.info("getOperationForBilling");
        List<Operation> operations = jdbcTemplate.query(
                Query.GET_OPERATION_FOR_BILLING, new OperationRowMapper());
        return operations;
    }
}
