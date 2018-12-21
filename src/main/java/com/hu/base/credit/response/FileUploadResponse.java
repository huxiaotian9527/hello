package com.hu.base.credit.response;

/**
 * 文件上传数据响应类
 *
 */
public class FileUploadResponse {
	
	/*
	 * 是否成功
	 */
	public boolean isSuccess = true;
	
	/*
	 * 错误信息
	 */
	private String errorMessage = "";
	
	/*
	 * 压缩加密后的文件路径
	 */
	private String encryptFilePath = "";
	
	/*
	 * 压缩加密后的文件名
	 */
	private String encryptFileName = "";

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

	public String getEncryptFilePath() {
		return encryptFilePath;
	}

	public void setEncryptFilePath(String encryptFilePath) {
		this.encryptFilePath = encryptFilePath;
	}

	public String getEncryptFileName() {
		return encryptFileName;
	}

	public void setEncryptFileName(String encryptFileName) {
		this.encryptFileName = encryptFileName;
	}
}
