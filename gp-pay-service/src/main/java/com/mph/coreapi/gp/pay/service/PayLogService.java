package com.mph.coreapi.gp.pay.service;

import java.util.List;

import com.mph.coreapi.gp.pay.entity.GroundPromotionPayLog;
import com.rogrand.common.ServiceResult;

/**
 * 易宝在线支付日志Service
 * 
 * @author qingwei.zhang 张青伟
 * @date 2016-7-25
 * @email qingwei.zhang@rogrand.com
 */
public interface PayLogService {

    /**
     * 添加易宝在线支付日志
     * @param
     * @return
     * @author qingwei.zhang 张青伟
     */
    ServiceResult<Integer> add(GroundPromotionPayLog payLogYiBao);

    /**
     * 更新易宝在线支付日志
     * @param
     * @return
     * @author qingwei.zhang 张青伟
     */
    ServiceResult<Boolean> update(GroundPromotionPayLog payLogYiBao);

    /**
     * 更新易宝在线支付日志
     * @param
     * @return
     * @author
     */
    ServiceResult<Integer> updateLogById(GroundPromotionPayLog payLogYiBao);

    ServiceResult<List<GroundPromotionPayLog>> listPayLogByStatus(Integer status, Integer payType, Integer startId, Integer pageSize);
}
