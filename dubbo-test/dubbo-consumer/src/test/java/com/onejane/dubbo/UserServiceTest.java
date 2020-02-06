package com.onejane.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.onejane.dubbo.pojo.User;
import com.onejane.dubbo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Reference(version = "1.0.0", loadbalance = "roundrobin")
    private UserService userService;

    @Test
    public void testQueryAll() {
        for (int i = 0; i < 100; i++) {

            System.out.println("开始调用远程服务 >>>>>" + i);

            List<User> users = this.userService.queryAll();
            for (User user : users) {
                System.out.println(user);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 小数转分数
     * @param num
     * @return
     */
    public static String dicimalToFraction(double num){
        int count = 0;
        int base = 10;
        while(num != Math.floor(num)){
            num *= base;
            count++;
        }

        base = (int)Math.pow(base,count);
        int nor = (int)num;
        int gcd = findGCD(nor, base);


        return String.valueOf(nor/gcd) + "/" + String.valueOf(base/gcd);
    }


    //求最大公约数
    private static int findGCD(int a, int b){
        if(a == 0){
            return b;
        }
        return findGCD(b%a, a);
    }

    @Test
    public void a(){
//        List<Integer> randomPos = new ArrayList<>();
//        do{
//            HashSet<Integer> set = new HashSet<Integer>();
//            randomSet(4,10,2,set);
//            randomPos.clear();
//            randomPos.addAll(set);
//        }while(Math.abs(randomPos.get(0)-randomPos.get(1))>3);
//        for(int i:randomPos){
//            System.out.println(i);
//        }
        for(int i=0;i<10;i++){
            Random r = new Random();
            System.out.println(r.nextInt(10));
        }
    }

    public static void randomSet(int min, int max, int n, HashSet<Integer> set) {
        if (n > (max - min + 1) || max < min) {
            return;
        }
        for (int i = 0; i < n; i++) {
            // 调用Math.random()方法
            int num = (int) (Math.random() * (max - min)) + min;
            set.add(num);// 将不同的数存入HashSet中
        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (setSize < n) {
            randomSet(min, max, n - setSize, set);// 递归
        }
    }
}
