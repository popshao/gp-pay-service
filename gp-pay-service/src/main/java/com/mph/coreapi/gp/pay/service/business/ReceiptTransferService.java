package com.mph.coreapi.gp.pay.service.business;

import java.util.List;

import com.mph.coreapi.gp.pay.dto.PayReceiptDto;
import com.mph.coreapi.gp.pay.dto.ReceiptManualAuditPassDto;
import com.rogrand.common.ServiceResult;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/8
 * @Version: 1.0
 */
public interface ReceiptTransferService {
    /**
     * 驳回提现单据
     * @param receiptId
     * @param reason
     * @param auditUserName
     * @return
     */
    ServiceResult<Integer> rejectReceipt(Integer receiptId, String reason, String auditUserName);
    
    /**
     * 手动批量审核通过兼职人员的提现数据
     * @param receiptList
     * @param auditUserName
     * @return
     */
    ServiceResult<Integer> manualBatchPassReceipt(List<ReceiptManualAuditPassDto> receiptList,String auditUserName);

    /**
     * 查询提现信息详情
     * @param receiptId
     * @return
     */
    ServiceResult<PayReceiptDto> getPayInfo(Integer receiptId);

    /**
     * 更新提现状态
     * @param receiptId
     * @param payStatus
     * @return
     */
    ServiceResult<Integer> updateReceiptPayStatus(Integer receiptId, Integer payStatus);

    /**
     * 更新提现状态
     * @param receiptId
     * @return
     */
    ServiceResult<Integer> updatePayReceiptProcessingStatus(Integer receiptId);
}
