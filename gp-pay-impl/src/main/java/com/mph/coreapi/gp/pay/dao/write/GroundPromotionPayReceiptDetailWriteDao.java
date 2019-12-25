package com.mph.coreapi.gp.pay.dao.write;

import java.util.List;

import com.mph.coreapi.gp.pay.entity.GroundPromotionPayReceiptDetail;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
public interface GroundPromotionPayReceiptDetailWriteDao {
    boolean createGroundPromotionPayReceiptDetail(GroundPromotionPayReceiptDetail item);
    boolean updateGroundPromotionPayReceiptDetail(GroundPromotionPayReceiptDetail item);

}
