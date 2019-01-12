package com.hu.base.rpc.client;

import com.hu.base.rpc.api.MyRpcService;
import com.hu.base.rpc.api.Result;
import com.hu.base.rpc.api.Send;
import com.hu.base.rpc.common.MyAutowired;
import lombok.extern.slf4j.Slf4j;

/**
 * 模拟的客户端service
 * @author hutiantian
 * @date: 2019/1/12 13:06
 * @since 1.0.0
 */
@Slf4j
public class ClientService {

    @MyAutowired //自定义注解，注入rpc接口的代理对象
    private MyRpcService myRpcService;                  //需要rpc调用service

    public void rpcInvoke(){
        //第一次发送
        Send sendA = getSendA();
        log.debug("客户端第一次发送对象为: "+sendA.toString());
        Result resultA = myRpcService.doRpc(sendA);
        log.debug("客户端第一次受到结果为: "+resultA.toString());

        //第二次发送
        Send sendB = getSendB();
        log.debug("客户端第二次发送对象为"+sendB.toString());
        Result resultB = myRpcService.doRpc(sendB);
        log.debug("客户端第二次受到结果为: "+resultB.toString());
    }

    private Send getSendA(){
        Send send = new Send();
        send.setName("hutiantian");
        send.setPassword("654231");
        return send;
    }

    private Send getSendB(){
        Send send = new Send();
        send.setName("hutiantian");
        send.setPassword("123456");
        return send;
    }
}
