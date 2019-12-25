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
@ApiModel("CardInfoDto")
public class CardInfoDto implements Serializable {
    /**
     * 地推人员的id
     * */
    @ApiModelProperty(value = "地推人员的id")
    private Integer userId;
    /**
     * 持卡人身份证号
     * */
    @ApiModelProperty(value = "持卡人身份证号")
    private String identityCardNo;
    /**
     * 银行卡号
     * */
    @ApiModelProperty(value = "银行卡号")
    private String bankCardNo;
    /**
     * 开户行
     * */
    @ApiModelProperty(value = "开户行")
    private String bankName;
    /**
     * 银行编号
     * */
    @ApiModelProperty(value = "银行编号")
    private String bankCode;
    /**
     * 支行名称
     * */
    @ApiModelProperty(value = "支行名称")
    private String branchName;
    /**
     * 省编号
     * */
    @ApiModelProperty(value = "省编号")
    private String provinceCode;
    /**
     * 市编号
     * */
    @ApiModelProperty(value = "市编号")
    private String cityCode;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
