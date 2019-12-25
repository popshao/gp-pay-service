package com.mph.coreapi.gp.pay.service.business.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.mph.coreapi.gp.pay.dto.mgmt.PayInfoSummaryDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.model.GroundPromotionIncomeDetailModel;
import com.mph.coreapi.gp.pay.service.GroundPromotionIncomeDetailService;
import com.mph.coreapi.gp.pay.service.business.MgmtWithDrawService;
import com.rogrand.common.page.Pagination;
import com.rogrand.coreapi.ditui.service.GroundPromotionUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/5
 * @Version: 1.0
 */
@Service
public class MgmtWithDrawServiceImpl implements MgmtWithDrawService {
    Logger logger = LoggerFactory.getLogger(MgmtWithDrawServiceImpl.class);

    @Resource
    GroundPromotionIncomeDetailModel groundPromotionIncomeDetailModel;

    @Autowired
    GroundPromotionUserService groundPromotionUserService;

    @Override
    public PayInfoSummaryDto getPayInfoSummary(Map<String, Object> param) {
        return null;
    }

    @Override
    public Pagination listPayInfoPersonalSummary(Map<String, Object> param, int pageNo, int pageSize) {
        return null;
    }
}
