package com.mph.coreapi.gp.pay.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/4
 * @Version: 1.0
 */
public class AchiveMemberInfoDto implements Serializable {
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    Date createTime;

    /**
     * svip/vip名称
     */
    @ApiModelProperty(value = "svip/vip名称")
    String name;

    /**
     *  到期时间
     */
    @ApiModelProperty(value = "到期时间")
    Date dueTime;

    /**
     * 推广人
     */
    @ApiModelProperty(value = "推广人")
    String operUserName;

    @ApiModelProperty(value = "开通会员的企业名称，专区商品商品名称，活跃企业名称，下线姓名")
    String itemName;

    @ApiModelProperty(value = "0 自主推广 1 下线推广")
    Integer associateType;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName=itemName;
    }

    public Integer getAssociateType() {
        return associateType;
    }

    public void setAssociateType(Integer associateType) {
        this.associateType=associateType;
    }
}
