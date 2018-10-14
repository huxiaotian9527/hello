package com.hu.base.jdk.io;

import java.io.File;
import java.io.FileFilter;

/**
 * @author Toby
 * @version 1.0.0
 * @filename UploadNexus.java
 * @time 2017-11-7 下午5:06:35
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class UploadNexus {
	public static void main(String[] args) {
		String localRepositoryUrl = "E:\\java\\apache-maven-3.2.2\\repository";
		File file = new File(localRepositoryUrl);
		String remoteRepositoryUrl = "http://127.0.0.1:8081/repository/sdnx/";
		String serverId = "toby";
		analyzeFile(file,remoteRepositoryUrl,serverId);
	}
	
	public static void analyzeFile(File file,String remoteRepositoryUrl,String serverId){
		File[] flist = file.listFiles(new FileFilter(){
			public boolean accept(File file){
				if(file.isDirectory()||file.getName().endsWith(".jar")){
					return true;
				}
				return false;
			}
		});
		for(File f:flist){
			if(f.isDirectory()){
				analyzeFile(f,remoteRepositoryUrl,serverId);
			}else{
				System.out.println(f.getName());
			}
		}
	}
	/**
	 * 调用cmd执行mvn命令，将jar上传至nexus
	 * 具体调用参考Runtime.getRuntime().exec(command).waitFor()方法顺序执行实现。
	 */
	public void up(){
//		mvn deploy:deploy-file -DgroupId=c3p0 
//		-DartifactId=c3p0 -Dversion=0.9.1.3 -Dpackaging=jar 
//				-Dfile=E:\c3p0-0.9.1.3.jar 
//				-Durl=http://127.0.0.1:8081/repository/sdnx/ -DrepositoryId=toby -e
	}
}
