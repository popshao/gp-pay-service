package com.mph.coreapi.gp.pay.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mph.coreapi.gp.pay.dto.AntPayInfoDto;
import com.mph.coreapi.gp.pay.dto.CashFlowDetailDto;
import com.mph.coreapi.gp.pay.dto.DaySummaryDto;
import com.mph.coreapi.gp.pay.dto.TaskProgressInfoDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionBankInfo;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayConfig;
import com.mph.coreapi.gp.pay.entity.OrderSpecificProductInfo;
import com.mph.coreapi.gp.pay.model.GroundPromotionBankInfoModel;
import com.mph.coreapi.gp.pay.model.GroundPromotionIncomeDetailModel;
import com.mph.coreapi.gp.pay.model.GroundPromotionPayConfigModel;
import com.mph.coreapi.gp.pay.model.component.GroundUserPerformanceComponent;
import com.mph.coreapi.gp.pay.pojo.GroundPromotionIncomeDetailForOrderDimensionality;
import com.mph.coreapi.gp.pay.service.GroundPromotionIncomeDetailForOrderDimensionalityDTO;
import com.mph.coreapi.gp.pay.service.GroundPromotionIncomeDetailService;
import com.mph.coreapi.gp.pay.util.PubliceMethod;
import com.rogrand.common.ServiceResult;
import com.rogrand.common.page.Pagination;
import com.rogrand.coreapi.ditui.entity.GroundPromotionCommissionConfig;
import com.rogrand.coreapi.ditui.entity.GroundPromotionUser;
import com.rogrand.coreapi.ditui.entity.TaskInfoFromBI;
import com.rogrand.coreapi.ditui.service.GroundPromotionCommissionConfigService;
import com.rogrand.coreapi.ditui.service.GroundPromotionUserService;
import com.rogrand.coreapi.ditui.service.InfoFromBIService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName: OrderSpecificProductInfoServiceImpl
 * @Description: 地推收入明细数据接口
 * @Author yuting.li
 * @Version 1.0
 * @Date 2018-06-01 13:31
 */
@Service("groundPromotionIncomeDetailService")
public class GroundPromotionIncomeDetailServiceImpl implements GroundPromotionIncomeDetailService {

    private static final Logger logger = LoggerFactory.getLogger(GroundPromotionIncomeDetailServiceImpl.class);

    @Autowired
    private GroundPromotionIncomeDetailModel groundPromotionIncomeDetailModel;
    @Autowired
    private GroundPromotionPayConfigModel    groundPromotionPayConfigModel;
    @Autowired
    private GroundPromotionBankInfoModel groundPromotionBankInfoModel;
    @Autowired
    private GroundUserPerformanceComponent groundUserPerformanceComponent;

    @Autowired
    private GroundPromotionCommissionConfigService groundPromotionCommissionConfigService;

    @Autowired
    private InfoFromBIService infoFromBIService;

    @Value("${withDraw.descUrl}")
    String withDrawDescUrl;

    @Autowired
    private GroundPromotionUserService groundPromotionUserService;

    /**
     * @Description: 添加地推收入明细数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/1 13:37
     * @param order
     * @return com.rogrand.common.ServiceResult<java.lang.String>
     */
    @Override
    public ServiceResult<String> saveOrderSpecificProductInfo(OrderSpecificProductInfo order){
        ServiceResult<String> result = new ServiceResult();
        List<OrderSpecificProductInfo.OrderDetail> list = null;
        try {
            //数据验证
            if(null == order){
                result.setSuccess(false);
                result.setMessage("地推的订单数据为空");
                return result;
            }else{
                list = order.getDetails();
                if(null == list){
                    result.setSuccess(false);
                    result.setMessage("地推的订单数据为空");
                    return result;
                }
            }
            Integer userId = order.getUserId();
            //查询用户的提成规则
            ServiceResult<GroundPromotionUser> userResult = groundPromotionUserService.getUser(userId);
            if(null != userResult && userResult.getResult() != null){
                dupGoodIdSpecificGoodListMerge(list);
                GroundPromotionUser user = userResult.getResult();
                //获取上级信息
                //Integer userSuperiorId = user.getRecommandId();
                //兼职 0非下线推广 有上级（1 下线推广）
                Integer associateType = 0;
                /*if(null != user.getUserJobType() && 2 == user.getUserJobType() && null != userSuperiorId){
                    ServiceResult<GroundPromotionUser> userSuperiorResult = groundPromotionUserService.getUser(userSuperiorId);
                    if(null != userSuperiorResult && userSuperiorResult.getResult() != null){
                        GroundPromotionUser userSuperior = userResult.getResult();
                        //添加上级数据
                        saveGroundProDtl(order,list,userSuperior,1);
                    }
                }*/
                //添加地推人员数据
                saveGroundProDtl(order,list,user,associateType);
            }

        } catch (Exception e) {
            logger.error("添加地推细表数据错误：",e);
        }
        return result;
    }

