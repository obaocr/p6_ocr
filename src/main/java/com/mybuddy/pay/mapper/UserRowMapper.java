package com.mybuddy.pay.mapper;

import com.mybuddy.pay.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class UserRowMapper for JDBC read
 */

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("ID"));
        user.setEmail(rs.getString("EMAIL"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setFirstName(rs.getString("FIRSTNAME"));
        user.setLastName(rs.getString("LASTNAME"));
        return user;
    }
}
