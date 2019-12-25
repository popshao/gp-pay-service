package com.mph.coreapi.gp.pay.dto;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/7
 * @Version: 1.0
 */
@ApiModel("IncomeDetailContextMenuDto")
public class IncomeDetailContextMenuDto implements Serializable {
    /**
     *  菜单类型
     */
    @ApiModelProperty(value = "菜单类型")
    Integer incomeType;

    /**
     * 菜单描述
     */
    @ApiModelProperty(value = "菜单描述")
    String menuItemDesc;

    public Integer getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(Integer incomeType) {
        this.incomeType = incomeType;
    }

    public String getMenuItemDesc() {
        return menuItemDesc;
    }

    public void setMenuItemDesc(String menuItemDesc) {
        this.menuItemDesc = menuItemDesc;
    }
}
