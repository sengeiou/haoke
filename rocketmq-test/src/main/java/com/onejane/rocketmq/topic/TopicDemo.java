package com.onejane.rocketmq.topic;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

// 创建topic
public class TopicDemo {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("haoke");

        //设置nameserver的地址
        producer.setNamesrvAddr("10.33.72.81:9876");

        // 启动生产者
        producer.start();

        /**
         * 创建topic，参数分别是：broker的名称，topic的名称，queue的数量
         *
         */
        producer.createTopic("broker_haoke_im", "my-topic", 8);

        System.out.println("topic创建成功!");

        producer.shutdown();
    }
}
