package com.mph.coreapi.gp.pay.entity;

import java.util.Date;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-06-19 0019
 * @Version: 1.0
 */
public class VipIncomeImport {
    private Integer eId;
    private Double  amount;
    private Integer userId;
    private String  userFullName;
    private Integer provinceId;
    private Date    premiumEnd;
    private Integer premiumType;
    private String oSn;
    private Date premiumOpenTime;
    private String userMobile;
    private Integer userJobType;
    private String oEName;
    private Integer operUserId;
    private String operUserName;

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Date getPremiumEnd() {
        return premiumEnd;
    }

    public void setPremiumEnd(Date premiumEnd) {
        this.premiumEnd = premiumEnd;
    }

    public Integer getPremiumType() {
        return premiumType;
    }

    public void setPremiumType(Integer premiumType) {
        this.premiumType = premiumType;
    }

    public String getoSn() {
        return oSn;
    }

    public void setoSn(String oSn) {
        this.oSn = oSn;
    }

    public Date getPremiumOpenTime() {
        return premiumOpenTime;
    }

    public void setPremiumOpenTime(Date premiumOpenTime) {
        this.premiumOpenTime = premiumOpenTime;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Integer getUserJobType() {
        return userJobType;
    }

    public void setUserJobType(Integer userJobType) {
        this.userJobType = userJobType;
    }

    public String getoEName() {
        return oEName;
    }

    public void setoEName(String oEName) {
        this.oEName = oEName;
    }

    public Integer getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Integer operUserId) {
        this.operUserId = operUserId;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }
}

