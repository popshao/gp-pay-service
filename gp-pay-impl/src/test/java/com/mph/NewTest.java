package com.mph;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mph.coreapi.TestBase;
import com.mph.coreapi.gp.pay.dao.write.GroundPromotionIncomeDetailWriteDao;
import com.mph.coreapi.gp.pay.dto.AchieveGoodInfoDto;
import com.mph.coreapi.gp.pay.dto.AntPayInfoDto;
import com.mph.coreapi.gp.pay.dto.AntPayRecordDto;
import com.mph.coreapi.gp.pay.dto.BankInfoDto;
import com.mph.coreapi.gp.pay.dto.CashFlowDetailDto;
import com.mph.coreapi.gp.pay.dto.DaySummaryDto;
import com.mph.coreapi.gp.pay.dto.ProvinceInfoDto;
import com.mph.coreapi.gp.pay.dto.RetDto;
import com.mph.coreapi.gp.pay.dto.TaskProgressInfoDto;
import com.mph.coreapi.gp.pay.entity.GroundPromotionBankInfo;
import com.mph.coreapi.gp.pay.entity.GroundPromotionIncomeDetail;
import com.mph.coreapi.gp.pay.entity.GroundPromotionPayLog;
import com.mph.coreapi.gp.pay.entity.OrderSpecificProductInfo;
import com.mph.coreapi.gp.pay.service.AntService;
import com.mph.coreapi.gp.pay.service.CommonPersonnelService;
import com.mph.coreapi.gp.pay.service.GroundPromotionIncomeDetailService;
import com.mph.coreapi.gp.pay.service.GroundPromotionOrderService;
import com.mph.coreapi.gp.pay.service.InsideService;
import com.mph.coreapi.gp.pay.service.PayLogService;
import com.mph.coreapi.gp.pay.service.business.AchievementService;
import com.mph.coreapi.gp.pay.service.business.GroundPromotionBankService;
import com.rogrand.common.ServiceResult;
import com.rogrand.common.page.Pagination;
import com.rogrand.coreapi.ditui.entity.GroundPromotionCommissionConfig;
import com.rogrand.coreapi.ditui.service.GroundPromotionCommissionConfigService;

/**
 * @ClassName: WithDrawTest
 * @Description: TODO
 * @Author yuting.li
 * @Version 1.0
 * @Date 2018-06-06 20:24
 */
public class NewTest extends TestBase {

    CommonPersonnelService commonPersonnelService;

    AntService antService;

    InsideService insideService;

    GroundPromotionIncomeDetailService groundPromotionIncomeDetailService;

    GroundPromotionBankService bankService;

    PayLogService payLogService;

    AchievementService achievementService;

    GroundPromotionOrderService groundPromotionOrderService;

    GroundPromotionCommissionConfigService groundPromotionCommissionConfigService;

    GroundPromotionIncomeDetailWriteDao groundPromotionIncomeDetailWriteDao;

    @Override
    protected void init() {
        commonPersonnelService = context.getBean("commonPersonnelService",CommonPersonnelService.class);
        antService = context.getBean("antService",AntService.class);
        insideService = context.getBean("insideService",InsideService.class);
        bankService = context.getBean("groundPromotionBankService",GroundPromotionBankService.class);
        groundPromotionIncomeDetailService = context.getBean("groundPromotionIncomeDetailService",GroundPromotionIncomeDetailService.class);
        achievementService = context.getBean("achievementService",AchievementService.class);

        payLogService = context.getBean("payLogService",PayLogService.class);
        groundPromotionOrderService = context.getBean("groundPromotionOrderService",GroundPromotionOrderService.class);

        groundPromotionCommissionConfigService = context.getBean("groundPromotionCommissionConfigService",GroundPromotionCommissionConfigService.class);
        groundPromotionIncomeDetailWriteDao = context.getBean("groundPromotionIncomeDetailWriteDao",GroundPromotionIncomeDetailWriteDao.class);
    }

    private static String userName = "user004";
    private static Integer pageNo = 1;
    private static Integer pageSize = 20;
    private static Integer userId = 29;
    private static Integer dateCount= 7;
    private static Integer provinceId=1;

