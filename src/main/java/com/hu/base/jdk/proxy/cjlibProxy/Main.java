package com.hu.base.jdk.proxy.cjlibProxy;

import com.hu.base.jdk.proxy.StudentService;
import com.hu.base.jdk.proxy.StudentServiceImp;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author Toby
 * @version 1.0.0
 * @time 2017-10-29 12:20:30
 */
public class Main {
	   public static void main(String[] args) {  
	        com.hu.base.jdk.proxy.cjlibProxy.CglibProxy cglibProxy = new com.hu.base.jdk.proxy.cjlibProxy.CglibProxy();
	        Enhancer enhancer = new Enhancer();  
	        enhancer.setSuperclass(StudentServiceImp.class);
	        enhancer.setCallback(cglibProxy);  
	        StudentService o = (StudentService)enhancer.create();
	        System.out.println(o.getName());  
	        System.out.println(o.getAge());
	    }  
}
