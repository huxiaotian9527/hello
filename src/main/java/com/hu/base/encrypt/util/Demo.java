package com.hu.base.encrypt.util;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long time1 = System.currentTimeMillis();
		//ftpUpLoad();
		//ftpDown();
		//ftpsUpLoad();
		//ftpsDown();
		//sftpUpLoad();
		sftpDown();
		System.out.println("耗时："+(System.currentTimeMillis()-time1));
	}

	/**
	 * FTP上传文件
	 */
	public static void ftpUpLoad(){ 
		boolean flag = FTPUtil.uploadFile("127.0.0.1", 21,"ftpuser","123456","/data/ftp","data.txt","D:\\data.txt");
		System.out.println(flag); 
	}
		
	/**
	 * FTP下载文件
	 */
	public static void ftpDown(){
		boolean flag = FTPUtil.downFile("127.0.0.1", 21,"ftpuser","123456", "/data/ftp","data.txt","D:\\data.txt"); 
		System.out.println(flag);  
	}
	
	/**
	 * FTPS上传文件
	 */
	public static void ftpsUpLoad(){ 
		boolean flag = FTPSUtil.uploadFile("127.0.0.1","ftpuser","111111","你好/乐信控股有限公司/creditdatafile/data/ftp","c1-共50W笔（正常情况）.txt","D:\\c1-共50W笔（正常情况）.txt");
		System.out.println(flag);
	}
	
	/**
	 * FTPS下载文件
	 */
	public static void ftpsDown(){ 
		boolean flag = FTPSUtil.downFile("127.0.0.1", 21, "ftpuser", "111111", "你好/乐信控股有限公司/creditdatafile/data/ftp","c1-共50W笔（正常情况）.txt","D:\\c1-共50W笔（正常情况）.txt"); 
		System.out.println(flag);  
	}
	
	/**
	 * SFTP上传文件
	 */
	public static void sftpUpLoad(){ 
		boolean flag = SFTPUtil.uploadFile("127.0.0.1", 990,"ftpuser","123456","/data/ftp","data.txt","D:\\data.txt");
		System.out.println(flag);
	}
	
	/**
	 * SFTP下载文件
	 */
	public static void sftpDown(){ 
		boolean flag = SFTPUtil.downFile("127.0.0.1", 990,"ftpuser","123456", "/data/ftp","data.txt","D:\\data.txt"); 
		System.out.println(flag);  
	}
	
}
