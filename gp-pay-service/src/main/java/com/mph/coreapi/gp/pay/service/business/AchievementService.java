package com.mph.coreapi.gp.pay.service.business;

import java.util.List;

import com.mph.coreapi.gp.pay.dto.AchieveGoodInfoDto;
import com.mph.coreapi.gp.pay.dto.AchiveMemberInfoDto;
import com.mph.coreapi.gp.pay.dto.AchivementSummaryDto;
import com.mph.coreapi.gp.pay.dto.GroundPromotionIncomeDisplaySnDto;
import com.mph.coreapi.gp.pay.dto.OrderInfoDto;
import com.mph.coreapi.gp.pay.dto.PerformanceDto;
import com.mph.coreapi.gp.pay.dto.ProvinceAchivementSummaryDto;
import com.rogrand.common.page.Pagination;

/**
 * @ClassName: AchievementService
 * @Description: 业绩查询接口
 * @Author yuting.li
 * @Version 1.0
 * @Date 2018-06-04 19:53
 */
public interface AchievementService {

    /**
     * @Description: 用户业绩概要数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/4 20:02
     * @param userId    用户id
     * @return com.mph.coreapi.gp.pay.dto.AchivementSummaryDto
     */
    AchivementSummaryDto getUserSummary(Integer userId, String userName);

    /**
     * @Description: 省业绩概要
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/4 20:45
     * @param provinceId 省区id
     * @return
     */
    ProvinceAchivementSummaryDto provinceSummary(Integer provinceId, String provinceName);

    /**
     * @Description: 用户的专区商品排名
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 11:17
     * @param userId
     * @return com.mph.coreapi.gp.pay.dto.OrderInfoDto<java.lang.Double>
     */
    OrderInfoDto<Double> getGoodsOrderForUser(Integer userId);

    /**
     * @Description: 用户的vip/svip发展数量排名
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 9:37
     * @param userId
     * @return com.mph.coreapi.gp.pay.dto.OrderInfoDto<java.lang.Double>
     */
    OrderInfoDto<Double> getMemberOrderForUser(Integer userId);

    /**
     * @Description: 省内的vip/svip发展数量排名
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 11:23
     * @param provinceId 省份id
     * @param startOrder 起始排名
     * @param endOrder  结束排名
     * @return java.util.List<com.mph.coreapi.gp.pay.dto.OrderInfoDto<java.lang.Integer>>
     */
    List<OrderInfoDto<Double>> getMemberOrderForProvince(Integer provinceId, Integer startOrder,
                                                         Integer endOrder);

    /**
     * @Description: 省内的专区商品排名
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 11:23
     * @param provinceId 省份id
     * @param startOrder 起始排名
     * @param endOrder  结束排名
     * @return java.util.List<com.mph.coreapi.gp.pay.dto.OrderInfoDto<java.lang.Integer>>
     */
    List<OrderInfoDto<Double>> getGoodsOrderForProvince(Integer provinceId, Integer startOrder,
                                                        Integer endOrder);

    /**
     * @Description: 用户vip/svip 业绩
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 13:57
     * @param userId
     * @param associateType 0 非下线推广 1 下线推广
     * @param pageNo
     * @param pageSize
     * @return
     */
    Pagination<AchiveMemberInfoDto> getUserMemberList(Integer userId, Integer associateType,
                                                      Integer pageNo, Integer pageSize);

    /**
     * @Description: 省份vip/svip 业绩
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 13:57
     * @param provinceId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Pagination<AchiveMemberInfoDto> getProvinceMemberList(Integer provinceId, Integer cityId,
                                                          List<Integer> regionIdList,
                                                          Integer associateType, Integer pageNo,
                                                          Integer pageSize);

    /**
     * @Description: 用户专区商品 业绩
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 13:57
     * @param userId
     * @param associateType 0 非下线推广 1 下线推广
     * @param pageNo
     * @param pageSize
     * @return
     */
    Pagination<AchieveGoodInfoDto> getUserGoodsList(Integer userId, Integer associateType,
                                                    Integer pageNo, Integer pageSize);



    /** 
     * @Description: 按订单维度查询专区商品提成
     * @Param: [userId, associateType, pageNo, pageSize]
     * @return: com.rogrand.common.page.Pagination<com.mph.coreapi.gp.pay.dto.GroundPromotionIncomeDisplaySnDto> 
     * @Author: qizhi.wang
     * @Date: 2019/6/12 
     */ 
    Pagination<GroundPromotionIncomeDisplaySnDto> getUserGoodsListByOrder(Integer userId, Integer associateType,
                                                                   Integer pageNo, Integer pageSize);



    /**
     * @Description: 省份专区商品 业绩
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 13:57
     * @param provinceId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Pagination<AchieveGoodInfoDto> getProvinceGoodsList(Integer provinceId, Integer cityId,
                                                        List<Integer> regionIdList,
                                                        Integer associateType, Integer pageNo,
                                                        Integer pageSize);

    /** 
     * @Description: 查看绩效详情
     * @Param: [userId,key, incomeType]
     * @return: com.mph.coreapi.gp.pay.dto.PerformanceDto 
     * @Author: qizhi.wang
     * @Date: 2019/6/11 
     */ 
    PerformanceDto getPerformanceDetail(Integer userId, String key, Integer incomeType);

}
