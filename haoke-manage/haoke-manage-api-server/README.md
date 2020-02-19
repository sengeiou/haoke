https://common-buy.aliyun.com/#/buy   购买oss服务
https://oss.console.aliyun.com/overview  新建bucket-标准存储，公共读-创建成功后左边栏即可进入bucket并上传文件，点击详情查看图片
https://oss.console.aliyun.com/bucket/oss-cn-beijing/codewj/image  图片处理-新建样式-water 为图片添加水印样式，并在文件管理的a.jpg详情中使用water图片样式
https://codewj.oss-cn-beijing.aliyuncs.com/a.jpg?x-oss-process=style/water

修改hosts 127.0.0.1       image.haoke.com

nginx.conf

``` nginx
server {
    listen       80;
    server_name  image.haoke.com;


    #charset koi8-r;


    #access_log  logs/host.access.log  main;


proxy_set_header X-Forwarded-Host $host;
    proxy_set_header X-Forwarded-Server $host;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;


    location / {
    root  F:\code\itcast-haoke\haoke-upload;
    }
    
}
```

chrome安装Altair GraphQL Client插件
http://127.0.0.1:18080/graphql
query
```$xslt
{
    HouseResources(id:1){
    id
    time
    title
    estateId
    contact
  }
}
```
result
```$xslt
{
  "data": {
    "HouseResources": {
      "id": 1,
      "time": 1,
      "title": "东方曼哈顿 3室2厅 16000 元",
      "estateId": 1005,
      "contact": "张 三"
    }
  }
}
```
query
```$xslt
{
  HouseResourcesList(page: 1, pageSize: 2) {
    list {
      id
      time
      estateId
      title
      mobile
      rent
      buildingNum
    }
    pagination {
      current
      pageSize
      total
    }
  }
}

```
result
```$xslt
{
  "data": {
    "HouseResourcesList": {
      "list": [
        {
          "id": 12,
          "time": 1,
          "estateId": 1005,
          "title": "个人博客",
          "mobile": "3",
          "rent": 3,
          "buildingNum": "3"
        },
        {
          "id": 11,
          "time": 1,
          "estateId": 1005,
          "title": "个人博客",
          "mobile": "3",
          "rent": 3,
          "buildingNum": "3"
        }
      ],
      "pagination": {
        "current": 1,
        "pageSize": 2,
        "total": 7
      }
    }
  }
}
```
query
```$xslt
{
  IndexAdList {
    list {
      original
    }
  }
}

```
result
```$xslt
{
  "data": {
    "IndexAdList": {
      "list": [
        {
          "original": "http://codewj.oss-cn-beijing.aliyuncs.com/images/2019/06/21/15610857626646530.jpg"
        },
        {
          "original": "http://codewj.oss-cn-beijing.aliyuncs.com/images/2019/06/21/15610857423871578.jpg"
        },
        {
          "original": "http://codewj.oss-cn-beijing.aliyuncs.com/images/2019/06/21/1561085716927197.jpg"
        }
      ]
    }
  }
}
```
query
```$xslt
query hk($id: Long) {
  HouseResources(id: $id) {
    id
    title
    estateId
    buildingUnit
    buildingFloorNum
    mobile
    useArea
    pic
  }
}

variables
{
  "id": 1
}
```
result
```$xslt
{
  "data": {
    "HouseResources": {
      "id": 1,
      "title": "东方曼哈顿 3室2厅 16000 元",
      "estateId": 1005,
      "buildingUnit": "1",
      "buildingFloorNum": "1",
      "mobile": "11111111111",
      "useArea": "2",
      "pic": null
    }
  }
}
```
query
```$xslt
query HouseResourcesList($pageSize: Int, $page: Int) {
  HouseResourcesList(pageSize: $pageSize, page: $page) {
    list {
      id
      pic
      title
      coveredArea
      orientation
      floor
      rent
    }
  }
}
variables
{
  "pageSize": 2,
    "page": 1
}
```
result
```$xslt
{
  "data": {
    "HouseResourcesList": {
      "list": [
        {
          "id": 12,
          "pic": "http://image.haoke.com/images/2020/02/06/202002061139517030691.jpg,http://image.haoke.com/images/2020/02/06/202002061140231020802.jpg",
          "title": "个人博客",
          "coveredArea": "345",
          "orientation": "南",
          "floor": "3/3",
          "rent": 3
        },
        {
          "id": 11,
          "pic": "http://image.haoke.com/images/2020/02/06/202002061139517030691.jpg",
          "title": "个人博客",
          "coveredArea": "345",
          "orientation": "南",
          "floor": "3/3",
          "rent": 3
        }
      ]
    }
  }
}
```

# redis集群
docker create --name redis-node01 -v /data/redis-data/node01:/data -p 6379:6379 redis:5.0.2 --cluster-enabled yes --cluster-config-file nodes-node-01.conf
docker create --name redis-node02 -v /data/redis-data/node02:/data -p 6380:6379 redis:5.0.2 --cluster-enabled yes --cluster-config-file nodes-node-02.conf
docker create --name redis-node03 -v /data/redis-data/node03:/data -p 6381:6379 redis:5.0.2 --cluster-enabled yes --cluster-config-file nodes-node-03.conf

docker start redis-node01 redis-node02 redis-node03
docker inspect redis-node01 -> 172.17.0.4 
docker inspect redis-node02 -> 172.17.0.5 
docker inspect redis-node03 -> 172.17.0.6


docker exec -it redis-node01 /bin/bash
redis-cli --cluster create 172.17.0.4:6379 172.17.0.5:6379 172.17.0.6:6379 -- cluster-replicas 0
其中172.17.0.1 为docker0网卡，在客户端（spring-data-redis）是没有办法访问的
docker stop redis-node01 redis-node02 redis-node03 
docker rm redis-node01 redis-node02 redis-node03
rm -rf /data/redis-data

## 基于host搭建
docker create --name redis-node01 --net host -v /data/redis-data/node01:/data redis:5.0.2 --cluster-enabled yes --cluster-config-file nodes-node-01.conf --port 6379 
docker create --name redis-node02 --net host -v /data/redis-data/node02:/data redis:5.0.2 --cluster-enabled yes --cluster-config-file nodes-node-02.conf --port 6380 
docker create --name redis-node03 --net host -v /data/redis-data/node03:/data redis:5.0.2 --cluster-enabled yes --cluster-config-file nodes-node-03.conf --port 6381 
docker start redis-node01 redis-node02 redis-node03   
docker exec -it redis-node01 /bin/bash 
redis-cli --cluster create 10.33.72.81:6379 10.33.72.81:6380 10.33.72.81:6381 --cluster-replicas 0