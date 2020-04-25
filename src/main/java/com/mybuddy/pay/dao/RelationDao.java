package com.mybuddy.pay.dao;

import java.sql.SQLDataException;
import java.sql.SQLException;

public interface RelationDao {
        public int countRelationByEmail(String emailAccount, String emailRelation);
        public int addRelationById(long PersonId, long relationId);
}
