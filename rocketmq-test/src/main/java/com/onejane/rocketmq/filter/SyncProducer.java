package com.onejane.rocketmq.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class SyncProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("haoke");

        producer.setNamesrvAddr("10.33.72.81:9876");

        producer.start();

        //发送消息
        String msg = "这是一个用户的消息, id = 1003";
        Message message = new Message("my-topic-filter", "delete", msg.getBytes("UTF-8"));
        message.putUserProperty("sex","女");
        message.putUserProperty("age","20");
        SendResult sendResult = producer.send(message);
        System.out.println("消息id：" + sendResult.getMsgId());
        System.out.println("消息队列：" + sendResult.getMessageQueue());
        System.out.println("消息offset值：" + sendResult.getQueueOffset());
        System.out.println(sendResult);

        producer.shutdown();
    }
}
