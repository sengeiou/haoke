package com.onejane.rocketmq.sendmsg;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

// 同步发送
public class SyncProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("haoke");

        producer.setNamesrvAddr("10.33.72.81:9876");

        producer.start();

        //发送消息
        String msg = "我的第一个消息6!";
        Message message = new Message("my-topic", "delete", msg.getBytes("UTF-8"));
        SendResult sendResult = producer.send(message);
        System.out.println("消息id：" + sendResult.getMsgId());
        System.out.println("消息队列：" + sendResult.getMessageQueue());
        System.out.println("消息offset值：" + sendResult.getQueueOffset());
        System.out.println(sendResult);

        producer.shutdown();
    }
}
