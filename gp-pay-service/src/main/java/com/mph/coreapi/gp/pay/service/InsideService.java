package com.mph.coreapi.gp.pay.service;

import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.dto.GroundPromotionIncomeDisplaySnDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.service.condition.GroundPromotionIncomeSearchCondition;
import com.rogrand.common.ServiceResult;
import com.rogrand.common.page.Pagination;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-28 0028
 * @Version: 1.0
 */
public interface InsideService {

    /**
     * 获得收益明细
     * @param params
     * @return
     */
    ServiceResult<Pagination> getInsideProfitDetails(Map<String, Object> param, int pageNo, int pageSize);

    /**
     * 根据收入类型及订单号，分组查询全职用户收益
     * @param userId
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServiceResult<Pagination<GroundPromotionIncomeDisplaySnDto>> listGroundPromotionIncomeGroupByDisplayNo(GroundPromotionIncomeSearchCondition condition);

    /** 
     * @Description: 查询指定订单的所有专区商品收益明细
     * @Param: [userId, type, displaySn]
     * @return: com.rogrand.common.ServiceResult<com.rogrand.common.page.Pagination<com.mph.coreapi.gp.pay.dto.GroundPromotionIncomeDisplaySnDto>> 
     * @Author: qizhi.wang
     * @Date: 2019/6/12 
     */
    ServiceResult<List<GroundPromotionIncomeDetail>> listGroundPromotionIncomeByDisplayNo(Integer userId, Integer type,
                                                                                          String displaySn, Integer associateType);
    
    /**
     * 根据收入类型及订单号，分组查询兼职用户收益（兼职包括提现记录）
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServiceResult<Pagination<GroundPromotionIncomeDisplaySnDto>> listPartTimeGroundPromotionIncomeGroupByDisplayNo(Integer userId, int pageNo, int pageSize);
}
