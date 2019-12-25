package com.mph.coreapi.gp.pay.model.component;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.model.GroundPromotionIncomeDetailModel;
import com.rogrand.common.ServiceResult;
import com.rogrand.coreapi.ditui.entity.GroundPromotionUser;
import com.rogrand.coreapi.ditui.service.GroundPromotionUserService;

/**
 * 地推人员绩效保存组件
 * @author jianghong.huang
 *
 */
@Component
public class GroundUserPerformanceComponent {
    
    @Autowired
    private GroundPromotionUserService groundPromotionUserService;
    @Autowired
    private GroundPromotionIncomeDetailModel groundPromotionIncomeDetailModel;
    
    /**
     * 
     * @param userId 地推人员Uid
     * @param memo  收益备注（例如：在30天内达成线上交易额大于300万奖励)
     * @param amount    绩效奖励金额
     * @param activeId 绩效计划活动ID
     * @param saleAmount 销售额
     * @param saleNum   销售频次
     * @return
     */
    public Boolean givenGroundUserPerformance(Integer userId, String memo, Double amount, String activeId, Double saleAmount, Integer saleNum) {
        Boolean result = Boolean.FALSE;
        ServiceResult<GroundPromotionUser> userResult = groundPromotionUserService.getUser(userId);
        if(null != userResult && userResult.getResult() != null){
            GroundPromotionUser user = userResult.getResult();
            GroundPromotionIncomeDetail g = this.createGroundPromotionIncomeDetail(user, memo, amount, activeId, saleAmount, saleNum);
            result = groundPromotionIncomeDetailModel.createGroundPromotionIncomeDetail(g);
        }
        return result;
    }
    
    /**
     * 
     * @param user
     * @param memo  收益备注（例如：在30天内达成线上交易额大于300万奖励)
     * @param amount    绩效奖励金额
     * @param activeId 绩效计划活动ID
     * @param saleAmount 销售额
     * @param saleNum   销售频次
     */
    private GroundPromotionIncomeDetail createGroundPromotionIncomeDetail(GroundPromotionUser user, String memo, Double amount, String activeId, Double saleAmount, Integer saleNum) {
        GroundPromotionIncomeDetail dtl =  new GroundPromotionIncomeDetail();
        dtl.setUserId(user.getUserId());
        dtl.setUserJobType(user.getUserJobType());//职务类型
        
        dtl.setItemName(memo);
        dtl.setType(5);//绩效奖励
        dtl.setAssociateType(0);//非下线推广
        dtl.setCreateTime(new Date());
        dtl.setAmount(amount);//TODO 需要根据公式计算修改
        dtl.setPayStatus(0);//待提现
        //对应的商品id
        dtl.setLinkIdentity(activeId);
        dtl.setLinkIdentity2(activeId);
        dtl.setTypeDetail(6);
        dtl.setUserProvinceId(user.getProvinceId()==null ? 0 : user.getProvinceId());//根据用户获取
        dtl.setUserCityId(user.getCityId()==null ? 0 : user.getCityId());//根据用户获取
        dtl.setUserRegionId(user.getRegionId()==null ? 0 : user.getRegionId());//根据用户获取
        dtl.setRemark("");
        dtl.setSaleAmount(saleAmount);
        dtl.setSaleNum(saleNum);//订单数量
        
        dtl.setIssuedAmount(0D);//TODO
        dtl.setAuditAmount(0D);//TODO
        dtl.setCompleteAmount(0D);//TODO
        dtl.setDisplaySn("JX-"+activeId+"-"+user.getUserId().intValue());
        dtl.setOperUserId(user.getUserId()==null ? 0 : user.getUserId());//据用户获取
        dtl.setOperUserName(user.getUserFullName());//根据用户获取
        dtl.setUpdateTime(new Date());
        dtl.setPicUrl("");
        dtl.setKpiFlag(1);
        return dtl;
    }
}
