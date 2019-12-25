package com.mph.coreapi.gp.pay.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.dto.BankInfoDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionBankInfo;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayConfig;
import com.mph.coreapi.gp.pay.entity.GroundPromotionUserRegister;
import com.rogrand.common.ServiceResult;
import com.rogrand.common.page.Pagination;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-28 0028
 * @Version: 1.0
 */
public interface AntService {

    /**
     * 获得收益明细
     * @param params
     * @return
     */
    ServiceResult<GroundPromotionIncomeDetail> getAntProfitDetails(Map<String,Object> params);

    /**
     * 绑定银行卡
     * @param bankCard
     * @return
     */
    boolean bindingBankCard(GroundPromotionBankInfo bankCard);

    /**
     * 提现明细列表
     * @return
     */
    ServiceResult<List<GroundPromotionIncomeDetail>> getWithdrawCashInfo(Map<String,Object> params);


    /**
     * 点击提现
     * @param userId
     * @return
     */
    ServiceResult<String>  putForward(Integer userId);

    /**
     * 提现驳回
     * @param userId
     * @return
     */
    ServiceResult<String> BackForward(Integer userId,Integer receiptId);

    /**
     * 得到待提现信息
     * @param userId
     * @return
     */
    GroundPromotionIncomeDetail getIncomeInfo(Integer userId);

    /**
     * 蚂蚁人员提现记录
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    ServiceResult<Pagination> listGroundPromotionPayReceiptInfo(Map<String,Object> param, int pageNo, int pageSize);

    /**
     * 蚂蚁人员确认提现
     * @param userId
     * @return
     */
    ServiceResult<String>  confirmGetPayAmount(Integer userId,String idList);

    /**
     * 根据userId得到用户银行卡信息
     * @param userId
     * @return
     */
    BankInfoDto getBankInfoByUserId(Integer userId);

    /**
     * vip推新提成
     * @param eId
     * @return
     */
    ServiceResult<String> SaveIncomeDetail(Integer eId,String oSn);


    /**
     * 用户推新 新增用户提成数据
     * 根据用户推新的推荐人手机号  关联到用户信息表  查询到用户所属的省区
     * 如果用户有单独的个人提成设置 优先使用个人提成设置的规则来计算收入
     * 得到收入提成规则之后  生成用户推新的收入数据 插入到income表 收入状态为待发放
     */
    ServiceResult<String> saveVipIncomeDetail(Integer eId, String oSn, Date createTime);

    /**
    * 用户注册 将用户未注册时的数据生成提成
    */
    ServiceResult<String> saveNewUserVipIncomeDetail(List<Integer> eIds,String mobilPhoto);


//    /**
//     * 用户推新 新增用户提成数据
//     * 根据用户推新的推荐人手机号  关联到用户信息表  查询到用户所属的省区
//     * 如果用户有单独的个人提成设置 优先使用个人提成设置的规则来计算收入
//     * 得到收入提成规则之后  生成用户推新的收入数据 插入到income表 收入状态为待发放
//     */
//    ServiceResult<String> saveVipIncomeDetailFromVipLog(Integer eId, String oSn, Date createTime);
//
//    /**
//     * 续费--用户注册 将用户未注册时的数据生成提成
//     */
//    ServiceResult<String> saveRegistUserVipIncomeDetail(List<Integer> eIds,String mobilPhoto);
}
