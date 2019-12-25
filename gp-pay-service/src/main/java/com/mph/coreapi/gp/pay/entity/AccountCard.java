package com.mph.coreapi.gp.pay.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 银行账户信息
 * @Email: shengliang.wei@rograndec.com
 * @Author: shengliang.wei
 * @Date: 2018/8/19 18:48
 */
public class AccountCard implements Serializable {

    private static final long serialVersionUID = 5336794303390717609L;

    private Integer acId;

    private Integer suId;

    private Integer eId;

    private String payBankId;

    private String bankName;

    private String branchName;

    private String cardNo;

    private String cpNo;

    private String accountName;

    private Integer cardType;

    private String idNo;

    private Integer applyStatus;

    private Integer status;

    private String mobile;

    private Integer isDefault;

    private Date createTime;

    private Date updateTime;

    //张青伟2016.5.24新增字段
    //银行ID
    private String payBankId1;
    //银行名称
    private String bankCode;
    private String bankDetailName;
    //省ID
    private Integer province;
    //市ID
    private Integer town;
    //开户
    private String epPic;
    private String bucket_key;
    private String imageInfo;
    private String imgUrl;

    //易宝支付银行ID
    private String payBankId2;
    private String yibaoProvinceId;
    private String yibaoCityId;
    //审核不通过原因
    private String refuseReason;

    private Integer isPayOnlieCom;

    private Integer isPayOnlieSelf;

    /**
     * 结算类型默认：0-每日结算打款,1-结算到余额
     */
    private Integer cashType;

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getYibaoProvinceId() {
        return yibaoProvinceId;
    }

    public void setYibaoProvinceId(String yibaoProvinceId) {
        this.yibaoProvinceId = yibaoProvinceId;
    }

    public String getYibaoCityId() {
        return yibaoCityId;
    }

    public void setYibaoCityId(String yibaoCityId) {
        this.yibaoCityId = yibaoCityId;
    }

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public Integer getSuId() {
        return suId;
    }

    public void setSuId(Integer suId) {
        this.suId = suId;
    }

    public Integer getEId() {
        return eId;
    }

    public void setEId(Integer eId) {
        this.eId = eId;
    }

    public String getPayBankId() {
        return payBankId;
    }

    public void setPayBankId(String payBankId) {
        this.payBankId = payBankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Integer getStatus() {
        return applyStatus;
    }

    public void setStatus(Integer status) {
        this.applyStatus = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPayBankId1() {
        return payBankId1;
    }

    public void setPayBankId1(String payBankId1) {
        this.payBankId1 = payBankId1;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getCmbcBankCode() {
        return payBankId;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankDetailName() {
        return bankDetailName;
    }

    public void setBankDetailName(String bankDetailName) {
        this.bankDetailName = bankDetailName;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getTown() {
        return town;
    }

    public void setTown(Integer town) {
        this.town = town;
    }

    public String getEpPic() {
        return epPic;
    }

    public void setEpPic(String epPic) {
        this.epPic = epPic;
    }

    public String getPayBankId2() {
        return payBankId2;
    }

    public void setPayBankId2(String payBankId2) {
        this.payBankId2 = payBankId2;
    }

    public String getBucket_key() {
        return bucket_key;
    }

    public void setBucket_key(String bucket_key) {
        this.bucket_key = bucket_key;
    }

    public String getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(String imageInfo) {
        this.imageInfo = imageInfo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String getCpNo() {
        return cpNo;
    }

    public void setCpNo(String cpNo) {
        this.cpNo = cpNo;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Integer getIsPayOnlieCom() {
        return isPayOnlieCom;
    }

    public void setIsPayOnlieCom(Integer isPayOnlieCom) {
        this.isPayOnlieCom = isPayOnlieCom;
    }

    public Integer getIsPayOnlieSelf() {
        return isPayOnlieSelf;
    }

    public void setIsPayOnlieSelf(Integer isPayOnlieSelf) {
        this.isPayOnlieSelf = isPayOnlieSelf;
    }

    public Integer getCashType() {
        return cashType;
    }

    public void setCashType(Integer cashType) {
        this.cashType = cashType;
    }
}