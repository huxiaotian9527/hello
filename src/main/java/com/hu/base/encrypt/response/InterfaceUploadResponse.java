package com.hu.base.encrypt.response;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口上传数据响应类
 *
 */
public class InterfaceUploadResponse {

	/*
	 * 是否成功
	 */
	public boolean isSuccess = true;
	
	/*
	 * 错误信息
	 */
	private String errorMessage = "";
	
	/*
	 * 加密后的数据集合
	 */
	private List<String> encryptData = new ArrayList<String>();

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<String> getEncryptData() {
		return this.encryptData;
	}

	public void setEncryptData(List<String> encryptData) {
		this.encryptData = encryptData;
	}
	
	public void addEncryptData(String str) {
		this.encryptData.add(str);
	}
}
