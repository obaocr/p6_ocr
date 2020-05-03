package com.mybuddy.pay.dao;

import com.mybuddy.pay.model.Operation;

import java.util.List;

/**
 * Interface OperationDao
 */

public interface OperationDao {
    public int createOperation(Operation operation);
    public List<Operation> getOperationForBilling();
}
