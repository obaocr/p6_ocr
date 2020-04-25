package com.mybuddy.pay.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLException;

@Repository
public class RelationDaoImpl implements RelationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LogManager.getLogger(RelationDaoImpl.class);
    private final String GET_REL_COUNT_BY_EMAIL = "SELECT count(*) FROM P6_RELATION T1, P6_PERSON T2, P6_PERSON T3" +
            "  WHERE T1.PERSON_ID = T2.ID AND T1.RELATION_ID  = T3.ID  AND T2.EMAIL = ? AND T3.EMAIL = ?";

    public int countRelationByEmail(String emailAccount, String emailRelation) {
        return jdbcTemplate.queryForObject(
                GET_REL_COUNT_BY_EMAIL, new Object[] { emailAccount, emailRelation }, Integer.class);
    }

    public int addRelationById(long PersonId, long relationId) {
        try {
            return jdbcTemplate.update("INSERT INTO P6_RELATION (PERSON_ID, RELATION_ID) VALUES (?,?)", PersonId, relationId);
        } catch (Exception e) {
            log.error("Erreur de création relation comptes :" + e.toString());
            return 0;
        }
    }
}
