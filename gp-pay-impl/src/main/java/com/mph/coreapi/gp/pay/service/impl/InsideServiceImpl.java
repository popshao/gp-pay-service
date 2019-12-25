package com.mph.coreapi.gp.pay.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.dto.GpIncomeDetailDto;
import com.mph.coreapi.gp.pay.dto.GroundPromotionIncomeDisplaySnDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.model.GroundPromotionIncomeDetailModel;
import com.mph.coreapi.gp.pay.model.GroundPromotionPayReceiptModel;
import com.mph.coreapi.gp.pay.service.InsideService;
import com.mph.coreapi.gp.pay.service.condition.GroundPromotionIncomeSearchCondition;
import com.rogrand.common.ServiceResult;
import com.rogrand.common.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-28 0028
 * @Version: 1.0
 */
@Service("insideService")
public class InsideServiceImpl implements InsideService {
    private Logger                           logger = LoggerFactory
        .getLogger(GroundPromotionOrderServiceImpl.class);

    @Autowired
    private GroundPromotionIncomeDetailModel groundPromotionIncomeDetailModel;
    @Autowired
    private GroundPromotionPayReceiptModel   groundPromotionPayReceiptModel;

    @Override
    public ServiceResult<Pagination> getInsideProfitDetails(Map<String, Object> params, int pageNo,
                                                            int pageSize) {
        ServiceResult<Pagination> result = new ServiceResult<Pagination>();
        List<GpIncomeDetailDto> antPayRecordDtoList = new ArrayList<GpIncomeDetailDto>();
        List<GroundPromotionIncomeDetail> list = new ArrayList<GroundPromotionIncomeDetail>();
        try {
            int totalCount = groundPromotionIncomeDetailModel.countByParam(params);
            // 构建分页数据对象
            Pagination pagination = new Pagination(pageNo, pageSize, totalCount);
            if (0 != totalCount) {
                Double historyAmount = 0d;
                if(params.containsKey("userId")) {
                    Integer userId = (Integer) params.get("userId");
                    historyAmount = groundPromotionPayReceiptModel.getGroundPromotionReceiptAmount(userId);
                }
                list = groundPromotionIncomeDetailModel
                    .getGroundPromotionIncomeDetailListByUserIdStatus(params,
                        pagination.getFirstResult(), pageSize);
                if (list != null && list.size() > 0) {
                    for (GroundPromotionIncomeDetail item : list) {
                        GpIncomeDetailDto gpIncomeDetailDto = new GpIncomeDetailDto();
                        gpIncomeDetailDto.setTaskDetailName(item.getTypeDetailName());
                        gpIncomeDetailDto.setItemName(item.getItemName());
                        gpIncomeDetailDto.setCreateTime(item.getCreateTime());
                        gpIncomeDetailDto.setAmount(item.getAmount());
                        gpIncomeDetailDto.setPayStatus(item.getPayStatus());
                        gpIncomeDetailDto.setDepositAmount(historyAmount);
                        antPayRecordDtoList.add(gpIncomeDetailDto);
                    }
                    pagination.setList(antPayRecordDtoList);
                } else {
                    pagination.setList(antPayRecordDtoList);
                }
            } else {
                pagination.setList(antPayRecordDtoList);
            }
            result.setResult(pagination);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("系统异常！");
            logger.error("获取数据错误：", e);
        }
        return result;
    }

    public ServiceResult<Pagination<GroundPromotionIncomeDisplaySnDto>> listGroundPromotionIncomeGroupByDisplayNo(GroundPromotionIncomeSearchCondition condition) {
        ServiceResult<Pagination<GroundPromotionIncomeDisplaySnDto>> result = new ServiceResult<Pagination<GroundPromotionIncomeDisplaySnDto>>();
        try {
            result.setResult(groundPromotionIncomeDetailModel
                .listGroundPromotionIncomeGroupByDisplayNo(condition));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("系统异常！");
            logger.error("获取数据错误：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<GroundPromotionIncomeDetail>> listGroundPromotionIncomeByDisplayNo(Integer userId,
                                                                                                 Integer type,
                                                                                                 String displaySn, Integer associateType) {
        ServiceResult<List<GroundPromotionIncomeDetail>> result = new ServiceResult<List<GroundPromotionIncomeDetail>>();
        try {
            result.setResult(groundPromotionIncomeDetailModel
                .listGroundPromotionIncomeByDisplayNo(userId, type, displaySn,associateType));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("系统异常！");
            logger.error("获取数据错误：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Pagination<GroundPromotionIncomeDisplaySnDto>> listPartTimeGroundPromotionIncomeGroupByDisplayNo(Integer userId,
                                                                                                                          int pageNo,
                                                                                                                          int pageSize) {
        ServiceResult<Pagination<GroundPromotionIncomeDisplaySnDto>> result = new ServiceResult<Pagination<GroundPromotionIncomeDisplaySnDto>>();
        try {
            result.setResult(groundPromotionIncomeDetailModel
                .listPartTimeGroundPromotionIncomeGroupByDisplayNo(userId, pageNo, pageSize));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("系统异常！");
            logger.error("获取数据错误：", e);
        }
        return result;
    }

}
