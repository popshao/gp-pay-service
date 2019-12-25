package com.mph.coreapi.gp.pay.entity;

import java.io.Serializable;

/**
* 地推支付地区表
* @date 2018-06-04
*/
public class GroundPromotionLocation implements Serializable {
    /**
    * 表主键
    */
    private Integer id;

    /**
    * 名称
    */
    private String locationName;

    /**
    * 国标
    */
    private String locationCode;

    /**
    * 1 省 2 市
    */
    private Integer locationType;

    /**
    * 上级地区国标
    */
    private String parentLocationCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public Integer getLocationType() {
        return locationType;
    }

    public void setLocationType(Integer locationType) {
        this.locationType = locationType;
    }

    public String getParentLocationCode() {
        return parentLocationCode;
    }

    public void setParentLocationCode(String parentLocationCode) {
        this.parentLocationCode = parentLocationCode;
    }
}