package com.mybuddy.pay.dao;

import com.mybuddy.pay.model.Relation;
import com.mybuddy.pay.model.RelationEmail;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Interface RelationDao
 */

public interface RelationDao {
        public Integer countRelationByEmail(long userId, String emailRelation);
        public Integer addRelationById(long userId, long relationId);
        public List<RelationEmail> getRelations(long userId);
}
