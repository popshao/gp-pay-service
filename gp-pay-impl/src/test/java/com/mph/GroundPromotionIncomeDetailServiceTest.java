package com.mph;

import com.mph.coreapi.TestBase;
import com.mph.coreapi.gp.pay.entity.OrderSpecificProductInfo;
import com.mph.coreapi.gp.pay.service.GroundPromotionIncomeDetailService;
import com.mph.coreapi.gp.pay.service.impl.GroundPromotionIncomeDetailServiceImpl;
import com.rogrand.common.ServiceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GroundPromotionIncomeDetailServiceTest
 * @Description: TODO
 * @Author yuting.li
 * @Version 1.0
 * @Date 2018-06-04 16:41
 */
public class GroundPromotionIncomeDetailServiceTest extends TestBase {

    GroundPromotionIncomeDetailService groundPromotionIncomeDetailService;

    @Override
    protected void init() {
        groundPromotionIncomeDetailService = context.getBean("groundPromotionIncomeDetailService",GroundPromotionIncomeDetailServiceImpl.class);
    }

    public void testSave(){
        OrderSpecificProductInfo order = new OrderSpecificProductInfo();
        order.setUserId(3);
        order.setOid(123);
        order.setOsn("123");
        List<OrderSpecificProductInfo.OrderDetail> list = new ArrayList<>(1);
        OrderSpecificProductInfo.OrderDetail dtl = new OrderSpecificProductInfo.OrderDetail();
        dtl.setAmount(10D);
        dtl.setGoodId(11);
        dtl.setOdName("测试");
        dtl.setQuantity(1);
        list.add(dtl);
        order.setDetails(list);
        ServiceResult<String> result = groundPromotionIncomeDetailService.saveOrderSpecificProductInfo(order);
        System.out.println(result);
    }


}
