package com.mph.coreapi.gp.pay.entity;

import java.util.Date;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-10-22 0022
 * @Version: 1.0
 */
public class GroundPromotionUserRegister {
    private Integer eid; //开通会员的商户ID
    private String  oSn;//开通会员对应的订单号
    private Date    createTime;//开通会员时间

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getoSn() {
        return oSn;
    }

    public void setoSn(String oSn) {
        this.oSn = oSn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
