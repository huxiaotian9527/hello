package com.hu.base.rpc.client;

import com.hu.base.rpc.common.Message;
import com.hu.base.rpc.common.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * InvocationHandler接口的实现类，用于创建rpc接口的代理对象
 *
 * 为了简单我把通讯也写在了这个类里面，是不对的。不符合单一职责
 * @author hutiantian
 * @date: 2019/1/12 13:50
 * @since 1.0.0
 */
@Slf4j
public class ClientProxy implements InvocationHandler {
    private String host = "127.0.0.1";      //服务方ip
    private int port = 9527;                //服务方端口


    /**
     * 代理对象的方法实现
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        Message message = new Message();
        //组装message对象
        message.setClassName(method.getDeclaringClass().getName());
        message.setMethodName(method.getName());
        message.setArgs(args);
        Message result = (Message) sendMessage(message);
        return result.getResult();
    }

    /**
     * 为rpc接口创建代理对象，代理方法为上面的invoke
     */
    public static <T> T newMapperProxy(Class<T> interfaceClass) {
        ClassLoader classLoader = interfaceClass.getClassLoader();
        Class<?>[] interfaces = new Class[]{interfaceClass};
        ClientProxy proxy = new ClientProxy();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
    }

    /**
     * 与服务方进行rpc通讯（socket实现）
     */
    public Object sendMessage(Message message) throws Exception {
        //获取message序列化后的字节数组
        ByteArrayOutputStream bao = MessageUtil.getBao(message);
        Socket socket = new Socket(host, port);
        //发送消息
        OutputStream os = socket.getOutputStream();
        os.write(bao.toByteArray());
        os.flush();
        //接收消息
        bao.reset();
        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int ret;
        while ((ret = is.read(bytes)) > 0) {
            bao.write(bytes, 0, ret);
        }
        //将返回的字节数组转对象
        return MessageUtil.getObj(bao);
    }

}
