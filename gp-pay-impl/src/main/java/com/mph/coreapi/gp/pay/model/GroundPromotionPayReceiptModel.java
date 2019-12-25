package com.mph.coreapi.gp.pay.model;

import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.dao.read.GroundPromotionPayReceiptReadDao;
import com.mph.coreapi.gp.pay.dao.write.GroundPromotionIncomeDetailWriteDao;
import com.mph.coreapi.gp.pay.dao.write.GroundPromotionPayReceiptWriteDao;
import com.mph.coreapi.gp.pay.dto.ReceiptManualAuditPassDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayReceipt;
import com.mph.coreapi.gp.pay.entity.VipReceiptInfo;
import com.mph.coreapi.gp.pay.enums.ReceiptPayStatusEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
@Component
public class GroundPromotionPayReceiptModel {
    @Autowired
    GroundPromotionPayReceiptReadDao  groundPromotionPayReceiptReadDao;
    @Autowired
    GroundPromotionPayReceiptWriteDao groundPromotionPayReceiptWriteDao;

    @Autowired
    GroundPromotionIncomeDetailWriteDao groundPromotionIncomeDetailWriteDao;

    public List<GroundPromotionPayReceipt> listGroundPromotionPayReceipt(Map<String,Object> param){
        return groundPromotionPayReceiptReadDao.listGroundPromotionPayReceipt(param);
    }

    public  List<GroundPromotionPayReceipt> listGroundPromotionPayReceiptAll(){
        return  groundPromotionPayReceiptReadDao.listGroundPromotionPayReceiptAll();
    }
    public Integer createGroundPromotionPayReceipt(GroundPromotionPayReceipt item){
        groundPromotionPayReceiptWriteDao.createGroundPromotionPayReceipt(item);
        return item.getId();
    }
    public boolean updateGroundPromotionPayReceipt(GroundPromotionPayReceipt item){
        return groundPromotionPayReceiptWriteDao.updateGroundPromotionPayReceipt(item);
    }

    public List<GroundPromotionPayReceipt> listGroundPromotionPayReceiptInfo(Map<String,Object> param,int pageNo, int pageSize){
        param.put("start",pageNo);
        param.put("pageSize",pageSize);
        return groundPromotionPayReceiptReadDao.listGroundPromotionPayReceiptInfo(param);
    }

    public Integer countGroundPromotionPayReceiptInfo(Map<String,Object> param){
        return groundPromotionPayReceiptReadDao.countGroundPromotionPayReceiptInfo(param);
    }
    
    public boolean updateReceiptPayStatus(Integer receiptId, Integer payStatus, String auditUser){
        return this.updateReceiptPayStatus(receiptId, payStatus, null, auditUser);
    }

    public boolean updateReceiptPayStatus(Integer receiptId, Integer payStatus, Double finalAmount, String auditUser){
        GroundPromotionPayReceipt item = new GroundPromotionPayReceipt();
        item.setId(receiptId);
        item.setStatus(payStatus);
        if(finalAmount != null) {
            item.setFinalAmount(finalAmount);
        }
        if (auditUser != null){
            item.setAuditUserName(auditUser);
        }

        boolean ret = groundPromotionPayReceiptWriteDao.updateGroundPromotionPayReceipt(item);
        if (ret){
            if (payStatus == 1){
                groundPromotionIncomeDetailWriteDao.updateIncomeDetailByReceiptId(2, receiptId);
            }
        }

        return ret;
    }

    public Double getGroundPromotionReceiptAmount(Integer userId){
        return groundPromotionPayReceiptReadDao.getGroundPromotionReceiptAmount(userId);
    }

    /**
     * 锁定单据为处理中状态
     * @param receiptId
     * @return
     */
    public Integer updatePayReceiptProcessingStatus(Integer receiptId){
        return groundPromotionPayReceiptWriteDao.updatePayReceiptProcessingStatus(receiptId);
    }

    public List<VipReceiptInfo> getSqlReceiptAmount(){
        return groundPromotionPayReceiptReadDao.getSqlReceiptAmount();
    }

    @Transactional
    public Integer manualBatchPassReceipt(List<ReceiptManualAuditPassDto> receiptList,
                                          String auditUserName) {
        Integer num = 0;
        for(ReceiptManualAuditPassDto dto: receiptList) {
            boolean status = this.updateReceiptPayStatus(dto.getReceiptId(), ReceiptPayStatusEnum.SUCCESS_PAY.getCode(), dto.getAdjustPayMoney(), auditUserName);
            if(status) {
                num++;
            }
        }
        return num;
    }
}
