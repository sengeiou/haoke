// 修饰器
function T(target) {
  console.log(target);
  target.country="中国"
}
@T
class People{
  constructor(name,age=20){
    this.name=name;
    this.age=age;
  }
}
console.log(People.country);
/*
* npm i yarn tyarn -g
* tyarn -v
* tyarn global add umi
* umi 启动
* tyarn init -y
* yarn global bin 获取yarn环境添加到path路径
* umi g page index 创建好index.js 和 index.css
* umi dev
* */
import Util from './Util';
console.log(Util.sum(10, 5));
