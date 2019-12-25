package com.mph.coreapi.gp.pay.service.condition;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GroundPromotionIncomeSearchCondition implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private List<Integer> types;
    private Integer associateType;
    
    private Date searchCreateBeginTime;
    private Date searchCreateEndTime;
    private Integer pageNo = 1;
    private Integer pageSize = 100;
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public List<Integer> getTypes() {
        return types;
    }
    public void setTypes(List<Integer> types) {
        this.types = types;
    }
    public Integer getAssociateType() {
        return associateType;
    }
    public void setAssociateType(Integer associateType) {
        this.associateType = associateType;
    }
    public Date getSearchCreateBeginTime() {
        return searchCreateBeginTime;
    }
    public void setSearchCreateBeginTime(Date searchCreateBeginTime) {
        this.searchCreateBeginTime = searchCreateBeginTime;
    }
    public Date getSearchCreateEndTime() {
        return searchCreateEndTime;
    }
    public void setSearchCreateEndTime(Date searchCreateEndTime) {
        this.searchCreateEndTime = searchCreateEndTime;
    }
    public Integer getPageNo() {
        return pageNo;
    }
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
