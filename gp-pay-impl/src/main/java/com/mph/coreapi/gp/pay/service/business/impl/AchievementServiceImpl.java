package com.mph.coreapi.gp.pay.service.business.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.dto.AchieveGoodInfoDto;
import com.mph.coreapi.gp.pay.dto.AchiveMemberInfoDto;
import com.mph.coreapi.gp.pay.dto.AchivementSummaryDto;
import com.mph.coreapi.gp.pay.dto.GroundPromotionIncomeDisplaySnDto;
import com.mph.coreapi.gp.pay.dto.OrderInfoDto;
import com.mph.coreapi.gp.pay.dto.PerformanceDto;
import com.mph.coreapi.gp.pay.dto.ProvinceAchivementSummaryDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionAchievement;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.model.GroundPromotionIncomeDetailModel;
import com.mph.coreapi.gp.pay.model.GroundPromotionOrderDaoModel;
import com.mph.coreapi.gp.pay.pojo.TaskAchievementSummary;
import com.mph.coreapi.gp.pay.service.CommonPersonnelService;
import com.mph.coreapi.gp.pay.service.business.AchievementService;
import com.mph.coreapi.gp.pay.service.condition.GroundPromotionIncomeSearchCondition;
import com.mph.coreapi.order.order.service.MphOrderService;
import com.mph.coreapi.order.order.vo.OrderVO;
import com.rogrand.common.ServiceResult;
import com.rogrand.common.page.Pagination;
import com.rogrand.coreapi.ditui.entity.GroundPromotionGoods;
import com.rogrand.coreapi.ditui.entity.TaskInfoFromBI;
import com.rogrand.coreapi.ditui.service.GroundPromotionGoodsService;
import com.rogrand.coreapi.ditui.service.GroundPromotionUserService;
import com.rogrand.coreapi.ditui.service.InfoFromBIService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: AchievementServiceImpl
 * @Description: 业绩查询接口
 * @Author yuting.li
 * @Version 1.0
 * @Date 2018-06-04 19:53
 */
