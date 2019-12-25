package com.mph.coreapi.gp.pay.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.mph.coreapi.gp.pay.entity.GroundPromotionPayLog;
import com.mph.coreapi.gp.pay.model.GroundPromotionPayLogModel;
import com.mph.coreapi.gp.pay.service.PayLogService;
import com.rogrand.common.BusinessException;
import com.rogrand.common.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/4
 * @Version: 1.0
 */
@Service("payLogService")
public class PayLogServiceImpl implements PayLogService {
    Logger logger = LoggerFactory.getLogger(PayLogServiceImpl.class);

    @Resource
    GroundPromotionPayLogModel groundPromotionPayLogModel;

    @Override
    public ServiceResult<Integer> add(GroundPromotionPayLog payLogYiBao) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(this.groundPromotionPayLogModel.add(payLogYiBao));
        } catch (Exception be) {
            logger.error(be.getMessage(), be);
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        }

        return result;
    }

    @Override
    public ServiceResult<Boolean> update(GroundPromotionPayLog payLogYiBao) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(this.groundPromotionPayLogModel.update(payLogYiBao));
        } catch (Exception be) {
            logger.error(be.getMessage(), be);
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        }

        return result;
    }

    @Override
    public ServiceResult<Integer> updateLogById(GroundPromotionPayLog payLogYiBao) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(this.groundPromotionPayLogModel.updateLogById(payLogYiBao));
        } catch (Exception be) {
            logger.error(be.getMessage(), be);
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        }

        return result;
    }

    @Override
    public ServiceResult<List<GroundPromotionPayLog>> listPayLogByStatus(Integer status, Integer payType, Integer startId, Integer pageSize) {
        ServiceResult<List<GroundPromotionPayLog>> result = new ServiceResult<List<GroundPromotionPayLog>>();
        try {
            result.setResult(this.groundPromotionPayLogModel.listPayLogByStatus(status, payType, startId, pageSize));
        } catch (Exception be) {
            logger.error(be.getMessage(), be);
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        }

        return result;
    }
}
