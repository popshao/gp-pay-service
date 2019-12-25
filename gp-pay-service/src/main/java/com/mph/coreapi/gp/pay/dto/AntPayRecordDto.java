package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 提现记录
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/3
 * @Version: 1.0
 */
@ApiModel("AntPayRecordDto")
public class AntPayRecordDto implements Serializable {
    @ApiModelProperty(value = "支付单号")
    private String receiptSn;           //支付单号
    @ApiModelProperty(value = "提现金额")
    private Double  totalAmount;         //提现金额
    @ApiModelProperty(value = "税费金额")
    private Double  taxAmount;            //税费金额
    @ApiModelProperty(value = "税后金额")
    private Double  finalAmount;         //税后金额
    @ApiModelProperty(value = "本月已提现金额")
    private Double  depositAmount;       //本月已提现金额
    @ApiModelProperty(value = "1 提现成功 2 提现中 3 提现失败")
    private Integer status;             //1 提现成功 2 提现中 3 提现失败
    @ApiModelProperty(value = "提现状态描述")
    private String  statusDesc; // 提现状态描述
    @ApiModelProperty(value = "提现时间")
    private Date    createTime;
    @ApiModelProperty(value = "银行名称")
    private String bankName;  // 银行名称
    @ApiModelProperty(value = "银行卡后四位")
    private String cardLast4No; //  银行卡后四位

    public String getReceiptSn() {
        return receiptSn;
    }

    public void setReceiptSn(String receiptSn) {
        this.receiptSn = receiptSn;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardLast4No() {
        return cardLast4No;
    }

    public void setCardLast4No(String cardLast4No) {
        this.cardLast4No = cardLast4No;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }
}
