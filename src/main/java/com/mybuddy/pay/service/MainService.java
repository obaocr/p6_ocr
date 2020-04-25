package com.mybuddy.pay.service;

import com.mybuddy.pay.model.ServiceResult;

public interface MainService {
    public ServiceResult addRelationByEmail(String accountEmail, String relationEmail);
}
