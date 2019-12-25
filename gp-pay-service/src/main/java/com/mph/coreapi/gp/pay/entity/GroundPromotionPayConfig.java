package com.mph.coreapi.gp.pay.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @Desc:地推提现全局配置表
 * @CreateDate: 2018-05-28 0028
 * @Version: 1.0
 */
public class GroundPromotionPayConfig implements Serializable {
    private Integer id;                 //表主键
    private Integer type;               //提现全局配置 1 提现起始金额
    private Double val;                 //金额
    private String payDescription;      //提现说明
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getVal() {
        return val;
    }

    public void setVal(Double val) {
        this.val = val;
    }

    public String getPayDescription() {
        return payDescription;
    }

    public void setPayDescription(String payDescription) {
        this.payDescription = payDescription;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
