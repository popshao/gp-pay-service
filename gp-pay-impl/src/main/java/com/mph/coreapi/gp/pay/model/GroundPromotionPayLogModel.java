package com.mph.coreapi.gp.pay.model;

import java.util.List;

import javax.annotation.Resource;

import com.mph.coreapi.gp.pay.dao.read.GroundPromotionPayLogReadDao;
import com.mph.coreapi.gp.pay.dao.write.GroundPromotionPayLogWriteDao;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayLog;
import org.springframework.stereotype.Repository;

/**
 * 易宝在线支付日志Model
 * 
 * @author qingwei.zhang 张青伟
 * @date 2016-02-01
 * @email qingwei.zhang@rogrand.com
 */
@Repository("groundPromotionPayLogModel")
public class GroundPromotionPayLogModel {

    @Resource(name = "groundPromotionPayLogWriteDao")
    private GroundPromotionPayLogWriteDao groundPromotionPayLogWriteDao;

    @Resource(name = "groundPromotionPayLogReadDao")
    private GroundPromotionPayLogReadDao groundPromotionPayLogReadDao;

    /**
     * 添加日志
     * @param
     * @return
     * @author qingwei.zhang 张青伟
     */
    public Integer add(GroundPromotionPayLog payLogYiBao) {
        groundPromotionPayLogWriteDao.add(payLogYiBao);
        return payLogYiBao.getId();
    }

    /**
     * 更新日志
     * @param
     * @return
     * @author qingwei.zhang 张青伟
     */
    public boolean update(GroundPromotionPayLog payLogYiBao) {
        groundPromotionPayLogWriteDao.update(payLogYiBao);
        return true;
    }

    /**
     * 更新日志
     * @param
     * @return
     * @author
     */
    public int updateLogById(GroundPromotionPayLog payLogYiBao) {
        return groundPromotionPayLogWriteDao.updateLogById(payLogYiBao);
    }

    public List<GroundPromotionPayLog> listPayLogByStatus(Integer status, Integer payType, Integer startId, Integer pageSize){
        return groundPromotionPayLogReadDao.listPayLogByStatus(status, payType, startId, pageSize);
    }
}
