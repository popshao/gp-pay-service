package com.mph.coreapi.gp.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.mph.coreapi.gp.pay.dto.AntPayRecordDto;
import com.mph.coreapi.gp.pay.dto.BankInfoDto;
import com.mph.coreapi.gp.pay.entity.*;
import com.mph.coreapi.gp.pay.model.GroundPromotionBankInfoModel;
import com.mph.coreapi.gp.pay.model.GroundPromotionIncomeDetailModel;
import com.mph.coreapi.gp.pay.model.GroundPromotionPayReceiptDetailModel;
import com.mph.coreapi.gp.pay.model.GroundPromotionPayReceiptModel;
import com.mph.coreapi.gp.pay.service.AntService;
import com.mph.coreapi.gp.pay.util.ExpenseCalcUtil;
import com.mph.coreapi.gp.pay.util.PubliceMethod;
import com.mph.coreapi.gp.pay.util.SnGenerator;
import com.mph.coreapi.order.order.conditon.PageOpenOrderConditon;
import com.mph.coreapi.order.order.entity.enumeration.OrderTypeEnum;
import com.mph.coreapi.order.order.service.MphOrderService;
import com.mph.coreapi.order.order.vo.OrderVO;
import com.rogrand.common.ServiceResult;
import com.rogrand.common.page.Pagination;
import com.rogrand.common.util.EncryptUtil;
import com.rogrand.common.util.StringUtil;
import com.rogrand.coreapi.ditui.entity.GroundPromotionCommissionConfig;
import com.rogrand.coreapi.ditui.entity.GroundPromotionUser;
import com.rogrand.coreapi.ditui.entity.GroundPromotionUserGroup;
import com.rogrand.coreapi.ditui.service.GroundPromotionCommissionConfigService;
import com.rogrand.coreapi.ditui.service.GroundPromotionUserGroupService;
import com.rogrand.coreapi.ditui.service.GroundPromotionUserService;
import com.rogrand.coreapi.user.entity.BizEnterpriseLevel;
import com.rogrand.coreapi.user.entity.BizEnterpriseVipLog;
import com.rogrand.coreapi.user.service.BizEnterpriseVipLogService;
import com.rogrand.coreapi.user.service.MphBizEnterpriseLevelService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-28 0028
 * @Version: 1.0
 */
@Service("antService")
public class AntServiceImpl implements AntService {
    private static final Logger                    logger = LoggerFactory
        .getLogger(AntServiceImpl.class);
    @Autowired
    private GroundPromotionIncomeDetailModel       groundPromotionIncomeDetailModel;
    @Autowired
    private GroundPromotionBankInfoModel           groundPromotionBankInfoModel;
    @Autowired
    private GroundPromotionPayReceiptDetailModel   groundPromotionPayReceiptDetailModel;
    @Autowired
    private GroundPromotionPayReceiptModel         groundPromotionPayReceiptModel;
    @Autowired
    private GroundPromotionUserService             groundPromotionUserService;
    @Autowired
    private GroundPromotionUserGroupService        groundPromotionUserGroupService;
    @Autowired
    private MphBizEnterpriseLevelService           mphBizEnterpriseLevelService;

    @Autowired
    private BizEnterpriseVipLogService             bizEnterpriseVipLogService;

    @Autowired
    private MphOrderService                        mphOrderService;

    @Autowired
    @Qualifier("transactionManager")
    private DataSourceTransactionManager           transactionManager;

    @Autowired
    private GroundPromotionCommissionConfigService groundPromotionCommissionConfigService;

    public ServiceResult<Double> issuedAntAmount(Map<String, Object> params) {
        return null;
    }

    public ServiceResult<GroundPromotionIncomeDetail> getAntProfitDetails(Map<String, Object> params) {
        return null;
    }

    /**
     * 新增修改银行卡信息
     * @param bankCard
     * @return
     */
    public boolean bindingBankCard(GroundPromotionBankInfo bankCard) {
        List<GroundPromotionBankInfo> groundPromotionBankInfoList = groundPromotionBankInfoModel
            .listGroundPromotionBankInfo(bankCard.getUserId());
        if (groundPromotionBankInfoList != null && groundPromotionBankInfoList.size() > 0) {
            bankCard.setId(groundPromotionBankInfoList.get(0).getId());
            return groundPromotionBankInfoModel.updateGroundPromotionBankInfo(bankCard);
        } else {
            return groundPromotionBankInfoModel.createGroundPromotionBankInfo(bankCard);
        }
    }

    public GroundPromotionPayConfig getWithdrawCashRule() {
        return null;
    }

    public ServiceResult<List<GroundPromotionIncomeDetail>> getWithdrawCashInfo(Map<String, Object> params) {
        return null;
    }

