package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 按照订单号来分组提成金额
 * @author jianghong.huang
 *
 */
public class GroundPromotionIncomeDisplaySnDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2000345013607145392L;

    private Integer           userId;                                  //地推人员的id
    private Integer           type;                                    //收益类型： 1 会员推广 2 专区商品 3 菲加云
    private Integer           associateType;
    private String            itemName;
    private Double            amount;                                  //提成额度
    private Integer           payStatus;                               //0 待发放（待提现） 1 提现中 2 已发放或提现成功
    private String            displaySn;                               //订单sn等
    private Date              createTime;                              //创建时间
    private Integer           operUserId;
    private String            operUserName;
    private String            sourceUserName;                          //下线地推名字

    public String getSourceUserName() {
        return sourceUserName;
    }

    public void setSourceUserName(String sourceUserName) {
        this.sourceUserName = sourceUserName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getDisplaySn() {
        return displaySn;
    }

    public void setDisplaySn(String displaySn) {
        this.displaySn = displaySn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Integer operUserId) {
        this.operUserId = operUserId;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public Integer getAssociateType() {
        return associateType;
    }

    public void setAssociateType(Integer associateType) {
        this.associateType = associateType;
    }

    @Override
    public String toString() {
        return "GroundPromotionIncomeDisplaySnDto [userId=" + userId + ", type=" + type
               + ", itemName=" + itemName + ", amount=" + amount + ", payStatus=" + payStatus
               + ", displaySn=" + displaySn + ", createTime=" + createTime + "]";
    }

}
