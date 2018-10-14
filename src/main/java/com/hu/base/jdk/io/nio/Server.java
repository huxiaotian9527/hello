package com.hu.base.jdk.io.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @version 1.0.0
 * @filename Server.java
 * @time 2018-3-25 下午5:48:16
 */
public class Server {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		InputStream in = null;
		try
		{
			serverSocket = new ServerSocket(8089);
			int recvMsgSize = 0;
			byte[] recvBuf = new byte[1024];
			while(true){
				Socket clntSocket = serverSocket.accept();
				in = clntSocket.getInputStream();
				while((recvMsgSize=in.read(recvBuf))!=-1){
					byte[] temp = new byte[recvMsgSize];
					System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
					System.out.println(new String(temp));
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally{
			try{
				if(serverSocket!=null){
					serverSocket.close();
				}
				if(in!=null){
					in.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
