package com.mph.coreapi.gp.pay.mq;

import com.alibaba.fastjson.JSON;
import com.mph.coreapi.gp.pay.entity.OrderSpecificProductInfo;
import com.mph.coreapi.gp.pay.service.GroundPromotionIncomeDetailService;
import com.mph.rabbitmq.annotation.ConsumerService;
import com.mph.rabbitmq.consumer.RabbitMqMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: xueli.ji
 * @Email: xueli.ji@rograndec.com
 * @CreateDate: 2018/4/11
 * @Version: 1.0
 */
@ConsumerService(queueName="mph.ditui.notify.queue", routingKey="specificProduct", exchangeName="mph.ditui.direct.exchange", clz = OrderSpecificProductInfo.class)
public class NewOrderNotifyHander implements RabbitMqMessageHandler<OrderSpecificProductInfo> {
    private static final Logger logger = LoggerFactory.getLogger(NewOrderNotifyHander.class);

    @Autowired(required = false)
    GroundPromotionIncomeDetailService groundPromotionIncomeDetailService;

    @Override
    public boolean handle(OrderSpecificProductInfo orderMessage) {
        try {
            logger.info("处理通知业务：{}", JSON.toJSONString(orderMessage));
            // 取消特殊处理下
            if (orderMessage.getType() != null && (orderMessage.getType().equals(2)||orderMessage.getType().equals(3))){
                if (orderMessage.getDetails() != null && orderMessage.getDetails().size() > 0){
                    for (OrderSpecificProductInfo.OrderDetail orderDetail : orderMessage.getDetails()){
                        orderDetail.setAmount(0.0);
                        orderDetail.setQuantity(0);
                    }
                }
            }

            groundPromotionIncomeDetailService.saveOrderSpecificProductInfo(orderMessage);
        }catch (Exception e){
                logger.error("支付通知mq消费处理业务异常:{}", e.getMessage(), e);
        }

        return true;
    }
}
