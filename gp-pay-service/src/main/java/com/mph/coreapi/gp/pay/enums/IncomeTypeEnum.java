package com.mph.coreapi.gp.pay.enums;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/7
 * @Version: 1.0
 */
public enum  IncomeTypeEnum {
    MEMBER(1, "会员推广"), SPECIFIC_PRODUCT(2, "专区商品"), SAAS(3,"菲加云"), ENTERPRISE_NEW(4,"注册拉新"),LOWER(5,"下线推广");
    Integer code;
    String desc;

    IncomeTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
