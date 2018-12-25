package com.hu.base.encrypt.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class SFTPUtil {

	/**
	 * SFTP上传文件
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
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			Session sshSession = jsch.getSession(username, server, port);
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp)channel;  
			File file = new File(local);
			sftp.cd(path);
			sftp.put(new FileInputStream(file), remote);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
            try {
            	if(sftp!=null && sftp.getSession() !=null && sftp.getSession().isConnected()){
            		sftp.getSession().disconnect();
            	}
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(sftp!=null && sftp.isConnected()){
            	sftp.disconnect();
            	sftp.exit();
            }
        }
		return success;
	}
	
	/**
	 * SFTP下载文件
	 * @param server：FTP服务器地址
	 * @param port：FTP服务器端口
	 * @param username：FTP用户名
	 * @param password：FTP密码
	 * @param path：FTP服务器目录
	 * @param remote：FTP服务器文件名
	 * @param local：本地文件路径
	 * @return
	 */
	public static boolean downFile(String server,int port,String username, String password, String path, String remote, String local) { 
		boolean success = false;
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			Session sshSession = jsch.getSession(username, server, port);
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp)channel;  
			File file = new File(local);
			sftp.cd(path);
            File localFile = new File(local); 
			OutputStream os = new FileOutputStream(localFile);
			sftp.get(remote, os);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
            try {
            	if(sftp!=null && sftp.getSession() !=null && sftp.getSession().isConnected()){
            		sftp.getSession().disconnect();
            	}
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(sftp!=null && sftp.isConnected()){
            	sftp.disconnect();
            	sftp.exit();
            }
        }
		return success;
	}
}
