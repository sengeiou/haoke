docker create --name percona -v /data/mysql-data:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root percona:5.7.23
docker start percona

CREATE TABLE `user` (
`id` bigint(20) NOT NULL COMMENT '主键ID', 
`name` varchar(30) DEFAULT NULL COMMENT '姓名', 
`age` int(11) DEFAULT NULL COMMENT '年龄', 
`email` varchar(50) DEFAULT NULL COMMENT '邮箱', 
PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
-- 插入数据 
INSERT INTO `user` (`id`, `name`, `age`, `email`) VALUES ('1', 'Jone', '18', 'test1@baomidou.com'); 
INSERT INTO `user` (`id`, `name`, `age`, `email`) VALUES ('2', 'Jack', '20', 'test2@baomidou.com'); 
INSERT INTO `user` (`id`, `name`, `age`, `email`) VALUES ('3', 'Tom', '28', 'test3@baomidou.com'); 
INSERT INTO `user` (`id`, `name`, `age`, `email`) VALUES ('4', 'Sandy', '21', 'test4@baomidou.com'); 
INSERT INTO `user` (`id`, `name`, `age`, `email`) VALUES ('5', 'Billie', '24', 'test5@baomidou.com');
