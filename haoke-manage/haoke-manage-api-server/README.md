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
返回
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