package com.mybuddy.pay.model;

import java.util.Date;

/**
 * RateFee model
 */

public class RateFee {
    private long id;
    private String rateCode;
    private double rate;
    private Date createDate;
    private Date updateDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRateCode() {
        return rateCode;
    }

    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
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
