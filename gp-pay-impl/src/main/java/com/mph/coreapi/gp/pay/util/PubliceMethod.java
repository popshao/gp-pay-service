package com.mph.coreapi.gp.pay.util;

import java.util.ArrayList;
import java.util.List;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;

import com.rogrand.common.ServiceResult;
import com.rogrand.common.util.ConvertUtil;
import com.rogrand.coreapi.ditui.entity.GroundPromotionCommissionConfig;
import com.rogrand.coreapi.ditui.entity.GroundPromotionUser;
import com.rogrand.coreapi.ditui.service.GroundPromotionCommissionConfigService;
import com.rogrand.coreapi.ditui.service.GroundPromotionUserService;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-06-04 0004
 * @UpdateDate: 2019-5-22 提成修改为三级提成
 * @Version: 1.1
 */
public  class PubliceMethod {
    
    /**
     * 根据userInfo bussinessType orderAmount得到income需要插入的数据
     * @param groundPromotionCommissionConfigService
     * @param userinfo
     * @param bussinessType
     * @param orderAmount
     * @param goodId
     * @param groundPromotionCommissionConfig
     * @param goodCount
     * @param groundPromotionUserService
     * @return
     */
    public static List<GroundPromotionIncomeDetail> getIncomeInfoDetail(GroundPromotionCommissionConfigService groundPromotionCommissionConfigService, GroundPromotionUser userinfo, Integer bussinessType, Double orderAmount, Integer goodId, GroundPromotionCommissionConfig groundPromotionCommissionConfig, Integer goodCount,GroundPromotionUserService groundPromotionUserService){

        List<GroundPromotionIncomeDetail> incomeList=new ArrayList<GroundPromotionIncomeDetail>();
        if (goodId == null){
            goodId = 0;
        }

        // 专区商品不给动态取配置
        if (groundPromotionCommissionConfig == null && bussinessType != 2){
            ServiceResult<GroundPromotionCommissionConfig> bestConfig = groundPromotionCommissionConfigService.getBestConfig(userinfo.getUserId(), goodId, bussinessType);
            if (bestConfig != null && bestConfig.getSuccess()){
                groundPromotionCommissionConfig = bestConfig.getResult();
            }
        }

        if(groundPromotionCommissionConfig!=null) {
            getListIncome(incomeList,groundPromotionCommissionConfig, userinfo, bussinessType, orderAmount, goodCount,groundPromotionUserService);
        }

        return incomeList;
    }

