package com.mybuddy.pay.dao;

import com.mybuddy.pay.mapper.PersonRowMapper;
import com.mybuddy.pay.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao {

    private static final Logger log = LogManager.getLogger(PersonDaoImpl.class);
    private final String GET_BY_EMAIL = "select ID,EMAIL,LASTNAME,FIRSTNAME from P6_PERSON where email = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Person getByEmail(String email) {
        log.info("getByEmail");
        try {
            Person person = jdbcTemplate.queryForObject(
                    GET_BY_EMAIL, new Object[]{email}, new PersonRowMapper());
            return person;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
