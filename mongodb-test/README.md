docker create --name mongodb -p 27017:27017 -v /data/mongodb:/data/db mongo:4.0.3
docker start mongodb
docker exec -it mongodb /bin/bash
mongo
show dbs #查询所有的数据库
use admin    #通过use关键字切换数据库
use testdb    #创建数据库 #说明：在MongoDB中，数据库是自动创建的，通过use切换到新数据库中，进行插入数据即可自动创建数据库
db.user.insert({id:1,name:'zhangsan'}) #插入数据
db.user.save({id:2,username:'lisi',age:25})
db.user.find() #查询数据
show dbs
show tables
show collections
db.user.drop()        #删除集合（表）
use testdb #先切换到要删除的数据中
db.dropDatabase() #删除数据库
show dbs
db.user.update({id:1},{$set:{age:22}}) #更新数据
db.user.update({id:1},{age:25})    #注意：如果这样写，会删除掉其他的字段
db.user.update({id:2},{$set:{sex:1}}) #更新数据    #更新不存在的字段，会新增字段
db.user.update({id:3},{$set:{sex:1}},true)    #如果设置第一个参数为true，就是新增数据
db.user.remove({age:25})        #删除了2条数据
db.user.remove({age:22},true)    #删除了1条数据
db.user.remove({})    #删除所有数据
db.user.deleteMany({}) #删除所有数据
db.user.deleteOne({id:1})    #说明：为了简化操作，官方推荐使用deleteOne()与deleteMany()进行删除数据操作
db.col.find().pretty()    #以易读的方式来读取数据


db.user.find() #查询全部数据
db.user.find({},{id:1,username:1}) #只查询id与username字段
db.user.find().count() #查询数据条数
db.user.find({id:1}) #查询id为1的数据
db.user.find({age:{$lte:21}}) #查询小于等于21的数据
db.user.find({age:{$lte:21}, id:{$gte:2}}) #and查询，age小于等于21并且id大于等于2
db.user.find({$or:[{id:1},{id:2}]}) #查询id=1 or id=2
分页查询：Skip()跳过几条，limit()查询条数
db.user.find().limit(2).skip(1) #跳过1条数据，查询2条数据
db.user.find().sort({id:-1}) #按照age倒序排序，-1为倒序，1为正序
db.user.getIndexes()        #查看索引
db.user.createIndex({'age':1})    #创建索引
db.user.dropIndex("age_1")    #删除索引
db.user.dropIndexes()    #删除除了_id之外的索引
db.user.createIndex({'age':1, 'id':-1})        #创建联合索引
db.user.totalIndexSize()    #查看索引大小，单位：字节
        
for(var i=1;i<1000;i++)db.user.insert({id:100+i,username:'name_'+i,age:10+i})        #插入1000条数据
db.user.find({age:{$gt:100},id:{$lt:200}}).explain()        #查看执行计划
winningPlan stage 查询方式，常见的有COLLSCAN/全表扫描、IXSCAN/索引扫描、 FETCH/根据索引去检索文档、SHARD_MERGE/合并分片结果、IDHACK/针对_id进行查询
db.user.find({username:'zhangsan'}).explain()    #测试没有使用索引