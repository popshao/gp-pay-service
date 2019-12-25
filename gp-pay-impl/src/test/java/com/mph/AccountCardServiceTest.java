package com.mph;

import com.alibaba.fastjson.JSON;
import com.mph.coreapi.TestBase;
import com.mph.coreapi.gp.pay.dto.PayReceiptDto;
import com.mph.coreapi.gp.pay.entity.AccountCard;
import com.mph.coreapi.gp.pay.entity.PayBankZj;
import com.mph.coreapi.gp.pay.service.AccountCardService;
import com.rogrand.common.ServiceResult;
import com.rogrand.common.util.JsonUtil;

import java.util.Date;
import java.util.List;

public class AccountCardServiceTest  extends TestBase {
    private AccountCardService accountCardService;

    @Override
    protected void init() {
        accountCardService = context.getBean("accountCardService",AccountCardService.class);
    }


    public void testFindAccountCardByEid(){
        ServiceResult<AccountCard> accountCardByEid = accountCardService.findAccountCardByEid(2771396);
        System.out.println(JsonUtil.toJson(accountCardByEid));
    }

    public void testFindPayBankByNam(){
        ServiceResult<List<PayBankZj>> byName = accountCardService.findPayBankByName("光大");
        System.out.println(JsonUtil.toJson(byName));

    }

    public void testUpdateAccountCard(){
        AccountCard accountCard = new AccountCard();
        accountCard.setAcId(10);
        accountCard.setCpNo("222");
        ServiceResult<Boolean> booleanServiceResult = accountCardService.updateAccountCard(accountCard);
        System.out.println(JsonUtil.toJson(booleanServiceResult));
    }

    public void testInsertAccountCard(){
        String js  = "{\"suId\":383,\"eId\":2602481,\"bankName\":\"中国银行\",\"cardNo\":\"999999999999999999\",\"accountName\":\"ermao\",\"applyStatus\":1,\"payBankId1\":\"104\",\"bankDetailName\":\"中国银行支行\",\"province\":1,\"town\":2,\"epPic\":\"2018/09/17/mph_3f4c138ca3c944a6ae0ec1795a29395e.jpg\",\"bucket_key\":\"2018/09/17/mph_3f4c138ca3c944a6ae0ec1795a29395e.jpg\",\"imageInfo\":\"{\\\"bucket\\\":\\\"rgdoc\\\", \\\"bucket_key\\\":\\\"2018/09/17/mph_3f4c138ca3c944a6ae0ec1795a29395e.jpg\\\", \\\"original_name\\\":\\\"1537168876584.jpg\\\", \\\"type\\\":\\\"图片文件\\\", \\\"size\\\":76, \\\"suffix\\\":\\\".jpg\\\", \\\"create_time\\\":\\\"2018-09-17 15:24:43\\\", \\\"table_field\\\":\\\"\\\", \\\"table_name\\\":\\\"\\\", \\\"table_id\\\":0, \\\"remark\\\":\\\"\\\"}\"}";
        AccountCard parse = JSON.parseObject(js, AccountCard.class);
//        AccountCard accountCard = new AccountCard();
//        accountCard.setCpNo("222");
//        accountCard.setPayBankId("313651011026");
//        accountCard.setBankName("光大银行");
//        accountCard.setAccountName("浦发2000615499");
//        accountCard.setBranchName("ermao");
//        accountCard.setCardNo("222222222222222222");
//        accountCard.setIdNo("123333333333111111");
//        accountCard.setRefuseReason("''");
//        accountCard.setMobile("12345678900");
//        accountCard.setCardType(1);
////        accountCard.setApplyStatus(1);
//        accountCard.setProvince(2546);
//        accountCard.setTown(2547);
//        accountCard.setBankDetailName("政府街支行");
//        accountCard.setEpPic("2016/06/21/mph_576908630032c335d9bf031a.jpg");
//        accountCard.setBucket_key("2016/06/21/mph_576908630032c335d9bf031a.jpg");
////        accountCard.setIsPayOnlieCom(0);
////        accountCard.setIsPayOnlieSelf(0);
        ServiceResult<Integer> integerServiceResult = accountCardService.insertAccountCard(parse);
        System.out.println(JsonUtil.toJson(integerServiceResult));
    }

    public void testListAccoutCard(){
        AccountCard accountCard = new AccountCard();
        accountCard.setCpNo("222");
        ServiceResult<List<AccountCard>> listServiceResult = accountCardService.listAccoutCard(accountCard);
        System.out.println(JsonUtil.toJson(listServiceResult));
    }

    public void testGetByEid(){
        ServiceResult<PayReceiptDto> yeepayPayInfoByEid = accountCardService.getYeepayPayInfoByEid(2772653);
        System.out.println();
    }

    public void testGetBySuId(){
        ServiceResult<PayReceiptDto> yeepayPayInfoByEid = accountCardService.getYeepayPayInfoBySuId(462);
        System.out.println();
    }
}
