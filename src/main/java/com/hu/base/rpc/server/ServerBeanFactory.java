package com.hu.base.rpc.server;

import java.util.HashMap;

/**
 * 服务的beanFactory
 * @author hutiantian
 * @date: 2019/1/12 15:35
 * @since 1.0.0
 */
public class ServerBeanFactory {

    //为了简单起见，还是只缓冲一个接口实现类
    public String CLASS_NAME = "com.hu.base.rpc.server.MyRpcServiceImpl";

    //ioc容器，key为接口className，value为实现类的实例
    private HashMap<String,Object> ioc = new HashMap<>();

    public ServerBeanFactory() throws Exception{
        init();
    }

    private void init() throws Exception{
        Class clazz = Class.forName(CLASS_NAME);
        Object obj = clazz.newInstance();
        //根据实现类的class获取到接口，保存到ioc容器中
        Class[] interfaces = clazz.getInterfaces();
        for (Class inter:interfaces){
            ioc.put(inter.getName(),obj);
        }
    }

    public Object getBean(String beanName){
        return ioc.get(beanName);
    }
}
