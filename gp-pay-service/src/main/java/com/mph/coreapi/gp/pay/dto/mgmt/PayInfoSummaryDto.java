package com.mph.coreapi.gp.pay.dto.mgmt;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/5
 * @Version: 1.0
 */
public class PayInfoSummaryDto {
    /**
     * 提成总额（已发放成功的，历史记录总和）
     */
    private Double totalIncomeAmount;

    /**
     * 未发放的总和
     */
    private Double totalIncomeAmountUnpaid;

    public Double getTotalIncomeAmount() {
        return totalIncomeAmount;
    }

    public void setTotalIncomeAmount(Double totalIncomeAmount) {
        this.totalIncomeAmount = totalIncomeAmount;
    }

    public Double getTotalIncomeAmountUnpaid() {
        return totalIncomeAmountUnpaid;
    }

    public void setTotalIncomeAmountUnpaid(Double totalIncomeAmountUnpaid) {
        this.totalIncomeAmountUnpaid = totalIncomeAmountUnpaid;
    }
}
