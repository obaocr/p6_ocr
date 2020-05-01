package com.mybuddy.pay.mapper;

import com.mybuddy.pay.model.RelationEmail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RelationEmailRowMapper implements RowMapper<RelationEmail> {

    @Override
    public RelationEmail mapRow(ResultSet rs, int rowNum) throws SQLException {
        RelationEmail relationEmail = new RelationEmail();
        relationEmail.setRelationId(rs.getLong("ID"));
        relationEmail.setEmailRelation(rs.getString("EMAIL"));
        return relationEmail;
    }
}