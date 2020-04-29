package com.mybuddy.pay.dao;

import com.mybuddy.pay.model.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OperationDaoImpl implements OperationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LogManager.getLogger(OperationDaoImpl.class);
    private  final  String INS_CREDIT_OPERATION = "INSERT INTO P6_OPERATION (ACCOUNT_ID,AMOUNT,FEE,TYPE,FLOW,DESCRIPTION,BIC_BENEF,IBAN_BENEF,BENEFICIARY_ID) VALUES (?,?,?,?,?,?,?,?,?)";

    public int createOperation(Operation operation) {

        log.info("createOperation");
        try {
            return jdbcTemplate.update(INS_CREDIT_OPERATION, operation.getAccountId(), operation.getAmount(), operation.getFee()
                    , operation.getType(), operation.getFlow() , operation.getDescription(),operation.getBicBenef(),operation.getIbanBenef(),operation.getBeneficiaryId());
        } catch (Exception e) {
            log.error("Error create operation  :" + e.toString());
            return 0;
        }
    }
}
