package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/2
 * @Version: 1.0
 */
@ApiModel("GpIncomeDetailDto")
public class GpIncomeDetailDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6196779442558611488L;

    /*
     * 收入的表主键
     */
    private Integer id;

    /*
     * 1 会员推广(SVIP) 2 专区商品 3 菲加云 4 注册拉新 5 会员推广(VIP)
     */
    @ApiModelProperty(value = " 会员推广(SVIP)  专区商品  菲加云  注册拉新  会员推广(VIP)")
    private String taskDetailName;

    /*
     * 开通会员的企业名称，专区商品商品名称，活跃企业名称，下线姓名
     */
    @ApiModelProperty(value = "开通会员的企业名称，专区商品商品名称，活跃企业名称，下线姓名")
    private String itemName;

    /*
     * 提成时间
     */
    @ApiModelProperty(value = "提成时间")
    private Date createTime;

    /*
     * 提成金额
     */
    @ApiModelProperty(value = "提成金额")
    private Double amount;
    
    @ApiModelProperty(value = "本月已提现金额")
    private Double  depositAmount;       //本月已提现金额


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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskDetailName() {
        return taskDetailName;
    }

    public void setTaskDetailName(String taskDetailName) {
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

    public Double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }
    
}
