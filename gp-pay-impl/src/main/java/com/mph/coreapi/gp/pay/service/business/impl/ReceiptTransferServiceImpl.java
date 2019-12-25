package com.mph.coreapi.gp.pay.service.business.impl;

import java.util.HashMap;
import java.util.List;
import com.mph.coreapi.gp.pay.dto.PayReceiptDto;
import com.mph.coreapi.gp.pay.dto.ReceiptManualAuditPassDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionBankInfo;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayReceipt;
import com.mph.coreapi.gp.pay.enums.ReceiptPayStatusEnum;
import com.mph.coreapi.gp.pay.model.GroundPromotionBankInfoModel;
import com.mph.coreapi.gp.pay.model.GroundPromotionPayReceiptModel;
import com.mph.coreapi.gp.pay.service.business.ReceiptTransferService;
import com.rogrand.common.ServiceResult;
import com.rogrand.coreapi.ditui.entity.GroundPromotionSaveMessage;
import com.rogrand.coreapi.ditui.entity.GroundPromotionUser;
import com.rogrand.coreapi.ditui.service.GroundPromotionMessageService;
import com.rogrand.coreapi.ditui.service.GroundPromotionUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/6/8
 * @Version: 1.0
 */
@Service("receiptTransferService")
public class ReceiptTransferServiceImpl implements ReceiptTransferService {
    Logger logger = LoggerFactory.getLogger(ReceiptTransferServiceImpl.class);

    @Autowired
    GroundPromotionMessageService groundPromotionMessageService;

    @Autowired
    GroundPromotionPayReceiptModel groundPromotionPayReceiptModel;

    @Autowired
    GroundPromotionBankInfoModel groundPromotionBankInfoModel;

    @Autowired
    GroundPromotionUserService groundPromotionUserService;

    @Override
    public ServiceResult<Integer> rejectReceipt(final Integer receiptId, final String reason, String auditUserName) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        boolean ret = groundPromotionPayReceiptModel.updateReceiptPayStatus(receiptId, ReceiptPayStatusEnum.REJECTED.getCode(), auditUserName);
        if (ret){
            List<GroundPromotionPayReceipt> items = groundPromotionPayReceiptModel.listGroundPromotionPayReceipt(new HashMap<String, Object>() {{
                this.put("receiptId", receiptId);
            }
            });

            if (items != null && items.size() > 0){
                Integer userId = items.get(0).getUserId();
                ServiceResult<GroundPromotionUser> userRes = groundPromotionUserService.getUser(userId);
                GroundPromotionUser user = userRes.getResult();
                GroundPromotionSaveMessage message = new GroundPromotionSaveMessage();
                message.setAcceptJob(3);
                message.setAcceptRegionId(user.getProvinceId().toString());
                message.setIsPersonal(2);
                message.setUserIds(userId.toString());
                message.setMessageText(reason);
                message.setMessageType(1);
                groundPromotionMessageService.saveAndPushMessage(message);
                result.setResult(1);
                return result;
            }
        }

        result.setResult(0);
        result.setSuccess(false);
        return result;
    }

    @Override
    public ServiceResult<PayReceiptDto> getPayInfo(final Integer receiptId) {
        GroundPromotionPayReceipt receipt = null;
        List<GroundPromotionPayReceipt> items = groundPromotionPayReceiptModel.listGroundPromotionPayReceipt(new HashMap<String, Object>() {{
            this.put("receiptId", receiptId);
        }
        });

        receipt = items.get(0);
        List<GroundPromotionBankInfo> groundPromotionBankInfos = groundPromotionBankInfoModel.listGroundPromotionBankInfo(receipt.getUserId());
        GroundPromotionBankInfo bankInfo = groundPromotionBankInfos.get(0);

        PayReceiptDto payReceiptDto = new PayReceiptDto();
        BeanUtils.copyProperties(receipt, payReceiptDto);
        BeanUtils.copyProperties(bankInfo, payReceiptDto);

        ServiceResult<GroundPromotionUser> userRes = groundPromotionUserService.getUser(receipt.getUserId());
        GroundPromotionUser user = userRes.getResult();
        payReceiptDto.setAccountName(user.getUserFullName());
        payReceiptDto.setId(receiptId);

        ServiceResult<PayReceiptDto> result = new ServiceResult<PayReceiptDto>();
        result.setResult(payReceiptDto);
        return result;
    }

    @Override
    public ServiceResult<Integer> updateReceiptPayStatus(Integer receiptId, Integer payStatus) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        boolean ret = groundPromotionPayReceiptModel.updateReceiptPayStatus(receiptId, payStatus, null);
        result.setResult(ret?1:0);
        return result;
    }

    @Override
    public ServiceResult<Integer> updatePayReceiptProcessingStatus(Integer receiptId) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        Integer ret = groundPromotionPayReceiptModel.updatePayReceiptProcessingStatus(receiptId);
        result.setResult(ret);
        return result;
    }

    @Override
    public ServiceResult<Integer> manualBatchPassReceipt(List<ReceiptManualAuditPassDto> receiptList,
                                                         String auditUserName) {
        
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        Integer ret = groundPromotionPayReceiptModel.manualBatchPassReceipt(receiptList,auditUserName);
        result.setResult(ret);
        return result;
    }
}
