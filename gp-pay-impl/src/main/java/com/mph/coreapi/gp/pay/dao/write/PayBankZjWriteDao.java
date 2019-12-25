package com.mph.coreapi.gp.pay.dao.write;

import com.mph.coreapi.gp.pay.entity.PayBankZj;

public interface PayBankZjWriteDao {

    int insert(PayBankZj record);

    int insertSelective(PayBankZj record);


    int updateByPrimaryKeySelective(PayBankZj record);

    int updateByPrimaryKey(PayBankZj record);
}
