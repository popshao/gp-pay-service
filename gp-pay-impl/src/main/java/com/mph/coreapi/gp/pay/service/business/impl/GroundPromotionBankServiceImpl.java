package com.mph.coreapi.gp.pay.service.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.dto.BankInfoDto;
import com.mph.coreapi.gp.pay.dto.CityInfoDto;
import com.mph.coreapi.gp.pay.dto.ProvinceInfoDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionLocation;
import com.mph.coreapi.gp.pay.model.GroundPromotionBankInfoModel;
import com.mph.coreapi.gp.pay.service.business.GroundPromotionBankService;
import com.rogrand.common.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/4
 * @Version: 1.0
 */
@Service("groundPromotionBankService")
public class GroundPromotionBankServiceImpl implements GroundPromotionBankService {
    Logger logger = LoggerFactory.getLogger(GroundPromotionBankServiceImpl.class);

    @Autowired
    GroundPromotionBankInfoModel groundPromotionBankInfoModel;

    @Override
    public ServiceResult<BankInfoDto[]> getBankList() {
        List<BankInfoDto> list = new ArrayList<BankInfoDto>();
        final Map<String, String> map = groundPromotionBankInfoModel.getBankList();
        for (final String key : map.keySet()){
            BankInfoDto bankInfoDto = new BankInfoDto();
            bankInfoDto.setBankCode(map.get(key));
            bankInfoDto.setBankName(key);
            list.add(bankInfoDto);
        }

        ServiceResult<BankInfoDto[]> result = new ServiceResult<BankInfoDto[]>();
        result.setResult(list.toArray(new BankInfoDto[0]));
        return result;
    }

    @Override
    public ServiceResult<List<ProvinceInfoDto>> getProvinceList() {
        List<ProvinceInfoDto> list = new ArrayList<ProvinceInfoDto>();
        List<GroundPromotionLocation> locations = groundPromotionBankInfoModel.listProvince();
        for (final GroundPromotionLocation location : locations){
            ProvinceInfoDto provinceInfoDto = new ProvinceInfoDto();
            provinceInfoDto.setProvinceName(location.getLocationName());
            provinceInfoDto.setProvinceCode(location.getLocationCode());
            list.add(provinceInfoDto);
        }

        ServiceResult<List<ProvinceInfoDto>> result = new ServiceResult<List<ProvinceInfoDto>>();
        result.setResult(list);
        return result;
    }

    @Override
    public ServiceResult<List<CityInfoDto>> getCityList(String provinceCode) {
        List<CityInfoDto> list = new ArrayList<CityInfoDto>();
        List<GroundPromotionLocation> locations = groundPromotionBankInfoModel.listCityByProvinceCode(provinceCode);
        for (final GroundPromotionLocation location : locations){
            CityInfoDto cityInfoDto = new CityInfoDto();
            cityInfoDto.setCityCode(location.getLocationCode());
            cityInfoDto.setCityName(location.getLocationName());
            list.add(cityInfoDto);
        }

        ServiceResult<List<CityInfoDto>> result = new ServiceResult<List<CityInfoDto>>();
        result.setResult(list);
        return result;
    }
}
