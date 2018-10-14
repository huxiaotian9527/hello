package com.hu.base.jdk.io;


/**
 * @author Toby
 * @version 1.0.0
 * @filename Test2.java
 * @time 2017-11-7 下午5:41:52
 * @copyright(C) 2015 深圳市北辰德科技有限公司
 */
public class Charset {
	public static void main(String[] args) throws Exception {
		String chinese = "中文 ";//java内部编码
		//按照utf-8编码转字节数组，然后在按ISO-8859-1解码转unicode到内存
		String gbkChinese = new String(chinese.getBytes("utf-8"),"ISO-8859-1");
		System.out.println(gbkChinese);
		//将内存中乱码的字符串按ISO-8859-1转字节数组，在按utf-8解码转unicode到内存
		String unicodeChinese = new String(gbkChinese.getBytes("ISO-8859-1"),"utf-8");
		System.out.println(unicodeChinese);//中文
		String utf8Chinese = new String(unicodeChinese.getBytes("gbk"),"utf-8");
		System.out.println(utf8Chinese);//乱码
		unicodeChinese = new String(utf8Chinese.getBytes("utf-8"),"gbk");
		System.out.println(unicodeChinese);//乱码
	}
}
