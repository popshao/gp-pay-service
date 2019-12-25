package com.mph.coreapi.gp.pay.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.mph.coreapi.gp.pay.entity.OrderSpecificProductInfo;
import com.mph.coreapi.gp.pay.service.GroundPromotionIncomeDetailService;
import com.rabbitmq.client.Channel;
import com.rograndec.mph.rabbitmq.handler.AbstractMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 处理地推提成消息
 * <pre>
 * queueName = mph.ditui.notify.queue
 * exchangeName = mph.ditui.direct.exchange
 * routingKey = specificProduct
 * </pre>
 * @Author: xueli.ji
 * @Email: xueli.ji@rograndec.com
 * @CreateDate: 2018/4/11
 * @Version: 1.0
 */
public class OrderNotifyHander extends AbstractMessageHandler{
    private static final Logger logger = LoggerFactory.getLogger(OrderNotifyHander.class);

    @Autowired(required = false)
    GroundPromotionIncomeDetailService groundPromotionIncomeDetailService;

    @Override
    public boolean handle(Message message, Channel channel) {
        try {
            String jsonMessage = new String(message.getBody(), "utf-8");
            jsonMessage = adaptNewMqService(jsonMessage);
            logger.info("Received order notification {}", jsonMessage);
            JSONObject jso = JSON.parseObject(jsonMessage);
            String messageStr = jso.getString("data");

            OrderSpecificProductInfo orderMessage = JSON.parseObject(messageStr, OrderSpecificProductInfo.class);
            groundPromotionIncomeDetailService.saveOrderSpecificProductInfo(orderMessage);
            logger.info("处理通知业务：{}", JSON.toJSONString(orderMessage));
        }catch (Exception e){
                logger.error("支付通知mq消费处理业务异常:{}",e.getMessage(),e);
        }
        return false;
    }

    private String adaptNewMqService(String jsonMessage){
        if (jsonMessage.startsWith("\"{\\")){
            jsonMessage = jsonMessage.replaceAll("\\\\", "");
        }

        jsonMessage = jsonMessage.replaceAll("\"\\{", "{");
        jsonMessage = jsonMessage.replaceAll("\\}\"", "}");

        return jsonMessage;
    }
}