    /**
     * 提现
     * 运营后台点击提现按钮
     * 用户的receipt表相关数据插入一条已发放数据
     * 用户的income表相关数据状态修改为已发放
     * 用户的receiptDetail表插入receipt和income表的关系数据  为一对多关系
     */
    public ServiceResult<String> putForward(Integer userId) {
        ServiceResult<String> result = new ServiceResult<String>();
        //判断银行卡是否绑定
        List<GroundPromotionBankInfo> groundPromotionBankInfoList = groundPromotionBankInfoModel
            .listGroundPromotionBankInfo(userId);
        if (groundPromotionBankInfoList == null || groundPromotionBankInfoList.size() == 0) {
            result.setMessage("该用户未绑定银行卡，请绑定银行卡之后再进行提现。");
            result.setSuccess(false);
            return result;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("payStatus", 0);//待提现
        List<GroundPromotionIncomeDetail> groundPromotionIncomeDetailList = groundPromotionIncomeDetailModel
            .getGroundPromotionIncomeDetailListByUserIdStatus(param, 0, 0);

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (groundPromotionIncomeDetailList != null
                && groundPromotionIncomeDetailList.size() > 0) {
                GroundPromotionPayReceipt groundPromotionPayReceipt = new GroundPromotionPayReceipt();
                //根据user查询待发放金额
                Double amount = groundPromotionIncomeDetailModel
                    .getGroundPromotionIncomeDetailAmount(userId);
                Double historyAmount = groundPromotionPayReceiptModel
                    .getGroundPromotionReceiptAmount(userId);
                Double taxAmount = ExpenseCalcUtil.calcMonthCommissionTax(historyAmount, amount);
                groundPromotionPayReceipt.setFinalAmount(amount - taxAmount);
                groundPromotionPayReceipt.setHistoryAmount(historyAmount);
                groundPromotionPayReceipt.setTotalAmount(amount);
                groundPromotionPayReceipt.setUserId(userId);
                groundPromotionPayReceipt.setStatus(0);
                Integer receiptId = groundPromotionPayReceiptModel
                    .createGroundPromotionPayReceipt(groundPromotionPayReceipt);
                for (GroundPromotionIncomeDetail item : groundPromotionIncomeDetailList) {
                    GroundPromotionPayReceiptDetail groundPromotionPayReceiptDetail = new GroundPromotionPayReceiptDetail();
                    groundPromotionPayReceiptDetail.setIncomeId(item.getId());
                    groundPromotionPayReceiptDetail.setReceiptId(receiptId);
                    groundPromotionPayReceiptDetailModel
                        .createGroundPromotionPayReceiptDetail(groundPromotionPayReceiptDetail);
                    item.setPayStatus(1);
                    groundPromotionIncomeDetailModel.updateGroundPromotionIncomeDetail(item);
                }
            }
            transactionManager.commit(status);
            result.setSuccess(true);
        } catch (Exception e) {
            transactionManager.rollback(status);
            result.setSuccess(false);
            result.setMessage(e.toString());
        }
        return result;
    }

    /**
     * 提现驳回
     * 运营后台提取收入 点击驳回
     * 需要把相关的income表的数据状态修改为待发放 receipt表的相关数据修改为已驳回
     * @param userId
     * @return
     */
    public ServiceResult<String> BackForward(Integer userId, Integer receiptId) {
        ServiceResult<String> result = new ServiceResult<String>();
        //根据receipt主键Id得到receipt表数据
        Map<String, Object> param = new HashMap<String, Object>();
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            GroundPromotionPayReceiptDetail groundPromotionPayReceiptDetail = new GroundPromotionPayReceiptDetail();
            groundPromotionPayReceiptDetail.setReceiptId(receiptId);
            List<GroundPromotionPayReceiptDetail> gpprdList = groundPromotionPayReceiptDetailModel
                .listGroundPromotionPayReceiptDetail(groundPromotionPayReceiptDetail);
            if (gpprdList != null && gpprdList.size() > 0) {
                for (GroundPromotionPayReceiptDetail item : gpprdList) {
                    GroundPromotionIncomeDetail gpid = new GroundPromotionIncomeDetail();
                    gpid.setId(item.getIncomeId());
                    gpid.setPayStatus(1);
                    groundPromotionIncomeDetailModel.updateGroundPromotionIncomeDetail(gpid);
                }
                GroundPromotionPayReceipt groundPromotionPayReceipt = new GroundPromotionPayReceipt();
                groundPromotionPayReceipt.setId(receiptId);
                groundPromotionPayReceipt.setStatus(5);
                groundPromotionPayReceiptModel
                    .updateGroundPromotionPayReceipt(groundPromotionPayReceipt);
            }
            transactionManager.commit(status);
            result.setSuccess(true);
        } catch (Exception e) {
            transactionManager.rollback(status);
            result.setSuccess(false);
            result.setMessage(e.toString());
        }

