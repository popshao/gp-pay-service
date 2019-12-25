package com.mph.coreapi.gp.pay.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 易宝支付日志表
 *
 * @author qingwei.zhang
 * @date 2016-7-25
 * @email qingwei.zhang@rogrand.com
 */
public class GroundPromotionPayLog implements Serializable {

    private static final long serialVersionUID = -2316403734004084976L;

    private Integer           id;

    private String            requestData;
    private String            responseData;
    private String            paymentNo;
    private String            remark;
    private String            statusDes;
    private Integer           payType;                                 // 1支付2退款
    private Date              requestTime;
    private Date              responseTime;
    private String            oSn;
    private String            amount;
    private Integer           status;
    private Integer businessType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public String getoSn() {
        return oSn;
    }

    public void setoSn(String oSn) {
        this.oSn = oSn;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }
}