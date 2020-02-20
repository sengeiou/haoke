# 介绍
Producer    消息生产者，负责产生消息，一般由业务系统负责产生消息。
  Producer Group    一类 Producer 的集合名称，这类 Producer 通常发送一类消息，且发送逻辑一致。
Consumer    消息费者，负责消费消息，一般是后台系统负责异步消费。
  Push Consumer    服务端向消费者端推送消息
  Pull Consumer    消费者端向服务定时拉取消息
  Consumer Group    一类 Consumer 的集合名称，这类 Consumer 通常消费一类消息，且消费逻辑一致。
NameServer    集群架构中的组织协调员    收集broker的工作情况    不负责消息的处理
Broker    是RocketMQ的核心负责消息的发送、接收、高可用等（真正干活的），需要定时发送自身情况到NameServer，默认10秒发送一次，超时2分钟会认为该broker失效。
Topic    不同类型的消息以不同的Topic名称进行区分，如User、Order等，是逻辑概念，Message Queue，消息队列，用于存储消息

# 非docker安装
unzip ~/rocketmq-all-4.3.2-bin-release.zip
cd rocketmq-all-4.3.2-bin-release/bin/ && vim runserver.sh
JAVA_OPT="${JAVA_OPT} -server -Xms128m -Xmx128m -Xmn128m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m"
vim runbroker.sh
JAVA_OPT="${JAVA_OPT} -server -Xms128m -Xmx128m -Xmn128m"
./mqnamesrv 启动nameserver 9876
./mqbroker -n 10.33.72.81:9876   启动broker 10911

export NAMESRV_ADDR=127.0.0.1:9876
./tools.sh org.apache.rocketmq.example.quickstart.Producer        发送消息测试，通过nameserver找到broker
./tools.sh org.apache.rocketmq.example.quickstart.Consumer

# 代码测试
mkdir -p /haoke/rmq/rmqbroker/conf/
vim /haoke/rmq/rmqbroker/conf/broker.conf
brokerIP1=10.33.72.81
namesrvAddr=10.33.72.81:9876
brokerName=broker_haoke_im
cd rocketmq-all-4.3.2-bin-release/ && bin/mqbroker -c /haoke/rmq/rmqbroker/conf/broker.conf

# docker安装
docker create -p 9876:9876 --name rmqserver  -e "JAVA_OPT_EXT=-server -Xms128m -Xmx128m -Xmn128m"  -e "JAVA_OPTS=-Duser.home=/opt"  -v /haoke/rmq/rmqserver/logs:/opt/logs  -v /haoke/rmq/rmqserver/store:/opt/store  foxiswho/rocketmq:server-4.3.2
docker create -p 10911:10911 -p 10909:10909 --name rmqbroker  -e "JAVA_OPTS=-Duser.home=/opt"  -e "JAVA_OPT_EXT=-server -Xms128m -Xmx128m -Xmn128m"  -v /haoke/rmq/rmqbroker/conf/broker.conf:/etc/rocketmq/broker.conf  -v /haoke/rmq/rmqbroker/logs:/opt/logs  -v /haoke/rmq/rmqbroker/store:/opt/store  foxiswho/rocketmq:broker-4.3.2
docker start rmqserver rmqbroker
export NAMESRV_ADDR=127.0.0.1:9876
./tools.sh org.apache.rocketmq.example.quickstart.Producer        发送消息测试，通过nameserver找到broker
./tools.sh org.apache.rocketmq.example.quickstart.Consumer
docker stop rmqbroker rmqserver 
docker rm rmqbroker rmqserver

docker run -di -e "JAVA_OPTS=-Drocketmq.namesrv.addr=10.33.72.81:9876 -Dcom.rocketmq.sendMessageWithVIPChannel=false" -p 8082:8080 -t styletang/rocketmq-console-ng:1.0.0
通过http://10.33.72.81:8082/#/访问

# 高级filter消息
vim /haoke/rmq/rmqbroker/conf/broker.conf
enablePropertyFilter=true
docker restart rmqbroker