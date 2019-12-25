package com.mph.coreapi.gp.pay.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Desc:地推收入明细表
 * @Version: 1.0
 */
public class GroundPromotionPayReceiptDetail implements Serializable {
    private Integer id;                  //表主键
    private Integer receiptId;           //单据表id
    private Integer incomeId;            //单据表id
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
