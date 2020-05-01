package com.mybuddy.pay.mapper;

import com.mybuddy.pay.model.RateFee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class RateFeeRowMapper for JDBC read
 */

public class RateFeeRowMapper implements RowMapper<RateFee> {

    @Override
    public RateFee mapRow(ResultSet rs, int rowNum) throws SQLException {
        RateFee rateFee = new RateFee();
        rateFee.setId(rs.getLong("ID"));
        rateFee.setRate(rs.getDouble("RATE"));
        return rateFee;
    }
}
