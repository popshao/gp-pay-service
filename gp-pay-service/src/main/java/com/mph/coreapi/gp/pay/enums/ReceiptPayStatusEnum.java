package com.mph.coreapi.gp.pay.enums;

/**
 * 0 待转账 1 转账成功 2 转账申请中 3 （财务）转账已发起，等待结果中 4 转账失败 5 转账驳回
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/8
 * @Version: 1.0
 */
public enum ReceiptPayStatusEnum {
    TO_PAY(0, "待转账"), SUCCESS_PAY(1, "转账成功"), APPLY_TO_TRANSFER(3, "（财务）转账已发起，等待结果中"),
    TRANSFER_FAIL(4,"转账失败"), REJECTED(5, "转账驳回");
    Integer code;
    String description;

    ReceiptPayStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
