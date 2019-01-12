package com.hu.base.rpc.api;

/**
 * rpc接口
 * @author hutiantian
 * @date: 2019/1/12 12:41
 * @since 1.0.0
 */
public interface MyRpcService {

    /**
     * 模拟dubbo中定义的api接口，一个简单的rpc请求
     * @param send  rpc请求参数
     * @return rpc处理后的返回
     */
    Result doRpc(Send send);
}
