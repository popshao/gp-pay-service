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
@ApiModel("BankInfoDto")
public class BankInfoDto implements Serializable {
    /*
     * 银行名称
     */
    @ApiModelProperty(value = "银行名称")
    String bankName;

    /*
     * 银行编码
     */
    @ApiModelProperty(value = "银行编码")
    String bankCode;

    /*
     * 持卡人身份证号
     */
    @ApiModelProperty(value = "持卡人身份证号")
    String identityCardNo;

    /*
     * 银行卡号
     */
    @ApiModelProperty(value = "银行卡号")
    String bankCardNo;

    /*
     * 支行名称
     */
    @ApiModelProperty(value = "支行名称")
    String branchName;


    /*
     * 省编号
     */
    @ApiModelProperty(value = "省编号")
    String provinceCode;
    /*
     * 市编号
     */
    @ApiModelProperty(value = "市编号")
    String cityCode;

    /*
     * 市名称
     */
    @ApiModelProperty(value = "市名称")
    String cityName;

    /*
     * 省名称
     */
    @ApiModelProperty(value = "省名称")
    String provinceName;


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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
