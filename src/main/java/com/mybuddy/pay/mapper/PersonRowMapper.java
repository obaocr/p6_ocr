package com.mybuddy.pay.mapper;

import com.mybuddy.pay.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getLong("ID"));
        person.setFirstName(rs.getString("FIRSTNAME"));
        person.setLastName(rs.getString("LASTNAME"));
        return person;
    }
}
