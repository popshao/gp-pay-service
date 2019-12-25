package com.mph.coreapi.gp.pay.dao.write;

import java.util.List;

import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
public interface GroundPromotionIncomeDetailWriteDao {
    boolean createGroundPromotionIncomeDetail(GroundPromotionIncomeDetail item);
    boolean updateGroundPromotionIncomeDetail(GroundPromotionIncomeDetail item);
    Integer updateIncomeDetailByReceiptId(@Param("payStatus") Integer payStatus, @Param("receiptId") Integer receiptId);
}
