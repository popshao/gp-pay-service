package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @Description: 业绩收入中的绩效详情
 * @Author: qizhi.wang
 * @Date: 2019/6/11 
 */
public class PerformanceDto implements Serializable {

    private static final long serialVersionUID = -8498470354615499886L;
    //金额
    @ApiModelProperty(value = "金额")
    private Double            amount;
    //规则描述
    @ApiModelProperty(value = "规则描述")
    private String            ruleDesc;
    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date              createDate;
    //订单号
    @ApiModelProperty(value = "订单号")
    private String            osn;
    //业绩类型
    @ApiModelProperty(value = "业绩类型")
    private Integer           type;
    //业绩类型描述
    @ApiModelProperty(value = "业绩类型描述")
    private String            typeDesc;
    //收入支付状态    '0 待发放（待提现） 1 提现中 2 已发放或提现成功 3 待入账',
    @ApiModelProperty(value = "收入支付状态")
    private Integer           payStatus;

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

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOsn() {
        return osn;
    }

    public void setOsn(String osn) {
        this.osn = osn;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }
}
