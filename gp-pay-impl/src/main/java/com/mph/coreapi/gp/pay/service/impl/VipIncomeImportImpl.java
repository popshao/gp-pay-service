package com.mph.coreapi.gp.pay.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayReceipt;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayReceiptDetail;
import com.mph.coreapi.gp.pay.entity.VipIncomeImport;
import com.mph.coreapi.gp.pay.entity.VipReceiptInfo;
import com.mph.coreapi.gp.pay.model.GroundPromotionIncomeDetailModel;
import com.mph.coreapi.gp.pay.model.GroundPromotionPayReceiptDetailModel;
import com.mph.coreapi.gp.pay.model.GroundPromotionPayReceiptModel;
import com.mph.coreapi.gp.pay.service.VipIncomeImportService;
import com.mph.coreapi.gp.pay.util.ExpenseCalcUtil;
import com.mph.coreapi.gp.pay.util.SnGenerator;
import com.rogrand.common.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-06-19 0019
 * @Version: 1.0
 */
public class VipIncomeImportImpl implements VipIncomeImportService {

    private static final Logger logger = LoggerFactory.getLogger(AntServiceImpl.class);
    @Autowired
    private GroundPromotionIncomeDetailModel groundPromotionIncomeDetailModel;
    @Autowired
    private GroundPromotionPayReceiptModel   groundPromotionPayReceiptModel;
    @Autowired
    private GroundPromotionPayReceiptDetailModel groundPromotionPayReceiptDetailModel;

    /**
     * vipIncomeImport
     * @param password
     * @return
     */
    public String vipIncomeImport(String password ) {
        if (StringUtils.isEmpty(password) || !"fefb774bdcb44f1d5c522e8fc25550c2".equals(MD5.mphMD5Code(password, "rograndec"))) {
            return "[FAIL] Wrong Password!";
        }
        //得到直接推广vip获取的提成数据
        List<VipIncomeImport> vipIncomeList= groundPromotionIncomeDetailModel.getVipIncomeList();
        //得到通过下线推广vip得到的提成数据
        if(vipIncomeList!=null&&vipIncomeList.size()>0){
            for (VipIncomeImport vipIncomeImport :vipIncomeList){
                GroundPromotionIncomeDetail incomeItem=new GroundPromotionIncomeDetail();
                if(vipIncomeImport.getUserId()==null){
                    continue;
                }
                incomeItem.setUserId(vipIncomeImport.getUserId());
                incomeItem.setDueTime(vipIncomeImport.getPremiumEnd());
                incomeItem.setDisplaySn(vipIncomeImport.getoSn());
                incomeItem.setItemName(vipIncomeImport.getoEName());
                incomeItem.setUserJobType(vipIncomeImport.getUserJobType());
                incomeItem.setLinkIdentity(vipIncomeImport.geteId().toString());
                incomeItem.setCreateTime(vipIncomeImport.getPremiumOpenTime());
                incomeItem.setOperUserId(vipIncomeImport.getOperUserId());
                incomeItem.setOperUserName(vipIncomeImport.getOperUserName());
                incomeItem.setAmount(vipIncomeImport.getAmount());
                incomeItem.setUserProvinceId(vipIncomeImport.getProvinceId());
                incomeItem.setPayStatus(2);
                incomeItem.setRemark("");
                incomeItem.setSaleAmount(0.00);
                incomeItem.setSaleNum(0);
                incomeItem.setPicUrl("");
                incomeItem.setType(1);
                if(vipIncomeImport.getAmount()==50){
                    incomeItem.setAssociateType(0);
                }
                else{
                    incomeItem.setAssociateType(1);
                }
                if(vipIncomeImport.getPremiumType()==1){
                    incomeItem.setTypeDetail(5);
                }else if(vipIncomeImport.getPremiumType()==2){
                    incomeItem.setTypeDetail(1);
                }

                logger.info("vipIncomeImport:插入income表数据json："+JSON.toJSONString(incomeItem));
                //判断重复 根据userid和订单号判断重复
                Map<String,Object> params=new HashMap<String,Object>();
                params.put("userId",vipIncomeImport.getUserId());
                params.put("oSn",vipIncomeImport.getoSn());
                List<VipIncomeImport> vipIncomeUserId=groundPromotionIncomeDetailModel.getIncomeByUserIdoSn(params);
                if(vipIncomeUserId!=null&&vipIncomeUserId.size()>0) {
                    logger.error("vipIncomeImport:插入income表数据时数据重复，sn："+vipIncomeImport.getoSn()+",UserId:"+vipIncomeImport.getUserId());
                }
                else{
                    boolean resultFlag = groundPromotionIncomeDetailModel.createGroundPromotionIncomeDetail(incomeItem);
                }
            }
        }
        ExpenseCalcUtil.calcMonthCommissionTax(0,0);

        return "";
    }

    /**
     * vipIncomeImport
     * @param password
     * @return
     */
    public String vipReceiptImport(String password){
        if (StringUtils.isEmpty(password) || !"fefb774bdcb44f1d5c522e8fc25550c2".equals(MD5.mphMD5Code(password, "rograndec"))) {
            return "[FAIL] Wrong Password!";
        }
        //获取user_id  根据userId 得到incomeList   计算然后插入receipt和receiptdetail表
        List<VipIncomeImport> vipIncomeUserId= groundPromotionIncomeDetailModel.getIncomeUserId();
        //根据userId得到income数据
        if(vipIncomeUserId!=null&&vipIncomeUserId.size()>0){
            for (VipIncomeImport item :vipIncomeUserId){
                Integer userId=item.getUserId();
                //根据userId得到income表的收入数据
                Map<String,Object> params=new HashMap<String,Object>();
                params.put("userId",userId);
                params.put("dateFlag",1);
                List<GroundPromotionIncomeDetail> incomeDetailList= groundPromotionIncomeDetailModel.getGroundPromotionIncomeDetailByIdDate(params);
                if(incomeDetailList!=null&&incomeDetailList.size()>0){
                    GroundPromotionPayReceipt groundPromotionPayReceipt=new GroundPromotionPayReceipt();
                    groundPromotionPayReceipt.setUserId(userId);
                    groundPromotionPayReceipt.setStatus(1);
                    groundPromotionPayReceipt.setReceiptSn(SnGenerator.genReceiptSn());
                    Double totalAmount=0.00;
                    for (GroundPromotionIncomeDetail incomeItem :incomeDetailList){
                        totalAmount=totalAmount+incomeItem.getAmount();
                    }
                    Double taxAmount =ExpenseCalcUtil.calcMonthCommissionTax(0,totalAmount);
                    groundPromotionPayReceipt.setTotalAmount(totalAmount);
                    groundPromotionPayReceipt.setTaxAmount(taxAmount);
                    groundPromotionPayReceipt.setHistoryAmount(0.00);
                    groundPromotionPayReceipt.setFinalAmount(totalAmount-taxAmount);
                    groundPromotionPayReceiptModel.createGroundPromotionPayReceipt(groundPromotionPayReceipt);
                    for (GroundPromotionIncomeDetail item1 :incomeDetailList){
                        GroundPromotionPayReceiptDetail groundPromotionPayReceiptDetail=new GroundPromotionPayReceiptDetail();
                        groundPromotionPayReceiptDetail.setIncomeId(item1.getId());
                        groundPromotionPayReceiptDetail.setReceiptId(groundPromotionPayReceipt.getId());
                        groundPromotionPayReceiptDetailModel.createGroundPromotionPayReceiptDetail(groundPromotionPayReceiptDetail);
                    }
                }
            }
        }
        return "true";
    }


}
