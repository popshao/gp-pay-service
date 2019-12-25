package com.mph.coreapi.gp.pay.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.dao.read.GroundPromotionBankInfoReadDao;
import com.mph.coreapi.gp.pay.dao.read.GroundPromotionLocationReadDao;
import com.mph.coreapi.gp.pay.dao.write.GroundPromotionBankInfoWriteDao;
import com.mph.coreapi.gp.pay.entity.GroundPromotionBankInfo;
import com.mph.coreapi.gp.pay.entity.GroundPromotionLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
@Component
public class GroundPromotionBankInfoModel {
    @Autowired
    GroundPromotionBankInfoReadDao  groundPromotionBankInfoReadDao;
    @Autowired
    GroundPromotionBankInfoWriteDao groundPromotionBankInfoWriteDao;

    @Autowired
    GroundPromotionLocationReadDao groundPromotionLocationReadDao;

    public List<GroundPromotionBankInfo> listGroundPromotionBankInfo(Integer userId) {
        return groundPromotionBankInfoReadDao.listgroundPromotionBankInfo(userId);
    }

    public List<GroundPromotionBankInfo> listgroundPromotionBankInfoAll(){
        return groundPromotionBankInfoReadDao.listgroundPromotionBankInfoAll();
    }

    public boolean createGroundPromotionBankInfo(GroundPromotionBankInfo item){
        return groundPromotionBankInfoWriteDao.createGroundPromotionBankInfo(item);
    }

    public boolean updateGroundPromotionBankInfo(GroundPromotionBankInfo item){
        return groundPromotionBankInfoWriteDao.updateGroundPromotionBankInfo(item);
    }

    /**
     * @Description: 获取省份
     * @version 1.0
     * @date 2018/5/31 14:58
     * @param
     * @return
     */
    public List<GroundPromotionLocation> listProvince(){
        return groundPromotionLocationReadDao.listProvince();
    }

    /**
     * @Description: 获取省份
     * @version 1.0
     * @date 2018/5/31 14:58
     * @param
     * @return
     */
    public List<GroundPromotionLocation> listCityByProvinceCode(String locationCode){
        return groundPromotionLocationReadDao.listCityByProvinceCode(locationCode);
    }

    /**
     * @Description: 获取银行列表
     * @version 1.0
     * @date 2018/5/31 14:58
     * @param
     * @return
     */
    public Map getBankList(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("工商银行","ICBC");
        map.put("农业银行","ABC");
        map.put("招商银行","CMBCHINA");
        map.put("建设银行","CCB");
        map.put("邮储银行","POST");
        map.put("中国银行","BOC");
        map.put("交通银行","BOCO");
        map.put("中信银行","CCB");
        map.put("光大银行","CEB");
        map.put("华夏银行","HXB");
        map.put("民生银行","CMBC");
        return map;
    }

    public GroundPromotionLocation getLocationInfo(Map<String,Object> params){
        return groundPromotionLocationReadDao.getLocationInfo(params);
    }
}
