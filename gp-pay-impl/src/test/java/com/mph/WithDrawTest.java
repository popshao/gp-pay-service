package com.mph;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mph.coreapi.TestBase;
import com.mph.coreapi.gp.pay.dto.*;
import com.mph.coreapi.gp.pay.entity.GroundPromotionBankInfo;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayLog;
import com.mph.coreapi.gp.pay.entity.GroundPromotionUserRegister;
import com.mph.coreapi.gp.pay.entity.OrderSpecificProductInfo;
import com.mph.coreapi.gp.pay.service.*;
import com.mph.coreapi.gp.pay.service.business.AchievementService;
import com.mph.coreapi.gp.pay.service.business.GroundPromotionBankService;
import com.mph.coreapi.gp.pay.service.business.ReceiptTransferService;
import com.rogrand.common.Result;
import com.rogrand.common.ServiceResult;
import com.rogrand.common.page.Pagination;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: WithDrawTest
 * @Description: TODO
 * @Author yuting.li
 * @Version 1.0
 * @Date 2018-06-06 20:24
 */
public class WithDrawTest extends TestBase {

    CommonPersonnelService commonPersonnelService;

    AntService antService;

    InsideService insideService;

    GroundPromotionIncomeDetailService groundPromotionIncomeDetailService;

    GroundPromotionBankService bankService;

    PayLogService payLogService;

    AchievementService achievementService;

    ReceiptTransferService receiptTransferService;

    VipIncomeImportService vipIncomeImportService;

    @Override
    protected void init() {
        commonPersonnelService = context.getBean("commonPersonnelService",CommonPersonnelService.class);
        antService = context.getBean("antService",AntService.class);
        insideService = context.getBean("insideService",InsideService.class);
        bankService = context.getBean("groundPromotionBankService",GroundPromotionBankService.class);
        groundPromotionIncomeDetailService = context.getBean("groundPromotionIncomeDetailService",GroundPromotionIncomeDetailService.class);
        achievementService = context.getBean("achievementService",AchievementService.class);

        payLogService = context.getBean("payLogService",PayLogService.class);
        receiptTransferService = context.getBean("receiptTransferService",ReceiptTransferService.class);
        vipIncomeImportService = context.getBean("vipIncomeImportService",VipIncomeImportService.class);
    }

    private static String userName = "user004";
    private static Integer pageNo = 1;
    private static Integer pageSize = 20;
    private static Integer userId = 21;
    private static Integer dateCount= 7;
    private static Integer provinceId=1;

    public void testToPayAmount(){
        ServiceResult<List<ProvinceInfoDto>> provinceList = bankService.getProvinceList();
        System.out.println();
    }


    public void testToPayList(){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("userId",21);
        params.put("type",2);
        ServiceResult<Pagination> result= insideService.getInsideProfitDetails(params,pageNo,pageSize);
        System.out.println(JSON.toJSONString(result));
    }

    public void testToPayInfo(){
        userId=21;
        GroundPromotionIncomeDetail groundPromotionIncomeDetail= antService.getIncomeInfo(userId);
        System.out.println(groundPromotionIncomeDetail);
    }

    public void testGetBankInfoByUserId(){
        BankInfoDto bankInfoDto= antService.getBankInfoByUserId(userId);
        System.out.println(bankInfoDto);
    }

    public void testSaveCardInfo(){
        GroundPromotionBankInfo bankInfo=new GroundPromotionBankInfo();
        bankInfo.setBankCardNo("22222");
        bankInfo.setIdentityCardNo("2222");
        bankInfo.setBankCode("2222");
        bankInfo.setBankName("22222");
        bankInfo.setBranchName("22222");
        bankInfo.setCityCode("222");
        bankInfo.setProvinceCode("222");
        bankInfo.setUserId(userId);
        boolean result= antService.bindingBankCard(bankInfo);
        System.out.println(result);
    }

    //TODO:TEST
    public void testGetTaskList(){
        List<TaskProgressInfoDto> taskProgressInfoDtoList= groundPromotionIncomeDetailService.getIncomeListByUserId(32,"hwen1215");
        System.out.println(taskProgressInfoDtoList);
    }

    public void testWithDrawInfo()
    {
        userId=21;
        AntPayInfoDto antPayInfoDto= groundPromotionIncomeDetailService.getIncomeInfo(userId);
        System.out.println(antPayInfoDto);
    }
    public void testIssuedAmount()
    {
        Map<String,Object> params =new HashMap<String,Object>();
        params.put("userId",29);
        GroundPromotionIncomeDetail groundPromotionIncomeDetail= commonPersonnelService.issuedAmount(params);
        Double issueAmount= groundPromotionIncomeDetail.getIssuedAmount();
        System.out.println(issueAmount);
    }

