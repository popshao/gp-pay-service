package com.mph.coreapi.gp.pay.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName: GroundPromotionOrderDataEntity
 * @Description: 地推月度排行数据结果
 * @Author yuting.li
 * @Version 1.0
 * @Date 2018-05-31 16:19
 */
public class GroundPromotionOrderDataEntity implements Serializable {


    /**
     * 地推人员的id
     */
    private Integer userId;

    /**
     * 收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
     */
    private Integer type;

    /**
     * 地推人员的所属省份id
     */
    private Integer userProvinceId;

    /**
     * 统计成果的月份：201805
     */
    private String monthDisplay;

    /**
     * 完成金额
     */
    private BigDecimal amount;

    /**
     * 完成数量
     */
    private BigDecimal quatity;

    /**
     * 当月排名
     */
    private Integer orderInProvince;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId=userId;
    }

    public Integer getUserProvinceId() {
        return userProvinceId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type=type;
    }

    public void setUserProvinceId(Integer userProvinceId) {
        this.userProvinceId=userProvinceId;
    }

    public String getMonthDisplay() {
        return monthDisplay;
    }

    public void setMonthDisplay(String monthDisplay) {
        this.monthDisplay=monthDisplay;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount=amount;
    }

    public BigDecimal getQuatity() {
        return quatity;
    }

    public void setQuatity(BigDecimal quatity) {
        this.quatity=quatity;
    }

    public Integer getOrderInProvince() {
        return orderInProvince;
    }

    public void setOrderInProvince(Integer orderInProvince) {
        this.orderInProvince=orderInProvince;
    }
}
