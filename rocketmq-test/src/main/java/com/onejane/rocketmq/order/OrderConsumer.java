package com.onejane.rocketmq.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class OrderConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new
                DefaultMQPushConsumer("HAOKE_ORDER_CONSUMER");
        consumer.setNamesrvAddr("10.33.72.81:9876");
        consumer.subscribe("haoke_order_topic", "*");
        consumer.registerMessageListener(new MessageListenerOrderly() {  // 实现有序监听 MessageListenerOrderly
            // 消费消息中在同一个队列消费
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                                                       ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " "
                                + msg.getQueueId() + " "
                                + new String(msg.getBody(),"UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

//                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
    }
}
// 同一个线程在同一个队列id中，保证消息的顺序
// 10个订单发送100条消息，1个队列有10个订单相关的数据，订单id为2的只会落到2的队列