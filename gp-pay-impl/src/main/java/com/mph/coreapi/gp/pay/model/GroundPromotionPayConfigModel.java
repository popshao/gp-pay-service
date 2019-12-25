package com.mph.coreapi.gp.pay.model;

import java.util.List;

import com.mph.coreapi.gp.pay.dao.read.GroundPromotionPayConfigReadDao;
import com.mph.coreapi.gp.pay.dao.write.GroundPromotionPayConfigWriteDao;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
@Component
public class GroundPromotionPayConfigModel {
    @Autowired
    GroundPromotionPayConfigReadDao  groundPromotionPayConfigReadDao;
    @Autowired
    GroundPromotionPayConfigWriteDao groundPromotionPayConfigWriteDao;

    public GroundPromotionPayConfig groundPromotionIncomeDetail(){
        return groundPromotionPayConfigReadDao.listGroundPromotionPayConfig();
    }
    public boolean SaveGroundPromotionPayConfig(GroundPromotionPayConfig item){
        return groundPromotionPayConfigWriteDao.createGroundPromotionPayConfig(item);
    }
    public boolean updateGroundPromotionPayConfig(GroundPromotionPayConfig item){
        return groundPromotionPayConfigWriteDao.updateGroundPromotionPayConfig(item);
    }
}
