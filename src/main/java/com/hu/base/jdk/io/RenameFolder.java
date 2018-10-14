package com.hu.base.jdk.io;

import java.io.File;

/**
 * @author Toby
 * @version 1.0.0
 * @filename RenameFolder.java
 * @time 2017-10-15 下午9:02:01
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class RenameFolder {
	public static void main(String[] args) {
		String filePath = "C:\\Users\\h\\Desktop\\需求\\大额存单\\大额存单支取";
		File file = new File(filePath);
		String[] str = file.list();
		for(String s : str){
			String newName = s.replace("开户", "支取");
			new File(filePath+"\\"+s).renameTo(new File(filePath+"\\"+newName));
		}
	}
}
