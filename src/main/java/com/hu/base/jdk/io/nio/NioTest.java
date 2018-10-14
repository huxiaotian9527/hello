package com.hu.base.jdk.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @version 1.0.0
 * @filename NioTest.java
 * @time 2018-3-25 下午5:45:46
 */
public class NioTest {
	public static void main(String[] args) {

	}

	public static void method2(){

		InputStream in = null;
		try{
			in = new BufferedInputStream(new FileInputStream("E:\\a.html"));
			byte [] buf = new byte[5];
			int bytesRead = in.read(buf);
			while(bytesRead != -1)
			{
				for(int i=0;i<bytesRead;i++)
					System.out.print((char)buf[i]);
				bytesRead = in.read(buf);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void method1(){
		RandomAccessFile aFile = null;
		try{
			aFile = new RandomAccessFile("E:\\a.html","rw");
			FileChannel fileChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(1024);

			int bytesRead = fileChannel.read(buf);
			System.out.println(bytesRead);

			while(bytesRead != -1)
			{
				buf.flip();
				while(buf.hasRemaining())
				{
					System.out.print((char)buf.get());
				}

				buf.compact();
				bytesRead = fileChannel.read(buf);
			}
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(aFile != null){
					aFile.close();
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
}
