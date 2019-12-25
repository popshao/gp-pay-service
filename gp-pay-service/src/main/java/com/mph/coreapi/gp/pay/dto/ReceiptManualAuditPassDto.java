package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;

public class ReceiptManualAuditPassDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5129007231898799002L;
    /**
     * 提现ID
     */
    private Integer receiptId;
    /**
     * 调整之后的金额
     */
    private Double adjustPayMoney;
    
    public Integer getReceiptId() {
        return receiptId;
    }
    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }
    public Double getAdjustPayMoney() {
        return adjustPayMoney;
    }
    public void setAdjustPayMoney(Double adjustPayMoney) {
        this.adjustPayMoney = adjustPayMoney;
    }
    
    
}
