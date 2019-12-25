package com.mph.coreapi.gp.pay.service.impl;

import com.mph.coreapi.gp.pay.entity.GroundPromotionAchievement;
import com.mph.coreapi.gp.pay.entity.GroundPromotionOrderDataEntity;
import com.mph.coreapi.gp.pay.model.GroundPromotionOrderDaoModel;
import com.mph.coreapi.gp.pay.service.GroundPromotionOrderService;
import com.rogrand.common.ServiceResult;
import com.rogrand.common.page.Pagination;
import com.rogrand.coreapi.ditui.entity.GroundPromotionUser;
import com.rogrand.coreapi.ditui.service.GroundPromotionUserService;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: GroundPromotionOrderServiceIpml
 * @Description: 地推月度排行数据
 * @Author yuting.li
 * @Version 1.0
 * @Date 2018-05-31 15:28
 */
@Service("groundPromotionOrderService")
public class GroundPromotionOrderServiceImpl implements GroundPromotionOrderService {

    private Logger logger =LoggerFactory.getLogger(GroundPromotionOrderServiceImpl.class);

    @Autowired
    private GroundPromotionOrderDaoModel groundPromotionOrderDaoModel;

    @Autowired
    private GroundPromotionUserService groundPromotionUserService;

    /**
     * @Description: 添加地堆按月排行数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 17:24
     * @param
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean saveGroundPromotionAchievementInner(Date date){
        DateFormat yyyyMM = new SimpleDateFormat("yyyyMM");
        boolean result = true;
        String dateStr = getDateStr(date);
        try {
            Map<String,Object> param = new HashMap<>();
            //type 1 会员推广
            param.put("type",1);
            param.put("createDate",dateStr);
            param.put("userJobType",1);
            List<GroundPromotionOrderDataEntity> memList = groundPromotionOrderDaoModel.getGroupGroundProOrderList(param);
            //type 2 专区商品
            param.put("type",2);
            param.put("createDate",dateStr);
            param.put("userJobType",1);
            List<GroundPromotionOrderDataEntity> goodsList = groundPromotionOrderDaoModel.getGroupGroundProOrderList(param);
            if(null != memList && memList.size() > 0){
                int i = 0;
                for (GroundPromotionOrderDataEntity m : memList){
                    GroundPromotionAchievement gpa = groundPromotionOrderDaoModel.getByUserId(m.getUserId(),m.getType(),yyyyMM.format(date));
                    if(null == gpa){
                        i++;
                        GroundPromotionAchievement gp = setGroundPro(1,m,i);
                        groundPromotionOrderDaoModel.createGroundPromotionAchievement(gp);
                    }
                }
            }
            if(null != goodsList && goodsList.size() > 0){
                int i = 0;
                for (GroundPromotionOrderDataEntity m : goodsList){
                    GroundPromotionAchievement gpa = groundPromotionOrderDaoModel.getByUserId(m.getUserId(),m.getType(),yyyyMM.format(date));
                    if(null == gpa){
                        i++;
                        GroundPromotionAchievement gp = setGroundPro(2,m,i);
                        groundPromotionOrderDaoModel.createGroundPromotionAchievement(gp);
                    }
                }
            }
        } catch (Exception e) {
            result = false;
            logger.error("保存地推月度成果数据错误：",e);
            throw new RuntimeException("保存地推月度成果数据错误");
        }
        return result;
    }

    /**
     * @Description: 更新相比上月排名变化
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 19:36
     * @param
     * @return boolean
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean updateGroundPromotionAchievementInner(Date date){
        boolean result = true;
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("date",date);
            //上个月的排名数据
            List<GroundPromotionAchievement> nowList = groundPromotionOrderDaoModel.getNowGroundProList(param);
            param.put("date",getMonthBefore(date));
            List<GroundPromotionAchievement> beforeList = groundPromotionOrderDaoModel.getBeforeGroundProList(param);
            if(null != nowList && null != beforeList){
                for (GroundPromotionAchievement ngp : nowList){
                    GroundPromotionAchievement update = new GroundPromotionAchievement();
                    update.setId(ngp.getId());
                    for (GroundPromotionAchievement bgp : beforeList){
                        int buserId = bgp.getUserId();
                        int btype = bgp.getType();
                        int orderInProvince = bgp.getOrderInProvince();
                        if(ngp.getUserId().equals(buserId) && ngp.getType().equals(btype)){
                            // 相比上月排名变化 = 上月 - 本月
                            int orderChange = bgp.getOrderInProvince() - ngp.getOrderInProvince();
                            update.setOrderChange(orderChange);
                        }
                    }
                    update.setUpdateTime(new Date());
                    groundPromotionOrderDaoModel.updateGroundPromotionAchievement(update);
                }
            }
        } catch (Exception e) {
            result = false;
            logger.error("更新相比上月排名变化错误：",e);
            throw new RuntimeException("更新相比上月排名变化错误");
        }
        return result;
    }

    private GroundPromotionAchievement setGroundPro(Integer type, GroundPromotionOrderDataEntity m, Integer order){
        GroundPromotionAchievement gp = new GroundPromotionAchievement();
        ServiceResult<GroundPromotionUser> userResult = groundPromotionUserService.getUser(m.getUserId());
        if(null != userResult && userResult.getResult() != null){
            gp.setUserName(userResult.getResult().getUserName());
        }else{
            gp.setUserName("");
        }
        gp.setType(type);
        gp.setUserId(m.getUserId());
        gp.setUserProvinceId(m.getUserProvinceId());
        gp.setMonthDisplay(m.getMonthDisplay());
        gp.setAmount(BigDecimal.ZERO);
        gp.setQuatity(null == m.getQuatity() ? BigDecimal.ZERO : m.getQuatity());
        gp.setOrderInProvince(order);
        gp.setCreateTime(new Date());
        gp.setOrderChange(0);
        gp.setUpdateTime(new Date());
        return gp;
    }

    /**
     * @Description: 获取地推月度成果数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 18:19
     * @param param
     * type   收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
     * order  排序字段 按数量quatity、按金额amount
     * sort   排序 ASC or DESC
     * @return
     */
    public ServiceResult<Pagination> getGroundPromotionAchievementList(Map<String,Object> param,int pageNo, int pageSize){
        ServiceResult<Pagination> result = new ServiceResult<Pagination>();
        List<GroundPromotionAchievement> list = null;
        try {
            int totalCount = groundPromotionOrderDaoModel.countByParam(param);
            // 构建分页数据对象
            Pagination pagination = new Pagination(pageNo, pageSize, totalCount);
            //参数验证
            if(null != param){
                String order = param.get("order")+"";
                String sort = param.get("sort")+"";
                if(StringUtils.isNotBlank(order) && (!order.equals("quatity") && !order.equals("amount"))){
                    result.setSuccess(false);
                    result.setMessage("排序字段不正确！按数量quatity、按金额amount");
                    return result;
                }
                if(StringUtils.isNotBlank(sort) && (!sort.equalsIgnoreCase("ASC") && !sort.equalsIgnoreCase("DESC"))){
                    sort = "DESC";
                }
            }
            if(0 != totalCount){
                list = groundPromotionOrderDaoModel.getGroundPromotionAchievementList(param,pagination.getFirstResult(),pageSize);
                pagination.setList(list);
            } else {
                list = new ArrayList<GroundPromotionAchievement>();
                pagination.setList(list);
            }
            result.setResult(pagination);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("系统异常！");
            logger.error("获取地推月度成果数据错误：",e);
        }
        return result;
    }

