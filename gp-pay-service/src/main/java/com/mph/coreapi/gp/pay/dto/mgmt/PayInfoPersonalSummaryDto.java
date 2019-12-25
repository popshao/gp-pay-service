package com.mph.coreapi.gp.pay.dto.mgmt;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/5
 * @Version: 1.0
 */
public class PayInfoPersonalSummaryDto {
    /*
     *
     */
    Integer userId;

    /*
     *
     */
    String userName;
    /*
     *
     */
    String phoneNum;
    /*
     *
     */
    String provinceName;
    /*
     * 月份时间
     */
    String timeDisplay;
    /*
     *
     */
    String payStatusDesc;
    /*
     *
     */
    Integer payStatus;
    /*
     *
     */
    Double amount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getTimeDisplay() {
        return timeDisplay;
    }

    public void setTimeDisplay(String timeDisplay) {
        this.timeDisplay = timeDisplay;
    }

    public String getPayStatusDesc() {
        return payStatusDesc;
    }

    public void setPayStatusDesc(String payStatusDesc) {
        this.payStatusDesc = payStatusDesc;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
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
}
