package com.hu.base.encrypt.response;

/**
 * 文件上传数据验证响应类
 *
 */
public class FileUploadValidationResponse {

	/*
	 * 是否成功
	 */
	public boolean isSuccess = true;
	
	/*
	 * 错误信息
	 */
	private String errorMessage = "";
	
	/*
	 * 解密解压后的文件路径
	 */
	private String decryptFilePath = "";
	
	/*
	 * 解密解压后的文件名
	 */
	private String decryptFileName = "";

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

	public String getDecryptFilePath() {
		return decryptFilePath;
	}

	public void setDecryptFilePath(String decryptFilePath) {
		this.decryptFilePath = decryptFilePath;
	}

	public String getDecryptFileName() {
		return decryptFileName;
	}

	public void setDecryptFileName(String decryptFileName) {
		this.decryptFileName = decryptFileName;
	}
	
	
}
