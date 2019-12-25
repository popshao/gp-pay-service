package com.mph.coreapi.gp.pay.model;

import java.util.List;

import com.mph.coreapi.gp.pay.dao.read.GroundPromotionPayReceiptDetailReadDao;
import com.mph.coreapi.gp.pay.dao.write.GroundPromotionPayReceiptDetailWriteDao;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayReceiptDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
@Component
public class GroundPromotionPayReceiptDetailModel {
    @Autowired
    GroundPromotionPayReceiptDetailReadDao  groundPromotionPayReceiptDetailReadDao;
    @Autowired
    GroundPromotionPayReceiptDetailWriteDao groundPromotionPayReceiptDetailWriteDao;

    public List<GroundPromotionPayReceiptDetail> listGroundPromotionPayReceiptDetail(GroundPromotionPayReceiptDetail item){
        return groundPromotionPayReceiptDetailReadDao.listGroundPromotionPayReceiptDetail(item);
    }

    public List<GroundPromotionPayReceiptDetail> listGroundPromotionPayReceiptDetailAll(){
        return groundPromotionPayReceiptDetailReadDao.listGroundPromotionPayReceiptDetailAll();
    }

    public boolean createGroundPromotionPayReceiptDetail(GroundPromotionPayReceiptDetail item){
        return groundPromotionPayReceiptDetailWriteDao.createGroundPromotionPayReceiptDetail(item);
    }
    public boolean updateGroundPromotionPayReceiptDetail(GroundPromotionPayReceiptDetail item){
        return groundPromotionPayReceiptDetailWriteDao.updateGroundPromotionPayReceiptDetail(item);
    }
}
