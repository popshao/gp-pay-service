package com.mph.coreapi.gp.pay.dao.read;

import com.mph.coreapi.gp.pay.entity.PayBankZj;

import java.util.List;

public interface PayBankZjReadDao {

    PayBankZj selectByPrimaryKey(String id);

    List<PayBankZj> listPayBankZj(PayBankZj payBankZj);

    /**
     * @Description: 根据银行名称模糊查询
     * @Email: shengliang.wei@rograndec.com
     * @Author: shengliang.wei
     * @Date: 2018/8/19 17:17
     */
    List<PayBankZj> findPayBankByName(String bankName);

}
