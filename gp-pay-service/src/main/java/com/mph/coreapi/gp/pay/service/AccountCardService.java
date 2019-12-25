package com.mph.coreapi.gp.pay.service;

import com.mph.coreapi.gp.pay.dto.PayReceiptDto;
import com.mph.coreapi.gp.pay.entity.AccountCard;
import com.mph.coreapi.gp.pay.entity.PayBankZj;
import com.rogrand.common.ServiceResult;

import java.util.List;

/**
 * 申请开通在线支付service
 * @author wsl
 */
public interface AccountCardService {

    /**
     * 根据e_id查询账户银行卡信息
     * @param eId 企业Id
     * @return 账户银行卡信息实体类
     */
    ServiceResult<AccountCard> findAccountCardByEid(Integer eId);

    /**
     * 修改账户银行卡信息
     * @param accountCard 账户银行卡信息实体类
     * @return 修改成功返回ture,失败返回false
     */
    ServiceResult<Boolean> updateAccountCard(AccountCard accountCard);

    /**
     * 插入账户银行卡信息
     * @param accountCard 账户银行卡信息实体类
     * @return 插入成功返回ture,失败返回false
     */
    ServiceResult<Integer> insertAccountCard(AccountCard accountCard);

    /**
     * 查询包含参数bankName的中金支付银行名称
     * @param bankName 银行名称或者模糊搜索条件
     * @return 银行实体类集合
     */
    ServiceResult<List<PayBankZj>> findPayBankByName(String bankName);

    ServiceResult<AccountCard> findAccountCardByPrimaryKey(Integer acId);

    ServiceResult<List<AccountCard>> listAccoutCard(AccountCard accountCard);

    /**
     * 根据suId 获取易宝支付所需信息
     * @param suId
     * @return
     */
    ServiceResult<PayReceiptDto> getYeepayPayInfoBySuId(Integer suId);

    /**
     * 根据eid 获取易宝支付所需信息
     * @param eid
     * @return
     */
    ServiceResult<PayReceiptDto> getYeepayPayInfoByEid(Integer eid);

    /**
     * 判断是否开通余额
     * @param suId 供应商主键
     * @return true-开通；false-未开通
     */
    ServiceResult<Boolean> isOpenCashBySuId(Integer suId);
}