        return result;
    }

    /**
     * 用户推新 新增用户提成数据
     * 根据用户推新的推荐人手机号  关联到用户信息表  查询到用户所属的省区
     * 如果用户有单独的个人提成设置 优先使用个人提成设置的规则来计算收入
     * 得到收入提成规则之后  生成用户推新的收入数据 插入到income表 收入状态为待发放
     */
    @Override
    public ServiceResult<String> SaveIncomeDetail(Integer eId, String oSn) {
        return saveVipIncomeDetail(eId, oSn, new Date());
    }

    /**
        * 用户注册 将用户未注册时的数据生成提成
    */
    public ServiceResult<String> saveNewUserVipIncomeDetailOld(List<Integer> eIds,
                                                               String mobilPhoto) {
        List<GroundPromotionUserRegister> groundPromotionUserRegisterList = new ArrayList<>();
        for (Integer eid : eIds) {
            //根据eid查询该商户开通vip的订单号
            PageOpenOrderConditon pageConditon = new PageOpenOrderConditon();
            List<Integer> orderTypeList = new ArrayList<Integer>();
            orderTypeList.add(OrderTypeEnum.vip.getCode());
            pageConditon.setOrderTypeList(orderTypeList);
            pageConditon.setPi(1);
            pageConditon.setPs(10000);
            pageConditon.setOrderStatus(100);
            pageConditon.setPayStatus(50);
            pageConditon.setEid(eid);
            ServiceResult<Pagination<OrderVO>> orderResult = mphOrderService
                .pageOpenOrder(pageConditon);
            List<OrderVO> orderVOList = orderResult.getResult().getList();
            if (orderVOList != null && orderVOList.size() > 0) {
                for (OrderVO item : orderVOList) {
                    GroundPromotionUserRegister groundPromotionUserRegister = new GroundPromotionUserRegister();
                    String vipType = "12";
                    String type = "";
                    if (!StringUtil.isEmpty(item.getOnote())) {
                        Map<String, Object> mapJson = JSON.parseObject(item.getOnote(), Map.class);
                        type = mapJson.get("type") != null ? mapJson.get("type").toString() : "0";
                        vipType = mapJson.get("expire") != null ? mapJson.get("expire").toString()
                            : "12";
                        if ("5".equals(type)) {
                            logger.info(
                                "[AntServiceImpl.SaveIncomeDetail] itemBizLevel object:工业vip赠送不产生提成，订单号："
                                        + item.getOsn());
                            break;
                        }
                    }
                    if (vipType.equals("12")) {
                        groundPromotionUserRegister.setCreateTime(item.getPayTime());//支付时间
                        groundPromotionUserRegister.setoSn(item.getOsn());//订单号
                        groundPromotionUserRegister.setEid(item.getOeId());//eid
                        groundPromotionUserRegisterList.add(groundPromotionUserRegister);
                    }
                }
            }

        }
        ServiceResult<String> result = new ServiceResult<String>();
        try {
            if (groundPromotionUserRegisterList != null
                && groundPromotionUserRegisterList.size() > 0) {
                for (GroundPromotionUserRegister itemUserRegisterInfo : groundPromotionUserRegisterList) {
                    Integer eId = itemUserRegisterInfo.getEid();
                    String oSn = itemUserRegisterInfo.getoSn();

                    //判断用户手机号是否存在 不存在直接提示用户手机号不存在  无法插入新收入
                    ServiceResult<BizEnterpriseLevel> bizResult = mphBizEnterpriseLevelService
                        .findEnterPriseLevelByEid(eId);

                    BizEnterpriseLevel itemBizLevel = new BizEnterpriseLevel();
                    itemBizLevel = bizResult.getResult();
                    logger.info("[AntServiceImpl.SaveIncomeDetail] itemBizLevel object:"
                                + (itemBizLevel != null ? itemBizLevel.toString() : ""));
                    String mobilPhone = itemBizLevel.getRecommendPhone();

                    Date createTime = itemBizLevel.getPremiumStart();
                    Integer kpiFlag = 1;
                    if (isSameDate(createTime)) {
                        kpiFlag = 0;
                    }
                    if (mobilPhone == null || mobilPhone.isEmpty()) {
                        result.setMessage("用户手机号为空，无法插入收入数据");
                        result.setSuccess(false);
                        return result;
                    }

                    //根据推新的推荐人手机号查询用户信息表数据
                    ServiceResult<GroundPromotionUser> userServiceResult = groundPromotionUserService
                        .getUserByMobile(mobilPhone);
                    //根据推新的推荐人手机号查询企业eId,vip或者svip有效期，vip或者svip标识
                    //TODO:柳慧林 根据推新的推荐人手机号查询企业eId,eName,vip或者svip有效期，vip或者svip标识

                    String eName = itemBizLevel.getEName() == null ? "无值" : itemBizLevel.getEName();
                    Integer vipFlag = itemBizLevel.getPremiumType() == 1 ? 5 : 1;//vip 5 svip 1
                    GroundPromotionUser groundPromotionUser = userServiceResult.getResult();
                    Integer provinceId = 0;

                    GroundPromotionCommissionConfig item = new GroundPromotionCommissionConfig();
                    if (groundPromotionUser != null) {
                        provinceId = groundPromotionUser.getProvinceId();
                        item.setLinkId(groundPromotionUser.getUserId());
                    }
                    item.setScopeType(2);
                    item.setBusinessType(1);
                    //如果获取小药宝注册人员信息为空的话.暂时不处理提成计算逻辑
                    if (groundPromotionUser == null) {
                        logger.warn("saveVipIncomeDetail方法的groundPromotionUser为null!");
                        result.setSuccess(false);
                        result.setMessage("saveVipIncomeDetail方法的groundPromotionUser为null!");
                        return result;
                    }
                    List<GroundPromotionIncomeDetail> incomeList = PubliceMethod
                        .getIncomeInfoDetail(groundPromotionCommissionConfigService,
                            groundPromotionUser, 1, 0.00, null, null, 1,
                            groundPromotionUserService);
                    for (GroundPromotionIncomeDetail groundPromotionIncomeDetail : incomeList) {
                        groundPromotionIncomeDetail.setItemName(eName);
                        groundPromotionIncomeDetail.setTypeDetail(vipFlag);
                        groundPromotionIncomeDetail.setLinkIdentity(eId.toString());
                        groundPromotionIncomeDetail.setType(1);
                        groundPromotionIncomeDetail.setRemark("补充数据");
                        groundPromotionIncomeDetail.setSaleAmount(0.00);
                        groundPromotionIncomeDetail.setSaleNum(0);
                        groundPromotionIncomeDetail.setDueTime(itemBizLevel.getPremiumEnd());
                        groundPromotionIncomeDetail.setCreateTime(createTime);
                        groundPromotionIncomeDetail.setDisplaySn(oSn);
                        groundPromotionIncomeDetail.setKpiFlag(kpiFlag);
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("associateType", groundPromotionIncomeDetail.getAssociateType());
                        params.put("oSn", oSn);

                        //12个月的会员购买才插入提成收入表数据   不是12个月的不插入提成
                        if (itemBizLevel.getPremiunExpire() == 12) {
                            List<GroundPromotionIncomeDetail> groundPromotionIncomeDetailList = groundPromotionIncomeDetailModel
                                .getGroundPromotionIncomeDetailById(params);
                            if (groundPromotionIncomeDetailList != null
                                && groundPromotionIncomeDetailList.size() > 0) {
                                logger.error("[AntServiceImpl.SaveIncomeDetail] 该收入已插入，订单号为： " + oSn
                                             + ",associateType为："
                                             + groundPromotionIncomeDetail.getAssociateType());
                            } else {
                                logger.info("[AntServiceImpl.SaveIncomeDetail] 即将插入提成，订单号为： " + oSn
                                            + ",associateType为："
                                            + groundPromotionIncomeDetail.getAssociateType());
                                groundPromotionIncomeDetailModel
                                    .createGroundPromotionIncomeDetail(groundPromotionIncomeDetail);
                            }
                        }

                    }
                    result.setSuccess(true);
                }
            }
        } catch (Exception e) {
            logger.error("[AntServiceImpl.SaveIncomeDetail] " + e.getMessage());
            logger.error("SaveIncomeDetail encounter exception", e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 用户推新 新增用户提成数据
     * 根据用户推新的推荐人手机号  关联到用户信息表  查询到用户所属的省区
     * 如果用户有单独的个人提成设置 优先使用个人提成设置的规则来计算收入
     * 得到收入提成规则之后  生成用户推新的收入数据 插入到income表 收入状态为待发放
     */
    @Override
    public ServiceResult<String> saveVipIncomeDetail(Integer eId, String oSn, Date createTime) {
        ServiceResult<String> result = new ServiceResult<String>();
        try {
            //判断用户手机号是否存在 不存在直接提示用户手机号不存在  无法插入新收入
            ServiceResult<BizEnterpriseLevel> bizResult = mphBizEnterpriseLevelService
                .findEnterPriseLevelByEid(eId);
            ServiceResult<BizEnterpriseVipLog> bizEnterpriseVipLogServiceResult = bizEnterpriseVipLogService
                .getInfoByOsn(oSn);

            BizEnterpriseLevel itemBizLevel = bizResult.getResult();
            BizEnterpriseVipLog bizEnterpriseVipLog = bizEnterpriseVipLogServiceResult.getResult();
            logger.info("[AntServiceImpl.SaveIncomeDetail] itemBizLevel object:"
                        + (itemBizLevel != null ? itemBizLevel.toString() : ""));
            logger.info("[AntServiceImpl.SaveIncomeDetail] bizEnterpriseVipLog object:"
                        + (bizEnterpriseVipLog != null ? bizEnterpriseVipLog.toString() : ""));
            String mobilPhone = "";
            if (bizEnterpriseVipLog != null) {
                mobilPhone = bizEnterpriseVipLog.getRecommendPhone();
            } else {
                logger.warn(
                    "[AntServiceImpl.SaveIncomeDetail] bizEnterpriseVipLog为空,请确认bizEnterpriseVipLogService.getInfoByOsn(oSn)");
            }

            if (itemBizLevel == null || bizEnterpriseVipLog == null || mobilPhone == null
                || mobilPhone.isEmpty()) {
                logger.warn(
                    "[AntServiceImpl.SaveIncomeDetail] mobilPhone为空,请确认mphBizEnterpriseLevelService.findEnterPriseLevelByEid(eId)");
                logger.warn("[AntServiceImpl.SaveIncomeDetail] itemBizLevel object:"
                            + (itemBizLevel != null ? itemBizLevel.toString() : ""));
                logger.warn("[AntServiceImpl.SaveIncomeDetail] bizEnterpriseVipLog object:"
                            + (bizEnterpriseVipLog != null ? bizEnterpriseVipLog.toString() : ""));
                result.setMessage("用户手机号为空，无法插入收入数据");
                result.setSuccess(false);
                return result;
            }

            //根据推新的推荐人手机号查询用户信息表数据
            ServiceResult<GroundPromotionUser> userServiceResult = groundPromotionUserService
                .getUserByMobile(mobilPhone);
            //如果获取小药宝注册人员信息为空的话.暂时不处理提成计算逻辑
            if (!userServiceResult.getSuccess() || userServiceResult.getResult() == null) {
                logger.warn("[AntServiceImpl.SaveIncomeDetail]根据手机号没有找到药小宝用户。eid:" + eId
                            + " orderSn:" + oSn + " mobile:" + mobilPhone);
                result.setSuccess(false);
                result.setMessage("saveVipIncomeDetail方法的groundPromotionUser为null!");
                return result;
            }

            //根据推新的推荐人手机号查询企业eId,vip或者svip有效期，vip或者svip标识
            //TODO:柳慧林 根据推新的推荐人手机号查询企业eId,eName,vip或者svip有效期，vip或者svip标识

            String eName = itemBizLevel.getEName() == null ? "无值" : itemBizLevel.getEName();
            Integer vipFlag = itemBizLevel.getPremiumType() == 1 ? 5 : 1;//vip 5 svip 1
            GroundPromotionUser groundPromotionUser = userServiceResult.getResult();

            List<GroundPromotionIncomeDetail> incomeList = PubliceMethod.getIncomeInfoDetail(
                groundPromotionCommissionConfigService, groundPromotionUser, 1, 0.00, null, null, 1,
                groundPromotionUserService);
            for (GroundPromotionIncomeDetail groundPromotionIncomeDetail : incomeList) {
                groundPromotionIncomeDetail.setItemName(eName);
                groundPromotionIncomeDetail.setTypeDetail(vipFlag);
                groundPromotionIncomeDetail.setLinkIdentity(eId.toString());
                groundPromotionIncomeDetail.setType(1);
                groundPromotionIncomeDetail.setRemark("");
                groundPromotionIncomeDetail.setSaleAmount(0.00);
                groundPromotionIncomeDetail.setSaleNum(0);
                groundPromotionIncomeDetail.setDueTime(bizEnterpriseVipLog.getPremiumEnd());
                groundPromotionIncomeDetail.setCreateTime(createTime);
                groundPromotionIncomeDetail.setKpiFlag(0);
                groundPromotionIncomeDetail.setDisplaySn(oSn);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("associateType", groundPromotionIncomeDetail.getAssociateType());
                params.put("oSn", oSn);

                //12个月的会员购买才插入提成收入表数据   不是12个月的不插入提成
                if (bizEnterpriseVipLog.getPremiunExpire() == 12) {
                    List<GroundPromotionIncomeDetail> groundPromotionIncomeDetailList = groundPromotionIncomeDetailModel
                        .getGroundPromotionIncomeDetailById(params);
                    if (groundPromotionIncomeDetailList != null
                        && groundPromotionIncomeDetailList.size() > 0) {
                        logger.error("[AntServiceImpl.SaveIncomeDetail] 该收入已插入，订单号为： " + oSn
                                     + ",associateType为："
                                     + groundPromotionIncomeDetail.getAssociateType());
                    } else {
                        groundPromotionIncomeDetailModel
                            .createGroundPromotionIncomeDetail(groundPromotionIncomeDetail);
                    }
                }

            }
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("[AntServiceImpl.SaveIncomeDetail] " + e.getMessage());
            logger.error("SaveIncomeDetail encounter exception", e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 续费--用户注册 将用户未注册时的数据生成提成
     */
    @Override
    public ServiceResult<String> saveNewUserVipIncomeDetail(List<Integer> eIds, String mobilPhoto) {
        List<GroundPromotionUserRegister> groundPromotionUserRegisterList = new ArrayList<>();
        ServiceResult<String> result = new ServiceResult<String>();
        for (Integer eid : eIds) {
            //根据eid查询该商户开通vip的订单号
            PageOpenOrderConditon pageConditon = new PageOpenOrderConditon();
            List<Integer> orderTypeList = new ArrayList<Integer>();
            orderTypeList.add(OrderTypeEnum.vip.getCode());
            pageConditon.setOrderTypeList(orderTypeList);
            pageConditon.setPi(1);
            pageConditon.setPs(10000);
            pageConditon.setOrderStatus(100);
            pageConditon.setPayStatus(50);
            pageConditon.setEid(eid);
            ServiceResult<Pagination<OrderVO>> orderResult = mphOrderService
                .pageOpenOrder(pageConditon);
            List<OrderVO> orderVOList = orderResult.getResult().getList();
            if (orderVOList != null && orderVOList.size() > 0) {
                for (OrderVO item : orderVOList) {
                    GroundPromotionUserRegister groundPromotionUserRegister = new GroundPromotionUserRegister();
                    String vipType = "12";
                    String type = "";
                    if (!StringUtil.isEmpty(item.getOnote())) {
                        Map<String, Object> mapJson = JSON.parseObject(item.getOnote(), Map.class);
                        vipType = mapJson.get("expire") != null ? mapJson.get("expire").toString()
                            : "12";
                        type = mapJson.get("type") != null ? mapJson.get("type").toString() : "0";
                        if (mapJson.get("invitationCode") != null
                            && mapJson.get("invitationCode") != "") {
                            continue;
                        }
                        if (type.equals("5")) {
                            continue;
                        }
                        if (vipType.equals("12")) {
                            groundPromotionUserRegister.setCreateTime(item.getPayTime());//支付时间
                            groundPromotionUserRegister.setoSn(item.getOsn());//订单号
                            groundPromotionUserRegister.setEid(item.getOeId());//eid
                            groundPromotionUserRegisterList.add(groundPromotionUserRegister);
                        }
                    }
                }
            }

        }

        try {
            if (groundPromotionUserRegisterList != null
                && groundPromotionUserRegisterList.size() > 0) {
                for (GroundPromotionUserRegister itemUserRegisterInfo : groundPromotionUserRegisterList) {
                    Integer eId = itemUserRegisterInfo.getEid();
                    String oSn = itemUserRegisterInfo.getoSn();

                    //判断用户手机号是否存在 不存在直接提示用户手机号不存在  无法插入新收入
                    ServiceResult<BizEnterpriseLevel> bizResult = mphBizEnterpriseLevelService
                        .findEnterPriseLevelByEid(eId);
                    ServiceResult<BizEnterpriseVipLog> bizEnterpriseVipLogServiceResult = bizEnterpriseVipLogService
                        .getInfoByOsn(oSn);

                    BizEnterpriseLevel itemBizLevel = new BizEnterpriseLevel();
                    BizEnterpriseVipLog bizEnterpriseVipLog = new BizEnterpriseVipLog();
                    itemBizLevel = bizResult.getResult();
                    bizEnterpriseVipLog = bizEnterpriseVipLogServiceResult.getResult();
                    logger.info("[AntServiceImpl.SaveIncomeDetail] itemBizLevel object:"
                                + (itemBizLevel != null ? itemBizLevel.toString() : ""));
                    String mobilPhone = "";
                    if (bizEnterpriseVipLog != null) {
                        mobilPhone = bizEnterpriseVipLog.getRecommendPhone();
                    } else {
                        logger.warn(
                            "[AntServiceImpl.SaveIncomeDetail] bizEnterpriseVipLog为空,请确认bizEnterpriseVipLogService.getInfoByOsn(oSn)");
                    }

                    Date createTime = bizEnterpriseVipLog.getPremiumStart();
                    Integer kpiFlag = 1;
                    if (isSameDate(createTime)) {
                        kpiFlag = 0;
                    }
                    if (mobilPhone == null || mobilPhone.isEmpty()) {
                        result.setMessage("用户手机号为空，无法插入收入数据");
                        result.setSuccess(false);
                        return result;
                    }

                    //根据推新的推荐人手机号查询用户信息表数据
                    ServiceResult<GroundPromotionUser> userServiceResult = groundPromotionUserService
                        .getUserByMobile(mobilPhone);
                    //根据推新的推荐人手机号查询企业eId,vip或者svip有效期，vip或者svip标识
                    //TODO:柳慧林 根据推新的推荐人手机号查询企业eId,eName,vip或者svip有效期，vip或者svip标识

                    String eName = itemBizLevel.getEName() == null ? "无值" : itemBizLevel.getEName();
                    Integer vipFlag = itemBizLevel.getPremiumType() == 1 ? 5 : 1;//vip 5 svip 1
                    GroundPromotionUser groundPromotionUser = userServiceResult.getResult();
                    Integer provinceId = 0;

                    GroundPromotionCommissionConfig item = new GroundPromotionCommissionConfig();
                    if (groundPromotionUser != null) {
                        provinceId = groundPromotionUser.getProvinceId();
                        item.setLinkId(groundPromotionUser.getUserId());
                    }
                    item.setScopeType(2);
                    item.setBusinessType(1);
                    //如果获取小药宝注册人员信息为空的话.暂时不处理提成计算逻辑
                    if (groundPromotionUser == null) {
                        logger.warn("saveVipIncomeDetail方法的groundPromotionUser为null!");
                        result.setSuccess(false);
                        result.setMessage("saveVipIncomeDetail方法的groundPromotionUser为null!");
                        return result;
                    }
                    List<GroundPromotionIncomeDetail> incomeList = PubliceMethod
                        .getIncomeInfoDetail(groundPromotionCommissionConfigService,
                            groundPromotionUser, 1, 0.00, null, null, 1,
                            groundPromotionUserService);
                    for (GroundPromotionIncomeDetail groundPromotionIncomeDetail : incomeList) {
                        groundPromotionIncomeDetail.setItemName(eName);
                        groundPromotionIncomeDetail.setTypeDetail(vipFlag);
                        groundPromotionIncomeDetail.setLinkIdentity(eId.toString());
                        groundPromotionIncomeDetail.setType(1);
                        groundPromotionIncomeDetail.setRemark("补充数据");
                        groundPromotionIncomeDetail.setSaleAmount(0.00);
                        groundPromotionIncomeDetail.setSaleNum(0);
                        groundPromotionIncomeDetail.setDueTime(bizEnterpriseVipLog.getPremiumEnd());
                        groundPromotionIncomeDetail.setCreateTime(createTime);
                        groundPromotionIncomeDetail.setDisplaySn(oSn);
                        groundPromotionIncomeDetail.setKpiFlag(kpiFlag);
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("associateType", groundPromotionIncomeDetail.getAssociateType());
                        params.put("oSn", oSn);

                        //12个月的会员购买才插入提成收入表数据   不是12个月的不插入提成
                        if (bizEnterpriseVipLog.getPremiunExpire() == 12) {
                            List<GroundPromotionIncomeDetail> groundPromotionIncomeDetailList = groundPromotionIncomeDetailModel
                                .getGroundPromotionIncomeDetailById(params);
                            if (groundPromotionIncomeDetailList != null
                                && groundPromotionIncomeDetailList.size() > 0) {
                                logger.error("[AntServiceImpl.SaveIncomeDetail] 该收入已插入，订单号为： " + oSn
                                             + ",associateType为："
                                             + groundPromotionIncomeDetail.getAssociateType());
                            } else {
                                logger.info("[AntServiceImpl.SaveIncomeDetail] 即将插入提成，订单号为： " + oSn
                                            + ",associateType为："
                                            + groundPromotionIncomeDetail.getAssociateType());
                                groundPromotionIncomeDetailModel
                                    .createGroundPromotionIncomeDetail(groundPromotionIncomeDetail);
                            }
                        }

                    }
                    result.setSuccess(true);
                }
            }
        } catch (Exception e) {
            logger.error("[AntServiceImpl.SaveIncomeDetail] " + e.getMessage());
            logger.error("SaveIncomeDetail encounter exception", e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 判断时间日期是否在本月范围内
     * @param date1
     * @return
     */
    public static boolean isSameDate(Date date1) {
        try {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(new Date());

            boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
            boolean isSameMonth = isSameYear
                                  && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
            boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

            return isSameDate;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 用户推新 新增用户提成数据
     * 根据用户推新的推荐人手机号  关联到用户信息表  查询到用户所属的省区
     * 如果用户有单独的个人提成设置 优先使用个人提成设置的规则来计算收入
     * 得到收入提成规则之后  生成用户推新的收入数据 插入到income表 收入状态为待发放
     */
    public ServiceResult<String> saveVipIncomeDetailold(Integer eId, String oSn, Date createTime) {
        ServiceResult<String> result = new ServiceResult<String>();
        try {
            //判断用户手机号是否存在 不存在直接提示用户手机号不存在  无法插入新收入
            ServiceResult<BizEnterpriseLevel> bizResult = mphBizEnterpriseLevelService
                .findEnterPriseLevelByEid(eId);

            BizEnterpriseLevel itemBizLevel = new BizEnterpriseLevel();
            itemBizLevel = bizResult.getResult();
            logger.info("[AntServiceImpl.SaveIncomeDetail] itemBizLevel object:"
                        + (itemBizLevel != null ? itemBizLevel.toString() : ""));
            String mobilPhone = itemBizLevel.getRecommendPhone();

            if (mobilPhone == null || mobilPhone.isEmpty()) {
                logger.warn(
                    "[AntServiceImpl.SaveIncomeDetail] mobilPhone为空,请确认mphBizEnterpriseLevelService.findEnterPriseLevelByEid(eId)");
                logger.warn("[AntServiceImpl.SaveIncomeDetail] itemBizLevel object:"
                            + (itemBizLevel != null ? itemBizLevel.toString() : ""));
                result.setMessage("用户手机号为空，无法插入收入数据");
                result.setSuccess(false);
                return result;
            }

            //根据推新的推荐人手机号查询用户信息表数据
            ServiceResult<GroundPromotionUser> userServiceResult = groundPromotionUserService
                .getUserByMobile(mobilPhone);
            //根据推新的推荐人手机号查询企业eId,vip或者svip有效期，vip或者svip标识
            //TODO:柳慧林 根据推新的推荐人手机号查询企业eId,eName,vip或者svip有效期，vip或者svip标识

            String eName = itemBizLevel.getEName() == null ? "无值" : itemBizLevel.getEName();
            Integer vipFlag = itemBizLevel.getPremiumType() == 1 ? 5 : 1;//vip 5 svip 1
            GroundPromotionUser groundPromotionUser = userServiceResult.getResult();
            Integer provinceId = 0;

            GroundPromotionCommissionConfig item = new GroundPromotionCommissionConfig();
            if (groundPromotionUser != null) {
                provinceId = groundPromotionUser.getProvinceId();
                item.setLinkId(groundPromotionUser.getUserId());
            }
            item.setScopeType(2);
            item.setBusinessType(1);
            //如果获取小药宝注册人员信息为空的话.暂时不处理提成计算逻辑
            if (groundPromotionUser == null) {
                logger.warn("saveVipIncomeDetail方法的groundPromotionUser为null!");
                result.setSuccess(false);
                result.setMessage("saveVipIncomeDetail方法的groundPromotionUser为null!");
                return result;
            }
            List<GroundPromotionIncomeDetail> incomeList = PubliceMethod.getIncomeInfoDetail(
                groundPromotionCommissionConfigService, groundPromotionUser, 1, 0.00, null, null, 1,
                groundPromotionUserService);
            for (GroundPromotionIncomeDetail groundPromotionIncomeDetail : incomeList) {
                groundPromotionIncomeDetail.setItemName(eName);
                groundPromotionIncomeDetail.setTypeDetail(vipFlag);
                groundPromotionIncomeDetail.setLinkIdentity(eId.toString());
                groundPromotionIncomeDetail.setType(1);
                groundPromotionIncomeDetail.setRemark("");
                groundPromotionIncomeDetail.setSaleAmount(0.00);
                groundPromotionIncomeDetail.setSaleNum(0);
                groundPromotionIncomeDetail.setDueTime(itemBizLevel.getPremiumEnd());
                groundPromotionIncomeDetail.setCreateTime(createTime);
                groundPromotionIncomeDetail.setKpiFlag(0);
                groundPromotionIncomeDetail.setDisplaySn(oSn);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("associateType", groundPromotionIncomeDetail.getAssociateType());
                params.put("oSn", oSn);

                //12个月的会员购买才插入提成收入表数据   不是12个月的不插入提成
                if (itemBizLevel.getPremiunExpire() == 12) {
                    List<GroundPromotionIncomeDetail> groundPromotionIncomeDetailList = groundPromotionIncomeDetailModel
                        .getGroundPromotionIncomeDetailById(params);
                    if (groundPromotionIncomeDetailList != null
                        && groundPromotionIncomeDetailList.size() > 0) {
                        logger.error("[AntServiceImpl.SaveIncomeDetail] 该收入已插入，订单号为： " + oSn
                                     + ",associateType为："
                                     + groundPromotionIncomeDetail.getAssociateType());
                    } else {
                        groundPromotionIncomeDetailModel
                            .createGroundPromotionIncomeDetail(groundPromotionIncomeDetail);
                    }
                }

            }
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("[AntServiceImpl.SaveIncomeDetail] " + e.getMessage());
            logger.error("SaveIncomeDetail encounter exception", e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 得到待提现信息
     * @param userId
     * @return
     */
    public GroundPromotionIncomeDetail getIncomeInfo(Integer userId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        String idList = "";
        Double amount = 0.00;
        List<GroundPromotionIncomeDetail> groundPromotionIncomeDetailList = groundPromotionIncomeDetailModel
            .getGroundPromotionIncomeDetailById(params);
        if (groundPromotionIncomeDetailList != null && groundPromotionIncomeDetailList.size() > 0) {
            for (GroundPromotionIncomeDetail incomeItem : groundPromotionIncomeDetailList) {
                amount += incomeItem.getAmount();
                idList += "," + incomeItem.getId();
            }
        }
        List<GroundPromotionBankInfo> bankInfoList = groundPromotionBankInfoModel
            .listGroundPromotionBankInfo(userId);
        Double historyAmount = groundPromotionPayReceiptModel
            .getGroundPromotionReceiptAmount(userId);

        Double taxAmount = 0D;
        Double finalAmount = 0D;
        if (null != amount) {
            ServiceResult<GroundPromotionUserGroup> rpcResult = groundPromotionUserGroupService.findGroupByUserId(userId);
            GroundPromotionUserGroup group = null;
            if(rpcResult != null && rpcResult.getSuccess() && rpcResult.getResult() != null ) {
                group = rpcResult.getResult();
            }
            if(group == null || group.getCommissionType() == null 
                    || "bTax".equalsIgnoreCase(group.getCommissionType())) {
                taxAmount = ExpenseCalcUtil.calcMonthCommissionTax(historyAmount, amount);
            }
            finalAmount = amount - taxAmount;
        }

        GroundPromotionIncomeDetail item = new GroundPromotionIncomeDetail();
        item.setTotalAmount(amount);
        item.setTaxAmount(taxAmount);
        item.setFinalAmount(finalAmount);
        item.setDepositAmount(historyAmount);
        item.setIdList(EncryptUtil.toBASE64(idList));

        if (bankInfoList != null && bankInfoList.size() > 0) {
            GroundPromotionBankInfo bankinfo = bankInfoList.get(0);
            item.setBankName(bankinfo.getBankName());
            String cardNo = bankinfo.getBankCardNo();
            if (cardNo.length() >= 4) {
                item.setCardLast4No(cardNo.substring(cardNo.length() - 4, cardNo.length()));
            } else {
                item.setCardLast4No(cardNo);
            }
        }
        return item;
    }

    /**
     * 蚂蚁人员提现记录
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public ServiceResult<Pagination> listGroundPromotionPayReceiptInfo(Map<String, Object> param,
                                                                       int pageNo, int pageSize) {
        ServiceResult<Pagination> result = new ServiceResult<Pagination>();
        List<GroundPromotionPayReceipt> list = null;
        List<AntPayRecordDto> antPayRecordDtoList = new ArrayList<AntPayRecordDto>();
        try {
            int totalCount = groundPromotionPayReceiptModel
                .countGroundPromotionPayReceiptInfo(param);
            // 构建分页数据对象
            Pagination pagination = new Pagination(pageNo, pageSize, totalCount);
            //参数验证

            if (0 != totalCount) {
                list = groundPromotionPayReceiptModel.listGroundPromotionPayReceiptInfo(param,
                    pagination.getFirstResult(), pageSize);
                if (list != null && list.size() > 0) {
                    Double historyAmount = 0d;
                    if(param.containsKey("userId")) {
                        Integer userId = (Integer) param.get("userId");
                        historyAmount = groundPromotionPayReceiptModel.getGroundPromotionReceiptAmount(userId);
                    }
                    for (GroundPromotionPayReceipt item : list) {
                        AntPayRecordDto antPayRecordDto = new AntPayRecordDto();
                        antPayRecordDto.setReceiptSn(item.getReceiptSn());
                        antPayRecordDto.setTotalAmount(item.getTotalAmount());
                        antPayRecordDto.setTaxAmount(item.getTaxAmount());
                        antPayRecordDto.setFinalAmount(item.getFinalAmount());
                        Map<String, Object> resultMap = getStatusInfo(item.getStatus());
                        antPayRecordDto
                            .setStatus(Integer.parseInt(resultMap.get("status").toString()));
                        antPayRecordDto.setStatusDesc(resultMap.get("statusDesc").toString());
                        antPayRecordDto.setCreateTime(item.getCreateTime());
                        antPayRecordDto.setBankName(item.getBankName());
                        antPayRecordDto.setCardLast4No(item.getBankCardNo().substring(
                            item.getBankCardNo().length() - 4, item.getBankCardNo().length()));
                        antPayRecordDto.setDepositAmount(historyAmount);
                        antPayRecordDtoList.add(antPayRecordDto);
                    }
                    pagination.setList(antPayRecordDtoList);
                } else {
                    pagination.setList(antPayRecordDtoList);
                }
            } else {
                pagination.setList(antPayRecordDtoList);
            }

            result.setResult(pagination);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("系统异常！");
        }
        return result;
    }

    public Map<String, Object> getStatusInfo(Integer receiptStatus) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String statusDesc = "";
        Integer status = 0;
        switch (receiptStatus) {
            case 0:
            case 2:
            case 3:
            case 4:
                status = 2;
                statusDesc = "提现中";
                break;
            case 1:
                status = 1;
                statusDesc = "提现成功";
                break;
            case 5:
                status = 3;
                statusDesc = "提现失败";
                break;
            default:
                status = -1;
                statusDesc = "";
                break;
        }
        resultMap.put("statusDesc", statusDesc);
        resultMap.put("status", status);
        return resultMap;
    }

    /**
     * 蚂蚁人员确认提现
     * @param userId
     * @return
     */
    public ServiceResult<String> confirmGetPayAmount(Integer userId, String idList) {
        ServiceResult<String> result = new ServiceResult<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        idList = EncryptUtil.fromBASE64(idList);
        idList = idList.substring(1, idList.length());

        params.put("userId", userId);
        params.put("idList", idList);
        List<GroundPromotionIncomeDetail> groundPromotionIncomeDetailList = groundPromotionIncomeDetailModel
            .getGroundPromotionIncomeDetailById(params);
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (groundPromotionIncomeDetailList != null
                && groundPromotionIncomeDetailList.size() > 0) {
                GroundPromotionPayReceipt groundPromotionPayReceipt = new GroundPromotionPayReceipt();
                //根据user查询待发放金额
                Double historyAmount = groundPromotionPayReceiptModel
                    .getGroundPromotionReceiptAmount(userId);
                Double amount = groundPromotionIncomeDetailModel
                    .getGroundPromotionIncomeDetailAmount(userId);

                Double taxAmount = 0d;
                ServiceResult<GroundPromotionUserGroup> rpcResult = groundPromotionUserGroupService.findGroupByUserId(userId);
                GroundPromotionUserGroup group = null;
                if(rpcResult != null && rpcResult.getSuccess() && rpcResult.getResult() != null ) {
                    group = rpcResult.getResult();
                }
                if(group == null || group.getCommissionType() == null 
                        || "bTax".equalsIgnoreCase(group.getCommissionType())) {
                    taxAmount = ExpenseCalcUtil.calcCommissionTax(amount);
                }
                groundPromotionPayReceipt.setFinalAmount(amount - taxAmount);
                groundPromotionPayReceipt.setTaxAmount(taxAmount);
                groundPromotionPayReceipt.setTotalAmount(amount);
                groundPromotionPayReceipt.setHistoryAmount(historyAmount);
                groundPromotionPayReceipt.setUserId(userId);
                groundPromotionPayReceipt.setStatus(0);
                groundPromotionPayReceipt.setReceiptSn(SnGenerator.genReceiptSn());
                groundPromotionPayReceiptModel
                    .createGroundPromotionPayReceipt(groundPromotionPayReceipt);
                Integer receiptId = groundPromotionPayReceipt.getId();
                for (GroundPromotionIncomeDetail item : groundPromotionIncomeDetailList) {
                    GroundPromotionPayReceiptDetail groundPromotionPayReceiptDetail = new GroundPromotionPayReceiptDetail();
                    groundPromotionPayReceiptDetail.setIncomeId(item.getId());
                    groundPromotionPayReceiptDetail.setReceiptId(receiptId);
                    groundPromotionPayReceiptDetailModel
                        .createGroundPromotionPayReceiptDetail(groundPromotionPayReceiptDetail);
                    item.setPayStatus(1);
                    groundPromotionIncomeDetailModel.updateGroundPromotionIncomeDetail(item);
                }
            }
            transactionManager.commit(status);
            result.setSuccess(true);
        } catch (Exception e) {
            transactionManager.rollback(status);
            result.setSuccess(false);
            result.setMessage(e.toString());
        }
        return result;
    }

    /**
     * 根据userId得到用户银行卡信息
     * @param userId
     * @return
     */
    public BankInfoDto getBankInfoByUserId(Integer userId) {
        List<GroundPromotionBankInfo> groundPromotionBankInfoList = groundPromotionBankInfoModel
            .listGroundPromotionBankInfo(userId);
        if (groundPromotionBankInfoList != null && groundPromotionBankInfoList.size() > 0) {
            BankInfoDto bankDto = new BankInfoDto();
            GroundPromotionBankInfo groundPromotionBankInfo = groundPromotionBankInfoList.get(0);
            bankDto.setBankCode(groundPromotionBankInfo.getBankCode());
            bankDto.setBankName(groundPromotionBankInfo.getBankName());
            bankDto.setBankCardNo(groundPromotionBankInfo.getBankCardNo());
            bankDto.setBranchName(groundPromotionBankInfo.getBranchName());
            bankDto.setCityCode(groundPromotionBankInfo.getCityCode());
            bankDto.setProvinceCode(groundPromotionBankInfo.getProvinceCode());
            bankDto.setIdentityCardNo(groundPromotionBankInfo.getIdentityCardNo());
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("locationType", 1);
            params.put("locationCode", groundPromotionBankInfo.getProvinceCode());
            GroundPromotionLocation location = new GroundPromotionLocation();
            location = groundPromotionBankInfoModel.getLocationInfo(params);
            String provinceName = "";
            if (location != null) {
                provinceName = location.getLocationName();
            }
            params.put("locationType", 2);
            params.put("locationCode", groundPromotionBankInfo.getCityCode());
            location = groundPromotionBankInfoModel.getLocationInfo(params);
            String cityName = "";
            if (location != null) {
                cityName = location.getLocationName();
            }
            bankDto.setProvinceName(provinceName);
            bankDto.setCityName(cityName);

            return bankDto;
        }
        return null;

    }

}
