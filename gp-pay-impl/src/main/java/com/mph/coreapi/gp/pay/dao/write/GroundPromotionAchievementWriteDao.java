package com.mph.coreapi.gp.pay.dao.write;

import com.mph.coreapi.gp.pay.entity.GroundPromotionAchievement;


/**
 *
 * @ClassName: GroundPromotionAchievementMapper
 * @Description: 地推月度成果统计表
 * @author yuting.li
 * @version 1.0
 * @date 2018年5月31日 下午2:39:32
 */
public interface GroundPromotionAchievementWriteDao {
    boolean createGroundPromotionAchievement(GroundPromotionAchievement item);
    boolean updateGroundPromotionAchievement(GroundPromotionAchievement item);
}
