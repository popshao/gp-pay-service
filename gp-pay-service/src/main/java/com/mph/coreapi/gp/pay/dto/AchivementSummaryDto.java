package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;

import com.mph.coreapi.gp.pay.pojo.TaskAchievementSummary;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/3
 * @Version: 1.0
 */
@ApiModel("AchivementSummaryDto")
public class AchivementSummaryDto implements Serializable {
    /*
     * 用户待发提成金额
     */
    @ApiModelProperty(value = "用户待发提成金额")
    Double toPayIncomeAmount;

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
     * vip/svip完成汇总
     */
    TaskAchievementSummary<Double> memberSummary;

    /*
     * 专区商品完成汇总
     */
    TaskAchievementSummary<Double> goodSummary;

    public Double getToPayIncomeAmount() {
        return toPayIncomeAmount;
    }

    public void setToPayIncomeAmount(Double toPayIncomeAmount) {
        this.toPayIncomeAmount = toPayIncomeAmount;
    }

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

    public TaskAchievementSummary<Double> getMemberSummary() {
        return memberSummary;
    }

    public void setMemberSummary(TaskAchievementSummary<Double> memberSummary) {
        this.memberSummary = memberSummary;
    }

    public TaskAchievementSummary<Double> getGoodSummary() {
        return goodSummary;
    }

    public void setGoodSummary(TaskAchievementSummary<Double> goodSummary) {
        this.goodSummary = goodSummary;
    }
}
