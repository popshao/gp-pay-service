package com.mph.coreapi.gp.pay.dao.read;

import com.mph.coreapi.gp.pay.entity.AccountCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountCardReadDao {


    /**
     * @Description: 根据主键查询
     * @Email: shengliang.wei@rograndec.com
     * @Author: shengliang.wei
     * @Date: 2018/8/19 16:49
     *
     */
    AccountCard findAccountCardByPrimaryKey(@Param("acId") Integer acId);


    /**
     * @Description: 根据企业eId查询
     * @Email: shengliang.wei@rograndec.com
     * @Author: shengliang.wei
     * @Date: 2018/8/19 17:49
     *
     */
    AccountCard findAccountCardByEid(@Param("eId") Integer eId);

    /**
     * 根据e_id查询账户银行卡信息
     * @param accountCard
     * @return 账户银行卡信息实体类
     */
    List<AccountCard> listAccoutCard(AccountCard accountCard);

    /**
     * 判断是否开通suId
     * @param suId 供应商主键
     * @return
     */
    Integer isOpenCashBySuId(@Param("suId") Integer suId);
}
