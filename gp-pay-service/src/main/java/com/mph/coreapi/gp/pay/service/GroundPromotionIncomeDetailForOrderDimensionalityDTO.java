package com.mph.coreapi.gp.pay.service;

import java.io.Serializable;

/**
 *  订单维度的地推收入明细接口                
 * @Author: daichao.peng
 * @Email: daichao.peng@rograndec.com
 * @CreateDate 2019年6月5日
 *
 */
public class GroundPromotionIncomeDetailForOrderDimensionalityDTO implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1808726378597076348L;
    /**
     * 收入金额
     */
    private double            incomeAmount;
    /**
     * 下线账户名称
     */
    private String            sourceUserName;
    /**
     * 订单编号
     */
    private String            osn;

    public String getOsn() {
        return osn;
    }

    public void setOsn(String osn) {
        this.osn = osn;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getSourceUserName() {
        return sourceUserName;
    }

    public void setSourceUserName(String sourceUserName) {
        this.sourceUserName = sourceUserName;
    }

}
