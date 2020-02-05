docker create --name zk -p 2181:2181 zookeeper:3.5

docker start zk

git clone https://github.com/apache/incubator-dubbo-ops.git

在 dubbo-admin-backend/src/main/resources/application.properties 中指定注册中心地址

dubbo.registry.address=zookeeper://10.33.72.81:2181

mvn clean package

mvn --projects dubbo-admin-backend spring-boot:run

http://10.33.72.81:8080/