package com.mph.coreapi.gp.pay.util;

import java.math.BigDecimal;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/5/28
 * @Version: 1.0
 */
public class NumberUtils {

    public static int compare(double v1, double v2, int scale){
        BigDecimal b1 = new BigDecimal(String.valueOf(v1)).setScale(scale, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(String.valueOf(v2)).setScale(scale, BigDecimal.ROUND_HALF_UP);
        return b1.compareTo(b2);
    }

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.add(b2).doubleValue();
    }

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(double v1, double v2) {
        return div(v1, v2, 10);
    }

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            scale = 0;
        }

        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.divide(b2, scale, 4).doubleValue();
    }
}
