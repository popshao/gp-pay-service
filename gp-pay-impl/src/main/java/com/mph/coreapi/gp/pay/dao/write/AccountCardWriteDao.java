package com.mph.coreapi.gp.pay.dao.write;

import com.mph.coreapi.gp.pay.entity.AccountCard;

public interface AccountCardWriteDao {

    /**
     * 修改账户银行卡信息
     *
     * @param accountCard 账户银行卡信息实体类
     * @return 修改成功返回ture, 失败返回false
     */
    Integer updateAccountCard(AccountCard accountCard);

    /**
     * 插入账户银行卡信息
     *
     * @param accountCard 账户银行卡信息实体类
     * @return 插入成功返回ture, 失败返回false
     */
    Integer insertAccountCard(AccountCard accountCard);

    /**
     * 更新企业信息表里面的开户证书
     *
     * @param accountCard
     */
    void updateBizEnterprisePic(AccountCard accountCard);

}
