package com.mph.coreapi.gp.pay.service.impl;

import com.mph.coreapi.gp.pay.dto.PayReceiptDto;
import com.mph.coreapi.gp.pay.entity.AccountCard;
import com.mph.coreapi.gp.pay.entity.PayBankZj;
import com.mph.coreapi.gp.pay.model.AccountCardModel;
import com.mph.coreapi.gp.pay.model.PayBankZjModel;
import com.mph.coreapi.gp.pay.service.AccountCardService;
import com.rogrand.common.BusinessException;
import com.rogrand.common.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * 申请开通在线支付service实现层
 *
 * @author wsl
 */
@Service("accountCardService")
public class AccountCardServiceImpl implements AccountCardService {
    Logger log = LoggerFactory.getLogger(AccountCardServiceImpl.class);

    @Resource
    private AccountCardModel accountCardModel;
    @Resource
    private PayBankZjModel payBankZjModel;

    /**
     * 根据e_id查询账户银行卡信息
     *
     * @param eId 企业Id
     * @return 账户银行卡信息实体类
     */
    @Override
    public ServiceResult<AccountCard> findAccountCardByEid(Integer eId) {
        Assert.notNull(accountCardModel, "Property 'accountCardModel' is required.");
        ServiceResult<AccountCard> result = new ServiceResult<AccountCard>();
        try {
            result.setResult(accountCardModel.findAccountCardByEid(eId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.warn(be.getMessage());
        } catch (Exception e) {
            result.setError("AccountCardServiceImpl_findAccountCardByEid_error:", "根据e_id查询账户银行卡信息发生异常!");
            result.setSuccess(false);
            log.error("[AccountCardServiceImpl][findAccountCardByEid]根据e_id查询账户银行卡信息发生异常:", e);
        }
        return result;
    }

    /**
     * 修改账户银行卡信息
     *
     * @param accountCard 账户银行卡信息实体类
     * @return 修改成功返回ture, 失败返回false
     */
    @Override
    public ServiceResult<Boolean> updateAccountCard(AccountCard accountCard) {
        Assert.notNull(accountCardModel, "Property 'accountCardModel' is required.");
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(accountCardModel.updateAccountCard(accountCard));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.warn(be.getMessage());
        } catch (Exception e) {
            result.setError("AccountCardServiceImpl_updateAccountCard_error:", "修改账户银行卡信息发生异常!");
            result.setSuccess(false);
            log.error("[AccountCardServiceImpl][updateAccountCard]修改账户银行卡信息发生异常:", e);
        }
        return result;
    }

    /**
     * 插入账户银行卡信息
     *
     * @param accountCard 账户银行卡信息实体类
     * @return 插入成功返回ture, 失败返回false
     */
    @Override
    public ServiceResult<Integer> insertAccountCard(AccountCard accountCard) {
        Assert.notNull(accountCardModel, "Property 'accountCardModel' is required.");
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(accountCardModel.insertAccountCard(accountCard));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.warn(be.getMessage());
        } catch (Exception e) {
            result.setError("_insertAccountCard_error:", "插入账户银行卡信息发生异常!");
            result.setSuccess(false);
            log.error("[AccountCardServiceImpl][insertAccountCard]插入账户银行卡信息发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<AccountCard> findAccountCardByPrimaryKey(Integer acId) {
        Assert.notNull(accountCardModel, "Property 'accountCardModel' is required.");
        ServiceResult<AccountCard> result = new ServiceResult<>();
        try {
            result.setResult(accountCardModel.findAccountCardByPrimaryKey(acId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.warn(be.getMessage());
        } catch (Exception e) {
            result.setError("AccountCardServiceImpl_findAccountCardByPrimaryKey_error:", "根据主键查询账户银行卡信息发生异常!");
            result.setSuccess(false);
            log.error("[AccountCardServiceImpl][findAccountCardByPrimaryKey]根据主键查询账户银行卡信息发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<AccountCard>> listAccoutCard(AccountCard accountCard) {
        Assert.notNull(accountCardModel, "Property 'accountCardModel' is required.");
        ServiceResult<List<AccountCard>> result = new ServiceResult<>();
        try {
            result.setResult(accountCardModel.listAccoutCard(accountCard));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.warn(be.getMessage());
        } catch (Exception e) {
            result.setError("AccountCardServiceImpl_listAccoutCard_error:", "根据条件查询账户银行卡信息发生异常!");
            result.setSuccess(false);
            log.error("[AccountCardServiceImpl][listAccoutCard]根据条件查询账户银行卡信息发生异常:", e);
        }
        return result;
    }

    /**
     * 查询包含参数bankName的中金支付银行名称
     *
     * @param bankName 银行名称或者模糊搜索条件
     * @return 银行实体类集合
     */
    @Override
    public ServiceResult<List<PayBankZj>> findPayBankByName(String bankName) {
        Assert.notNull(payBankZjModel, "Property 'payBankZjModel' is required.");
        ServiceResult<List<PayBankZj>> result = new ServiceResult<>();
        try {
            result.setResult(payBankZjModel.findPayBankByName(bankName));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.warn(be.getMessage());
        } catch (Exception e) {
            result.setError("AccountCardServiceImpl_findPayBankByName_error:", "查询所有银行名称发生异常!");
            result.setSuccess(false);
            log.error("[AccountCardServiceImpl][findPayBankByName]查询所有银行名称发生异常:", e);
        }
        return result;
    }

    /**
     * 根据suId 获取易宝支付所需信息
     * @param suId
     * @return
     */
    @Override
    public ServiceResult<PayReceiptDto> getYeepayPayInfoBySuId(Integer suId){
        ServiceResult<PayReceiptDto> result = new ServiceResult<>();
        AccountCard accountCard = new AccountCard();
        accountCard.setSuId(suId);
        accountCard.setIsDefault(1);
        accountCard.setApplyStatus(10);
        try {
            List<AccountCard> accountCards = accountCardModel.listAccoutCard(accountCard);
            if (accountCards != null && accountCards.size() > 0){
                PayReceiptDto payReceiptDto = transferAccountCardToPayInfo(accountCards.get(0));
                result.setResult(payReceiptDto);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            log.error("获取账户信息异常", e);
        }

        return result;
    }

    /**
     * 根据eid 获取易宝支付所需信息
     * @param eid
     * @return
     */
    @Override
    public ServiceResult<PayReceiptDto> getYeepayPayInfoByEid(Integer eid){
        ServiceResult<PayReceiptDto> result = new ServiceResult<>();
        AccountCard accountCard = new AccountCard();
        accountCard.setEId(eid);
        accountCard.setIsDefault(1);
        accountCard.setApplyStatus(10);
        try {
            List<AccountCard> accountCards = accountCardModel.listAccoutCard(accountCard);
            if (accountCards != null && accountCards.size() > 0){
                PayReceiptDto payReceiptDto = transferAccountCardToPayInfo(accountCards.get(0));
                result.setResult(payReceiptDto);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            log.error("获取账户信息异常", e);
        }

        return result;
    }

    private PayReceiptDto transferAccountCardToPayInfo(AccountCard card){
        PayReceiptDto payReceiptDto = new PayReceiptDto();
        payReceiptDto.setAccountName(card.getAccountName());
        payReceiptDto.setBankName(card.getBankName());
        payReceiptDto.setBranchName(card.getBankDetailName());
        payReceiptDto.setBankCardNo(card.getCardNo());
        payReceiptDto.setProvinceCode(card.getYibaoProvinceId());
        payReceiptDto.setBankCode(card.getPayBankId2());
        payReceiptDto.setCityCode(card.getYibaoCityId());
        return payReceiptDto;
    }

    /**
     * 判断是否开通suId
     *
     * @param suId 供应商主键
     * @return
     */
    @Override
    public ServiceResult<Boolean> isOpenCashBySuId(Integer suId) {
        Assert.notNull(accountCardModel, "Property 'accountCardModel' is required.");
        Assert.notNull(suId, "Property 'suId' is required.");
        ServiceResult<Boolean> result = new ServiceResult<>();
        boolean flag = false;
        try {
            Integer count = accountCardModel.isOpenCashBySuId(suId);
            if (count != null && count == 1) {
                flag = true;
            }
            result.setResult(flag);
        } catch (Exception e) {
            result.setError("AccountCardServiceImpl_isOpenCashBySuId_error:", "判断是否开通suId!异常");
            result.setSuccess(false);
            log.error("[AccountCardServiceImpl][isOpenCashBySuId]判断是否开通suId:", e);
        }
        return result;
    }
}
