package com.mph.coreapi.gp.pay.dao.read;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import com.mph.coreapi.TestBase;
import com.mph.coreapi.gp.pay.dao.condition.GroundPromotionIncomeDetailCondition;
import com.mph.coreapi.gp.pay.dto.GroundPromotionIncomeDisplaySnDto;

public class GroundPromotionIncomeDetailReadDaoTest extends TestBase {
    
    private GroundPromotionIncomeDetailReadDao groundPromotionIncomeDetailReadDao;

    @Override
    protected void init() {
        groundPromotionIncomeDetailReadDao = context.getBean(GroundPromotionIncomeDetailReadDao.class);
    }
    
//    @Test
    public void testCountGroundPromotionIncomeGroupByDisplayNo() {
        GroundPromotionIncomeDetailCondition conditon = new GroundPromotionIncomeDetailCondition();
        groundPromotionIncomeDetailReadDao.countGroundPromotionIncomeGroupByDisplayNo(conditon );
    }
    
    @Test
    public void testListGroundPromotionIncomeGroupByDisplayNo() throws ParseException {
        GroundPromotionIncomeDetailCondition conditon = new GroundPromotionIncomeDetailCondition();
        List<GroundPromotionIncomeDisplaySnDto> data = groundPromotionIncomeDetailReadDao.listGroundPromotionIncomeGroupByDisplayNo(conditon);
        System.out.println(data);
    }

}
