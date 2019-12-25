package com.mph.coreapi.gp.pay.pojo;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/3
 * @Version: 1.0
 */
@ApiModel("TaskAchievementSummary")
public class TaskAchievementSummary<T> implements Serializable {
    /*
     * 当月kpi已完成额度
     */
    @ApiModelProperty(value = "当月kpi已完成额度")
    Double currentMonthKpiPercent;

    /*
     * 上月kpi完成额度
     */
    @ApiModelProperty(value = "上月kpi完成额度")
    Double lastMonthKpiPercent;

    /*
     * 当月已完成
     */
    @ApiModelProperty(value = "当月已完成")
    T quantityCurrentMonth;

    /*
     * 当月预期完成
     */
    @ApiModelProperty(value = "当月预期完成")
    T expectedQuantityCurrentMonth;

    /*
     * 上月完成
     */
    @ApiModelProperty(value = "上月完成")
    T quantityLastMonth;

    /*
     * 上月预期完成
     */
    @ApiModelProperty(value = "上月预期完成")
    T expectedQuantityLastMonth;

    public Double getCurrentMonthKpiPercent() {
        return currentMonthKpiPercent;
    }

    public void setCurrentMonthKpiPercent(Double currentMonthKpiPercent) {
        this.currentMonthKpiPercent = currentMonthKpiPercent;
    }

    public Double getLastMonthKpiPercent() {
        return lastMonthKpiPercent;
    }

    public void setLastMonthKpiPercent(Double lastMonthKpiPercent) {
        this.lastMonthKpiPercent = lastMonthKpiPercent;
    }

    public T getQuantityCurrentMonth() {
        return quantityCurrentMonth;
    }

    public void setQuantityCurrentMonth(T quantityCurrentMonth) {
        this.quantityCurrentMonth = quantityCurrentMonth;
    }

    public T getExpectedQuantityCurrentMonth() {
        return expectedQuantityCurrentMonth;
    }

    public void setExpectedQuantityCurrentMonth(T expectedQuantityCurrentMonth) {
        this.expectedQuantityCurrentMonth = expectedQuantityCurrentMonth;
    }

    public T getQuantityLastMonth() {
        return quantityLastMonth;
    }

    public void setQuantityLastMonth(T quantityLastMonth) {
        this.quantityLastMonth = quantityLastMonth;
    }

    public T getExpectedQuantityLastMonth() {
        return expectedQuantityLastMonth;
    }

    public void setExpectedQuantityLastMonth(T expectedQuantityLastMonth) {
        this.expectedQuantityLastMonth = expectedQuantityLastMonth;
    }
}
