package com.hu.base.rpc.common;

import lombok.Data;

import java.io.Serializable;

/**
 * rpc通讯的对象，需要jdk序列化转字节数组
 * @author hutiantian
 * @date: 2019/1/12 12:41
 * @since 1.0.0
 */
@Data
public class Message implements Serializable {
    private String className;       //要调用的rpc接口的类名
    private String methodName;      //要调用的rpc接口的方法名
    private Object[] args;          //rpc接口的形参列表
    private Object result;          //rpc接口调用后的结果
}
