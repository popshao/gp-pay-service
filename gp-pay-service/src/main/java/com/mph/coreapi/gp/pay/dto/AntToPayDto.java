package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 蚂蚁人员待提现信息
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/3
 * @Version: 1.0
 */
@ApiModel("AntToPayDto")
public class AntToPayDto implements Serializable {
    @ApiModelProperty(value = "提现金额")
    private Double  totalAmount;         //提现金额
    @ApiModelProperty(value = "税费金额")
    private Double  taxAmount;            //税费金额
    @ApiModelProperty(value = "税后金额")
    private Double  finalAmount;         //税后金额
    @ApiModelProperty(value = "银行名称")
    private String bankName;  // 银行名称
    @ApiModelProperty(value = "银行卡后四位")
    private String cardLast4No; //  银行卡后四位
    @ApiModelProperty(value = "提现起始id, 确认提现时，回传给服务器")
    private Integer fromId; // 提现起始id, 确认提现时，回传给服务器
    @ApiModelProperty(value = "提现结束id, 确认提现时，回传给服务器")
    private Integer toId;  // 提现结束id, 确认提现时，回传给服务器
    @ApiModelProperty(value = "确认提现时回传给服务器")
    private String hashCode; // 确认提现时回传给服务器

    @ApiModelProperty(value = "id集合")
    private String idList; // id集合

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
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

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }
}