    /**
     * 某些订单中同一个专区商品以两个订单明细出现了，需要合并掉
     * @param list
     * @return
     */
    private List dupGoodIdSpecificGoodListMerge(List<OrderSpecificProductInfo.OrderDetail> list) {
        Map<String, Integer> goodMap = new HashMap<>();
        list.forEach(detail -> {
            String key = String.valueOf(detail.getGoodId() + "_" + detail.getSuitGid());
            if (goodMap.containsKey(key)) {
                goodMap.put(key, goodMap.get(key) + 1);
            } else {
                goodMap.put(key, 1);
            }
        });

        // 遍历看看是否有相同商品id但是多条的，处理下
        goodMap.forEach((key, value) -> {
            // 相同商品id但是多条
            if (value > 1) {
                OrderSpecificProductInfo.OrderDetail detailItem = null;
                Set<String> odIdSet = new HashSet<>();
                // 提取出订单明细id集合，防止错误数据重复计算
                for (OrderSpecificProductInfo.OrderDetail detail : list) {
                    String tempKey = String.valueOf(detail.getGoodId() + "_" + detail.getSuitGid());
                    if (tempKey.equals(key)) {
                        odIdSet.add(tempKey);
                        detailItem = detail;
                    }
                }

                // 留 detailItem 在list中
                odIdSet.remove(detailItem.getOdId() + detailItem.getSuitGid() + "");
                Iterator<OrderSpecificProductInfo.OrderDetail> iterator = list.iterator();
                while (iterator.hasNext()) {
                    OrderSpecificProductInfo.OrderDetail detail = iterator.next();
                    String tempKey = String.valueOf(detail.getGoodId() + detail.getSuitGid());
                    // 列表中匹配商品id的处理list数据
                    if (tempKey.equals(key)) {
                        if (odIdSet.contains(tempKey)) {
                            detailItem.setQuantity(detailItem.getQuantity() + detail.getQuantity());
                            detailItem.setAmount(detailItem.getAmount() + detail.getAmount());
                            odIdSet.remove(tempKey);
                            iterator.remove();
                        } else { // detailItem 或者 数据搞错相同订单明细id的出现了多条了？
                            if (detailItem != detail) {
                                iterator.remove();
                            }
                        }
                    }
                }
            }
        });

        return list;
    }

