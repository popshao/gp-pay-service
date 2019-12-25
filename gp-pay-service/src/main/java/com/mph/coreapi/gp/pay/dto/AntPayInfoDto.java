package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 蚂蚁员工支付信息
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/2
 * @Version: 1.0
 */
@ApiModel("AntPayInfoDto")
public class AntPayInfoDto implements Serializable {
    /*
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /*
     * 是否可以提现 0 不可以 1 可以
     */
    @ApiModelProperty(value = "是否可以提现 0 不可以 1 可以")
    private Integer canWithDraw;

    /*
     * 是否已经绑卡 0 没有绑 1 绑了
     */
    @ApiModelProperty(value = "是否已经绑卡 0 没有绑 1 绑了")
    private Integer bindCard;

    /*
     * 提现说明
     */
    @ApiModelProperty(value = "提现说明")
    private String withDrawConditionDesc;

    /*
     * 可提现
     */
    @ApiModelProperty(value = "可提现")
    private Double availableAmount;

    /*
     * 已提现
     */
    @ApiModelProperty(value = "已提现")
    private Double receivedAmount;
    /*
     * 提现中
     */
    @ApiModelProperty(value = "提现中")
    private Double receivingAmount;
    /*
     * 提现中
     */
    @ApiModelProperty(value = "待入账")
    private Double waitingAccountAmount;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCanWithDraw() {
        return canWithDraw;
    }

    public void setCanWithDraw(Integer canWithDraw) {
        this.canWithDraw = canWithDraw;
    }

    public Integer getBindCard() {
        return bindCard;
    }

    public void setBindCard(Integer bindCard) {
        this.bindCard = bindCard;
    }

    public String getWithDrawConditionDesc() {
        return withDrawConditionDesc;
    }

    public void setWithDrawConditionDesc(String withDrawConditionDesc) {
        this.withDrawConditionDesc = withDrawConditionDesc;
    }

    public Double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Double availableAmount) {
        this.availableAmount = availableAmount;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Double getReceivingAmount() {
        return receivingAmount;
    }

    public void setReceivingAmount(Double receivingAmount) {
        this.receivingAmount = receivingAmount;
    }

    public Double getWaitingAccountAmount() {
        return waitingAccountAmount;
    }

    public void setWaitingAccountAmount(Double waitingAccountAmount) {
        this.waitingAccountAmount = waitingAccountAmount;
    }
}
