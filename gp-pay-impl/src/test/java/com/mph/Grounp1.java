package com.mph;


import java.util.List;

import com.alibaba.fastjson.JSON;
import com.mph.coreapi.TestBase;
import com.mph.coreapi.gp.pay.dto.PayReceiptDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayLog;
import com.mph.coreapi.gp.pay.service.AccountCardService;
import com.mph.coreapi.gp.pay.service.CommonPersonnelService;
import com.mph.coreapi.gp.pay.service.GroundPromotionOrderService;
import com.mph.coreapi.gp.pay.service.PayLogService;
import com.mph.coreapi.gp.pay.service.impl.CommonPersonnelServiceImpl;
import com.rogrand.common.ServiceResult;

public class Grounp1 extends TestBase {
    CommonPersonnelService      commonPersonnelService;
    GroundPromotionOrderService groundPromotionOrderService;
    AccountCardService          accountCardService;
    PayLogService               gpPayLogService;
    @Override
    protected void init() {
        commonPersonnelService = context.getBean("commonPersonnelService", CommonPersonnelServiceImpl.class);
        //groundPromotionOrderService = context.getBean("groundPromotionOrderService", GroundPromotionOrderServiceImpl.class);
        accountCardService = context.getBean("accountCardService", AccountCardService.class);
        gpPayLogService = context.getBean("payLogService", PayLogService.class);
    }

    /**
     * 得到提成配置列表
     */
    public void testPromotionCommissionConfig(){
//        GroundPromotionCommissionConfig item=new GroundPromotionCommissionConfig();
//        item.setScopeType(1);
//        //GroundPromotionCommissionConfig  test=commonPersonnelService.getGroundPromotionCommissionConfigList(item);
//        System.out.println("success");
    }

    public void testAddPromotionCommissionConfig()
    {
//        GroundPromotionCommissionConfig item =new GroundPromotionCommissionConfig();
//        item.setScopeType(1);
//        item.setLinkId(1);
//        item.setLinkName("1");
//        item.setBusinessType(1);
//        item.setComputeType(1);
//        item.setPercentVal(10.00);
//        item.setPercentValLead(9.00);
//        item.setFixVal(50.00);
//        item.setComputeTypeLead(11.00);
//        item.setFixValLead(12.00);
//        Date date = new Date();
//        ParsePosition pos = new ParsePosition(0);
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//        Date currentTime = dateFormat.parse(dateFormat.format(new Date()), pos);
//
//        item.setCreateTime(currentTime);
//        item.setUpdateTime(currentTime);
//        //ServiceResult<String> aa= commonPersonnelService.saveGroundPromotionCommissionConfig(item);
//        System.out.println("success");
    }

    public void testUpdatePromotionCommissionConfig(){
//        GroundPromotionCommissionConfig item =new GroundPromotionCommissionConfig();
//        item.setId(1);
//        item.setScopeType(2);
//        item.setLinkId(2);
//        item.setLinkName("2");
//        item.setBusinessType(2);
//        item.setComputeType(2);
//        item.setPercentVal(20.00);
//        item.setPercentValLead(29.00);
//        item.setFixVal(20.00);
//        item.setComputeTypeLead(21.00);
//        item.setFixValLead(22.00);
//        ParsePosition pos = new ParsePosition(0);
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//        Date currentTime = dateFormat.parse(dateFormat.format(new Date()), pos);
//
//        item.setCreateTime(currentTime);
//        item.setUpdateTime(currentTime);
//        //ServiceResult<String> aa= commonPersonnelService.editGroundPromotionCommissionConfig(item);
//        System.out.println("success");
    }

    public void testGetYeepayPayInfoByEid(){
        ServiceResult<PayReceiptDto> yeepayPayInfoByEid = accountCardService.getYeepayPayInfoByEid(2772653);
        System.out.println(JSON.toJSONString(yeepayPayInfoByEid));
    }

    public void testGetYeepayPayInfoBySuId(){
        ServiceResult<PayReceiptDto> yeepayPayInfoByEid = accountCardService.getYeepayPayInfoBySuId(461);
        System.out.println(JSON.toJSONString(yeepayPayInfoByEid));
    }

    public void testQueryLog(){
        Integer processingStatus = 2;
        Integer pageSize = 50;
        Integer startId = 0;
        Integer payType = 3;
        ServiceResult<List<GroundPromotionPayLog>> serviceResult = gpPayLogService.listPayLogByStatus(processingStatus, payType, startId, pageSize);
        System.out.println(JSON.toJSONString(serviceResult));
    }
}
