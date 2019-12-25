package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;

import com.mph.coreapi.gp.pay.enums.IncomeTypeEnum;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/7
 * @Version: 1.0
 */
public class RetDto implements Serializable {
    /**
     * 包装app返回
     */
    Integer ret;

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }
}