    public void testayRecordList(){
        Integer payStatus=1;
        Map<String,Object> params=new HashMap<String,Object>();
        //TODO:加个提现状态
        if(payStatus==1){
            params.put("payStatus","1");
        }else if(payStatus==2){
            params.put("payStatus","0,2,3,4");
        }else if(payStatus==3){
            params.put("payStatus","5");
        }
        params.put("userId",userId);
        ServiceResult<Pagination> result=antService.listGroundPromotionPayReceiptInfo(params,pageNo,pageSize);
        Pagination<AntPayRecordDto> resultMap=result.getResult();
        System.out.println(resultMap);
    }

    public void testCashFlowList(){
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("userId",21);
        ServiceResult<Pagination> result= groundPromotionIncomeDetailService.getIOInfoList(param,pageNo,pageSize);
        Pagination<CashFlowDetailDto> resultMap=result.getResult();
        System.out.println(resultMap);
    }

    public List<Date> getDateList(Integer dateCount){
        List<Date> dateList = new ArrayList<>();
        Date dt = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date queDate = calendar.getTime();
        //dateList.add(queDate);
        int i=0;
        while (dt.after(calendar.getTime())&&i<7){
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            dateList.add(calendar.getTime());
            i++;
        }
        return dateList;
    }

    public void testLatestGoodSales(){
        Map<String,Object> params=new HashMap<String,Object>();
        //getNeedTime(0,0,0,0,-);
        params.put("userId",userId);
        Date queDate = getNeedTime(0,0,0,-dateCount+7);
        Date queEndDate = getNeedTime(23,59,59,0);
        params.put("queEndDate",queEndDate);
        params.put("queTime",queDate);
        params.put("userProvinceId",38);
        params.put("userCityId",64);
        params.put("type",1);
        List<DaySummaryDto> daySummaryDtoList= groundPromotionIncomeDetailService.groundPromotionIncomeDetailList(params);
        List<DaySummaryDto<Double>> itemList =new ArrayList<DaySummaryDto<Double>>();
        List<Date> dateList = getDateList(dateCount);
        for(Date d : dateList){
            DaySummaryDto<Double> resultItem=new DaySummaryDto<Double>();
            for (DaySummaryDto item :daySummaryDtoList ){
                if(item.getDate().equals(d)){
                    resultItem.setDate(item.getDate());
                    resultItem.setQuantity(item.getQueAmount());
                    break;
                }


            }
            if(resultItem.getDate() ==null){
                resultItem.setDate(d);
                resultItem.setQuantity(0d);
            }
            itemList.add(resultItem);
        }
        System.out.println(itemList.get(0).getDate());
        System.out.println(JSON.toJSONString(itemList));
    }

    public void testSaveIncomeDetail()
    {
        Integer eid=6290562;
        String oSn="VP201905061552026146";
        ServiceResult<String> aa=antService.SaveIncomeDetail(eid,oSn);
    }

    public void testLatestProvinceGoodSales(){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("userId",userId);
        Date queDate = getNeedTime(0,0,0,-dateCount);
        Date queEndDate = getNeedTime(0,0,0,0);
        params.put("queTime",queDate);
        params.put("queEndDate",queEndDate);
        params.put("type",2);
        List<DaySummaryDto> daySummaryDtoList= groundPromotionIncomeDetailService.groundPromotionIncomeDetailList(params);
        List<DaySummaryDto<Double>> itemList =new ArrayList<DaySummaryDto<Double>>();
        for (DaySummaryDto item :daySummaryDtoList ){
            DaySummaryDto<Double> resultItem=new DaySummaryDto<Double>();
            resultItem.setDate(item.getDate());
            resultItem.setQuantity(item.getQueAmount());
            itemList.add(resultItem);
        }
    }

    public void test(){
        ServiceResult<String> result= antService.confirmGetPayAmount(userId,"MjA1");
        boolean resultFlag= result.getSuccess();
    }

    public void testConfirmPay(){
        //TODO:最好传个id的list

        ServiceResult<String> result= antService.confirmGetPayAmount(userId,"LDEsMiwz");
        boolean resultFlag= result.getSuccess();

        RetDto retDto = new RetDto();
        retDto.setRet(resultFlag?1:0);
    }

    public void testSaveNewUserVipIncomeDetail() throws ParseException {
        List<Integer> eIds=new ArrayList<>();
        eIds.add(6291215);

        ServiceResult<String> result=antService.saveNewUserVipIncomeDetail(eIds,"17000000010");
        System.out.println();
    }

