package com.mph.coreapi.gp.pay.dao.read;

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
public interface GroundPromotionPayLogReadDao {
    /**
     * 更新日志
     * @param status 日志状态
     * @param payType 支付类型
     * @param startId 起始id
     * @param pageSize 起始最大返回条数
     * @return
     * @author
     */
    List<GroundPromotionPayLog> listPayLogByStatus(@Param("status") Integer status, @Param("payType") Integer payType, @Param("id") Integer startId, @Param("pageSize") Integer pageSize);
}
