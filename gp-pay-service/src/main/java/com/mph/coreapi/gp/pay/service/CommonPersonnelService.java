package com.mph.coreapi.gp.pay.service;

import java.util.List;
import java.util.Map;


import com.mph.coreapi.gp.pay.entity.GroundPromotionBankInfo;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayConfig;
import com.rogrand.common.ServiceResult;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
public interface CommonPersonnelService {

    /**
     * 获取人员金额
     * @param params
     * @return
     */
    GroundPromotionIncomeDetail issuedAmount(Map<String,Object> params);

    /**
     * 获取银行列表
     * @return
     */
    List<GroundPromotionBankInfo> listgroundPromotionBankInfoAll();



}
