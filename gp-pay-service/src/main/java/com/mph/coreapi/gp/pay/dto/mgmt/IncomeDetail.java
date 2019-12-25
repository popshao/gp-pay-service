package com.mph.coreapi.gp.pay.dto.mgmt;

import java.util.Date;

/**
 * 时间  提成类型  推广类型 提成 专区商品销售额 专区商品名称 推广企业名称
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/5
 * @Version: 1.0
 */
public class IncomeDetail {
    /**
     * 时间
     */
    Date createTime;

    /**
     * 提成类型
     */
    String taskDesc;

    /**
     * 推广类型
     */
    String jobTypeDesc;

    /**
     * 提成金额
     */
    Double amount;

    /**
     * 专区商品销售额
     */
    Double saleAmount;

    /**
     * 专区商品名称
     */
    String goodName;

    /**
     * 推广企业名称
     */
    String memberEnterpriseName;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getJobTypeDesc() {
        return jobTypeDesc;
    }

    public void setJobTypeDesc(String jobTypeDesc) {
        this.jobTypeDesc = jobTypeDesc;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getMemberEnterpriseName() {
        return memberEnterpriseName;
    }

    public void setMemberEnterpriseName(String memberEnterpriseName) {
        this.memberEnterpriseName = memberEnterpriseName;
    }
}
