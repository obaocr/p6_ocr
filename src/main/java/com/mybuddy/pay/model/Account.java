package com.mybuddy.pay.model;

import java.util.Date;

/**
 * Account model
 */

public class Account {

    private long id;
    private long personId;
    private String accountNumber;
    private String activeflg;
    private double balance;
    private Date createDate;
    private Date updateDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getActiveflg() {
        return activeflg;
    }

    public void setActiveflg(String activeflg) {
        this.activeflg = activeflg;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreateDate()
    {
        return createDate != null ? new Date(createDate.getTime()) : null;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate != null ? new Date(createDate.getTime()) : null;
    }

    public Date getUpdateDate()
    {
        return updateDate != null ? new Date(updateDate.getTime()) : null;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate != null ? new Date(updateDate.getTime()) : null;
    }
}
