package com.mph.coreapi.gp.pay.util;

import java.math.BigDecimal;

public class NumberComparer {
    public static int compare(Long left, Double right){
        BigDecimal bleft = new BigDecimal(left);
        BigDecimal bright = BigDecimal.valueOf(right).setScale(2, BigDecimal.ROUND_FLOOR);
        return bleft.compareTo(bright);
    }

    public static int compare(Integer left, Integer right){
        return left.compareTo(right);
    }
}
