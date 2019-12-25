package com.mph.coreapi.gp.pay.util;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/5/28
 * @Version: 1.0
 */
public class ExpenseCalcUtil {
    /**
    * 劳务费计算公式：=ROUND(MAX((A-IF(A<4000,800,A*0.2))*10%*{2,3,4}-1000*{0,2,7},0),2)
     *   A：按照奖励提成计算的收入，即劳务报酬收入额； 应纳税所得额：指按照国家规定，减去可以扣除的项目后的金额
      *   序号     劳务报酬收入额      应纳税所得税      税率       速算扣除数       税额
       *  1         A<=800               0              0                            0
        * 2      800<A<4000           A-800            20%                     （A-800）*20%
       *  3      4000<=A<25000        A*0.8            20%                       A*0.8*20%
       *  4      25000<=A<62500       A*0.8            30%          2000         A*0.8*30%-2000
      *   5       62500<=A            A*0.8            40%          7000         A*0.8*40%-7000
      *
     */
    public static double calcCommissionTax(double input){
        if (NumberUtils.compare(input, 800, 2) <= 0){
            return 0.0;
        }

        if (NumberUtils.compare(input, 4000, 2) < 0){
            return (input - 800)*0.2;
        }

        if (NumberUtils.compare(input, 25000, 2) < 0){
            return input*0.8*0.2;
        }

        if (NumberUtils.compare(input, 62500, 2) < 0){
            return input*0.8*0.3-2000;
        } else {
            return input*0.8*0.4-7000;
        }
    }

    public static double calcMonthCommissionTax(double historyAmount, double currentAmount){
        double allTax = calcCommissionTax(historyAmount + currentAmount);
        double historyTax = calcCommissionTax(historyAmount);
        return allTax - historyTax;
    }
}