    public void testUserMemberList(){
        String jsonMessage = "{\"id\":\"0c3cf31bae41468eb5e37c2819a23115\",\"module\":\"order\",\"type\":\"DiTuiOrder\",\"time\":1528970504803,\"data\":{\"oid\":4716203,\"osn\":\"DM201806141800547835\",\"userId\":89,\"details\":[{\"odId\":21278184,\"amount\":5.0,\"odName\":\"pyz益达89（pzz咀嚼片11）\",\"goodId\":9085444,\"quantity\":500,\"goodPicUrl\":null,\"duituiConfig\":\"{\\\"id\\\":110,\\\"scopeType\\\":2,\\\"goodId\\\":34,\\\"linkId\\\":89,\\\"linkName\\\":\\\"小贞\\\",\\\"businessType\\\":2,\\\"computeType\\\":1,\\\"percentVal\\\":0.1,\\\"fixVal\\\":0.0,\\\"computeTypeLead\\\":1,\\\"percentValLead\\\":0.1,\\\"fixValLead\\\":0.0,\\\"createTime\\\":\\\"2018-06-14 17:54:08\\\",\\\"updateTime\\\":\\\"2018-06-14 17:54:08\\\"}\"}]},\"recordStatusFlag\":false}";
        JSONObject jso = JSON.parseObject(jsonMessage);
        String messageStr = jso.getString("data");

        OrderSpecificProductInfo productInfo = JSON.parseObject(messageStr, OrderSpecificProductInfo.class);
        groundPromotionIncomeDetailService.saveOrderSpecificProductInfo(productInfo);//String messageStr = null;
        System.out.println();
    }

    public void testUpdateLog(){
        String test = "{\"id\":343,\"responseData\":\"<?xml version=\\\"1.0\\\" encoding=\\\"GBK\\\"?>\\n<data>\\n<cmd>TransferBatch</cmd>\\n<ret_Code>0046</ret_Code>\\n<error_Msg>商户可用打款余额不足</error_Msg>\\n<mer_Id>10014772219</mer_Id>\\n<batch_No>201806141453052</batch_No>\\n<total_Amt>0.00</total_Amt>\\n<total_Num>0</total_Num>\\n<r1_Code>0030</r1_Code>\\n\\n<hmac>MIIE6QYJKoZIhvcNAQcCoIIE2jCCBNYCAQExCzAJBgUrDgMCGgUAMC8GCSqGSIb3DQEHAaAiBCBmOTkyZWZjNGU3ZGYzZDk3ZmY0NTlkNjkxNjczODhlMKCCA7EwggOtMIIDFqADAgECAhAuyfCgPbMcigfvVyGazeQRMA0GCSqGSIb3DQEBBQUAMCQxCzAJBgNVBAYTAkNOMRUwEwYDVQQKEwxDRkNBIFRFU1QgQ0EwHhcNMTExMTI4MDcwOTUzWhcNMTMxMTI4MDcwOTUzWjBzMQswCQYDVQQGEwJDTjEVMBMGA1UEChMMQ0ZDQSBURVNUIENBMQ8wDQYDVQQLEwZZRUVQQVkxEjAQBgNVBAsTCUN1c3RvbWVyczEoMCYGA1UEAxQfMDQxQFoxMjNxd2VAemhpd2VuLm1laUAwMDAwMDAwMTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAzrITNElBaFF7xPXtPguWeTnGOI1gVMMkUDI57ZQz+Gg9PPcfF+ExrtDgMQEOwfRs7X4XiraPE2l6ub0Xkpl0ftu8ELnii91wUKAqsvp88NIdAdLQnC7PeveWlquVSAf//2WtAkdBI7xnhXaL/ObUkhHheT0aR5miYmDyLAkTBj8CAwEAAaOCAY8wggGLMB8GA1UdIwQYMBaAFEZy3CVynwJOVYO1gPkL2+mTs/RFMB0GA1UdDgQWBBS0k6A7ZSwLRwbIhFsgcChrYd27PDALBgNVHQ8EBAMCBaAwDAYDVR0TBAUwAwEBADA7BgNVHSUENDAyBggrBgEFBQcDAQYIKwYBBQUHAwIGCCsGAQUFBwMDBggrBgEFBQcDBAYIKwYBBQUHAwgwgfAGA1UdHwSB6DCB5TBPoE2gS6RJMEcxCzAJBgNVBAYTAkNOMRUwEwYDVQQKEwxDRkNBIFRFU1QgQ0ExDDAKBgNVBAsTA0NSTDETMBEGA1UEAxMKY3JsMTI2XzE5NDCBkaCBjqCBi4aBiGxkYXA6Ly90ZXN0bGRhcC5jZmNhLmNvbS5jbjozODkvQ049Y3JsMTI2XzE5NCxPVT1DUkwsTz1DRkNBIFRFU1QgQ0EsQz1DTj9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0P2Jhc2U/b2JqZWN0Y2xhc3M9Y1JMRGlzdHJpYnV0aW9uUG9pbnQwDQYJKoZIhvcNAQEFBQADgYEAKX4CXCPQEE4RWGsZTXZXLBct2gcPYEjqpgPZ5ERiUrYLTDGuIT90ECfSoxCrcTJEeY7EJBojig9gLRoMn/4xXW/XscGarQ3XxyZw8VxTMFkotuUkAPoaacYlIrc34t2DR0DqvU6umgFL3yTMYxl5WLjOh47OH7Aw7VPscmrtzEIxgd0wgdoCAQEwODAkMQswCQYDVQQGEwJDTjEVMBMGA1UEChMMQ0ZDQSBURVNUIENBAhAuyfCgPbMcigfvVyGazeQRMAkGBSsOAwIaBQAwDQYJKoZIhvcNAQEBBQAEgYCWMOH6vsA5EY5Z7xPTyP7tUse3boa9fbeJEKhjS+SvLVrA8Rj1jasrM4twl4cuhtyuB1G5l/WhsTfdkPlU4O39cgvJyJ3VlTryC5GkDRxGHrrXRtD+9JIEtfByvqQ0uzQzn6BckF9CAhVJ+IAfi/zYnTvehQg8QlnajzGD78F7tw==</hmac>\\n</data>\\n\",\"responseTime\":1528959193796,\"status\":-1,\"statusDes\":\"转账请求失败，返回代码：0046\"}";
        GroundPromotionPayLog log = JSON.parseObject(test, GroundPromotionPayLog.class);
        payLogService.updateLogById(log);
        System.out.println();
    }

