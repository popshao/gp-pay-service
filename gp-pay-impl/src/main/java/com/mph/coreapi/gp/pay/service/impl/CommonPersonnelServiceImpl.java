package com.mph.coreapi.gp.pay.service.impl;

import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.entity.GroundPromotionBankInfo;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayConfig;
import com.mph.coreapi.gp.pay.model.GroundPromotionIncomeDetailModel;
import com.mph.coreapi.gp.pay.model.GroundPromotionPayConfigModel;
import com.mph.coreapi.gp.pay.service.CommonPersonnelService;
import com.rogrand.common.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
@Service("commonPersonnelService")
public class CommonPersonnelServiceImpl implements CommonPersonnelService {
    @Autowired
    private GroundPromotionIncomeDetailModel groundPromotionIncomeDetailModel;
    @Autowired
    private GroundPromotionPayConfigModel    groundPromotionPayConfigModel;



    /**
     * 获取人员待发放金额
     * @param params
     * @return
     */
    public GroundPromotionIncomeDetail issuedAmount(Map<String,Object> params){
        return groundPromotionIncomeDetailModel.getGroundPromotionIncomeDetailAmount(params);
    }

    @Override
    public List<GroundPromotionBankInfo> listgroundPromotionBankInfoAll() {
        return null;
    }
}
