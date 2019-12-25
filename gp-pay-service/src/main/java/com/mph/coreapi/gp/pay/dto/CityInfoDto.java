package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/3
 * @Version: 1.0
 */
@ApiModel("CityInfoDto")
public class CityInfoDto implements Serializable {
    /*
     * 市名称
     */
    @ApiModelProperty(value = "市名称")
    String cityName;

    /*
     * 市国标码
     */
    @ApiModelProperty(value = "市国标码")
    String cityCode;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
