package com.mph.coreapi.gp.pay.model;

import com.mph.coreapi.gp.pay.dao.read.PayBankZjReadDao;
import com.mph.coreapi.gp.pay.dao.write.PayBankZjWriteDao;
import com.mph.coreapi.gp.pay.entity.PayBankZj;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Component
public class PayBankZjModel {

    @Resource
    private PayBankZjReadDao payBankZjReadDao;
    @Resource
    private PayBankZjWriteDao payBankZjWriteDao;

    public PayBankZj selectByPrimaryKey(String id) {
        Assert.notNull(payBankZjReadDao, "Property payBankZjReadDao must not be null");
        return payBankZjReadDao.selectByPrimaryKey(id);
    }

    public List<PayBankZj> listPayBankZj(PayBankZj payBankZj){
        Assert.notNull(payBankZjReadDao, "Property payBankZjReadDao must not be null");
        return payBankZjReadDao.listPayBankZj(payBankZj);
    }

    public List<PayBankZj> findPayBankByName(String bankName){
        Assert.notNull(payBankZjReadDao, "Property payBankZjReadDao must not be null");
        return payBankZjReadDao.findPayBankByName(bankName);
    }

    public int insert(PayBankZj payBankZj) {
        Assert.notNull(payBankZjWriteDao, "Property payBankZjWriteDao must not be null");
        return payBankZjWriteDao.insert(payBankZj);
    }

    public int insertSelective(PayBankZj payBankZj) {
        Assert.notNull(payBankZjWriteDao, "Property payBankZjWriteDao must not be null");
        return payBankZjWriteDao.insertSelective(payBankZj);
    }


    public int updateByPrimaryKeySelective(PayBankZj payBankZj) {
        Assert.notNull(payBankZjWriteDao, "Property payBankZjWriteDao must not be null");
        return payBankZjWriteDao.updateByPrimaryKeySelective(payBankZj);
    }

    public int updateByPrimaryKey(PayBankZj payBankZj) {
        Assert.notNull(payBankZjWriteDao, "Property payBankZjWriteDao must not be null");
        return payBankZjWriteDao.updateByPrimaryKey(payBankZj);
    }
}