    private void saveGroundProDtl(OrderSpecificProductInfo order,List<OrderSpecificProductInfo.OrderDetail> list,GroundPromotionUser user,Integer associateType){
        //收益类型： 1 会员推广 2 专区商品 3 菲加云
        Integer type = 2;
        Integer payStatus = 0;
        Integer kpiFlag = 0;
        if (order.getType() != null && order.getType().equals(0)){
            payStatus = 3;
            kpiFlag = 1;
        }

        if (order.getType() != null && (order.getType().equals(2)||order.getType().equals(3))){
            payStatus = 2;
            kpiFlag = 1;
        }

        for (OrderSpecificProductInfo.OrderDetail ord : list){
            /*if (ord.getQuantity() == 0){
                continue;
            }*/

            List<GroundPromotionIncomeDetail> gpiList =new ArrayList<>();
            GroundPromotionCommissionConfig config = null;
            Integer orderDetailGoodId = ord.getGoodId();
            if (StringUtils.isNotBlank(ord.getDuituiConfig())){
                config = JSON.parseObject(ord.getDuituiConfig(), GroundPromotionCommissionConfig.class);
                if (config.getGoodId() != null && !config.getGoodId().equals(0)){
                    ord.setGoodId(config.getGoodId());
                }
            }
            gpiList=PubliceMethod.getIncomeInfoDetail(groundPromotionCommissionConfigService,user,type,ord.getAmount(), ord.getGoodId(), config, ord.getQuantity(),groundPromotionUserService);
            if(null != gpiList && gpiList.size() > 0){
                //GroundPromotionIncomeDetail gpAmount = gpiList.get(0);
                //amount = gpAmount.getAmount();
                for (GroundPromotionIncomeDetail detail : gpiList){
                    //地推收入
                    GroundPromotionIncomeDetail dtl =  new GroundPromotionIncomeDetail();
                    dtl.setUserId(detail.getUserId());

                    dtl.setItemName(ord.getOdName());
                    dtl.setType(type);
                    dtl.setAssociateType(detail.getAssociateType());//
                    dtl.setCreateTime(new Date());
                    dtl.setAmount(detail.getAmount());//TODO 需要根据公式计算修改
                    dtl.setPayStatus(payStatus);//待提现
                    //对应的商品id
                    dtl.setLinkIdentity(ord.getGoodId()+"");
                    dtl.setLinkIdentity2(orderDetailGoodId+"");
                    dtl.setTypeDetail(type);
                    dtl.setUserProvinceId(user.getProvinceId()==null ? 0 : user.getProvinceId());//根据用户获取
                    dtl.setUserCityId(user.getCityId()==null ? 0 : user.getCityId());//根据用户获取
                    dtl.setUserRegionId(user.getRegionId()==null ? 0 : user.getRegionId());//根据用户获取
                    dtl.setRemark("");
                    dtl.setSaleAmount(ord.getAmount());
                    dtl.setSaleNum(ord.getQuantity());//订单数量
                    dtl.setUserJobType(user.getUserJobType());//职务类型
                    dtl.setIssuedAmount(0D);//TODO
                    dtl.setAuditAmount(0D);//TODO
                    dtl.setCompleteAmount(0D);//TODO
                    dtl.setDisplaySn(order.getOsn());
                    dtl.setOperUserId(user.getUserId()==null ? 0 : user.getUserId());//据用户获取
                    dtl.setOperUserName(user.getUserFullName());//根据用户获取
                    dtl.setSourceUserId(detail.getSourceUserId());//据用户获取
                    dtl.setSourceUserName(detail.getSourceUserName());//根据用户获取
                    dtl.setUpdateTime(new Date());
                    dtl.setPicUrl(ord.getGoodPicUrl());
                    dtl.setUserJobType(detail.getUserJobType());
                    dtl.setKpiFlag(kpiFlag);
                    dtl.setOdId(ord.getOdId());
                    groundPromotionIncomeDetailModel.createGroundPromotionIncomeDetail(dtl);
                }
            }
            
        }
    }

