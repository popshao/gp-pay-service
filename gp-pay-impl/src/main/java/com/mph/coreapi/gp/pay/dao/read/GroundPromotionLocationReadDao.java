package com.mph.coreapi.gp.pay.dao.read;

import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.entity.GroundPromotionLocation;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/4
 * @Version: 1.0
 */
public interface GroundPromotionLocationReadDao {
    /**
     * @Description: 获取省份
     * @version 1.0
     * @date 2018/5/31 14:58
     * @param
     * @return
     */
    List<GroundPromotionLocation> listProvince();

    /**
     * @Description: 获取省份
     * @version 1.0
     * @date 2018/5/31 14:58
     * @param
     * @return
     */
    List<GroundPromotionLocation> listCityByProvinceCode(@Param("locationCode") String locationCode);

    GroundPromotionLocation getLocationInfo(Map<String,Object> params);
}
