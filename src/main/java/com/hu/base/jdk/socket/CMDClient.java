package com.hu.base.jdk.socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class CMDClient {
    public static void main(String[] args) throws IOException {
        String host1 = "192.168.26.38";
        String host2 = "10.109.129.28";
        int port = 8899;
        if(isHostReachable(host1,100)){
            try {
                Socket client = new Socket(host1, port);
                Writer writer = new OutputStreamWriter(client.getOutputStream());
                writer.write("oa");
                writer.flush();//写完后要记得flush
                writer.close();
                client.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }else {
            try {
                Socket client = new Socket(host2, port);
                Writer writer = new OutputStreamWriter(client.getOutputStream());
                writer.write("wifi");
                writer.flush();//写完后要记得flush
                writer.close();
                client.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public static boolean isHostReachable(String host, Integer timeOut) {
        try {
            return InetAddress.getByName(host).isReachable(timeOut);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
