package com.mph.coreapi.gp.pay.service;

import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.dto.AntPayInfoDto;
import com.mph.coreapi.gp.pay.dto.DaySummaryDto;
import com.mph.coreapi.gp.pay.dto.TaskProgressInfoDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.entity.OrderSpecificProductInfo;
import com.rogrand.common.ServiceResult;
import com.rogrand.common.page.Pagination;

/**
 * @ClassName: GroundPromotionIncomeDetailService
 * @Description: 地推收入明细接口
 * @Author yuting.li
 * @Version 1.0
 * @Date 2018-06-04 15:15
 */
public interface GroundPromotionIncomeDetailService {

    /**
     * @Description: 添加地推收入明细数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/1 13:37
     * @param order
     * @return com.rogrand.common.ServiceResult<java.lang.String>
     */
    ServiceResult<String> saveOrderSpecificProductInfo(OrderSpecificProductInfo order);

    
    /**
     * 地推人员绩效数据入库服务
     * @param groundUserId 地推人员Uid
     * @param performanceActiveId 绩效计划活动ID
     * @param amount    绩效奖励金额
     * @param memo  收益备注（例如：在30天内达成线上交易额大于300万奖励)
     * @param saleAmount 销售额
     * @param saleNum   销售频次
     * @return
     */
    ServiceResult<Boolean> givenGroundUserPerformance(Integer groundUserId,
                                                    String performanceActiveId,
                                                    Double amount, String memo, Double saleAmount, Integer saleNum);
    
    

    /**
     * 根据用户ID得到任务完成度
     * @param userId
     * @return
     */
    List<TaskProgressInfoDto> getIncomeListByUserId(Integer userId,String UserName);

    /**
     * 蚂蚁人员提现信息
     * @param userId
     */
    AntPayInfoDto getIncomeInfo(Integer userId);

    /**
     * 根据条件查询数据
     * @param param
     * @return
     */
    List<DaySummaryDto> groundPromotionIncomeDetailList(Map<String,Object> param);

    /**
     * 得到收支明细
     * @param params
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServiceResult<Pagination> getIOInfoList(Map<String,Object> params, int pageNo, int pageSize);

    /**
     * 判断今天是否是当月最后一天
     * @return
     */
    Boolean monthLastDay();
    /**
     * 获取订单维度的分页数据
     * @param userId
     * @param associateType
     * @param pageNo
     * @param pageSize
     * @return
     * @author dachao.peng
     */
    ServiceResult<Pagination<GroundPromotionIncomeDetailForOrderDimensionalityDTO>> queryPageForOrderDimensionality(Integer userId,
                                                                                                                 Integer associateType,
                                                                                                                 Integer pageNo,
                                                                                                                 Integer pageSize);

}
