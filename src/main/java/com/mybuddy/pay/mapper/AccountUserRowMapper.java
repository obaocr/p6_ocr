package com.mybuddy.pay.mapper;

import com.mybuddy.pay.model.AccountUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountUserRowMapper implements RowMapper<AccountUser>  {

    @Override
    public AccountUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountUser accountUser = new AccountUser();
        accountUser.setId(rs.getLong("ID"));
        accountUser.setPersonId(rs.getLong("PERSON_ID"));
        accountUser.setAccountNumber(rs.getString("ACCOUNT_NUMBER"));
        accountUser.setBalance(rs.getLong("BALANCE"));
        accountUser.setFirstName(rs.getString("FIRSTNAME"));
        accountUser.setLastName(rs.getString("LASTNAME"));
        accountUser.setEmail(rs.getString("EMAIL"));
        accountUser.setBic(rs.getString("BIC"));
        accountUser.setIban(rs.getString("IBAN"));
        return accountUser;
    }
}
