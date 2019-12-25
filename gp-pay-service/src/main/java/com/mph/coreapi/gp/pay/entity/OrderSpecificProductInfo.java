package com.mph.coreapi.gp.pay.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/5/29
 * @Version: 1.0
 */
public class OrderSpecificProductInfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8586789239325843475L;
    /**
     * 订单id
     */
    Integer oid;
    /**
     * 订单sn
     */
    String osn;
    /**
     * 地推人员id
     */
    Integer userId;

    /**
     * 0支付 1收货 2 取消 3 全驳
     */
    Integer type;

    /**
     * 专区商品明细
     */
    List<OrderDetail> details;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getOsn() {
        return osn;
    }

    public void setOsn(String osn) {
        this.osn = osn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public static class OrderDetail{
        /**
         * order detail id
         */
        Integer odId;
        /**
         * 专区商品实际金额  总金额 - 驳回 - 退货
         */
        Double  amount;
        /**
         * 专区商品名称
         */
        String odName;
        /**
         * 专区商品的goodId
         */
        Integer goodId;
        
        /**
         * 专区商品GID
         */
        Integer suitGid;
        
        /**
         * 专区商品的实际数量 总数-驳回数量-退货数量
         */
        Integer quantity;

        /**
         * 商品图片
         */
        String goodPicUrl;

        /**
         *  专区商品配置json
         */
        String duituiConfig;
        
        public Integer getOdId() {
            return odId;
        }

        public void setOdId(Integer odId) {
            this.odId = odId;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getOdName() {
            return odName;
        }

        public void setOdName(String odName) {
            this.odName = odName;
        }

        public Integer getGoodId() {
            return goodId;
        }

        public void setGoodId(Integer goodId) {
            this.goodId=goodId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity=quantity;
        }

        public String getGoodPicUrl() {
            return goodPicUrl;
        }

        public void setGoodPicUrl(String goodPicUrl) {
            this.goodPicUrl = goodPicUrl;
        }

        public String getDuituiConfig() {
            return duituiConfig;
        }

        public void setDuituiConfig(String duituiConfig) {
            this.duituiConfig = duituiConfig;
        }

        public Integer getSuitGid() {
            return suitGid;
        }

        public void setSuitGid(Integer suitGid) {
            this.suitGid = suitGid;
        }

    }
}
