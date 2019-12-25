package com.mph.coreapi.gp.pay.dao.write;

import java.util.List;

import com.mph.coreapi.gp.pay.entity.GroundPromotionPayLog;
import org.apache.ibatis.annotations.Param;

/**
 * 中金在线支付日志WriteDao
 * 
 * @author qingwei.zhang 张青伟
 * @date 2016-7-25
 * @email qingwei.zhang@rogrand.com
 */
public interface GroundPromotionPayLogWriteDao {

    /**
     * 添加日志
     * @param payLogYiBao
     * @return
     * @author qingwei.zhang 张青伟
     */
    int add(GroundPromotionPayLog payLogYiBao);

    /**
     * 更新日志
     * @param payLogYiBao
     * @return
     * @author qingwei.zhang 张青伟
     */
    int update(GroundPromotionPayLog payLogYiBao);

    /**
     * 更新日志状态字段
     * @param payLogYiBao 日志对象
     * @return
     * @author
     */
    int updateLogStatus(GroundPromotionPayLog payLogYiBao);

    /**
     * 更新日志字段
     * @param payLogYiBao 日志对象
     * @return
     * @author
     */
    int updateLogById(GroundPromotionPayLog payLogYiBao);
}