    /**
     * 根据用户ID得到任务完成度
     * @param userId
     * @return
     */
    public List<TaskProgressInfoDto> getIncomeListByUserId(Integer userId,String userName){
        List<GroundPromotionIncomeDetail> incomeDetailList=new ArrayList<GroundPromotionIncomeDetail>();
        incomeDetailList=groundPromotionIncomeDetailModel.listGroundPromotionIncomeDetail(userId,1);
        List<GroundPromotionIncomeDetail> vipIncomeList=new ArrayList<GroundPromotionIncomeDetail>();
        List<GroundPromotionIncomeDetail> goodIncomeList=new ArrayList<GroundPromotionIncomeDetail>();
        Double goodAmount=0.00;
        List<TaskProgressInfoDto> taskProgressInfoDtoList = new ArrayList<>();

        for (GroundPromotionIncomeDetail item :incomeDetailList){
            if(item.getType()==1){
                vipIncomeList.add(item);
            }
            if(item.getType()==2){
                goodIncomeList.add(item);
                goodAmount=goodAmount+item.getSaleAmount();
            }
        }

        if (vipIncomeList == null){
            vipIncomeList = new LinkedList<GroundPromotionIncomeDetail>();
        }

        if(vipIncomeList!=null){
            ServiceResult<TaskInfoFromBI> taskinfoFromBI= infoFromBIService.getTaskInfoByType(userName, 1, 1);
            TaskProgressInfoDto vipIncomeDto=new TaskProgressInfoDto();
            vipIncomeDto.setProgressStandardType(1);
            vipIncomeDto.setUserId(userId);
            vipIncomeDto.setFinishConditionDiscription("成功邀请开通VIP/SVIP会员");
            vipIncomeDto.setTaskTypeDisplay("会员推广");
            vipIncomeDto.setTaskType(1);
            vipIncomeDto.setActualQuantity(vipIncomeList.size());
            if(null != taskinfoFromBI && null != taskinfoFromBI.getResult()){
                vipIncomeDto.setExpectedQuantity(taskinfoFromBI.getResult().getTaskQuantity());
            }

            if (vipIncomeList.size() > 0 || (vipIncomeDto.getExpectedQuantity()!=null&&vipIncomeDto.getExpectedQuantity() > 0)){
                taskProgressInfoDtoList.add(vipIncomeDto);
            }
        }

        if (goodIncomeList == null){
            goodIncomeList = new LinkedList<GroundPromotionIncomeDetail>();
        }
        if(goodIncomeList!=null){
            ServiceResult<TaskInfoFromBI> taskinfoFromBI= infoFromBIService.getTaskInfoByType(userName, 1, 3);
            TaskProgressInfoDto goodIncomeDto=new TaskProgressInfoDto();
            goodIncomeDto.setProgressStandardType(2);
            goodIncomeDto.setUserId(userId);
            goodIncomeDto.setFinishConditionDiscription("达到规定的专区商品销售额");
            goodIncomeDto.setTaskTypeDisplay("专区商品");
            goodIncomeDto.setTaskType(2);
            goodIncomeDto.setActualAmount(goodAmount);
            boolean getBiData = false;
            if(null != taskinfoFromBI && null != taskinfoFromBI.getResult()){
                goodIncomeDto.setExpectedQuantity(taskinfoFromBI.getResult().getTaskQuantity());
                if (taskinfoFromBI.getResult().getTaskQuantity() > 0){
                    getBiData = true;
                }
            }

            if (goodIncomeList.size() > 0 || getBiData){
                taskProgressInfoDtoList.add(goodIncomeDto);
            }
        }

        return taskProgressInfoDtoList;
    }

    /**
     * 蚂蚁人员提现信息
     * @param userId
     */
    public AntPayInfoDto getIncomeInfo(Integer userId){
        GroundPromotionPayConfig payConfig=new GroundPromotionPayConfig();
        payConfig=groundPromotionPayConfigModel.groundPromotionIncomeDetail();
        List<GroundPromotionBankInfo> bankInfoList=new ArrayList<GroundPromotionBankInfo>();
        bankInfoList=groundPromotionBankInfoModel.listGroundPromotionBankInfo(userId);
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("userId",userId);
        GroundPromotionIncomeDetail groundPromotionIncomeDetail=new GroundPromotionIncomeDetail();
        groundPromotionIncomeDetail=groundPromotionIncomeDetailModel.getGroundPromotionIncomeDetailAmount(params);
        AntPayInfoDto antPayInfoDto=new AntPayInfoDto();
        antPayInfoDto.setUserId(userId);
        antPayInfoDto.setWithDrawConditionDesc(withDrawDescUrl);
        antPayInfoDto.setAvailableAmount(groundPromotionIncomeDetail.getIssuedAmount());
        antPayInfoDto.setReceivedAmount(groundPromotionIncomeDetail.getCompleteAmount());
        antPayInfoDto.setReceivingAmount(groundPromotionIncomeDetail.getAuditAmount());
        antPayInfoDto.setWaitingAccountAmount(groundPromotionIncomeDetail.getWaitingAccountAmount());
        antPayInfoDto.setCanWithDraw(0);
        antPayInfoDto.setBindCard(0);
        if (bankInfoList!=null&&bankInfoList.size()>0){
            antPayInfoDto.setBindCard(1);
        }

        if (groundPromotionIncomeDetail != null && payConfig != null){
            if(monthLastDay()&&groundPromotionIncomeDetail.getIssuedAmount()>=payConfig.getVal()&&bankInfoList!=null&&bankInfoList.size()>0)
            {
                antPayInfoDto.setCanWithDraw(1);
            }
        } else {
            if (groundPromotionIncomeDetail == null){
                logger.error("userId " + userId + " failed to get valid groundPromotionIncomeDetail");
            }

            if (payConfig == null){
                logger.error("userId " + userId + " failed to get valid payConfig");
            }
        }

        return antPayInfoDto;
    }