    private String getDateStr(Date date){
        if(null == date){
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = df.format(date);
        return dateStr;
    }

    private Date getMonthBefore(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //月份减一
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    @Override
    public void saveGroundPromotionAchievement() {
        Date now = new Date();
        Date inputDate = null;
        //DateUtils.getFragmentInDays()
        long seq = DateUtils.getFragmentInDays(now, Calendar.MONTH);
        if (seq <= 25){// 5号前要把上月的生成出来，尽量是1号凌晨为最佳时间
            inputDate = DateUtils.addMonths(now, -1);
            saveGroundPromotionAchievementInner(inputDate);
        } else {
            logger.error("生成排名的时间已经超过了5号， 存在特殊原因？");
            //return false;
        }
    }

    @Override
    public void updateGroundPromotionAchievement() {
        Date now = new Date();
        Date inputDate = null;
        //DateUtils.getFragmentInDays()
        long seq = DateUtils.getFragmentInDays(now, Calendar.MONTH);
        if (seq <= 25){// 5号前要把上月的生成出来，尽量是1号凌晨为最佳时间
            inputDate = DateUtils.addMonths(now, -1);
            updateGroundPromotionAchievementInner(inputDate);
        } else {
            logger.error("生成排名的时间已经超过了5号， 存在特殊原因？");
            //return false;
        }
    }
}
