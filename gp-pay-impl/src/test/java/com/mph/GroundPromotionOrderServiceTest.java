package com.mph;

import com.mph.coreapi.TestBase;
import com.mph.coreapi.gp.pay.service.GroundPromotionOrderService;
import com.rogrand.common.ServiceResult;
import com.rogrand.common.page.Pagination;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: GroundPromotionOrderServiceTest
 * @Description: TODO
 * @Author yuting.li
 * @Version 1.0
 * @Date 2018-06-04 10:37
 */
public class GroundPromotionOrderServiceTest extends TestBase {

    private GroundPromotionOrderService groundPromotionOrderService;

    @Override
    protected void init() {
        groundPromotionOrderService = context.getBean("groundPromotionOrderService", GroundPromotionOrderService.class);
    }

//    public void testSave(){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        //月份减一
//        calendar.add(Calendar.MONTH, -1);
//        boolean result = groundPromotionOrderService.saveGroundPromotionAchievement(new Date());
//        System.out.println(result);
//    }
//
//    public void testUpdate(){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        //月份减一
//        calendar.add(Calendar.MONTH, -1);
//        boolean result = groundPromotionOrderService.updateGroundPromotionAchievement(calendar.getTime());
//        System.out.println(result);
//    }

    public void testGetGroundPromotionAchievementList(){
        Map<String,Object> param = new HashMap<>();
        param.put("type","1");
        param.put("order","quatity");
        param.put("sort","ASC");
        int pageNo = 1;
        int pageSize = 20;
        ServiceResult<Pagination> result = groundPromotionOrderService.getGroundPromotionAchievementList(param,pageNo,pageSize);
        System.out.println(result.getResult());
    }

}
