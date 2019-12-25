package com.mph.coreapi.gp.pay.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @Desc:地推收入明细表
 * @CreateDate: 2018-05-28 0028
 * @Version: 1.0
 */
public class GroundPromotionIncomeDetail implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7833408058475555006L;
    private Integer id;                 //表主键
    private Integer userId;             //地推人员的id
    private String itemName;            //开通会员的企业名称，专区商品商品名称，活跃企业名称，下线姓名
    private Integer type;               //收益类型： 1 会员推广 2 专区商品 3 菲加云
    private Integer associateType;      //0 非下线推广 1 下线推广
    private Date createTime;
    private Date updateTime;
    private Double amount;              //提成额度
    private Integer payStatus;          //0 待发放（待提现） 1 提现中 2 已发放或提现成功
    private String linkIdentity;        //所关联的内容如订单号，企业号，菲加云标识等，用于辅助追溯来源
    private Integer typeDetail;         //收益类型细分： 1 会员推广(SVIP) 2 专区商品 3 菲加云 4 注册拉新 5 会员推广(VIP)
    private String typeDetailName;
    private Integer userProvinceId;     //地推人员的所属省份id
    private Integer userCityId;         //地推人员的所属城市id
    private Integer userRegionId;       //地推人员的所属地区id
    private String remark;              //描述
    private Double saleAmount;          //推销额度
    private Integer saleNum;            //推销数量

    private Integer userJobType;        //1：全职 2：兼职

    private Double issuedAmount;        //待发放金额
    private Double auditAmount;         //发放中金额
    private Double completeAmount;      //已发放金额
    private Double waitingAccountAmount;//待入账金额

    private Double  totalAmount;         //提现金额
    private Double  taxAmount;            //税费金额
    private Double  finalAmount;         //税后金额
    private Double  depositAmount;       //已提现金额

    private String bankName;  // 银行名称
    private String cardLast4No; //  银行卡后四位
    private String idList;
    private String picUrl;
    private Integer kpiFlag; //kpi统计标识 0统计 1 不统计

    /**
     * 注意： 本属性getter方法有处理逻辑，不是标准返回
     * linkIdentity加强版，对于专区商品linkIdentity存专区商品id,linkIdentity2 存标准商品Id
     * 目前2019-05-16, 该字段只用于互斥保护，以后应用了尽量加说明
     */
    private String linkIdentity2;

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }

    //订单sn等
    private String displaySn;

    //工作执行人员id, associate_type 为1 时会与user_id不同，为下线id
    private Integer operUserId;

    //工作执行人员名字
    private String operUserName;

    /**
     * 提成来源id，如果非下线推广记实际操作人的user_id
     */
    private Integer sourceUserId;

    /**
     * 提成来源名字，如果非下线推广记实际操作人的user_name
     */
    private String sourceUserName;

    //vip/svip过期时间
    private Date dueTime;

    private Integer odId;

    public Integer getOdId() {
        return odId;
    }

    public void setOdId(Integer odId) {
        this.odId = odId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAssociateType() {
        return associateType;
    }

    public void setAssociateType(Integer associateType) {
        this.associateType = associateType;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getLinkIdentity() {
        return linkIdentity;
    }

    public void setLinkIdentity(String linkIdentity) {
        this.linkIdentity = linkIdentity;
    }

    public Integer getUserJobType() {
        return userJobType;
    }

    public void setUserJobType(Integer userJobType) {
        this.userJobType = userJobType;
    }

    public Double getIssuedAmount() {
        return issuedAmount;
    }

    public void setIssuedAmount(Double issuedAmount) {
        this.issuedAmount = issuedAmount;
    }

    public Double getAuditAmount() {
        return auditAmount;
    }

    public void setAuditAmount(Double auditAmount) {
        this.auditAmount = auditAmount;
    }

    public Double getCompleteAmount() {
        return completeAmount;
    }

    public void setCompleteAmount(Double completeAmount) {
        this.completeAmount = completeAmount;
    }

    public Double getWaitingAccountAmount() {
        return waitingAccountAmount;
    }

    public void setWaitingAccountAmount(Double waitingAccountAmount) {
        this.waitingAccountAmount = waitingAccountAmount;
    }

    public Integer getTypeDetail() {
        return typeDetail;
    }

    public String getTypeDetailName(){
        return typeDetailName;
    }

    public void setTypeDetailName(String taskDetailName)
    {
        this.typeDetailName = taskDetailName;
    }

    public void setTypeDetail(Integer typeDetail) {
        if(typeDetail==1){
            this.typeDetailName="会员推广(SVIP)";
        }
        else if(typeDetail==2){
            this.typeDetailName="专区商品";
        }
        else if(typeDetail==3){
            this.typeDetailName="菲加云";
        }
        else if(typeDetail==4){
            this.typeDetailName="注册拉新";
        }
        else if(typeDetail==5){
            this.typeDetailName="会员推广(VIP)";
        }
        this.typeDetail=typeDetail;
    }

    public Integer getUserProvinceId() {
        return userProvinceId;
    }

    public void setUserProvinceId(Integer userProvinceId) {
        this.userProvinceId=userProvinceId;
    }

    public Integer getUserCityId() {
        return userCityId;
    }

    public void setUserCityId(Integer userCityId) {
        this.userCityId = userCityId;
    }

    public Integer getUserRegionId() {
        return userRegionId;
    }

    public void setUserRegionId(Integer userRegionId) {
        this.userRegionId = userRegionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark=remark;
    }

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        this.saleAmount=saleAmount;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum=saleNum;
    }

    public String getDisplaySn() {
        return displaySn;
    }

    public void setDisplaySn(String displaySn) {
        this.displaySn=displaySn;
    }

    public Integer getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Integer operUserId) {
        this.operUserId=operUserId;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName=operUserName;
    }

    public Integer getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(Integer sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public String getSourceUserName() {
        return sourceUserName;
    }

    public void setSourceUserName(String sourceUserName) {
        this.sourceUserName = sourceUserName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardLast4No() {
        return cardLast4No;
    }

    public void setCardLast4No(String cardLast4No) {
        this.cardLast4No = cardLast4No;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime=dueTime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getKpiFlag() {
        return kpiFlag;
    }

    public void setKpiFlag(Integer kpiFlag) {
        this.kpiFlag = kpiFlag;
    }

    public String getLinkIdentity2() {
        if (linkIdentity2 == null){
            return linkIdentity;
        } else {
            return linkIdentity2;
        }
    }

    public void setLinkIdentity2(String linkIdentity2) {
        this.linkIdentity2 = linkIdentity2;
    }

    public Double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }
}