    private Date getNeedTime(int hour,int minute,int second,int addDay,int ...args){
        Date dt = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(Calendar.DATE, addDay);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MILLISECOND, minute);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    public void testAddLog(){
        String str = "{\"id\":\"64a7564af1564af5b90e1c615368b665\",\"module\":\"order\",\"type\":\"DiTuiOrder\",\"time\":1528873568840,\"data\":{\"oid\":4716115,\"osn\":\"DM201806131504467366\",\"userId\":96,\"details\":[{\"odId\":21278093,\"amount\":4423.3,\"odName\":\"我的心机绿茶面膜哦哦哦哦哦哦（180）\",\"goodId\":12886881,\"quantity\":2,\"goodPicUrl\":null},{\"odId\":21278094,\"amount\":-6642.2,\"odName\":\"pyz益达89\",\"goodId\":12886880,\"quantity\":2,\"goodPicUrl\":null}]},\"recordStatusFlag\":false}";
        JSONObject jso = JSON.parseObject(str);
        String messageStr = jso.getString("data");
        GroundPromotionPayLog log = JSON.parseObject(str, GroundPromotionPayLog.class);
        payLogService.add(log);

    }

    public void testGetLog(){
        String str = "{\"amount\":\"99.00\",\"oSn\":\"6\",\"payType\":3,\"paymentNo\":\"201806091728557\",\"remark\":\"发起易宝转账\",\"requestData\":\"<data><cmd>TransferBatch</cmd><version>1.1</version><group_Id>10014772219</group_Id><mer_Id>10014772219</mer_Id><batch_No>201806091728557</batch_No><total_Num>1</total_Num><total_Amt>99.00</total_Amt><is_Repay>0</is_Repay><hmac>z0G6L4TSu77WU0IU2mav87627H8xCU3Gil149riNE72TaylK0FtQ8892j37Y</hmac><list><item><order_Id>201806091728557</order_Id><bank_Code>ICBC</bank_Code><cnaps></cnaps><branch_Bank_Name>工商银行恩施</branch_Bank_Name><amount>99.00</amount><account_Name>user003</account_Name><account_Number>ICBC</account_Number><account_Type>pu</account_Type><province>130000</province><city>130300</city><fee_Type>SOURCE</fee_Type><payee_Email></payee_Email><payee_Mobile></payee_Mobile><urgency>0</urgency><leave_Word>MPHnull</leave_Word><abstractInfo>MPHnull</abstractInfo><remarksInfo>MPHnull</remarksInfo></item></list></data>\",\"requestTime\":1528536535802,\"responseData\":\"\",\"responseTime\":1528536535802,\"status\":2,\"statusDes\":\"\"}";
        GroundPromotionPayLog log = JSON.parseObject(str, GroundPromotionPayLog.class);
        //payLogService.add(log);
        ServiceResult<List<GroundPromotionPayLog>> listServiceResult = payLogService.listPayLogByStatus(2, 3, 0, 100);

        System.out.println();

    }

    public void testUserGoodsList()
    {
        Pagination<AchieveGoodInfoDto> pageList= achievementService.getUserGoodsList(27,2,1,20);
        System.out.println();
    }

    public void testUserMemberList(){
        String jsonMessage = "{\"details\":[{\"amount\":100,\"duituiConfig\": \"{\"id\":105,\"scopeType\":1,\"goodId\":2,\"linkId\":38,\"linkName\":\"河北省\",\"businessType\":2,\"computeType\":1,\"percentVal\":10.0,\"fixVal\":0.0,\"computeTypeLead\":1,\"percentValLead\":15.0,\"fixValLead\":0.0,\"createTime\":\"2019-01-25 15:38:37\",\"updateTime\":\"2019-01-25 15:38:37\"}\",\"goodId\":9085273,\"goodPicUrl\":\"2019/01/25/g_e431eed0707f4641a090b2be83bf240a.jpg\",\"odId\":26890057,\"odName\":\"小药宝test2环境专用商品（专用商品test2）\",\"quantity\":10}],\"oid\":5930695,\"osn\":\"DM201901251717044318\",\"userId\":18}";
        OrderSpecificProductInfo productInfo = JSON.parseObject(jsonMessage, OrderSpecificProductInfo.class);
        groundPromotionIncomeDetailService.saveOrderSpecificProductInfo(productInfo);//String messageStr = null;
        System.out.println();
    }

