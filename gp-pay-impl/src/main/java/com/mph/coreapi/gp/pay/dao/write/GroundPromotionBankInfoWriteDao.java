package com.mph.coreapi.gp.pay.dao.write;

import java.util.List;

import com.mph.coreapi.gp.pay.entity.GroundPromotionBankInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
public interface GroundPromotionBankInfoWriteDao {
    boolean createGroundPromotionBankInfo(GroundPromotionBankInfo item);
    boolean updateGroundPromotionBankInfo(GroundPromotionBankInfo item);

}
