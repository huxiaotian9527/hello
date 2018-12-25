package com.hu.base.encrypt.request;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口上传数据请求类
 *
 */
public class InterfaceUploadRequest {

	/*
	 * 待加密的数据集合 
	 */
	private List<String> data = new ArrayList<String>();

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}
	
	public void addData(String str){
		this.data.add(str);
	}
}
