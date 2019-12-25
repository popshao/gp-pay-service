package com.mph.coreapi.gp.pay.model.component;

import org.junit.Test;

import com.mph.coreapi.TestBase;

public class GroundUserPerformanceComponentTest extends TestBase {

    private GroundUserPerformanceComponent groundUserPerformanceComponent;
    
    @Test
    public void testGivenGroundUserPerformance() {
        Boolean result = groundUserPerformanceComponent.givenGroundUserPerformance(591, "在30天内达成 线上交易5000元奖励", 10d, "1000", 100000d, 0);
        System.out.print(result);
        
    }

    @Override
    protected void init() {
        groundUserPerformanceComponent = context.getBean(GroundUserPerformanceComponent.class);
        
    }

}
