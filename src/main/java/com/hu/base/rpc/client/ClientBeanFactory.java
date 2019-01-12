package com.hu.base.rpc.client;

import com.hu.base.rpc.common.MyAutowired;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * 模拟客户端的beanFactory
 * @author hutiantian
 * @date: 2019/1/12 15:13
 * @since 1.0.0
 */
public class ClientBeanFactory {

    public static String CLIENT_BEAN_NAME = "com.hu.base.rpc.client.ClientService";

    //ioc容器，key为bean的name，value为DI后的bean的实例
    private HashMap<String,Object> ioc = new HashMap<>();

    public ClientBeanFactory() throws Exception{
        init();
    }

    /**
     * 初始化方法
     */
    private void init() throws Exception{
        //这里为了简单，假设只加载一个bean
        //可以通过指定包路路径扫描获+注解实现，也可以通过xml定义bean实现
        Class clazz=Class.forName(CLIENT_BEAN_NAME);
        Object obj =clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        //bean对象带有autowired的属性DI注入rpc接口的代理对象
        for (Field field : fields){
            //如果加载的bean的成员变量字段带有MyAutowired的注解
            if(field.isAnnotationPresent(MyAutowired.class)){
                field.setAccessible(true);      //private的属性需要set
                //创建rpc接口的代理对象
                Object poxy = ClientProxy.newMapperProxy(field.getType());
                //第一个obj为bean实例，第二个obj是bean的属性的实例对象（也就是代理对象）
                field.set(obj,poxy);
            }
        }
        ioc.put(CLIENT_BEAN_NAME,obj);
    }

    public Object getBean(String beanName){
        return ioc.get(beanName);
    }

    public static void main(String[] args) throws Exception{
        //假装是spring的beanFactory
        ClientBeanFactory beanFactory = new ClientBeanFactory();
        //从beanFactory种获取bean的实例（已经DI过）
        ClientService clientService = (ClientService)beanFactory.getBean(CLIENT_BEAN_NAME);
        //调用bean种的rpc方法
        clientService.rpcInvoke();
    }
}
