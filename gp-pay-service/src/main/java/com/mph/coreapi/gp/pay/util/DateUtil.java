package com.mph.coreapi.gp.pay.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/*
 * 风控模型中所需要的时间计算方法
 */
public class DateUtil {
    /*
     * 获取整月时间
     * @Param time 输入的初始时间
     * @Param time 月份偏移量 正数为加，负数为减，零则返回输入时间的月时间起始值
     * TODO: 调试一下代码是否线程安全
     */
    public static Date getMonthTime(Date time, Integer num){
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DATE, 1);
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        cale.set(Calendar.MILLISECOND, 0);
        cale.add(Calendar.MONTH, num);
        return cale.getTime();
    }
    /**
     * 获取两个日期相差的月数
     * @param d1  较大的日期
     * @param d2  较小的日期
     * @return 如果d1>d2返回 月数差 否则返回0
     */
    public static int getMonthDiff(Date d1, Date d2) {
    		//defalut 1970-01-01 00:00:00
    		if(d1 ==null ||d2 ==null) return 0;
    		try {
    		Calendar dd=Calendar.getInstance();
    		dd.setTimeInMillis(28800000l);
    		//1515660505368
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        //-28800000 28800000
        if(Math.abs(c1.getTimeInMillis())==Math.abs(dd.getTimeInMillis())||Math.abs(c2.getTimeInMillis())==Math.abs(dd.getTimeInMillis())) {
        		return 0;
        }
        if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16 d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if(month1 < month2 || month1 == month2 && day1 < day2) yearInterval --;
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2 ;
        if(day1 < day2) monthInterval --;
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
        }catch(Exception e) {
        		System.out.println("转化时间异常:"+e.getMessage()+"D1:"+d1+",D2:"+d2);
        	   return 0;
        }
    }
}
