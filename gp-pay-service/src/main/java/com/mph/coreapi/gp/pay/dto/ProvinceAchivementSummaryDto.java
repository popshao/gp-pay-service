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
@ApiModel("ProvinceAchivementSummaryDto")
public class ProvinceAchivementSummaryDto implements Serializable {
    /*
     * 成员数
     */
    @ApiModelProperty(value = "成员数")
    Integer userCount;

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
    @ApiModelProperty(value = "")
    TaskAchievementSummary<Double> memberSummary;

    /*
     * 专区商品完成汇总
     */
    @ApiModelProperty(value = "")
    TaskAchievementSummary<Double> goodSummary;

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
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
