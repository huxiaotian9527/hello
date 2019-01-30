package com.hu.base.util;

/**
 * 获取jvm参数，可以用来做分布式定时任务
 * @Author hutiantian
 * @Date 2019/1/30 15:40:32
 */
public class JVMParamUtil {

    public static void main(String[] args) {
        //在vm option中添加jvm参数-Dserver.no=1156 -Dspring.profiles.active=development
        //即可在运行时获取，如果没配置结果为null
        String serverNo = System.getProperty("server.no");
        String test = System.getProperty("spring.profiles.active");
        System.out.println(test);
        System.out.println(serverNo);
    }
}
