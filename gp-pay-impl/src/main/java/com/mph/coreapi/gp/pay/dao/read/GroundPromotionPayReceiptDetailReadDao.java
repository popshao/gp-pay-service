package com.mph.coreapi.gp.pay.dao.read;

import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.entity.GroundPromotionPayReceiptDetail;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
public interface GroundPromotionPayReceiptDetailReadDao {
    List<GroundPromotionPayReceiptDetail> listGroundPromotionPayReceiptDetail(GroundPromotionPayReceiptDetail item);
    List<GroundPromotionPayReceiptDetail> listGroundPromotionPayReceiptDetailAll();

}
