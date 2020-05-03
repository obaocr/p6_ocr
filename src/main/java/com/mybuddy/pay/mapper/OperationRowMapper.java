package com.mybuddy.pay.mapper;

import com.mybuddy.pay.model.Operation;
import com.mybuddy.pay.model.RelationEmail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OperationRowMapper implements RowMapper<Operation> {

    @Override
    public Operation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Operation operation = new Operation();
        operation.setId(rs.getLong("ID"));
        operation.setAccountId(rs.getLong("ACCOUNT_ID"));
        operation.setBeneficiaryId(rs.getLong("BENEFICIARY_ID"));
        operation.setAmount(rs.getDouble("AMOUNT"));
        operation.setFee(rs.getDouble("FEE"));
        operation.setType(rs.getString("TYPE"));
        operation.setFlow(rs.getString("FLOW"));
        operation.setDescription(rs.getString("DESCRIPTION"));
        operation.setBicBenef(rs.getString("BIC_BENEF"));
        operation.setIbanBenef(rs.getString("IBAN_BENEF"));
        return operation;
    }
}

