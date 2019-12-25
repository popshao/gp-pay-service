package com.mph.coreapi.gp.pay.dao.read;

import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.entity.GroundPromotionPayReceipt;
import com.mph.coreapi.gp.pay.entity.VipReceiptInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
public interface GroundPromotionPayReceiptReadDao {
    List<GroundPromotionPayReceipt> listGroundPromotionPayReceipt(Map<String,Object> param);
    List<GroundPromotionPayReceipt> listGroundPromotionPayReceiptAll();

    List<GroundPromotionPayReceipt> listGroundPromotionPayReceiptInfo(Map<String,Object> param);
    Integer countGroundPromotionPayReceiptInfo(Map<String,Object> param);
    Double getGroundPromotionReceiptAmount(@Param("userId") Integer userId);
    List<VipReceiptInfo> getSqlReceiptAmount();
}
