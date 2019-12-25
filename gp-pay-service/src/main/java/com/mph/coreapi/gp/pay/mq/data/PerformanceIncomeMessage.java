package com.mph.coreapi.gp.pay.mq.data;

import java.io.Serializable;

public class PerformanceIncomeMessage implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 7810783773713120872L;
    
    private Integer groundUserId;//地推人员Uid
    private String performanceActiveId;//绩效计划活动ID
    private Double amount;//绩效奖励金额
    private String memo;//收益备注（例如：在30天内达成线上交易额大于300万奖励)
    private Double saleAmount;//销售额
    private Integer saleNum;//销售频次
    
    public Integer getGroundUserId() {
        return groundUserId;
    }
    public void setGroundUserId(Integer groundUserId) {
        this.groundUserId = groundUserId;
    }
    public String getPerformanceActiveId() {
        return performanceActiveId;
    }
    public void setPerformanceActiveId(String performanceActiveId) {
        this.performanceActiveId = performanceActiveId;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public Double getSaleAmount() {
        return saleAmount;
    }
    public void setSaleAmount(Double saleAmount) {
        this.saleAmount = saleAmount;
    }
    public Integer getSaleNum() {
        return saleNum;
    }
    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }
    @Override
    public String toString() {
        return "PerformanceIncomeMessage [groundUserId=" + groundUserId + ", performanceActiveId="
               + performanceActiveId + ", amount=" + amount + ", memo=" + memo + ", saleAmount="
               + saleAmount + ", saleNum=" + saleNum + "]";
    }
}
