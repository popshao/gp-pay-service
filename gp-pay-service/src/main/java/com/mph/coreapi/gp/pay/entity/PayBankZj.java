package com.mph.coreapi.gp.pay.entity;

import java.io.Serializable;

/**
 * 支付银行。
 */
public class PayBankZj implements Serializable {

    private static final long serialVersionUID = -3121979028884756492L;

    private String id;
    private String name;
    private String code;

    /**
     * 获取 支付银行ID。
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置 支付银行ID。
     *
     * @param value 属性值
     */
    public void setId(String value) {
        this.id = value;
    }


    /**
     * 获取 支付银行名称。
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 支付银行名称。
     *
     * @param value 属性值
     */
    public void setName(String value) {
        this.name = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}