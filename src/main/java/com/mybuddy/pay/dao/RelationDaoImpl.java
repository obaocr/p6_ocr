package com.mybuddy.pay.dao;

import com.mybuddy.pay.constants.Query;
import com.mybuddy.pay.mapper.RelationEmailRowMapper;
import com.mybuddy.pay.model.RelationEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RelationDaoImpl implements RelationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LogManager.getLogger(RelationDaoImpl.class);

    public Integer countRelationByEmail(long userId, String emailRelation) {
        return jdbcTemplate.queryForObject(
                Query.GET_REL_COUNT_BY_EMAIL, new Object[]{userId, emailRelation}, Integer.class);
    }

    public Integer addRelationById(long userId, long relationId) {
        return jdbcTemplate.update(Query.INS_REL, userId, relationId);
    }

    public List<RelationEmail> getRelations(long userId) {
        List<RelationEmail> relationEmails = jdbcTemplate.query(Query.GET_RELATIONS, new Object[]{userId}, new RelationEmailRowMapper());
        return relationEmails;
    }
}
