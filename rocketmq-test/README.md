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

# 分布式事务
分布式事务分类有这几种：
    基于单个JVM，数据库分库分表了（跨多个数据库）。
    基于多JVM，服务拆分了（不跨数据库）。
    基于多JVM，服务拆分了 并且数据库分库分表了。
    
半消息：指的是暂不能投递的消息，发送方已经将消息成功发送到了 MQ 服务端，但是服务端未收到生产者对该消息的二次确认，此时该消息被标记成“暂不能投递”状态，处于该种状态下的消息即半消息。
消息回查：由于网络闪断、生产者应用重启等原因，导致某条事务消息的二次确认丢失，MQ 服务端通过扫描发现某条消息长期处于“半消息”时，需要主动向消息生产者询问该消息的最终状态（Commit 或是 Rollback），该过程即消息回查。 
 
1. 发送方向 MQ 服务端发送消息。
2. MQ Server 将消息持久化成功之后，向发送方 ACK 确认消息已经发送成功，此时消息为半消息。
3. 发送方开始执行本地事务逻辑。
4. 发送方根据本地事务执行结果向 MQ Server 提交二次确认（Commit 或是 Rollback），MQ Server 收到
Commit 状态则将半消息标记为可投递，订阅方最终将收到该消息；MQ Server 收到 Rollback 状态则删除半
消息，订阅方将不会接受该消息。
5. 在断网或者是应用重启的特殊情况下，上述步骤4提交的二次确认最终未到达 MQ Server，经过固定时间后
MQ Server 将对该消息发起消息回查。
6. 发送方收到消息回查后，需要检查对应消息的本地事务执行的最终结果。
7. 发送方根据检查得到的本地事务的最终状态再次提交二次确认，MQ Server 仍按照步骤4对半消息进行操作。 