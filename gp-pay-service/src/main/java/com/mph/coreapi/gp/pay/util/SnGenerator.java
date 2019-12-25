package com.mph.coreapi.gp.pay.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/4/12
 * @Version: 1.0
 */
public class SnGenerator {
    static Random        random     = new Random(System.currentTimeMillis());
    static AtomicInteger receiptSeq = new AtomicInteger(random.nextInt(100000));
    static AtomicInteger tradeSeq   = new AtomicInteger(random.nextInt(100000));

    /**
     * 生成支付的dealNo
     * @param platform 支付平台
     * @return
     */
    public static String genDealNo(int platform){
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        // 微信流程号生成方法特殊
        if (platform == 5){
            return null;
        }

        int val = tradeSeq.addAndGet(1);
        if (val + 100000 > Integer.MAX_VALUE){
            tradeSeq.set(val%100000);
        }

        String dealNo = String.format("%02d%s%04d", platform, df.format(new Date()), val%10000);

        return dealNo;
    }

    /**
     * 生成支付的dealNo
     * @param platform 支付平台
     * @return
     */
    public static String genWxDealNo(int platform, String paySource, Integer highAmountTag){
        return "123";
    }

    /**
     * 生成单据sn
     * @return
     */
    public static String genReceiptSn(){
        String receiptCode = "01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        int val = receiptSeq.addAndGet(1);
        if (val + 100000 > Integer.MAX_VALUE){
            receiptSeq.set(val%100000);
        }

        String sn = String.format("%s%s%04d", receiptCode, sdf.format(new Date()), val%10000);
        return sn;
    }
}