    public void testUpdateLog(){
        String test = "{\"id\":343,\"responseData\":\"<?xml version=\\\"1.0\\\" encoding=\\\"GBK\\\"?>\\n<data>\\n<cmd>TransferBatch</cmd>\\n<ret_Code>0046</ret_Code>\\n<error_Msg>商户可用打款余额不足</error_Msg>\\n<mer_Id>10014772219</mer_Id>\\n<batch_No>201806141453052</batch_No>\\n<total_Amt>0.00</total_Amt>\\n<total_Num>0</total_Num>\\n<r1_Code>0030</r1_Code>\\n\\n<hmac>MIIE6QYJKoZIhvcNAQcCoIIE2jCCBNYCAQExCzAJBgUrDgMCGgUAMC8GCSqGSIb3DQEHAaAiBCBmOTkyZWZjNGU3ZGYzZDk3ZmY0NTlkNjkxNjczODhlMKCCA7EwggOtMIIDFqADAgECAhAuyfCgPbMcigfvVyGazeQRMA0GCSqGSIb3DQEBBQUAMCQxCzAJBgNVBAYTAkNOMRUwEwYDVQQKEwxDRkNBIFRFU1QgQ0EwHhcNMTExMTI4MDcwOTUzWhcNMTMxMTI4MDcwOTUzWjBzMQswCQYDVQQGEwJDTjEVMBMGA1UEChMMQ0ZDQSBURVNUIENBMQ8wDQYDVQQLEwZZRUVQQVkxEjAQBgNVBAsTCUN1c3RvbWVyczEoMCYGA1UEAxQfMDQxQFoxMjNxd2VAemhpd2VuLm1laUAwMDAwMDAwMTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAzrITNElBaFF7xPXtPguWeTnGOI1gVMMkUDI57ZQz+Gg9PPcfF+ExrtDgMQEOwfRs7X4XiraPE2l6ub0Xkpl0ftu8ELnii91wUKAqsvp88NIdAdLQnC7PeveWlquVSAf//2WtAkdBI7xnhXaL/ObUkhHheT0aR5miYmDyLAkTBj8CAwEAAaOCAY8wggGLMB8GA1UdIwQYMBaAFEZy3CVynwJOVYO1gPkL2+mTs/RFMB0GA1UdDgQWBBS0k6A7ZSwLRwbIhFsgcChrYd27PDALBgNVHQ8EBAMCBaAwDAYDVR0TBAUwAwEBADA7BgNVHSUENDAyBggrBgEFBQcDAQYIKwYBBQUHAwIGCCsGAQUFBwMDBggrBgEFBQcDBAYIKwYBBQUHAwgwgfAGA1UdHwSB6DCB5TBPoE2gS6RJMEcxCzAJBgNVBAYTAkNOMRUwEwYDVQQKEwxDRkNBIFRFU1QgQ0ExDDAKBgNVBAsTA0NSTDETMBEGA1UEAxMKY3JsMTI2XzE5NDCBkaCBjqCBi4aBiGxkYXA6Ly90ZXN0bGRhcC5jZmNhLmNvbS5jbjozODkvQ049Y3JsMTI2XzE5NCxPVT1DUkwsTz1DRkNBIFRFU1QgQ0EsQz1DTj9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0P2Jhc2U/b2JqZWN0Y2xhc3M9Y1JMRGlzdHJpYnV0aW9uUG9pbnQwDQYJKoZIhvcNAQEFBQADgYEAKX4CXCPQEE4RWGsZTXZXLBct2gcPYEjqpgPZ5ERiUrYLTDGuIT90ECfSoxCrcTJEeY7EJBojig9gLRoMn/4xXW/XscGarQ3XxyZw8VxTMFkotuUkAPoaacYlIrc34t2DR0DqvU6umgFL3yTMYxl5WLjOh47OH7Aw7VPscmrtzEIxgd0wgdoCAQEwODAkMQswCQYDVQQGEwJDTjEVMBMGA1UEChMMQ0ZDQSBURVNUIENBAhAuyfCgPbMcigfvVyGazeQRMAkGBSsOAwIaBQAwDQYJKoZIhvcNAQEBBQAEgYCWMOH6vsA5EY5Z7xPTyP7tUse3boa9fbeJEKhjS+SvLVrA8Rj1jasrM4twl4cuhtyuB1G5l/WhsTfdkPlU4O39cgvJyJ3VlTryC5GkDRxGHrrXRtD+9JIEtfByvqQ0uzQzn6BckF9CAhVJ+IAfi/zYnTvehQg8QlnajzGD78F7tw==</hmac>\\n</data>\\n\",\"responseTime\":1528959193796,\"status\":-1,\"statusDes\":\"转账请求失败，返回代码：0046\"}";
        GroundPromotionPayLog log = JSON.parseObject(test, GroundPromotionPayLog.class);
        payLogService.updateLogById(log);
        System.out.println();
    }

    public void testrejectReceipt(){
        ServiceResult<Integer> test= receiptTransferService.rejectReceipt(49,"12311","12312");
        System.out.println();
    }

    public void testVipIncomeImport(){
        //vipIncomeImportService.vipIncomeImport("vipIncomeImport");
        vipIncomeImportService.vipReceiptImport("vipIncomeImport");
        System.out.println();
    }

    public void testmonthLastDay(){
        groundPromotionIncomeDetailService.monthLastDay();
        System.out.println();
    }

}
