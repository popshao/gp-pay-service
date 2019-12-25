package com.mph.coreapi.gp.pay.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-28 0028
 * @Desc:银行卡信息表
 * @Version: 1.0
 */
public class GroundPromotionBankInfo implements Serializable {
    /**
     * 表主键
     * */
    private Integer id;
    /**
     * 地推人员的id
     * */
    private Integer userId;
    /**
     * 持卡人身份证号
     * */
    private String identityCardNo;
    /**
     * 银行卡号
     * */
    private String bankCardNo;
    /**
     * 开户行
     * */
    private String bankName;
    /**
     * 银行编号
     * */
    private String bankCode;
    /**
     * 支行名称
     * */
    private String branchName;
    /**
     * 省编号
     * */
    private String provinceCode;
    /**
     * 市编号
     * */
    private String cityCode;
    /**
     * 0 未删除 1已删除
     * */
    private Integer deleted;

    private Date createTime;

    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
