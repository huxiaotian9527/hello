package com.hu.base.jdk.Socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author hutiantian
 * @create 2018/5/31 15:30
 * @since 1.0.0
 */
public class Server {
    public static void main(String args[]) throws IOException {
        //为了简单起见，所有的异常信息都往外抛
        int port = 8899;
        //定义一个ServerSocket监听在端口8899上
        ServerSocket server = new ServerSocket(port);
        //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
        while (true) {
            Socket socket = server.accept();
            //跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。
            InputStream is = socket.getInputStream();
            //尝试读取返回消息报文
            long startTime = System.nanoTime();				//开始接收时间
            ByteArrayOutputStream baos = new ByteArrayOutputStream();       //返回字节流
            boolean readingFlag = false;
            long lastReadTime = System.nanoTime();		//上次读取报文内容时间
            while(true) {
                if(is.available() > 0) {
                    byte[] tmpBytes = new byte[1024];
                    int ret = is.read(tmpBytes);
                    if(ret == -1) {
                        break;
                    } else {
                        if(ret > 0) {
                            //读取到有效字节
                            lastReadTime = System.nanoTime();
                            readingFlag = true;
                            baos.write(tmpBytes, 0, ret);
                            continue;
                        }
                    }
                }
                long nowTime = System.nanoTime();
                //判断是否已超时
                if(readingFlag) {
                    //已开始接收报文
                    if((nowTime - lastReadTime) / 1000000l > 6) {
                        //指定时间内没有再读取到内容，认为接收完毕
                        break;
                    }
                } else {
                    //未开始接收报文
                    if((nowTime - startTime) > (1000000l * 60000)) {
//                        logger.debug("接受报文超时");
                        break;
                    }
                }
            }

            System.out.println("from client: " + new String(baos.toByteArray(),"utf-8"));
            socket.close();
            server.close();
        }

    }


}
