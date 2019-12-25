package com.mph.coreapi.gp.pay.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

 
/**
 * 
 * ground_promotion_achievement
 * 
 * @Description: 地推月度成果统计表
 * @author yuting.li
 * 
 * 2018-05-31
 */
public class GroundPromotionAchievement implements Serializable {
    /**
     * 表主键
     */
    private Integer id;

    /**
     * 地推人员的id
     */
    private Integer userId;

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

    /**
     * 相比上月排名变化
     */
    private Integer orderChange;

    /**
     * 收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
     */
    private Integer type;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 地推人员的名字
     */
    private String userName;

    /**
     * ground_promotion_achievement
     */
    private static final long serialVersionUID = 1L;

    /**
     * 表主键
     * @return id 表主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 表主键
     * @param id 表主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 地推人员的id
     * @return user_id 地推人员的id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 地推人员的id
     * @param userId 地推人员的id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 地推人员的所属省份id
     * @return user_province_id 地推人员的所属省份id
     */
    public Integer getUserProvinceId() {
        return userProvinceId;
    }

    /**
     * 地推人员的所属省份id
     * @param userProvinceId 地推人员的所属省份id
     */
    public void setUserProvinceId(Integer userProvinceId) {
        this.userProvinceId = userProvinceId;
    }

    /**
     * 统计成果的月份：201805
     * @return month_display 统计成果的月份：201805
     */
    public String getMonthDisplay() {
        return monthDisplay;
    }

    /**
     * 统计成果的月份：201805
     * @param monthDisplay 统计成果的月份：201805
     */
    public void setMonthDisplay(String monthDisplay) {
        this.monthDisplay = monthDisplay == null ? null : monthDisplay.trim();
    }

    /**
     * 完成金额
     * @return amount 完成金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 完成金额
     * @param amount 完成金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 完成数量
     * @return quatity 完成数量
     */
    public BigDecimal getQuatity() {
        return quatity;
    }

    /**
     * 完成数量
     * @param quatity 完成数量
     */
    public void setQuatity(BigDecimal quatity) {
        this.quatity = quatity;
    }

    /**
     * 当月排名
     * @return order_in_province 当月排名
     */
    public Integer getOrderInProvince() {
        return orderInProvince;
    }

    /**
     * 当月排名
     * @param orderInProvince 当月排名
     */
    public void setOrderInProvince(Integer orderInProvince) {
        this.orderInProvince = orderInProvince;
    }

    /**
     * 相比上月排名变化
     * @return order_change 相比上月排名变化
     */
    public Integer getOrderChange() {
        return orderChange;
    }

    /**
     * 相比上月排名变化
     * @param orderChange 相比上月排名变化
     */
    public void setOrderChange(Integer orderChange) {
        this.orderChange = orderChange;
    }

    /**
     * 收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
     * @return type 收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
     */
    public Integer getType() {
        return type;
    }

    /**
     * 收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
     * @param type 收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return update_time 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName=userName;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", userProvinceId=").append(userProvinceId);
        sb.append(", monthDisplay=").append(monthDisplay);
        sb.append(", amount=").append(amount);
        sb.append(", quatity=").append(quatity);
        sb.append(", orderInProvince=").append(orderInProvince);
        sb.append(", orderChange=").append(orderChange);
        sb.append(", type=").append(type);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}