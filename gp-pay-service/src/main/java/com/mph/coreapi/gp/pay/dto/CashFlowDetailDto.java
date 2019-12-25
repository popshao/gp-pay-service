package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 蚂蚁员工收支明细
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/2
 * @Version: 1.0
 */
@ApiModel("CashFlowDetailDto")
public class CashFlowDetailDto implements Serializable {
    /*
     * 1 收入 2 取现
     */
    @ApiModelProperty(value = "1 收入 2 取现")
    private Integer ioType;

    /*
     * 会员推广(SVIP) 专区商品 菲加云  注册拉新  会员推广(VIP)  取现等
     */
    @ApiModelProperty(value = "会员推广(SVIP) 专区商品 菲加云  注册拉新  会员推广(VIP)  取现等")
    private String taskDetailName;

    /*
     * 开通会员的企业名称，专区商品商品名称，活跃企业名称，下线姓名
     */
    @ApiModelProperty(value = "开通会员的企业名称，专区商品商品名称，活跃企业名称，下线姓名")
    private String itemName;

    /*
     * 提成时间 或者 取现时间
     */
    @ApiModelProperty(value = "提成时间 或者 取现时间")
    private Date createTime;

    /*
     * 提成金额
     */
    @ApiModelProperty(value = "提成金额")
    private Double amount;

    /*
     * 提成状态
     */
    @ApiModelProperty(value = "提成状态")
    private Integer payStatus;

    /*
     * 提成状态
     */
    @ApiModelProperty(value = "提成状态")
    private String payStatusDesc;


    public String getTaskDetailName() {

        return taskDetailName;
    }

    public void setTaskDetailName(String taskDetailName) {

        if("1".equals(taskDetailName)){
            taskDetailName="会员推广(SVIP)";
        }
        else if("2".equals(taskDetailName)){
            taskDetailName="专区商品";
        }
        else if("3".equals(taskDetailName)){
            taskDetailName="菲加云";
        }
        else if("4".equals(taskDetailName)){
            taskDetailName="注册拉新";
        }
        else if("5".equals(taskDetailName)){
            taskDetailName="会员推广(VIP)";
        }
        this.taskDetailName = taskDetailName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getIoType() {
        return ioType;
    }

    public void setIoType(Integer ioType) {
        this.ioType = ioType;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
        String StatusDesc="";
        switch (this.payStatus){
            case 0:
                StatusDesc= "已入账";
                break;
            case 1:
                StatusDesc= "提现中";
                break;
            case 2:
                StatusDesc= "已提现";
                break;
            case 3:
                StatusDesc= "待入账";
                break;
            case -1:
                StatusDesc= "提现";
                break;
            default:
                StatusDesc= "";
                break;
        }
        this.payStatusDesc=StatusDesc;
    }

    public String getPayStatusDesc() {
        String StatusDesc="";
        switch (this.payStatus){
            case 0:
                StatusDesc= "已入账";
                break;
            case 1:
                StatusDesc= "提现中";
                break;
            case 2:
                StatusDesc= "已提现";
                break;
            case 3:
                StatusDesc= "待入账";
                break;
            case -1:
                StatusDesc= "提现";
                break;
            default:
                StatusDesc= "";
                break;
        }
        return StatusDesc;
    }

    public void setPayStatusDesc(String payStatusDesc) {
        this.payStatusDesc = payStatusDesc;
    }
}
