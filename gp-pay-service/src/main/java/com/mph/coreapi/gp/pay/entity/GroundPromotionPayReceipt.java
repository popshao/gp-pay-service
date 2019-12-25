package com.mph.coreapi.gp.pay.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-28 0028
 * @Desc:地推支付单据表
 * @Version: 1.0
 */
public class GroundPromotionPayReceipt implements Serializable {
    private Integer id;                 //表主键
    private String receiptSn;           //支付单号
    private Integer userId;             //地推人员的id
    private Double totalAmount;         //提现金额
    private Double taxAmount;            //税费金额
    private Double finalAmount;         //税后金额
    private Double historyAmount;         //本月已结算金额
    private Integer status;             //0 待转账 1 转账成功 2 转账申请中 3 （财务）转账已发起，等待结果中 4 转账失败 5 转账驳回
    private Date createTime;
    private Date updateTime;
    private String bankName;
    private String bankCardNo;
    private String auditUserName;

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    public Double getHistoryAmount() {
        return historyAmount;
    }

    public void setHistoryAmount(Double historyAmount) {
        this.historyAmount = historyAmount;
    }
}