   public void testGenOrder(){
       groundPromotionOrderService.saveGroundPromotionAchievement();
   }

    public void testGenConfig(){
        ServiceResult<GroundPromotionCommissionConfig> bestConfig = groundPromotionCommissionConfigService.getBestConfig(93, 0, 1);
        System.out.println();
    }

    public void testCashList(){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("payStatus","0,2,3,4");
        params.put("userId",44);
        ServiceResult<Pagination> paginationServiceResult = antService.listGroundPromotionPayReceiptInfo(params, 1, 10);
        System.out.println();
    }

    public void testUpdate(){
        groundPromotionIncomeDetailWriteDao.updateIncomeDetailByReceiptId(2, 53);
    }

    public void testSaveAchievementOrderInfo(){
        groundPromotionOrderService.saveGroundPromotionAchievement();
        System.out.println();
    }


    public void testZqsp(){
        String jsonMessage = "{\"details\":[{\"amount\":10,\"duituiConfig\":\"\",\"goodId\":11847487,\"goodPicUrl\":\"2018/11/08/g_497ebc15a78d406bbe2a3e23081f7bec.jpg\",\"odId\":27198184,\"odName\":\"小药宝专用商品（空白商品税务码商品84）\",\"quantity\":0},{\"amount\":10,\"duituiConfig\":\"\",\"goodId\":11847486,\"goodPicUrl\":\"2018/11/08/g_497ebc15a78d406bbe2a3e23081f7bec.jpg\",\"odId\":27198185,\"odName\":\"小药宝专用商品（空白商品税务码商品83）\",\"quantity\":0}],\"oid\":5987566,\"osn\":\"DM201905151619150300\",\"type\":0,\"userId\":18}";
        String details = "[{\"amount\":10,\"duituiConfig\":\"{\"id\":119,\"scopeType\":1,\"goodId\":3,\"linkId\":1873,\"linkName\":\"湖北省\",\"businessType\":2,\"computeType\":1,\"percentVal\":5.0,\"fixVal\":0.0,\"computeTypeLead\":1,\"percentValLead\":1.0,\"fixValLead\":0.0,\"createTime\":\"2018-10-25 16:45:51\",\"updateTime\":\"2018-10-25 16:45:51\"}\",\"goodId\":11847487,\"goodPicUrl\":\"2018/11/08/g_497ebc15a78d406bbe2a3e23081f7bec.jpg\",\"odId\":27198184,\"odName\":\"小药宝专用商品（空白商品税务码商品84）\",\"quantity\":0},{\"amount\":10,\"duituiConfig\":\"{\"id\":119,\"scopeType\":1,\"goodId\":3,\"linkId\":1873,\"linkName\":\"湖北省\",\"businessType\":2,\"computeType\":1,\"percentVal\":5.0,\"fixVal\":0.0,\"computeTypeLead\":1,\"percentValLead\":1.0,\"fixValLead\":0.0,\"createTime\":\"2018-10-25 16:45:51\",\"updateTime\":\"2018-10-25 16:45:51\"}\",\"goodId\":11847486,\"goodPicUrl\":\"2018/11/08/g_497ebc15a78d406bbe2a3e23081f7bec.jpg\",\"odId\":27198185,\"odName\":\"小药宝专用商品（空白商品税务码商品83）\",\"quantity\":0}]";
        OrderSpecificProductInfo productInfo = JSON.parseObject(jsonMessage, OrderSpecificProductInfo.class);
        for (OrderSpecificProductInfo.OrderDetail detail : productInfo.getDetails()){
            if (detail.getOdId().equals(27198184)){
                detail.setDuituiConfig("{\"id\":119,\"scopeType\":1,\"goodId\":3,\"linkId\":1873,\"linkName\":\"湖北省\",\"businessType\":2,\"computeType\":1,\"percentVal\":5.0,\"fixVal\":0.0,\"computeTypeLead\":1,\"percentValLead\":1.0,\"fixValLead\":0.0,\"createTime\":\"2018-10-25 16:45:51\",\"updateTime\":\"2018-10-25 16:45:51\"}");
            }

            if (detail.getOdId().equals(27198185)){
                detail.setDuituiConfig("{\"id\":119,\"scopeType\":1,\"goodId\":3,\"linkId\":1873,\"linkName\":\"湖北省\",\"businessType\":2,\"computeType\":1,\"percentVal\":5.0,\"fixVal\":0.0,\"computeTypeLead\":1,\"percentValLead\":1.0,\"fixValLead\":0.0,\"createTime\":\"2018-10-25 16:45:51\",\"updateTime\":\"2018-10-25 16:45:51\"}");
            }
        }

        String res = JSON.toJSONString(productInfo);
        System.out.println(res);
        groundPromotionIncomeDetailService.saveOrderSpecificProductInfo(productInfo);//String messageStr = null;
        System.out.println();
    }