@Service("achievementService")
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    private GroundPromotionOrderDaoModel groundPromotionOrderDaoModel;

    @Autowired
    private GroundPromotionIncomeDetailModel groundPromotionIncomeDetailModel;

    @Autowired
    private GroundPromotionGoodsService groundPromotionGoodsService;

    @Autowired
    private GroundPromotionUserService groundPromotionUserService;

    @Autowired
    private CommonPersonnelService commonPersonnelService;

    @Autowired
    private MphOrderService mphOrderService;

    @Autowired
    private InfoFromBIService infoFromBIService;

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private static final Logger logger =LoggerFactory.getLogger(AchievementServiceImpl.class);

    /**
     * @Description: 用户业绩概要数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/4 20:02
     * @param userId    用户id
     * @return com.mph.coreapi.gp.pay.dto.AchivementSummaryDto
     */
    @Override
    public AchivementSummaryDto getUserSummary(Integer userId,String userName){
        AchivementSummaryDto dto = new AchivementSummaryDto();
        try {
            // TODO 内部接口通过用户获取待发放金额
            Map<String,Object> params =new HashMap<String,Object>();
            params.put("userId",userId);
            GroundPromotionIncomeDetail groundPromotionIncomeDetail= commonPersonnelService.issuedAmount(params);
            Double toPayIncomeAmount = groundPromotionIncomeDetail.getIssuedAmount();
            dto.setToPayIncomeAmount(toPayIncomeAmount);
            TaskAchievementSummary<Double> memberSummary = setUserSummary(userId,userName,1);
            TaskAchievementSummary<Double> goodSummary = setUserSummary(userId,userName,2);
            dto.setMemberSummary(memberSummary);
            dto.setGoodSummary(goodSummary);
            Double ms = memberSummary.getCurrentMonthKpiPercent();
            Double gs = goodSummary.getCurrentMonthKpiPercent();
            Double currentMonthKpiPercent = addDivide(ms,gs);
            dto.setCurrentMonthKpiPercent(currentMonthKpiPercent);
            Double lms = memberSummary.getLastMonthKpiPercent();
            Double lgs = goodSummary.getLastMonthKpiPercent();
            Double lastMonthKpiPercent = addDivide(lms,lgs);
            dto.setLastMonthKpiPercent(lastMonthKpiPercent);
        } catch (Exception e) {
            logger.error("获取用户业绩概要数据错误",e);
        }
        return dto;
    }

    /**
     * @Description: 用户业绩概要数据汇总
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/4 20:09
     * @param userId 用户id
     * @param userName 用户名称
     * @param type 类型 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
     * @return com.mph.coreapi.gp.pay.pojo.TaskAchievementSummary
     */
    private TaskAchievementSummary setUserSummary(Integer userId,String userName,Integer type){
        Integer biType = null;
        if (type == 1){
            biType = 1;
        }

        if (type == 2){
            biType = 3;
        }

        Map<String,Object> param = new HashMap<>();
        param.put("type",type);
        param.put("userId",userId);
        param.put("date",df.format(new Date()));
        //TODO 获取内部当月接口数据
        Double quantityCurrentMonth = getSummaryByParam(param);
        //TODO 获取外部接口当月BI数据
        Double expectedQuantityCurrentMonth = getByUserNameBI(userName,1,biType);
        param.put("date",df.format(getMonthBefore(new Date())));
        //TODO 获取内部上月接口数据
        Double quantityLastMonth = getSummaryByParam(param);
        //TODO 获取外部接口上月BI数据
        Double expectedQuantityLastMonth = getByUserNameBI(userName,2,type);
        TaskAchievementSummary tas = setTaskAch(quantityCurrentMonth,expectedQuantityCurrentMonth,quantityLastMonth,expectedQuantityLastMonth);
        return tas;
    }


    /**
     * @Description: 获取汇总数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 19:35
     * @param param
     * @return java.lang.Double
     */
    private Double getSummaryByParam(Map<String,Object> param){
        Double result = 0D;
        List<GroundPromotionIncomeDetail> list = groundPromotionIncomeDetailModel.listGroundPromotionIncomeDetailByDate(param);
        Integer type=Integer.parseInt(param.get("type").toString());
        if(null != list && list.size() > 0){
            if(type==1){
                result = Double.valueOf(list.size());
            }
            else if(type==2){
                for (GroundPromotionIncomeDetail item :list){
                    result=result+item.getSaleAmount();
                }
            }
        }
        return result;
    }

    /**
     * 通过BI获取用户数据
     * @param userName 账号
     * @param monthType 月份类型：1，当月；2，上月
     * @param taskType 任务类型
     * @return
     */
    private Double getByUserNameBI(String userName, Integer monthType, Integer taskType){
        ServiceResult<TaskInfoFromBI> result = infoFromBIService.getTaskInfoByType(userName,monthType,taskType);
        if(null != result && null != result.getResult()){
            TaskInfoFromBI bi = result.getResult();
            return Double.valueOf(null == bi.getTaskQuantity() ? "0" : bi.getTaskQuantity().toString());
        }
        return 0D;
    }

    /**
     * 通过BI获取省份数据
     * @param provinceName 省名称
     * @param monthType 月份类型：1，当月；2，上月
     * @param taskType 任务类型
     * @return
     */
    private Double getByProvinceNameBI(String provinceName, Integer monthType, Integer taskType){
        ServiceResult<TaskInfoFromBI> result = infoFromBIService.getProvinceTaskInfoByType(provinceName,monthType,monthType);
        if(null != result && null != result.getResult()){
            TaskInfoFromBI bi = result.getResult();
            return Double.valueOf(null == bi.getTaskQuantity() ? "0" : bi.getTaskQuantity().toString());
        }
        return 0D;
    }

    /**
     * @Description: 设置数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/4 20:28
     * @param quantityCurrentMonth
     * @param expectedQuantityCurrentMonth,
     * @param quantityLastMonth,
     * @param expectedQuantityLastMonth
     * @return com.mph.coreapi.gp.pay.pojo.TaskAchievementSummary
     */
    private TaskAchievementSummary setTaskAch(Double quantityCurrentMonth,Double expectedQuantityCurrentMonth,Double quantityLastMonth,Double expectedQuantityLastMonth){
        TaskAchievementSummary tas = new TaskAchievementSummary();
        //当月kpi已完成额度
        Double currentMonthKpiPercent = divide(quantityCurrentMonth,expectedQuantityCurrentMonth);
        tas.setCurrentMonthKpiPercent(currentMonthKpiPercent);
        tas.setQuantityCurrentMonth(quantityCurrentMonth);
        tas.setExpectedQuantityCurrentMonth(expectedQuantityCurrentMonth);
        //上月kpi完成额度
        Double lastMonthKpiPercent = divide(quantityCurrentMonth,expectedQuantityCurrentMonth);
        tas.setQuantityLastMonth(quantityLastMonth);
        tas.setExpectedQuantityLastMonth(expectedQuantityLastMonth);
        tas.setLastMonthKpiPercent(lastMonthKpiPercent);
        return tas;
    }

    /**
     * @Description: 省业绩概要
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/4 20:45
     * @param provinceId 省区id
     * @return
     */
    public ProvinceAchivementSummaryDto provinceSummary(Integer provinceId,String provinceName){
        ProvinceAchivementSummaryDto dto = new ProvinceAchivementSummaryDto();
        try {
            // TODO 外部接口通过获取用户
            Integer userCount = getUserCount(provinceId);
            dto.setUserCount(userCount);
            TaskAchievementSummary<Double> memberSummary = setProMemberSummary(provinceId,provinceName,1);
            TaskAchievementSummary<Double> goodSummary = setProMemberSummary(provinceId,provinceName,2);
            dto.setMemberSummary(memberSummary);
            dto.setGoodSummary(goodSummary);
            Double ms = memberSummary.getCurrentMonthKpiPercent();
            Double gs = goodSummary.getCurrentMonthKpiPercent();
            Double currentMonthKpiPercent = addDivide(ms,gs);
            dto.setCurrentMonthKpiPercent(currentMonthKpiPercent);
            Double lms = memberSummary.getLastMonthKpiPercent();
            Double lgs = goodSummary.getLastMonthKpiPercent();
            Double lastMonthKpiPercent = addDivide(lms,lgs);
            dto.setLastMonthKpiPercent(lastMonthKpiPercent);
        } catch (Exception e) {
            logger.error("获取省业绩概要数据错误",e);
        }
        return dto;
    }

    /**
     * @Description: 根据省份id获取成员数
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 18:15
     * @param provinceId
     * @return java.lang.Integer
     */
    private Integer getUserCount(Integer provinceId){
        Integer userCount = 0;
        List<Integer> ids = new ArrayList<>(1);
        ids.add(provinceId);
        ServiceResult<Map<Integer, Integer>> result = groundPromotionUserService.countProvinceUser(ids);
        if(null != result && result.getResult() != null){
            Map<Integer, Integer> map = result.getResult();
            userCount = map.get(provinceId);
        }
        return userCount;
    }

    /**
     * @Description: 省业绩概要汇总
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/4 20:09
     * @param provinceId 省份id
     * @param provinceName 省份名称
     * @param type 类型 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
     * @return
     */
    private TaskAchievementSummary setProMemberSummary(Integer provinceId,String provinceName,Integer type){
        Map<String,Object> param = new HashMap<>();
        param.put("type",type);
        param.put("provinceId",provinceId);
        param.put("userJobType",1);
        param.put("date",df.format(new Date()));
        //TODO 获取内部接口当月数据
        Double quantityCurrentMonth = getSummaryByParam(param);
        //TODO 获取外部接口当月BI数据
        Double expectedQuantityCurrentMonth = getByProvinceNameBI(provinceName,1,type);
        //TODO 获取内部接口上月数据
        param.put("date",df.format(getMonthBefore(new Date())));
        Double quantityLastMonth = getSummaryByParam(param);
        //TODO 获取外部接口上月BI数据
        Double expectedQuantityLastMonth = getByProvinceNameBI(provinceName,2,type);
        TaskAchievementSummary tas = setTaskAch(quantityCurrentMonth,expectedQuantityCurrentMonth,quantityLastMonth,expectedQuantityLastMonth);
        return tas;
    }


    /**
     * @Description: 用户的专区商品排名
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 11:17
     * @param userId
     * @return com.mph.coreapi.gp.pay.dto.OrderInfoDto<java.lang.Double>
     */
    @Override
    public OrderInfoDto<Double> getGoodsOrderForUser(Integer userId){
        OrderInfoDto<Double> dto = new OrderInfoDto<>();
        try {
            //收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
            Integer type = 2;
            dto = convertGroundProAch(type,userId);
        } catch (Exception e) {
            logger.error("获取用户的专区商品排名错误",e);
        }
        return dto;
    }

    /**
     * @Description: 用户的vip/svip发展数量排名
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 9:37
     * @param userId
     * @return com.mph.coreapi.gp.pay.dto.OrderInfoDto<java.lang.Double>
     */
    @Override
    public OrderInfoDto<Double> getMemberOrderForUser(Integer userId){
        OrderInfoDto<Double> dto = new OrderInfoDto<>();
        try {
            //收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
            Integer type = 1;
            dto = convertGroundProAch(type,userId);
        } catch (Exception e) {
            logger.error("获取用户的vip/svip发展数量排名错误",e);
        }
        return dto;
    }

    /**
     * @Description: 转为dto
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 9:37
     * @param type
     * @param userId
     * @return
     */
    private OrderInfoDto<Double> convertGroundProAch(Integer type,Integer userId){
        GroundPromotionAchievement gpa = groundPromotionOrderDaoModel.getByUserId(userId,type,null);
        OrderInfoDto<Double> dto = convertDto(gpa);
        return dto;
    }

    private OrderInfoDto<Double> convertDto(GroundPromotionAchievement gpa){
        OrderInfoDto<Double> dto = new OrderInfoDto<>();
        if(null != gpa){
            dto.setOrderInProvince(gpa.getOrderInProvince());
            if (gpa.getType() == 1){
                dto.setQuantity(gpa.getQuatity().doubleValue());
            } else {
                if (gpa.getType() == 2){
                    dto.setQuantity(gpa.getAmount().doubleValue());
                } else { // 其他未知，先这么着吧
                    dto.setQuantity(gpa.getAmount().doubleValue());
                }
            }

            dto.setOrderChange(gpa.getOrderChange());
            dto.setUserName(gpa.getUserName());
        }
        return dto;
    }

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
    @Override
    public List<OrderInfoDto<Double>> getMemberOrderForProvince(Integer provinceId,Integer startOrder,Integer endOrder){
        List<OrderInfoDto<Double>> list = new ArrayList<>(1);
        try {
            //收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
            Integer type = 1;
            list = convertProData(type,provinceId,startOrder,endOrder);
        } catch (Exception e) {
            logger.error("获取省内的vip/svip发展数量排名错误",e);
        }
        return list;
    }

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
    @Override
    public List<OrderInfoDto<Double>> getGoodsOrderForProvince(Integer provinceId,Integer startOrder,Integer endOrder){
        List<OrderInfoDto<Double>> list = new ArrayList<>(1);
        try {
            //收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
            Integer type = 2;
            list = convertProData(type,provinceId,startOrder,endOrder);
        } catch (Exception e) {
            logger.error("获取省内的专区商品排名错误",e);
        }
        return list;
    }

    /**
     * @Description: convert
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 11:27
     * @param type
     * @param provinceId
     * @param startOrder
     * @param endOrder
     * @return java.util.List<com.mph.coreapi.gp.pay.dto.OrderInfoDto<java.lang.Integer>>
     */
    private List<OrderInfoDto<Double>> convertProData(Integer type,Integer provinceId,Integer startOrder,Integer endOrder){
        List<OrderInfoDto<Double>> list = new ArrayList<>(1);
        List<GroundPromotionAchievement> gpaList = groundPromotionOrderDaoModel.getByProvinceId(type,provinceId,startOrder,endOrder);
        if(null != gpaList && gpaList.size() > 0){
            for (GroundPromotionAchievement gpa : gpaList){
                OrderInfoDto<Double> dto = convertDto(gpa);
                list.add(dto);
            }
        }
        return list;
    }

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
    @Override
    public Pagination<AchiveMemberInfoDto> getUserMemberList(Integer userId,Integer associateType,Integer pageNo,Integer pageSize){
        Integer totalCount = 0;
        Pagination<AchiveMemberInfoDto> pagination = null;
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("type",1);
            param.put("userId",userId);
            param.put("associateType",associateType);
            totalCount = groundPromotionIncomeDetailModel.countByParamPage(param);
            pagination = new Pagination<>(pageNo, pageSize, totalCount);
            param.put("start",pagination.getFirstResult());
            param.put("pageSize",pageSize);
            if(0 != totalCount){
                List<GroundPromotionIncomeDetail> list = groundPromotionIncomeDetailModel.getByParamPage(param);
                pagination.setList(covertAchPage(list));
            } else {
                List<AchiveMemberInfoDto> list = new ArrayList<AchiveMemberInfoDto>();
                pagination.setList(list);
            }
        } catch (Exception e) {
            logger.error("首页业绩查询vip/svip 业绩错误：",e);
        }
        return pagination;
    }

    private List<AchiveMemberInfoDto> covertAchPage(List<GroundPromotionIncomeDetail> list){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<AchiveMemberInfoDto> dtoList = new ArrayList<>(1);
        if(null != list && list.size() > 0){
            for(GroundPromotionIncomeDetail gpd : list){
                AchiveMemberInfoDto dto = new AchiveMemberInfoDto();
                dto.setCreateTime(gpd.getCreateTime());
                dto.setName(gpd.getItemName());
                dto.setDueTime(gpd.getDueTime());
                dto.setOperUserName(gpd.getOperUserName());
                dto.setItemName(gpd.getItemName());
                dto.setAssociateType(gpd.getAssociateType());
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

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
    @Override
    public Pagination<AchiveMemberInfoDto> getProvinceMemberList(Integer provinceId,Integer cityId,List<Integer> regionIdList,Integer associateType,Integer pageNo,Integer pageSize){
        Integer totalCount = 0;
        Pagination<AchiveMemberInfoDto> pagination = null;
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("type",1);
            param.put("provinceId",provinceId);
            param.put("cityId",cityId);
            if(regionIdList!=null&&regionIdList.size()>0) {
                param.put("regionIds", regionIdList);
            }
            param.put("associateType",associateType);
            param.put("userJobType",1);
            totalCount = groundPromotionIncomeDetailModel.countByParamPage(param);
            pagination = new Pagination<>(pageNo, pageSize, totalCount);
            param.put("start",pagination.getFirstResult());
            param.put("pageSize",pageSize);
            if(0 != totalCount){
                List<GroundPromotionIncomeDetail> list = groundPromotionIncomeDetailModel.getByParamPage(param);
                pagination.setList(covertAchPage(list));
            } else {
                List<AchiveMemberInfoDto> list = new ArrayList<AchiveMemberInfoDto>();
                pagination.setList(list);
            }
        } catch (Exception e) {
            logger.error("省份vip/svip 业绩错误：",e);
        }
        return pagination;
    }

    /**
     * @Description: 用户专区商品 业绩
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 13:57
     * @param userId
     * @param associateType
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Pagination<AchieveGoodInfoDto> getUserGoodsList(Integer userId, Integer associateType, Integer pageNo, Integer pageSize){
        Integer totalCount = 0;
        Pagination<AchieveGoodInfoDto> pagination = new Pagination<>();
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("type",2);
            param.put("userId",userId);
            param.put("associateType",associateType);
            totalCount = groundPromotionIncomeDetailModel.countByParamPage(param);
            pagination = new Pagination<>(pageNo, pageSize, totalCount);
            param.put("start",pagination.getFirstResult());
            param.put("pageSize",pageSize);
            if(0 != totalCount){
                List<GroundPromotionIncomeDetail> list = groundPromotionIncomeDetailModel.getByParamPage(param);
                pagination.setList(covertGoodPage(list));
            } else {
                List<AchieveGoodInfoDto> list = new ArrayList<AchieveGoodInfoDto>();
                pagination.setList(list);
            }
        } catch (Exception e) {
            logger.error("用户专区商品业绩错误：",e);
            pagination =null;
        }
        return pagination;
    }

    private List<AchieveGoodInfoDto> covertGoodPage(List<GroundPromotionIncomeDetail> list){
        List<AchieveGoodInfoDto> dtoList = new ArrayList<>(1);
        if(null != list && list.size() > 0){
            for(GroundPromotionIncomeDetail gpd : list){
                AchieveGoodInfoDto dto = new AchieveGoodInfoDto();
                dto.setOrderSn(gpd.getDisplaySn());
                dto.setPicUrl(gpd.getPicUrl());
                //TODO 需要调用外部接口获取订单信息
                OrderVO orderVO = getByOsn(gpd.getDisplaySn());
                if(null != orderVO){
                    if(null != orderVO.getOaddTime()){
                        dto.setOrderCreateTime(orderVO.getOaddTime());
                    }
                    dto.setBuyerName(orderVO.getOeName());
                }
                String goodId = gpd.getLinkIdentity();
                if(StringUtils.isNotBlank(goodId)){
                    ServiceResult<GroundPromotionGoods> gd = groundPromotionGoodsService.findById(Integer.valueOf(goodId));
                    if(null != gd && gd.getResult() != null){
                        GroundPromotionGoods goods = gd.getResult();
                        dto.setGoodName(goods.getNrName());
                        dto.setManufacturer(goods.getNrProduceUnit());
                        dto.setSpecification(goods.getNrSpecifications());
                    }
                }

                dto.setOperUserName(gpd.getOperUserName());
                dto.setBenifitAmount(gpd.getAmount()+"");
                dto.setItemName(gpd.getItemName());
                dto.setAssociateType(gpd.getAssociateType());
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    @Override
    public Pagination<GroundPromotionIncomeDisplaySnDto> getUserGoodsListByOrder(Integer userId, Integer associateType,
                                                                                 Integer pageNo, Integer pageSize) {
       
        
        GroundPromotionIncomeSearchCondition condition = new GroundPromotionIncomeSearchCondition();
        condition.setUserId(userId);
        //只查专区商品数据，type为2，associateType为前端传值
        List<Integer> typeList = new ArrayList<Integer>(1);
        typeList.add(2);
        condition.setTypes(typeList);
        condition.setAssociateType(associateType);
        condition.setPageNo(pageNo);
        condition.setPageSize(pageSize);
        return groundPromotionIncomeDetailModel.listGroundPromotionIncomeGroupByDisplayNo(condition );
    }

    /**
     * @Description: 通过订单获取信息
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/6 10:20
     * @param osn mph订单号
     * @return com.mph.coreapi.order.order.vo.OrderVO
     */
    private OrderVO getByOsn(String osn){
        OrderVO orderVO = new OrderVO();
        List<String> osnList = new ArrayList<>(1);
        osnList.add(osn);
        ServiceResult<List<OrderVO>> list = mphOrderService.listOrderByOsns(osnList);
        if(null != list && list.getResult() != null){
            List<OrderVO> orderList = list.getResult();
            if(null != orderList && orderList.size() > 0){
                orderVO = orderList.get(0);
            }
        }
        return orderVO;
    }

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
    @Override
    public Pagination<AchieveGoodInfoDto> getProvinceGoodsList(Integer provinceId,Integer cityId,List<Integer> regionIdList,Integer associateType, Integer pageNo, Integer pageSize){
        Integer totalCount = 0;
        Pagination<AchieveGoodInfoDto> pagination = new Pagination<>();
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("type",2);
            param.put("provinceId",provinceId);
            param.put("cityId",cityId);
            if(regionIdList!=null&&regionIdList.size()>0){
                param.put("regionIds",regionIdList);
            }
            param.put("associateType",associateType);
            param.put("userJobType",1);
            totalCount = groundPromotionIncomeDetailModel.countByParamPage(param);
            pagination = new Pagination<>(pageNo, pageSize, totalCount);
            param.put("start",pagination.getFirstResult());
            param.put("pageSize",pageSize);
            if(0 != totalCount){
                List<GroundPromotionIncomeDetail> list = groundPromotionIncomeDetailModel.getByParamPage(param);
                pagination.setList(covertGoodPage(list));
            } else {
                List<AchieveGoodInfoDto> list = new ArrayList<AchieveGoodInfoDto>();
                pagination.setList(list);
            }
        } catch (Exception e) {
            logger.error("省份专区商品业绩错误：",e);
            pagination = null;
        }
        return pagination;
    }

    @Override
    public PerformanceDto getPerformanceDetail(Integer userId, String key, Integer incomeType) {
        return groundPromotionIncomeDetailModel.getPerformanceDetail(userId,key,incomeType);
    }

    /**
     * @Description: 除法计算
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/4 20:20
     * @param a
     * @param b
     * @return java.lang.Double
     */
    private Double divide(Double a,Double b){
        Double d = 0D;
        BigDecimal abig = new BigDecimal(a);
        BigDecimal bbig = new BigDecimal(b);
        BigDecimal result = bbig.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : abig.divide(bbig).setScale(2,BigDecimal.ROUND_HALF_UP);
        return result.doubleValue();
    }

    /**
     * @Description: 计算金额
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/4 20:41
     * @param
     * @return
     */
    private Double addDivide(Double a,Double b){
        Double d = 0D;
        BigDecimal abig = new BigDecimal(a);
        BigDecimal bbig = new BigDecimal(b);
        BigDecimal addResult = abig.add(bbig);
        BigDecimal result = addResult.divide(new BigDecimal(2)).setScale(2,BigDecimal.ROUND_HALF_UP);
        return result.doubleValue();
    }

    private Date getMonthBefore(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //月份减一
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }



}
