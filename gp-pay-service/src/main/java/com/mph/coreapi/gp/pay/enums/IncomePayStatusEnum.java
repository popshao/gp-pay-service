package com.mph.coreapi.gp.pay.enums;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/8
 * @Version: 1.0
 */
public enum  IncomePayStatusEnum {
    TO_PAY(0, "待发放（待提现）"), IN_PAY(1, "提现中"), PAID(2, "已发放或提现成功");
    Integer code;
    String description;

    IncomePayStatusEnum(Integer code, String description) {
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