    public void testSpecificProductInfo(){
        String json = "{\"details\":[{\"amount\":120,\"duituiConfig\":\"{\\\"id\\\":525,\\\"scopeType\\\":2,\\\"goodId\\\":16,\\\"linkId\\\":21,\\\"linkName\\\":\\\"二毛\\\",\\\"businessType\\\":2,\\\"computeType\\\":1,\\\"percentVal\\\":10.0,\\\"fixVal\\\":0.0,\\\"computeTypeLead\\\":1,\\\"percentValLead\\\":1.0,\\\"fixValLead\\\":0.0,\\\"createTime\\\":\\\"2019-05-16 13:25:13\\\",\\\"updateTime\\\":\\\"2019-05-16 13:25:13\\\"}\",\"goodId\":9085280,\"odId\":27198419,\"odName\":\"小药宝商品2（新斯达舒7）\",\"quantity\":10},{\"amount\":60,\"duituiConfig\":\"{\\\"id\\\":525,\\\"scopeType\\\":2,\\\"goodId\\\":16,\\\"linkId\\\":21,\\\"linkName\\\":\\\"二毛\\\",\\\"businessType\\\":2,\\\"computeType\\\":1,\\\"percentVal\\\":10.0,\\\"fixVal\\\":0.0,\\\"computeTypeLead\\\":1,\\\"percentValLead\\\":1.0,\\\"fixValLead\\\":0.0,\\\"createTime\\\":\\\"2019-05-16 13:25:13\\\",\\\"updateTime\\\":\\\"2019-05-16 13:25:13\\\"}\",\"goodId\":11847478,\"odId\":27198420,\"odName\":\"小药宝商品2（空白商品税务码商品75）\",\"quantity\":12},{\"amount\":50,\"duituiConfig\":\"{\\\"id\\\":524,\\\"scopeType\\\":2,\\\"goodId\\\":3,\\\"linkId\\\":21,\\\"linkName\\\":\\\"二毛\\\",\\\"businessType\\\":2,\\\"computeType\\\":1,\\\"percentVal\\\":10.0,\\\"fixVal\\\":0.0,\\\"computeTypeLead\\\":1,\\\"percentValLead\\\":5.0,\\\"fixValLead\\\":0.0,\\\"createTime\\\":\\\"2019-05-15 15:44:40\\\",\\\"updateTime\\\":\\\"2019-05-15 15:44:40\\\"}\",\"goodId\":11847486,\"goodPicUrl\":\"2018/11/08/g_497ebc15a78d406bbe2a3e23081f7bec.jpg\",\"odId\":27198421,\"odName\":\"小药宝专用商品（83）\",\"quantity\":10},{\"amount\":50,\"duituiConfig\":\"{\\\"id\\\":524,\\\"scopeType\\\":2,\\\"goodId\\\":3,\\\"linkId\\\":21,\\\"linkName\\\":\\\"二毛\\\",\\\"businessType\\\":2,\\\"computeType\\\":1,\\\"percentVal\\\":10.0,\\\"fixVal\\\":0.0,\\\"computeTypeLead\\\":1,\\\"percentValLead\\\":5.0,\\\"fixValLead\\\":0.0,\\\"createTime\\\":\\\"2019-05-15 15:44:40\\\",\\\"updateTime\\\":\\\"2019-05-15 15:44:40\\\"}\",\"goodId\":11847487,\"goodPicUrl\":\"2018/11/08/g_497ebc15a78d406bbe2a3e23081f7bec.jpg\",\"odId\":27198422,\"odName\":\"小药宝专用商品（84）\",\"quantity\":10}],\"oid\":5987712,\"osn\":\"DM201905161523311055\",\"type\":0,\"userId\":21}";

        OrderSpecificProductInfo productInfo = JSON.parseObject(json, OrderSpecificProductInfo.class);
        groundPromotionIncomeDetailService.saveOrderSpecificProductInfo(productInfo);
        System.out.println();
    }

