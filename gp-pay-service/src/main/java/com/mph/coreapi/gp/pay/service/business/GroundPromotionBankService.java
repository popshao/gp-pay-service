package com.mph.coreapi.gp.pay.service.business;

import java.util.List;

import com.mph.coreapi.gp.pay.dto.BankInfoDto;
import com.mph.coreapi.gp.pay.dto.CityInfoDto;
import com.mph.coreapi.gp.pay.dto.ProvinceInfoDto;
import com.rogrand.common.ServiceResult;
import org.springframework.stereotype.Service;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/4
 * @Version: 1.0
 */

public interface GroundPromotionBankService {
    /*
     * 获取银行列表
     */
    ServiceResult<BankInfoDto[]> getBankList();

    /*
     * 获取省份列表
     */
    ServiceResult<List<ProvinceInfoDto>> getProvinceList();

    /*
     * 获取省份列表
     */
    ServiceResult<List<CityInfoDto>> getCityList(String provinceCode);
}
