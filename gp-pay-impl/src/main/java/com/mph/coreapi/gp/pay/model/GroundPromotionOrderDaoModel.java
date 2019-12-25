package com.mph.coreapi.gp.pay.model;

import com.mph.coreapi.gp.pay.dao.read.GroundPromotionAchievementReadDao;
import com.mph.coreapi.gp.pay.dao.read.GroundPromotionIncomeDetailReadDao;
import com.mph.coreapi.gp.pay.dao.write.GroundPromotionAchievementWriteDao;
import com.mph.coreapi.gp.pay.entity.GroundPromotionAchievement;
import com.mph.coreapi.gp.pay.entity.GroundPromotionOrderDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: GroundPromotionOrderModel
 * @Description: 地推月度排行查询数据
 * @Author yuting.li
 * @Version 1.0
 * @Date 2018-05-31 16:17
 */
@Component
public class GroundPromotionOrderDaoModel {

    @Autowired
    private GroundPromotionIncomeDetailReadDao groundPromotionIncomeDetailReadDao;

    @Autowired
    private GroundPromotionAchievementWriteDao groundPromotionAchievementWriteDao;

    @Autowired
    private GroundPromotionAchievementReadDao groundPromotionAchievementReadDao;

    public List<GroundPromotionOrderDataEntity> getGroupGroundProOrderList(Map<String,Object> param){
        return groundPromotionIncomeDetailReadDao.getGroupGroundProOrderList(param);
    }

    public void createGroundPromotionAchievement(GroundPromotionAchievement item){
        groundPromotionAchievementWriteDao.createGroundPromotionAchievement(item);
    }

    public void updateGroundPromotionAchievement(GroundPromotionAchievement item){
        groundPromotionAchievementWriteDao.updateGroundPromotionAchievement(item);
    }

    public int countByParam(Map<String,Object> param){
        return groundPromotionAchievementReadDao.countByParam(param);
    }

    public List<GroundPromotionAchievement> getGroundPromotionAchievementList(Map<String,Object> param,int start,int pageSize){
        param.put("start",start);
        param.put("pageSize",pageSize);
        return groundPromotionAchievementReadDao.getByParam(param);
    }

    public List<GroundPromotionAchievement> getNowGroundProList(Map<String,Object> param){
        return groundPromotionAchievementReadDao.getNowGroundProList(param);
    }

    public List<GroundPromotionAchievement> getBeforeGroundProList(Map<String,Object> param){
        return groundPromotionAchievementReadDao.getBeforeGroundProList(param);
    }

    public GroundPromotionAchievement getByUserId(Integer userId,Integer type,String monthDisplay){
        List<GroundPromotionAchievement> gpaList = groundPromotionAchievementReadDao.getByUserId(userId,type,monthDisplay);
        GroundPromotionAchievement groundPromotionAchievement=null;
        if(gpaList!=null&&gpaList.size()>0){
            groundPromotionAchievement=gpaList.get(0);
        }
        return groundPromotionAchievement;
    }

    public List<GroundPromotionAchievement> getByProvinceId(Integer type,Integer provinceId,Integer startOrder,Integer endOrder){
        return groundPromotionAchievementReadDao.getByProvinceId(type,provinceId,startOrder,endOrder);
    }

}
