package com.mph.coreapi.gp.pay.model;

import java.util.List;
import java.util.Map;

import com.mph.coreapi.gp.pay.dao.condition.GroundPromotionIncomeDetailCondition;
import com.mph.coreapi.gp.pay.dao.read.GroundPromotionIncomeDetailReadDao;
import com.mph.coreapi.gp.pay.dao.write.GroundPromotionIncomeDetailWriteDao;
import com.mph.coreapi.gp.pay.dto.CashFlowDetailDto;
import com.mph.coreapi.gp.pay.dto.DaySummaryDto;
import com.mph.coreapi.gp.pay.dto.GroundPromotionIncomeDisplaySnDto;
import com.mph.coreapi.gp.pay.dto.PerformanceDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.entity.VipIncomeImport;
import com.mph.coreapi.gp.pay.pojo.GroundPromotionIncomeDetailForOrderDimensionality;
import com.mph.coreapi.gp.pay.service.condition.GroundPromotionIncomeSearchCondition;
import com.rogrand.common.page.Pagination;
import com.rogrand.common.util.ConvertUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:huilin.liu
 * @Email: huilin.liu@rograndec.com
 * @CreateDate: 2018-05-29 0029
 * @Version: 1.0
 */
@Component
public class GroundPromotionIncomeDetailModel {
    @Autowired
    GroundPromotionIncomeDetailReadDao  groundPromotionIncomeDetailReadDao;
    
    
    @Autowired
    GroundPromotionIncomeDetailWriteDao groundPromotionIncomeDetailWriteDao;
    public List<GroundPromotionIncomeDetail> listGroundPromotionIncomeDetail(Integer userId,Integer dateQue){
        return groundPromotionIncomeDetailReadDao.listGroundPromotionIncomeDetail(userId,dateQue);
    }

    public List<GroundPromotionIncomeDetail> listAllGroundPromotionIncomeDetail(){
        return groundPromotionIncomeDetailReadDao.listAllGroundPromotionIncomeDetail();
    }

    public boolean createGroundPromotionIncomeDetail(GroundPromotionIncomeDetail item){
        return groundPromotionIncomeDetailWriteDao.createGroundPromotionIncomeDetail(item);
    }
    public boolean updateGroundPromotionIncomeDetail(GroundPromotionIncomeDetail item){
        return groundPromotionIncomeDetailWriteDao.updateGroundPromotionIncomeDetail(item);
    }

    public GroundPromotionIncomeDetail getGroundPromotionIncomeDetailAmount(Map<String,Object> param){
        return groundPromotionIncomeDetailReadDao.getGroundPromotionIncomeDetailInfo(param);
    }

    public List<GroundPromotionIncomeDetail> getGroundPromotionIncomeDetailListByUserIdStatus(Map<String,Object> params,int pageNo, int pageSize){
        params.put("start",pageNo);
        params.put("pageSize",pageSize);
        return groundPromotionIncomeDetailReadDao.getGroundPromotionIncomeDetailListByUserIdStatus(params);
    }

    public Double getGroundPromotionIncomeDetailAmount(Integer userId){
        return groundPromotionIncomeDetailReadDao.getGroundPromotionIncomeDetailAmount(userId);
    }

    /**
     * @Description: 根据条件获取收益明细
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 15:01
     * @param param
     * @return int
     */
    public Integer countByParam(Map<String,Object> param){
        return groundPromotionIncomeDetailReadDao.countByParam(param);
    }

    public List<GroundPromotionIncomeDetail> getGroundPromotionIncomeDetailById(Map<String,Object> params){
        return groundPromotionIncomeDetailReadDao.getGroundPromotionIncomeDetailById(params);
    }

    public Integer countByParamPage(Map<String,Object> param){
        return groundPromotionIncomeDetailReadDao.countByParamPage(param);
    }

    public List<GroundPromotionIncomeDetail> getByParamPage(Map<String,Object> param){
        return groundPromotionIncomeDetailReadDao.getByParamPage(param);
    }

    /**
     * 根据条件查询数据
     * @param param
     * @return
     */
    public List<DaySummaryDto> groundPromotionIncomeDetailList(Map<String,Object> param){
        return groundPromotionIncomeDetailReadDao.groundPromotionIncomeDetailList(param);
    }

    /**
     * @Description: 按日期获取不同条件的数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 18:33
     * @param param
     * @return
     */
    public List<GroundPromotionIncomeDetail> listGroundPromotionIncomeDetailByDate(Map<String,Object> param){
        return groundPromotionIncomeDetailReadDao.listGroundPromotionIncomeDetailByDate(param);
    }

    /**
     * 得到收支明细
     * @param params
     * @return
     */
    public List<CashFlowDetailDto> getIOInfoList(Map<String,Object> params,int pageNo, int pageSize){
        params.put("start",pageNo);
        params.put("pageSize",pageSize);
        return groundPromotionIncomeDetailReadDao.getIOInfoList(params);
    }

    /**
     * 获取收支明细
     * @param param
     * @return
     */
    public Integer getIOInfoListCount(Map<String,Object> param){
        return groundPromotionIncomeDetailReadDao.getIOInfoListCount(param);
    }