    public void testSpecificProductInfoDup(){
        String json = "{\"details\":[{\"amount\":158.4,\"duituiConfig\":\"{\\\"id\\\":580,\\\"scopeType\\\":2,\\\"goodId\\\":16,\\\"linkId\\\":1599,\\\"linkName\\\":\\\"小四\\\",\\\"cityId\\\":0,\\\"cityName\\\":\\\"\\\",\\\"businessType\\\":2,\\\"computeType\\\":3,\\\"percentVal\\\":10.0,\\\"fixVal\\\":4.0,\\\"computeTypeLead\\\":3,\\\"percentValLead\\\":5.0,\\\"fixValLead\\\":2.0,\\\"enableTopFlag\\\":1,\\\"computeTypeTop\\\":3,\\\"percentValTop\\\":10.0,\\\"fixValTop\\\":3.0,\\\"createTime\\\":\\\"2019-05-30 14:08:08\\\",\\\"updateTime\\\":\\\"2019-05-30 14:08:08\\\"}\",\"goodId\":9085280,\"odId\":46069438,\"odName\":\"小药宝商品2（新斯达舒7）\",\"quantity\":15}],\"oid\":9596868,\"osn\":\"DM201905301421270906\",\"type\":0,\"userId\":1599}";

        OrderSpecificProductInfo productInfo = JSON.parseObject(json, OrderSpecificProductInfo.class);
        groundPromotionIncomeDetailService.saveOrderSpecificProductInfo(productInfo);
        System.out.println();
    }
}
