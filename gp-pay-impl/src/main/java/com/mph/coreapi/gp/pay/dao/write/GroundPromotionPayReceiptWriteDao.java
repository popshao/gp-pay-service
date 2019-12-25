package com.mph.coreapi.gp.pay.dao.write;

import java.util.List;

import com.mph.coreapi.gp.pay.entity.GroundPromotionPayReceipt;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
public interface GroundPromotionPayReceiptWriteDao {
    Integer createGroundPromotionPayReceipt(GroundPromotionPayReceipt item);
    boolean updateGroundPromotionPayReceipt(GroundPromotionPayReceipt item);

    /**
     * 锁定单据为处理中状态
     * @param id
     * @return
     */
    Integer updatePayReceiptProcessingStatus(@Param("id") Integer id);
}
