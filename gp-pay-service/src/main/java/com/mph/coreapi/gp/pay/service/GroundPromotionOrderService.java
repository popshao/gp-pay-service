package com.mph.coreapi.gp.pay.service;

import com.rogrand.common.ServiceResult;
import com.rogrand.common.page.Pagination;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName: GroundPromotionOrderService
 * @Description: 地推月度排行数据
 * @Author yuting.li
 * @Version 1.0
 * @Date 2018-06-01 9:23
 */
public interface GroundPromotionOrderService {

    /**
     * @Description: 添加地堆按月排行数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 17:24
     * @param
     * @return
     */
    boolean saveGroundPromotionAchievementInner(Date date);

    /**
     * @Description: 添加地堆按月排行数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 17:24
     * @param
     * @return
     */
    void saveGroundPromotionAchievement();

    /**
     * @Description: 更新相比上月排名变化
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 19:36
     * @param
     * @return boolean
     */
    boolean updateGroundPromotionAchievementInner(Date date);

    /**
     * @Description: 更新相比上月排名变化
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 19:36
     * @param
     * @return boolean
     */
    void updateGroundPromotionAchievement();

    /**
     * @Description: 获取地推月度成果数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/1 9:44
     * @param  param
     * type   收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
     * order  排序字段 按数量quatity、按金额amount
     * sort   排序 ASC or DESC
     * @param pageNo
     * @param  pageSize
     * @return com.rogrand.common.ServiceResult<com.rogrand.common.page.Pagination>
     */
    ServiceResult<Pagination> getGroundPromotionAchievementList(Map<String,Object> param, int pageNo, int pageSize);

}
