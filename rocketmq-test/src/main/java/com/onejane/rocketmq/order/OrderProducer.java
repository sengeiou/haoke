package com.onejane.rocketmq.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class OrderProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("HAOKE_ORDER_PRODUCER");
        producer.setNamesrvAddr("10.33.72.81:9876");
        producer.start();
        for (int i = 0; i < 100; i++) { // 100条消息，0-9个订单共10个订单，发送到4个订单
            // 订单创建 付款 完成落在同一个topic中的同一个queue顺序执行
            int orderId = i % 10; // 模拟生成订单id 生产0-9 10个订单
            String msgStr = "order --> " + i +", id = "+ orderId;
            Message message = new Message("haoke_order_topic", "ORDER_MSG",
                    msgStr.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message, (mqs, msg, arg) -> {
                Integer id = (Integer) arg;
                int index = id % mqs.size();
                return mqs.get(index);
            }, orderId); // orderId->arg,通过实现MessageQueueSelector选择消息队列，mqs.size()默认四个队列，id % mqs.size()得到0-3，返回0-3的消息队列，实现发送消息时将消息发送到同一个队列中
            System.out.println(sendResult);
        }
        producer.shutdown();
    }

}