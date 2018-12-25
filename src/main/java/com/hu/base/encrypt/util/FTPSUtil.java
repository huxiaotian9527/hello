package com.hu.base.encrypt.util;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import java.io.*;

/**
 * FTPS工具类
 *
 */
public final class FTPSUtil
{	
	/**
	 * FTPS上传文件
	 * @param server：FTP服务器地址
	 * @param username：FTP用户名
	 * @param password：FTP密码
	 * @param path：文件上传到FTP服务器后，存放文件的目录
	 * @param remote：文件上传到FTP服务器后，存放文件的文件名
	 * @param local：本地待上传的文件路径
	 * @return
	 */
	public static boolean uploadFile(String server,String username,String password,String path,String remote,String local){
		boolean success = false;
        String protocol = "SSL";    // SSL/TLS
        FTPSClient ftps = new FTPSClient(protocol);
        ftps.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        try{
            ftps.connect(server);
            int reply = ftps.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)){
                ftps.disconnect();
                return success;
            }
            ftps.setBufferSize(1000);
            if (!ftps.login(username, password)){
                ftps.logout();
                return success;
            }
            ftps.enterLocalPassiveMode();
            ftps.setFileType(FTP.BINARY_FILE_TYPE);
            ftps.enterLocalPassiveMode();
            InputStream input = new FileInputStream(local);
            ftps.changeWorkingDirectory(new String(path.getBytes("UTF-8"),"iso-8859-1")); 
            ftps.storeFile(new String(remote.getBytes("UTF-8"),"iso-8859-1"), input);
            input.close();
            ftps.logout();
            success = true; 
        }catch (Exception e){
            if (ftps.isConnected()){
                try{
                    ftps.disconnect();
                }catch (IOException f){
                   f.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally{
            if (ftps.isConnected()){
                try{
                    ftps.disconnect();
                }catch (IOException f){
                   f.printStackTrace();
                }
            }
        }
        return success;
    } 
	
	/**
	 * FTPS下载文件
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
        String protocol = "SSL";    // SSL/TLS
        FTPSClient ftps = new FTPSClient(protocol);
        ftps.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        try{
            ftps.connect(server);
            int reply = ftps.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)){
                ftps.disconnect();
                return success;
            }
            ftps.setBufferSize(1000);
            if (!ftps.login(username, password)){
                ftps.logout();
                return success;
            }
            ftps.enterLocalPassiveMode();
            ftps.setFileType(FTP.BINARY_FILE_TYPE);
            ftps.enterLocalPassiveMode();
            ftps.changeWorkingDirectory(new String(path.getBytes("UTF-8"),"iso-8859-1"));//转移到FTP服务器目录 
            File localFile = new File(local); 
			OutputStream is = new FileOutputStream(localFile); 
			ftps.retrieveFile(new String(remote.getBytes("UTF-8"),"iso-8859-1"), is); 
			is.close(); 
			ftps.logout(); 
			success = true; 
        }catch (Exception e){
            if (ftps.isConnected()){
                try{
                    ftps.disconnect();
                }catch (IOException f){
                   f.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally{
            if (ftps.isConnected()){
                try{
                    ftps.disconnect();
                }catch (IOException f){
                   f.printStackTrace();
                }
            }
        }
        return success;
    }
}