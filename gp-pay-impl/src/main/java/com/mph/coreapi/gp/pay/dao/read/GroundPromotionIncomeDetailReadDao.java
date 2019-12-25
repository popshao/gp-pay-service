package com.mph.coreapi.gp.pay.dao.read;

import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.dao.condition.GroundPromotionIncomeDetailCondition;
import com.mph.coreapi.gp.pay.dto.CashFlowDetailDto;
import com.mph.coreapi.gp.pay.dto.DaySummaryDto;
import com.mph.coreapi.gp.pay.dto.GroundPromotionIncomeDisplaySnDto;
import com.mph.coreapi.gp.pay.dto.PerformanceDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.entity.GroundPromotionOrderDataEntity;
import com.mph.coreapi.gp.pay.entity.VipIncomeImport;
import com.mph.coreapi.gp.pay.pojo.GroundPromotionIncomeDetailForOrderDimensionality;

import org.apache.ibatis.annotations.Param;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
public interface GroundPromotionIncomeDetailReadDao {
    List<GroundPromotionIncomeDetail> listGroundPromotionIncomeDetail (@Param("userId") Integer userId,@Param("DateQue") Integer dateQue);
    List<GroundPromotionIncomeDetail> listAllGroundPromotionIncomeDetail();
    GroundPromotionIncomeDetail getGroundPromotionIncomeDetailInfo(Map<String,Object> param);
    List<GroundPromotionIncomeDetail> getGroundPromotionIncomeDetailListByUserIdStatus(Map<String,Object> params);
    
    
    Integer countGroundPromotionIncomeGroupByDisplayNo(GroundPromotionIncomeDetailCondition conditon);
    
    /**
     * 根据display_no进行分组查询
     * @param conditon 查询条件
     * @param indexSit  记录开始
     * @param size 查询记录条数
     * @return
     */
    List<GroundPromotionIncomeDisplaySnDto> listGroundPromotionIncomeGroupByDisplayNo(GroundPromotionIncomeDetailCondition conditon);
    
    Double getGroundPromotionIncomeDetailAmount(@Param("userId") Integer userId);

    /**
     * @Description: 地推收入明细表中按月统计排序数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 16:49
     * @param param
     * @return
     */
    List<GroundPromotionOrderDataEntity> getGroupGroundProOrderList(Map<String,Object> param);

    Integer countByParam(Map<String,Object> param);

    List<GroundPromotionIncomeDetail> getGroundPromotionIncomeDetailById(Map<String,Object> params);

    /**
     * @Description: 分页获取地推详细数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 15:10
     * @param param
     * @return
     */
    Integer countByParamPage(Map<String,Object> param);
    List<GroundPromotionIncomeDetail> getByParamPage(Map<String,Object> param);

    /**
     * 根据条件查询数据
     * @param param
     * @return
     */
    List<DaySummaryDto> groundPromotionIncomeDetailList(Map<String,Object> param);

    /**
     * @Description: 按日期获取不同条件的数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 18:33
     * @param param
     * @return
     */
    List<GroundPromotionIncomeDetail> listGroundPromotionIncomeDetailByDate(Map<String,Object> param);

    /**
     * 获取收支明细
     * @param param
     * @return
     */
    List<CashFlowDetailDto> getIOInfoList(Map<String,Object> params);

    /**
     * 获取收支明细
     * @param param
     * @return
     */
    Integer getIOInfoListCount(Map<String,Object> param);

    List<VipIncomeImport> getVipIncomeList();

    List<VipIncomeImport> getIncomeUserId();

    List<VipIncomeImport> getIncomeByUserIdoSn(Map<String,Object> params);

    List<GroundPromotionIncomeDetail> getGroundPromotionIncomeDetailByIdDate(Map<String,Object> params);
    
    List<GroundPromotionIncomeDetailForOrderDimensionality> listForOrderDimensionality(@Param("userId")Integer userId,
                                                                                       @Param("associateType")Integer associateType,
                                                                                       @Param("offset")int offset,
                                                                                       @Param("limit")int limit);

    PerformanceDto getPerformanceDetail(@Param("userId") Integer userId, @Param("key") String key, @Param("incomeType") Integer incomeType);
    
    
    Integer countPartTimeGroundPromotionIncomeGroupByDisplayNo(GroundPromotionIncomeDetailCondition conditon);
    
    List<GroundPromotionIncomeDisplaySnDto> listPartTimeGroundPromotionIncomeGroupByDisplayNo(GroundPromotionIncomeDetailCondition conditon);

    List<GroundPromotionIncomeDetail> listGroundPromotionIncomeByDisplayNo(@Param("userId") Integer userId,
                                                                           @Param("type") Integer type,
                                                                           @Param("displaySn") String displaySn,
                                                                           @Param("associateType") Integer associateType);
}