    /**
     * 判断今天是否是当月最后一天
     * @return
     */
    public Boolean monthLastDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        String now=format.format(ca.getTime());
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        if(!now.equals(last)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 根据条件查询数据
     * @param
     * @return
     */
    public List<DaySummaryDto> groundPromotionIncomeDetailList(Map<String,Object> param)
    {
        return groundPromotionIncomeDetailModel.groundPromotionIncomeDetailList(param);
    }

    /**
     * 得到收支明细
     * @param
     * @return
     */
    public ServiceResult<Pagination> getIOInfoList(Map<String,Object> params,int pageNo, int pageSize){
        ServiceResult<Pagination> result = new ServiceResult<Pagination>();
        List<CashFlowDetailDto> list = new ArrayList<CashFlowDetailDto>();

        try {
            int totalCount = groundPromotionIncomeDetailModel.getIOInfoListCount(params);
            // 构建分页数据对象
            Pagination pagination = new Pagination(pageNo, pageSize, totalCount);
            if(0 != totalCount){
                list = groundPromotionIncomeDetailModel.getIOInfoList(params,pagination.getFirstResult(),pageSize);

                pagination.setList(list);
            } else {
                pagination.setList(list);
            }
            result.setResult(pagination);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("系统异常！");
            logger.error("获取数据错误：",e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> givenGroundUserPerformance(Integer groundUserId,
                                                           String performanceActiveId,
                                                           Double amount, String memo, Double saleAmount, Integer saleNum) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(groundUserPerformanceComponent.givenGroundUserPerformance(groundUserId, memo, amount, performanceActiveId, saleAmount, saleNum));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("系统异常！");
            logger.error("获取数据错误：",e);
        }
        return result;
    }

    
    
    @Override
    public ServiceResult<Pagination<GroundPromotionIncomeDetailForOrderDimensionalityDTO>> queryPageForOrderDimensionality(Integer userId,
                                                                                                                           Integer associateType,
                                                                                                                           Integer pageNo,
                                                                                                                           Integer pageSize) {
        ServiceResult<Pagination<GroundPromotionIncomeDetailForOrderDimensionalityDTO>> result = new ServiceResult<>();
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        params.put("associateType", associateType);
        params.put("type", 2);
        int totalCount = groundPromotionIncomeDetailModel.countByParam(params);
        Pagination<GroundPromotionIncomeDetailForOrderDimensionalityDTO> pagination = new Pagination<>(
            pageNo, pageSize, totalCount);

        List<GroundPromotionIncomeDetailForOrderDimensionality> list = groundPromotionIncomeDetailModel
            .listForOrderDimensionality(userId, associateType, pagination.getFirstResult(),
                pageSize);
        List<GroundPromotionIncomeDetailForOrderDimensionalityDTO> dtoList = Lists.newArrayList();
        if (list != null) {
            for (GroundPromotionIncomeDetailForOrderDimensionality groundPromotionIncomeDetailForOrderDimensionality : list) {
                GroundPromotionIncomeDetailForOrderDimensionality dto = new GroundPromotionIncomeDetailForOrderDimensionality();
                BeanUtils.copyProperties(groundPromotionIncomeDetailForOrderDimensionality, dto);

            }
        }
        pagination.setList(dtoList);
        result.setResult(pagination);
        return result;
    }
 



}
