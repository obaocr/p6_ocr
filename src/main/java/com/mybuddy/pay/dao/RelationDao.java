package com.mybuddy.pay.dao;

import java.sql.SQLDataException;
import java.sql.SQLException;

public interface RelationDao {
        public int countRelationByEmail(long userId, String emailRelation);
        public int addRelationById(long PersonId, long relationId);
}