    /**
     * 得到收入列表
     * @param groundPromotionCommissionConfig
     * @param userinfo
     * @param bussinessType
     * @param orderAmount
     * @return
     */
    public static List<GroundPromotionIncomeDetail> getListIncome(List<GroundPromotionIncomeDetail> incomeList,GroundPromotionCommissionConfig groundPromotionCommissionConfig,GroundPromotionUser userinfo,Integer bussinessType,Double orderAmount, Integer count,GroundPromotionUserService groundPromotionUserService){
        //个人提成
        GroundPromotionIncomeDetail incomeItem=new GroundPromotionIncomeDetail();
        //个人上级提成
        GroundPromotionIncomeDetail incomeLeadItem=new GroundPromotionIncomeDetail();
        //三级提成
        GroundPromotionIncomeDetail incomeTopItem=new GroundPromotionIncomeDetail();


        //个人提成
        incomeItem.setPayStatus(0);
        if(ConvertUtil.toInt(groundPromotionCommissionConfig.getComputeType(), 0).intValue() == 1){
            incomeItem.setAmount(orderAmount*groundPromotionCommissionConfig.getPercentVal()/100);
        }else if (ConvertUtil.toInt(groundPromotionCommissionConfig.getComputeType(), 0).intValue()==2){
            incomeItem.setAmount(groundPromotionCommissionConfig.getFixVal()*count);
        } else if(ConvertUtil.toInt(groundPromotionCommissionConfig.getComputeType(), 0).intValue()==3){
            incomeItem.setAmount(NumberUtils.add(orderAmount*groundPromotionCommissionConfig.getPercentVal()/100,groundPromotionCommissionConfig.getFixVal()*count));
        }else{
            incomeItem.setAmount(0.00);
            incomeItem.setPayStatus(2);
        }
        setIncomeObject(incomeItem,userinfo,0,userinfo.getUserJobType());
        incomeItem.setUserId(userinfo.getUserId());
        incomeItem.setSourceUserId(userinfo.getUserId());
        incomeItem.setSourceUserName(userinfo.getUserFullName());
        incomeList.add(incomeItem);
        
        //二级提成（兼职人员的三级才有提成）
        GroundPromotionUser leadUserInfo=null;
        if(ConvertUtil.toInt(userinfo.getUserJobType(), 0).intValue() == 2 && userinfo.getRecommandId() != null){
            ServiceResult<GroundPromotionUser> leadUserServiceResult = null;//二级人员对象
            if(ConvertUtil.toInt(userinfo.getUserJobType(), 0).intValue() == 1) {
                if(userinfo.getUserSuperiorId() != null) {
                    leadUserServiceResult = groundPromotionUserService.getUser(userinfo.getUserSuperiorId());
                }
            }else if(ConvertUtil.toInt(userinfo.getUserJobType(), 0).intValue() == 2) {
                if(userinfo.getRecommandId() != null) {
                    leadUserServiceResult = groundPromotionUserService.getUser(userinfo.getRecommandId());
                }
            }
            if(leadUserServiceResult != null && leadUserServiceResult.getSuccess() && leadUserServiceResult.getResult() != null) {
                leadUserInfo = leadUserServiceResult.getResult();
            }

            if(leadUserInfo != null) {
                incomeLeadItem.setPayStatus(0);
                if(ConvertUtil.toInt(groundPromotionCommissionConfig.getComputeTypeLead(), 0).intValue() == 1){
                    incomeLeadItem.setAmount(orderAmount*groundPromotionCommissionConfig.getPercentValLead()/100);
                }else if(ConvertUtil.toInt(groundPromotionCommissionConfig.getComputeTypeLead(), 0).intValue()==2){
                    incomeLeadItem.setAmount(groundPromotionCommissionConfig.getFixValLead()*count);
                } else if(ConvertUtil.toInt(groundPromotionCommissionConfig.getComputeTypeLead(), 0).intValue()==3){
                    incomeLeadItem.setAmount(NumberUtils.add(groundPromotionCommissionConfig.getFixValLead()*count,orderAmount*groundPromotionCommissionConfig.getPercentValLead()/100));
                } else if(ConvertUtil.toInt(groundPromotionCommissionConfig.getComputeTypeLead(), 1).intValue()==0){
                    incomeLeadItem.setAmount(0.00);
                    incomeLeadItem.setPayStatus(2);
                }
                leadUserInfo =leadUserServiceResult.getResult();
                setIncomeObject(incomeLeadItem,userinfo,1,leadUserInfo.getUserJobType());
                incomeLeadItem.setUserId(userinfo.getRecommandId());
                incomeLeadItem.setSourceUserId(userinfo.getUserId());
                incomeLeadItem.setSourceUserName(userinfo.getUserFullName());
                incomeList.add(incomeLeadItem);
//                if(incomeLeadItem.getAmount().doubleValue() > 0) {
//                    //20190620 杨煜怡 强烈要求把这个零元的收益去掉！！！
//                    incomeList.add(incomeLeadItem);
//                }
            }
        }
        
        //三级提成
        GroundPromotionUser topUserInfo=null;
        if(ConvertUtil.toInt(groundPromotionCommissionConfig.getEnableTopFlag(), 0).intValue() == 1 
                && ConvertUtil.toInt(groundPromotionCommissionConfig.getComputeTypeTop(), 0).intValue() != 0
                && leadUserInfo != null){
            ServiceResult<GroundPromotionUser> topUserServiceResult = null;//二级人员对象
            if(ConvertUtil.toInt(leadUserInfo.getUserJobType(), 0).intValue() == 1) {
                if(leadUserInfo.getUserSuperiorId() != null) {
                    topUserServiceResult = groundPromotionUserService.getUser(leadUserInfo.getUserSuperiorId());
                }
            }else if(ConvertUtil.toInt(leadUserInfo.getUserJobType(), 0).intValue() == 2) {
                if(leadUserInfo.getRecommandId() != null) {
                    topUserServiceResult = groundPromotionUserService.getUser(leadUserInfo.getRecommandId());
                }
            }
            if(topUserServiceResult != null && topUserServiceResult.getSuccess() && topUserServiceResult.getResult() != null) {
                topUserInfo = topUserServiceResult.getResult();
            }
            
            if(topUserInfo != null) {
                incomeTopItem.setPayStatus(0);
                Double incomeAmount=0d;
                if(ConvertUtil.toInt(ConvertUtil.toInt(groundPromotionCommissionConfig.getComputeTypeTop(), 0).intValue(), 0).intValue() ==1){
                    incomeAmount=orderAmount*groundPromotionCommissionConfig.getPercentValTop()/100;
                }else if(ConvertUtil.toInt(groundPromotionCommissionConfig.getComputeTypeTop(), 0).intValue()==2){
                    incomeAmount=groundPromotionCommissionConfig.getFixValTop()*count;
                }
                else if(ConvertUtil.toInt(groundPromotionCommissionConfig.getComputeTypeTop(), 0).intValue()==3){
                    incomeAmount=NumberUtils.add(groundPromotionCommissionConfig.getFixValTop()*count,orderAmount*groundPromotionCommissionConfig.getPercentValTop()/100);
                }
                //20190620 杨煜怡 强烈要求把这个零元的收益去掉！！！
                setIncomeObject(incomeTopItem,userinfo,1,1);
                incomeTopItem.setAmount(incomeAmount);
                incomeTopItem.setUserId(topUserInfo.getUserId());
                incomeTopItem.setSourceUserId(leadUserInfo.getUserId());
                incomeTopItem.setSourceUserName(leadUserInfo.getUserFullName());
                incomeList.add(incomeTopItem);
            }
        }
        return incomeList;
    }

    public static void setIncomeObject(GroundPromotionIncomeDetail incomeItem,GroundPromotionUser operUserInfo,Integer associateType,Integer userJobType){
        incomeItem.setUserJobType(userJobType);
        incomeItem.setAssociateType(associateType);
        incomeItem.setUserProvinceId(operUserInfo.getProvinceId());
        incomeItem.setUserCityId(operUserInfo.getCityId());
        incomeItem.setUserRegionId(operUserInfo.getRegionId());
        incomeItem.setOperUserId(operUserInfo.getUserId());
        incomeItem.setOperUserName(operUserInfo.getUserFullName());
        incomeItem.setPayStatus(0);
    }
}
