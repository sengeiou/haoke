package com.onejane.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.onejane.dubbo.pojo.User;
import com.onejane.dubbo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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


}
