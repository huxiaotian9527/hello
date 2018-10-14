package com.hu.base.jdk.io;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Toby
 * @version 1.0.0
 * @filename FileFilter.java
 * @time 2017-11-11 下午7:49:09
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class FileFilter {
	public static void main(String[] args) throws Exception{
		File file = new File("D:\\安装包");
		ArrayList<File> list = new ArrayList<File>();
		FileFilter.getDirectory(file,list);
		for(File f:list){
			System.out.println(f.getName());
		}
	}

	static void getDirectory(File file,ArrayList<File> list) {  
		File flist[] = file.listFiles(new java.io.FileFilter(){
			public boolean accept(File file){
				if(file.isDirectory()||file.getName().endsWith(".exe")){
					return true;
				}
				return false;
			}
		});  
		for (File f : flist) {  
			if (f.isDirectory()) {  
				//这里将列出所有的文件夹  
//				System.out.println("Dir==>" + f.getAbsolutePath());   
				getDirectory(f,list);  
			} else {  
				//这里将列出所有的文件  
//				System.out.println("file==>" + f.getAbsolutePath());  
				list.add(f);
			}  
		}  
	}  
}
