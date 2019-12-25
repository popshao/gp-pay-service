package com.mph.coreapi.gp.pay.model;

import com.mph.coreapi.gp.pay.dao.read.AccountCardReadDao;
import com.mph.coreapi.gp.pay.dao.write.AccountCardWriteDao;
import com.mph.coreapi.gp.pay.entity.AccountCard;
import com.rogrand.common.BusinessException;
import com.rogrand.common.ServiceResult;
import com.rogrand.common.util.RoUtil;
import com.rogrand.coreapi.user.entity.BizEnterprisePic;
import com.rogrand.coreapi.user.service.MphPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccountCardModel {

    @Resource
    private AccountCardWriteDao accountCardWriteDao;
    @Resource
    private AccountCardReadDao accountCardReadDao;
    @Resource
    private DataSourceTransactionManager transactionManager;
    @Autowired(required = false)
    private MphPicService mphPicService;


    public AccountCard findAccountCardByPrimaryKey(Integer acId) {
        Assert.notNull(accountCardReadDao,"Property accountCardReadDao must not be null");
        return accountCardReadDao.findAccountCardByPrimaryKey(acId);
    }

    /**
     * @Description: 根据企业eId查询
     * @Email: shengliang.wei@rograndec.com
     * @Author: shengliang.wei
     * @Date: 2018/8/19 17:49
     *
     */
    public AccountCard findAccountCardByEid(Integer eId){
        Assert.notNull(accountCardReadDao, "Property 'accountCardReadDao' is required.");
        if (eId <= 0) {
            throw new BusinessException("[AccountCardModel][findAccountCardByEid]:企业id不存在！");
        }
        AccountCard accountCard = accountCardReadDao.findAccountCardByEid(eId);
        if (RoUtil.isEmpty(accountCard)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("epType", 11);
            map.put("eId", eId);
            ServiceResult<BizEnterprisePic> result = mphPicService.queryEnterprisePic(eId, 11);
            if (result.getSuccess() == false) {
                throw new BusinessException(
                        "[AccountCardModel][findAccountCardByEid]:调用mphPicService.queryEnterprisePic(eId, 11)服务异常！参数:" + eId);
            }
            if (null != result.getResult()) {
                accountCard = new AccountCard();
                accountCard.setEpPic(result.getResult().getEpPic());
            }
        }
        return accountCard;
    }

    public List<AccountCard> listAccoutCard(AccountCard accountCard) {
        Assert.notNull(accountCardReadDao,"Property accountCardReadDao must not be null");
        return accountCardReadDao.listAccoutCard(accountCard);
    }


    /**
     * 修改账户银行卡信息
     *
     * @param accountCard 账户银行卡信息实体类
     * @return 修改成功返回ture, 失败返回false
     */
    public Boolean updateAccountCard(AccountCard accountCard) {
        Assert.notNull(accountCardWriteDao, "Property 'accountCardWriteDao' is required.");
        if (RoUtil.isEmpty(accountCard)) {
            throw new BusinessException("[AccountCardModel][findAccountCard]:账户银行卡信息实体类为空！");
        }
        int m = 0;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            m = accountCardWriteDao.updateAccountCard(accountCard);
            if (!RoUtil.isEmpty(accountCard.getEpPic())) {
                accountCardWriteDao.updateBizEnterprisePic(accountCard);
            }
            //插入图片记录信息
//            addImageInfo(accountCard);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

        return m > 0;
    }

    /**
     * 插入账户银行卡信息
     *
     * @param accountCard 账户银行卡信息实体类
     * @return 插入成功返回ture, 失败返回false
     */
    public Integer insertAccountCard(AccountCard accountCard) {
        Assert.notNull(accountCardWriteDao, "Property 'accountCardWriteDao' is required.");
        if (RoUtil.isEmpty(accountCard)) {
            throw new BusinessException("[AccountCardModel][findAccountCard]:账户银行卡信息实体类为空！");
        }

        int m = 0;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            m = accountCardWriteDao.insertAccountCard(accountCard);

            if (!RoUtil.isEmpty(accountCard.getEpPic())) {
                accountCardWriteDao.updateBizEnterprisePic(accountCard);
            }
            //插入图片记录信息
            //addImageInfo(accountCard);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

        if (m > 0) {
            return accountCard.getAcId();
        } else {
            return 0;
        }
    }

    /**
     * 判断是否开通suId
     * @param suId 供应商主键
     * @return
     */
    public Integer isOpenCashBySuId(Integer suId) {
        return accountCardReadDao.isOpenCashBySuId(suId);
    }

}
