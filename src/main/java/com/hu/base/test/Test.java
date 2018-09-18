package com.hu.base.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author hutiantian
 * @Date 2018/9/18 15:27:33
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 1500; i++) {
            log.debug("测试logback"+i);
        }
    }
}
