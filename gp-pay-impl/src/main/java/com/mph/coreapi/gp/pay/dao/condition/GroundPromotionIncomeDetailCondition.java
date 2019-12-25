package com.mph.coreapi.gp.pay.dao.condition;

import java.util.List;

public class GroundPromotionIncomeDetailCondition {



    private Integer userId;
    private List<Integer> types;
    private Integer associateType;
    
    private String searchCreateBeginTimeStr;
    private String searchCreateEndTimeStr;
    private Integer offset = 0;
    private Integer limit = 100;
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
    
    public void setSearchCreateBeginTimeStr(String searchCreateBeginTimeStr) {
        this.searchCreateBeginTimeStr = searchCreateBeginTimeStr;
    }
    public void setSearchCreateEndTimeStr(String searchCreateEndTimeStr) {
        this.searchCreateEndTimeStr = searchCreateEndTimeStr;
    }
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public String getSearchCreateBeginTimeStr() {
        return searchCreateBeginTimeStr;
    }
    public String getSearchCreateEndTimeStr() {
        return searchCreateEndTimeStr;
    }
}
