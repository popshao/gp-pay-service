package com.mph;

import com.alibaba.fastjson.JSON;
import com.mph.coreapi.TestBase;
import com.mph.coreapi.gp.pay.dto.*;
import com.mph.coreapi.gp.pay.service.business.AchievementService;
import com.rogrand.common.page.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AchievementServiceTest
 * @Description: TODO
 * @Author yuting.li
 * @Version 1.0
 * @Date 2018-06-06 15:44
 */
public class AchievementServiceTest extends TestBase {

    private AchievementService achievementService;

    @Override
    protected void init() {
        achievementService = context.getBean("achievementService",AchievementService.class);
    }

    Integer userId = 467;
    String usreName = "hwen1215";
    public void testGetUserSummary(){
        AchivementSummaryDto dto = achievementService.getUserSummary(userId,usreName);
        System.out.println(JSON.toJSONString(dto));
    }

    public void testProvinceSummary(){
        Integer provinceId = 3396;
        String provinceName = "新疆";
        ProvinceAchivementSummaryDto dto = achievementService.provinceSummary(provinceId,provinceName);
        System.out.println(JSON.toJSONString(dto));
    }

    public void testOrder(){
        OrderInfoDto<Double> dto = achievementService.getMemberOrderForUser(userId);
        System.out.println(JSON.toJSONString(dto));
        OrderInfoDto<Double> goodsdto = achievementService.getGoodsOrderForUser(userId);
        System.out.println(JSON.toJSONString(goodsdto));
    }

    public void testOrderList(){
        Integer provinceId = 1;
        Integer startOrder = 1;
        Integer endOrder = 10;
        List<OrderInfoDto<Double>> list1 = achievementService.getMemberOrderForProvince(provinceId,startOrder,endOrder);
        System.out.println(JSON.toJSONString(list1));
        List<OrderInfoDto<Double>> list2 = achievementService.getGoodsOrderForProvince(provinceId,startOrder,endOrder);
        System.out.println(JSON.toJSONString(list2));
    }

    Integer pageNo = 1;
    Integer pageSize = 20;

    public void testPageUser(){
        Integer associateType = 0;
        Pagination<AchiveMemberInfoDto> page1 = achievementService.getUserMemberList(29,2,1,100);
        System.out.println(JSON.toJSONString(page1));
        /*Pagination<AchieveGoodInfoDto> page2 = achievementService.getUserGoodsList(userId,associateType,pageNo,pageSize);
        System.out.println(JSON.toJSONString(page2));*/
    }

    public void testPagePro(){
        Integer provinceId =38;
        Integer cityId =64;
        List<Integer> regionIds=new ArrayList<>();
//        regionIds.add(100);
//        regionIds.add(101);
//        regionIds.add(102);
        Integer associateType =2;

//        Pagination<AchiveMemberInfoDto> page1 = achievementService.getProvinceMemberList(provinceId,cityId,regionIds,associateType,pageNo,pageSize);
//        System.out.println(JSON.toJSONString(page1));
        Pagination<AchieveGoodInfoDto> page2 = achievementService.getProvinceGoodsList(provinceId,cityId,regionIds,associateType,pageNo,pageSize);
        System.out.println(JSON.toJSONString(page2));
    }
    // 业绩查询vip的查询功能单元测试
    public void testgetVip(){
        //1.个人
        Integer userId=29;
        Integer associateType=2;
        Pagination<AchiveMemberInfoDto> page  =  achievementService.getUserMemberList(userId,associateType,pageNo,pageSize);
        System.out.println(JSON.toJSONString(page));
//        //2 省区
//       // Integer userId=29;
//        Integer associateType1=2;
//        Pagination<AchiveMemberInfoDto> page2=achievementService.getProvinceMemberList(1,associateType1,pageNo,pageSize);
//        System.out.println(JSON.toJSONString(page2));
    }

    // 业绩查询vip的查询功能单元测试
    public void testgetGoods(){
        //1.个人
        Integer userId=29;
        Integer associateType=2;
        Pagination<AchieveGoodInfoDto> page  =  achievementService.getUserGoodsList(userId,associateType,pageNo,pageSize);
        System.out.println(JSON.toJSONString(page));
        //2 省区
//        // Integer userId=29;
//        Integer associateType1=2;
//        Pagination<AchieveGoodInfoDto> page2=achievementService.getProvinceGoodsList(1,associateType1,pageNo,pageSize);
//        System.out.println(JSON.toJSONString(page2));
    }

}
