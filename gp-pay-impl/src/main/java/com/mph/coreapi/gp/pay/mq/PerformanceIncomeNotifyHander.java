package com.mph.coreapi.gp.pay.mq;

import com.alibaba.fastjson.JSON;
import com.mph.coreapi.gp.pay.model.component.GroundUserPerformanceComponent;
import com.mph.coreapi.gp.pay.mq.data.PerformanceIncomeMessage;
import com.mph.rabbitmq.annotation.ConsumerService;
import com.mph.rabbitmq.consumer.RabbitMqMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 绩效收益入库MQ消息处理类
 * @author jianghong.huang
 *
 */
@ConsumerService(queueName="mph.ditui.performance.income.notify.queue", routingKey="PerformanceIncomeNotify", exchangeName="mph.ditui.direct.exchange", clz = PerformanceIncomeMessage.class)
public class PerformanceIncomeNotifyHander implements RabbitMqMessageHandler<PerformanceIncomeMessage> {
    
    private static final Logger logger = LoggerFactory.getLogger(PerformanceIncomeNotifyHander.class);

    @Autowired
    private GroundUserPerformanceComponent groundUserPerformanceComponent;

    @Override
    public boolean handle(PerformanceIncomeMessage message) {
        try {
            boolean ret = groundUserPerformanceComponent.givenGroundUserPerformance(message.getGroundUserId(), message.getMemo()
                , message.getAmount(), message.getPerformanceActiveId()
                , message.getSaleAmount(), message.getSaleNum());
            logger.info("处理通知业务：{}，处理结果：{}", JSON.toJSONString(message), ret);
        }catch (Exception e){
            logger.error("支付通知mq消费处理业务异常:{}", e.getMessage(), e);
        }
        return true;
    }
}
