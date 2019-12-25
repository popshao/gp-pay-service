package com.mph.coreapi.gp.pay.service;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-06-25 0025
 * @Version: 1.0
 */
public interface VipIncomeImportService {
    /**
     * income表数据导入
     * @param password
     * @return
     */
    String vipIncomeImport(String password );

    /**
     * receipt表数据生成
     * @param password
     * @return
     */
    String vipReceiptImport(String password);
}
