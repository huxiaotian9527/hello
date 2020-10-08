package com.hu.base.jdk.socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * @author hutiantian
 * @date: 2018/7/28 10:57
 * @since 1.0.0
 */
public class Client {

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 8899;
        Socket client = new Socket(host,port);
        Writer writer = new OutputStreamWriter(client.getOutputStream());
        for(int i=0;i<5;i++){
            writer.write("Hello Server."+i);
            writer.flush();//写完后要记得flush
        }
        writer.close();
        client.close();
    }
}
