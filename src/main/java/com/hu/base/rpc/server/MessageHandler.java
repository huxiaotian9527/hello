package com.hu.base.rpc.server;

import com.hu.base.rpc.common.Message;
import com.hu.base.rpc.common.MessageUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * rpc服务处理器
 * @author hutiantian
 * @date: 2019/1/12 15:46
 * @since 1.0.0
 */
@Slf4j
public class MessageHandler {

    private int port=9527;

    public void receiveMessage() throws Exception{
        ServerSocket server = new ServerSocket(port);
        //单线程处理socket，可以优化线程池处理任务
        while(true){
            Socket client = server.accept();
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            //接收消息
            InputStream is = client.getInputStream();
            byte[] bytes = new byte[1024];
            int ret;
            //默认客户端连接后，阻塞读1000ms，避免客户端都阻塞在inputStream的read方法
            //可以优化长连接，分割符；也可以约定报文前8个字节为长度。按长度读
            long startTime = System.currentTimeMillis();
            while(true){
                if(is.available() > 0) {
                    ret = is.read(bytes);
                    if(ret == -1) {
                        break;
                    } else {
                        if(ret > 0) {
                            bao.write(bytes,0,ret);
                            continue;
                        }
                    }
                }//最多阻塞读1000ms
                if(System.currentTimeMillis()-startTime>1000){
                    break;
                }
            }
            //反序列化rpc请求的message
            Message message = (Message)MessageUtil.getObj(bao);
            //假装服务器的beanFactory（new的过程会初始化ioc容器）
            ServerBeanFactory beanFactory = new ServerBeanFactory();
            //根据rpc请求className到beanFactory中寻找rpc接口的实现类
            Object obj = beanFactory.getBean(message.getClassName());
            Object[] args = message.getArgs();
            Class[] classes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i]=args[i].getClass();
            }
            //根据方法名和参数列表找到对应的方法
            Method method = obj.getClass().getMethod(message.getMethodName(),classes);
            //反射调用rpc实现类的方法
            Object result = method.invoke(obj,args);
            //将结果写会到rpc返回message，序列化后写入serverSocket的输出流中
            message.setResult(result);
            OutputStream os = client.getOutputStream();
            os.write(MessageUtil.getBao(message).toByteArray());
            os.flush();
            //服务端socket主动断开连接
            client.close();
        }
    }

    public static void main(String[] args) throws Exception{
        MessageHandler handler = new MessageHandler();
        try {
            handler.receiveMessage();
        }catch (Exception e){
            log.debug("",e);
        }
    }
}
