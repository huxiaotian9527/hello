package com.hu.base.rpc.api;

import lombok.Data;

import java.io.Serializable;

/**
 * rpc接口返回对象
 * @author hutiantian
 * @date: 2019/1/12 12:41
 * @since 1.0.0
 */
@Data
public class Result implements Serializable {
    private int code;               //返回码
    private String message;         //描述
}
