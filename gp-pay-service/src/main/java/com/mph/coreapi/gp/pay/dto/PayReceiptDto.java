package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/8
 * @Version: 1.0
 */
public class PayReceiptDto implements Serializable {
    /**
     * 表主键
     * */
    private Integer id;

    /**
     * 支付单号
     * */
    private String  receiptSn;

    /**
     * 地推人员的id
     * */
    private Integer userId;

    /**
     * 提现金额
     * */
    private Double  totalAmount;

    /**
     * 税费金额
     * */
    private Double  taxAmount;

    /**
     * 税后金额
     * */
    private Double  finalAmount;

    /**
     * 0 待转账 1 转账成功 2 转账申请中 3 （财务）转账已发起，等待结果中 4 转账失败 5 转账驳回
     * */
    private Integer status;

    /**
     * 开户名
     * */
    private String accountName;

    /**
     * 银行卡号
     * */
    private String bankCardNo;
    /**
     * 银行名称
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
     * 描述
     * */
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceiptSn() {
        return receiptSn;
    }

    public void setReceiptSn(String receiptSn) {
        this.receiptSn = receiptSn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
