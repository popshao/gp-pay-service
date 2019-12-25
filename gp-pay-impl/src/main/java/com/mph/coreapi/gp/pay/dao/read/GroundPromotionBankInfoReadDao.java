package com.mph.coreapi.gp.pay.dao.read;

import java.util.List;

import com.mph.coreapi.gp.pay.entity.GroundPromotionBankInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
public interface GroundPromotionBankInfoReadDao {
    /*
     * 省列表
     */
    List<GroundPromotionBankInfo> listgroundPromotionBankInfo(@Param("userId") Integer userId);

    /*
     * 省内市列表
     */
    List<GroundPromotionBankInfo> listgroundPromotionBankInfoAll();

}
