package com.hu.base.rpc.api;

import lombok.Data;

import java.io.Serializable;

/**
 * rpc接口请求对象
 * @author hutiantian
 * @date: 2019/1/12 12:40
 * @since 1.0.0
 */
@Data
public class Send implements Serializable {
    private String name;                //请求姓名
    private String password;            //请求密码
}