    public Integer updateIncomeDetailPayStatusByReceiptId(Integer payStatus, Integer receiptId){
        return groundPromotionIncomeDetailWriteDao.updateIncomeDetailByReceiptId(payStatus, receiptId);
    }

    public List<VipIncomeImport> getVipIncomeList(){
        return groundPromotionIncomeDetailReadDao.getVipIncomeList();
    }
    public List<VipIncomeImport> getIncomeUserId(){
        return groundPromotionIncomeDetailReadDao.getIncomeUserId();
    }
    public List<VipIncomeImport> getIncomeByUserIdoSn(Map<String,Object> params){
        return groundPromotionIncomeDetailReadDao.getIncomeByUserIdoSn(params);
    }

    public List<GroundPromotionIncomeDetail> getGroundPromotionIncomeDetailByIdDate(Map<String,Object> params){
        return groundPromotionIncomeDetailReadDao.getGroundPromotionIncomeDetailByIdDate(params);
    }

    public List<GroundPromotionIncomeDetailForOrderDimensionality> listForOrderDimensionality(Integer userId,
                                                                                              Integer associateType,
                                                                                              int offset,
                                                                                              int limit) {
        return groundPromotionIncomeDetailReadDao.listForOrderDimensionality(userId,associateType,offset,limit);
    }
    
    
    /**
     * 
     * @param userId
     * @param type 1 会员推广  2 专区商品  3 菲加云  4 注册拉新 5 下限推广
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination<GroundPromotionIncomeDisplaySnDto> listGroundPromotionIncomeGroupByDisplayNo(GroundPromotionIncomeSearchCondition searchCondition) {
        Pagination<GroundPromotionIncomeDisplaySnDto> result = new Pagination<GroundPromotionIncomeDisplaySnDto>();
        result.setPageNo(searchCondition.getPageNo());result.setPageSize(searchCondition.getPageSize());
        
        GroundPromotionIncomeDetailCondition condition = new GroundPromotionIncomeDetailCondition();
        condition.setUserId(searchCondition.getUserId());
        condition.setAssociateType(searchCondition.getAssociateType());
        condition.setTypes(searchCondition.getTypes());
        if(searchCondition.getSearchCreateBeginTime() != null) {
            condition.setSearchCreateBeginTimeStr(DateFormatUtils.format(searchCondition.getSearchCreateBeginTime(), "yyyy-MM-dd HH:mm:ss"));
        }
        if(searchCondition.getSearchCreateEndTime() != null) {
            condition.setSearchCreateEndTimeStr(DateFormatUtils.format(searchCondition.getSearchCreateEndTime(), "yyyy-MM-dd HH:mm:ss"));
        }
        Integer size = groundPromotionIncomeDetailReadDao.countGroundPromotionIncomeGroupByDisplayNo(condition);
        result.setTotalCount(ConvertUtil.toInt(size, 0));
        if(ConvertUtil.toInt(size, 0) > 0) {
            int sPageNo = searchCondition.getPageNo() - 1;
            if(sPageNo < 0) {
                sPageNo = 0;
            }
            condition.setOffset(sPageNo * searchCondition.getPageSize());
            condition.setLimit(searchCondition.getPageSize());
            List<GroundPromotionIncomeDisplaySnDto> list = groundPromotionIncomeDetailReadDao.listGroundPromotionIncomeGroupByDisplayNo(condition);
            result.setList(list);
        }
        return result;
    }

    public PerformanceDto getPerformanceDetail(Integer userId, String key, Integer incomeType) {
        return groundPromotionIncomeDetailReadDao.getPerformanceDetail(userId,key,incomeType);
    }

    public List<GroundPromotionIncomeDetail> listGroundPromotionIncomeByDisplayNo(Integer userId, Integer type, String displaySn, Integer associateType) {
        return groundPromotionIncomeDetailReadDao.listGroundPromotionIncomeByDisplayNo(userId,type,displaySn,associateType);
    }

    public Pagination<GroundPromotionIncomeDisplaySnDto> listPartTimeGroundPromotionIncomeGroupByDisplayNo(Integer userId,
                                                                                                           int pageNo,
                                                                                                           int pageSize) {
        Pagination<GroundPromotionIncomeDisplaySnDto> result = new Pagination<GroundPromotionIncomeDisplaySnDto>();
        result.setPageNo(pageNo);result.setPageSize(pageSize);
        
        GroundPromotionIncomeDetailCondition conditon = new GroundPromotionIncomeDetailCondition();
        conditon.setUserId(userId);
        Integer size = groundPromotionIncomeDetailReadDao.countPartTimeGroundPromotionIncomeGroupByDisplayNo(conditon);
        result.setTotalCount(ConvertUtil.toInt(size, 0));
        if(ConvertUtil.toInt(size, 0) > 0) {
            int sPageNo = pageNo - 1;
            if(sPageNo < 0) {
                sPageNo = 0;
            }
            conditon.setOffset(sPageNo * pageSize);
            conditon.setLimit(pageSize);
            List<GroundPromotionIncomeDisplaySnDto> list = groundPromotionIncomeDetailReadDao.listPartTimeGroundPromotionIncomeGroupByDisplayNo(conditon);
            result.setList(list);
        }
        return result;
    }
}
