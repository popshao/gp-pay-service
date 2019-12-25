package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/2
 * @Version: 1.0
 */
@ApiModel("TaskProgressInfoDto")
public class TaskProgressInfoDto implements Serializable {
    /*
     *  1 会员推广 2 专区商品 3 菲加云 4 注册拉新
     */
    @ApiModelProperty(value = "1 会员推广 2 专区商品 3 菲加云 4 注册拉新")
    private Integer taskType;

    /*
     *  1 数量 2 金额
     */
    @ApiModelProperty(value = "1 数量 2 金额")
    private Integer progressStandardType;

    /*
     * 用户表示
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /*
     * 完成条件描述
     */
    @ApiModelProperty(value = "完成条件描述")
    private String finishConditionDiscription;

    /*
     * 会员推广  专区商品 菲加云  注册拉新
     */
    @ApiModelProperty(value = "会员推广  专区商品 菲加云  注册拉新")
    private String taskTypeDisplay;

    /*
    * 实际完成数量  progressStandardType = 1 有效
    */
    @ApiModelProperty(value = "实际完成数量  progressStandardType = 1 有效")
    private Integer actualQuantity;

    /*
    * 预期完成数量  progressStandardType = 1 有效
    */
    @ApiModelProperty(value = "预期完成数量  progressStandardType = 1 有效")
    private Integer expectedQuantity;

    /*
    * 实际完成数量  progressStandardType = 2 有效
    */
    @ApiModelProperty(value = "实际完成数量  progressStandardType = 2 有效")
    private Double actualAmount;

    /*
    * 预期完成数量  progressStandardType = 2 有效
    */
    @ApiModelProperty(value = "预期完成数量  progressStandardType = 2 有效")
    private Double expectedAmount;

    public Integer getProgressStandardType() {
        return progressStandardType;
    }

    public void setProgressStandardType(Integer progressStandardType) {
        this.progressStandardType = progressStandardType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFinishConditionDiscription() {
        return finishConditionDiscription;
    }

    public void setFinishConditionDiscription(String finishConditionDiscription) {
        this.finishConditionDiscription = finishConditionDiscription;
    }

    public String getTaskTypeDisplay() {
        return taskTypeDisplay;
    }

    public void setTaskTypeDisplay(String taskTypeDisplay) {
        this.taskTypeDisplay = taskTypeDisplay;
    }

    public Integer getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(Integer actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public Integer getExpectedQuantity() {
        return expectedQuantity;
    }

    public void setExpectedQuantity(Integer expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Double getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(Double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }
}
