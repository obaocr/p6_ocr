package com.mybuddy.pay.model;

import java.util.Date;

/**
 * User model
 */

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String bic;
    private String iban;
    private String activeFlg;
    private Date createDate;
    private Date updateDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getActiveFlg() {
        return activeFlg;
    }

    public void setActiveFlg(String activeFlg) {
        this.activeFlg = activeFlg;
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
