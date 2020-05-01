package com.mybuddy.pay.service;

import com.mybuddy.pay.model.ServiceResponse;
import com.mybuddy.pay.model.User;

/**
 * Interface MainService
 */

public interface MainService {
    public User login(String email, String password);
    public ServiceResponse addRelationByEmail(long userId, String relationEmail) throws Exception;
    public ServiceResponse creditAccount(long userId, Double amount, String description) throws Exception;
    public ServiceResponse externalTransferAccount(long userId, Double amount, String description) throws Exception;
    public ServiceResponse transferToAnotherAccount(long userId, String beneficiaryEmail, Double amount, String description) throws Exception;
}
