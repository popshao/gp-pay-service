package com.mph.coreapi.gp.pay.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/4
 * @Version: 1.0
 */
public class AchieveGoodInfoDto implements Serializable {
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    String orderSn;

    /**
     * 订单创建时间
     */
    @ApiModelProperty(value = "订单创建时间")
    Date orderCreateTime;

    /**
     * 采购商名称
     */
    @ApiModelProperty(value = "采购商名称")
    String buyerName;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    String goodName;

    /**
     * 制造商名称
     */
    @ApiModelProperty(value = "制造商名称")
    String manufacturer;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    String specification;

    /**
     * 推广人
     */
    @ApiModelProperty(value = "推广人")
    String operUserName;

    /**
     * 提成金额
     */
    @ApiModelProperty(value = "提成金额")
    String benifitAmount;

    @ApiModelProperty(value = "开通会员的企业名称，专区商品商品名称，活跃企业名称，下线姓名")
    String itemName;

    @ApiModelProperty(value = "0 自主推广 1 下线推广")
    Integer associateType;

    @ApiModelProperty(value = "图片url")
    String picUrl;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public String getBenifitAmount() {
        return benifitAmount;
    }

    public void setBenifitAmount(String benifitAmount) {
        this.benifitAmount = benifitAmount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName=itemName;
    }

    public Integer getAssociateType() {
        return associateType;
    }

    public void setAssociateType(Integer associateType) {
        this.associateType=associateType;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
