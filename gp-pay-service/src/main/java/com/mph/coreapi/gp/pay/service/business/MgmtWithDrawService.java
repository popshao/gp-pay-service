package com.mph.coreapi.gp.pay.service.business;

import java.util.Map;

import com.mph.coreapi.gp.pay.dto.mgmt.PayInfoSummaryDto;
import com.rogrand.common.page.Pagination;


/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/5
 * @Version: 1.0
 */
public interface MgmtWithDrawService {
    /**
     * 获取地推提成汇总信息
     * @param param
     * @return
     */
    PayInfoSummaryDto getPayInfoSummary(Map<String,Object> param);

    /**
     * 获取人员提成汇总列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    Pagination listPayInfoPersonalSummary(Map<String,Object> param, int pageNo, int pageSize);
}
