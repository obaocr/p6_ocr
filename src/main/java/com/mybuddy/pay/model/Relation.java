package com.mybuddy.pay.model;

import java.util.Date;

/**
 * Relation model
 */

public class Relation {
    private long id;
    private long userId;
    private long relationId;
    private Date createDate;
    private Date updateDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRelationId() {
        return relationId;
    }

    public void setRelationId(long relationId) {
        this.relationId = relationId;
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
