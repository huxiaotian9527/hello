package com.hu.base.encrypt.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * FTP工具类
 *
 */
public class FTPUtil {

	/**
	 * FTP上传文件
	 * @param server：FTP服务器地址
	 * @param port：FTP服务器端口
	 * @param username：FTP用户名
	 * @param password：FTP密码
	 * @param path：FTP服务器目录
	 * @param remote：FTP服务器文件名
	 * @param local：本地文件路径
	 * @return
	 */
	public static boolean uploadFile(String server,int port,String username, String password, String path, String remote, String local) { 
		boolean success = false;
		FTPClient ftp = new FTPClient(); 
		try { 
			int reply; 
			ftp.connect(server, port);//连接FTP服务器 
			ftp.login(username, password);//登录 
			reply = ftp.getReplyCode(); 
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success; 
			} 
			InputStream input = new FileInputStream(local);
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.changeWorkingDirectory(new String(path.getBytes("UTF-8"),"iso-8859-1"));
			ftp.storeFile(new String(remote.getBytes("UTF-8"),"iso-8859-1"), input); 
			input.close(); 
			ftp.logout(); 
			success = true; 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} finally { 
			if (ftp.isConnected()) { 
				try { 
					ftp.disconnect(); 
				}catch (IOException ioe) { 
				} 
			} 
		} 
		return success; 
	}

	/**
	 * FTP下载文件
	 * @param server：FTP服务器地址
	 * @param port：FTP服务器端口
	 * @param username：FTP用户名
	 * @param password：FTP密码
	 * @param path：FTP服务器目录
	 * @param remote：FTP服务器文件名
	 * @param local：本地文件路径
	 * @return
	 */
	public static boolean downFile(String server, int port,String username, String password, String path, String remote, String local) { 
		boolean success = false; 
		FTPClient ftp = new FTPClient(); 
		try { 
			int reply; 
			ftp.connect(server, port); 
			ftp.login(username, password);//登录 
			reply = ftp.getReplyCode(); 
			if (!FTPReply.isPositiveCompletion(reply)) { 
				ftp.disconnect();
				return success; 
			}
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.changeWorkingDirectory(new String(path.getBytes("UTF-8"),"iso-8859-1"));//转移到FTP服务器目录 
			File localFile = new File(local); 
			OutputStream is = new FileOutputStream(localFile); 
			ftp.retrieveFile(new String(remote.getBytes("UTF-8"),"iso-8859-1"), is); 
			is.close(); 
			ftp.logout(); 
			success = true; 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} finally { 
			if (ftp.isConnected()) { 
				try { 
					ftp.disconnect(); 
				} catch (IOException ioe) { 
				} 
			} 
		} 
		return success; 
	}
}
