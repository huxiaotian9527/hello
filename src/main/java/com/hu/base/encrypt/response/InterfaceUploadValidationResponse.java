package com.hu.base.encrypt.response;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口上传数据验证响应类
 *
 */
public class InterfaceUploadValidationResponse {

	/*
	 * 是否成功
	 */
	public boolean isSuccess = true;
	
	/*
	 * 错误信息
	 */
	private String errorMessage = "";
	
	/*
	 * 解密后的数据集合 
	 */
	private List<String> decryptData = new ArrayList<String>();

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

	public List<String> getDecryptData() {
		return decryptData;
	}

	public void setDecryptData(List<String> decryptData) {
		this.decryptData = decryptData;
	}
	
	public void addDecryptData(String str){
		this.decryptData.add(str);
	}
}
