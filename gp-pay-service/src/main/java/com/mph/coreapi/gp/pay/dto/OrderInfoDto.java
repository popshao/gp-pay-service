package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 排名相关信息
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/3
 * @Version: 1.0
 */
@ApiModel("OrderInfoDto")
public class OrderInfoDto<T> implements Serializable {
    /**
     * 当月排名
     */
    @ApiModelProperty(value = "当月排名")
    private Integer orderInProvince;

    /**
     * 完成量
     */
    @ApiModelProperty(value = "完成量")
    private T quantity;

    /**
     * 相比上月排名变化
     */
    @ApiModelProperty(value = "相比上月排名变化")
    private Integer orderChange;

    /**
     * 地推人员的名字
     */
    @ApiModelProperty(value = "地推人员的名字")
    private String userName;

    public Integer getOrderInProvince() {
        return orderInProvince;
    }

    public void setOrderInProvince(Integer orderInProvince) {
        this.orderInProvince = orderInProvince;
    }

    public T getQuantity() {
        return quantity;
    }

    public void setQuantity(T quantity) {
        this.quantity = quantity;
    }

    public Integer getOrderChange() {
        return orderChange;
    }

    public void setOrderChange(Integer orderChange) {
        this.orderChange = orderChange;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
