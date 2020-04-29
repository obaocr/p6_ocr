package com.mybuddy.pay.model;

import java.util.Date;

public class Operation {
    private long id;
    private long accountId;
    private Long beneficiaryId;
    private double amount;
    private double fee;
    private Date operationDate;
    private String type;
    private String flow;
    private String description;
    private String bicBenef;
    private String ibanBenef;
    private Date createDate;
    private Date updateDate;

    public Operation() {

    }

    public Operation(long accountId, Long beneficiaryId, double amount, double fee, String type, String flow, String description, String bicBenef, String ibanBenef) {
        this.accountId = accountId;
        this.beneficiaryId = beneficiaryId;
        this.amount = amount;
        this.fee = fee;
        this.type = type;
        this.flow = flow;
        this.description = description;
        this.bicBenef = bicBenef;
        this.ibanBenef = ibanBenef;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBicBenef() {
        return bicBenef;
    }

    public void setBicBenef(String bicBenef) {
        this.bicBenef = bicBenef;
    }

    public String getIbanBenef() {
        return ibanBenef;
    }

    public void setIbanBenef(String ibanBenef) {
        this.ibanBenef = ibanBenef;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}
