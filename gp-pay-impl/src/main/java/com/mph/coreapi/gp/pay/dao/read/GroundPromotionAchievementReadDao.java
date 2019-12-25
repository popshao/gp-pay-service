package com.mph.coreapi.gp.pay.dao.read;

import com.mph.coreapi.gp.pay.entity.GroundPromotionAchievement;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 *
 * @ClassName: GroundPromotionAchievementMapper
 * @Description: 地推月度成果统计表
 * @author yuting.li
 * @version 1.0
 * @date 2018年5月31日 下午2:39:32
 */
public interface GroundPromotionAchievementReadDao {

    /**
     * @Description: 获取全部数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 14:58
     * @param
     * @return
     */
    List<GroundPromotionAchievement> findAllList();

    /**
     * @Description: 根据主键id获取数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 14:59
     * @param id
     * @return com.mph.coreapi.gp.pay.entity.GroundPromotionAchievement
     */
    GroundPromotionAchievement selectByPrimaryKey(Integer id);

    /**
     * @Description: 根据条件获取地推月度成果总数
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 15:01
     * @param param
     * @return int
     */
    int countByParam(Map<String,Object> param);

    /**
     * @Description: 根据条件获取地推月度成果
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 15:01
     * @param param
     * @return java.util.List<com.mph.coreapi.gp.pay.entity.GroundPromotionAchievement>
     */
    List<GroundPromotionAchievement> getByParam(Map<String,Object> param);

    /**
     * @Description: 获取本月地推月度成果
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 15:01
     * @return java.util.List<com.mph.coreapi.gp.pay.entity.GroundPromotionAchievement>
     */
    List<GroundPromotionAchievement> getNowGroundProList(Map<String,Object> param);

    /**
     * @Description: 获取上个月地推月度成果
     * @author yuting.li
     * @version 1.0
     * @date 2018/5/31 15:01
     * @return java.util.List<com.mph.coreapi.gp.pay.entity.GroundPromotionAchievement>
     */
    List<GroundPromotionAchievement> getBeforeGroundProList(Map<String,Object> param);

    /**
     * @Description: 根据用户获取一个月的排名数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 9:51
     * @param userId 用户id
     * @param type 收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
     * @return
     */
    List<GroundPromotionAchievement> getByUserId(@Param("userId") Integer userId,@Param("type") Integer type,@Param("monthDisplay") String monthDisplay);

    /**
     * @Description: 按省查询排名数据
     * @author yuting.li
     * @version 1.0
     * @date 2018/6/5 9:51
     * @param provinceId 省区id
     * @param type 收益类型： 1 会员推广 2 专区商品 3 菲加云 4 注册拉新
     * @param startOrder 起始排名
     * @param endOrder 结束排名
     * @return
     */
    List<GroundPromotionAchievement> getByProvinceId(@Param("type") Integer type,@Param("provinceId") Integer provinceId,@Param("startOrder") Integer startOrder,@Param("endOrder") Integer endOrder);

}
