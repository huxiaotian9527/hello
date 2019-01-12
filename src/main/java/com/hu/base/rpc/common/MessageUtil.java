package com.hu.base.rpc.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * rpc消息工具类
 * @author hutiantian
 * @date: 2019/1/12 15:51
 * @since 1.0.0
 */
public class MessageUtil {

    /**
     * 将message消息转换成字节流
     */
    public static ByteArrayOutputStream getBao(Message message) throws Exception{
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(bao);
        ous.writeObject(message);
        ous.close();
        return bao;
    }

    /**
     * 将字节流转对象
     */
    public static Object getObj(ByteArrayOutputStream bao) throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}
