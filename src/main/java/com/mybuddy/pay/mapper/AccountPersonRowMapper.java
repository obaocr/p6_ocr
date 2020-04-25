package com.mybuddy.pay.mapper;

import com.mybuddy.pay.model.AccountPerson;
import com.mybuddy.pay.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountPersonRowMapper  implements RowMapper<AccountPerson>  {

    @Override
    public AccountPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountPerson accountPerson = new AccountPerson();
        accountPerson.setId(rs.getLong("ID"));
        accountPerson.setPersonId(rs.getLong("PERSON_ID"));
        accountPerson.setAccountNumber(rs.getString("ACCOUNT_NUMBER"));
        accountPerson.setBalance(rs.getLong("BALANCE"));
        accountPerson.setFirstName(rs.getString("FIRSTNAME"));
        accountPerson.setLastName(rs.getString("LASTNAME"));
        accountPerson.setEmail(rs.getString("EMAIL"));
        return accountPerson;
    }
}
