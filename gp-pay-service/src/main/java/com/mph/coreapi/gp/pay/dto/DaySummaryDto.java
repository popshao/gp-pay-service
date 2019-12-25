package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/3
 * @Version: 1.0
 */
@ApiModel("DaySummaryDto")
public class DaySummaryDto<T> implements Serializable {
    /*
     * 日期
     */
    @ApiModelProperty(value = "日期")
    Date date;

    /*
     * 当日完成
     */
    @ApiModelProperty(value = "当日完成")
    T quantity;

    private Integer quantityCount;
    private Double queAmount;



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public T getQuantity() {
        return quantity;
    }

    public void setQuantity(T quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantityCount() {
        return quantityCount;
    }

    public void setQuantityCount(Integer quantityCount) {
        this.quantityCount = quantityCount;
    }

    public Double getQueAmount() {
        return queAmount;
    }

    public void setQueAmount(Double queAmount) {
        this.queAmount = queAmount;
    }
}
