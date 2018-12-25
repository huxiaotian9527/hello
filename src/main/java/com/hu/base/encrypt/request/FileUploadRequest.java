package com.hu.base.encrypt.request;

/**
 * 文件上传数据请求类
 *
 */
public class FileUploadRequest {

	/**
	 * 原始数据文件
	 */
	private String dataFile = "";
	
	/**
	 * 密文文件输出路径
	 */
	private String targetFilePath = "";

	public String getDataFile() {
		return dataFile;
	}

	public void setDataFile(String dataFile) {
		this.dataFile = dataFile;
	}

	public String getTargetFilePath() {
		return targetFilePath;
	}

	public void setTargetFilePath(String targetFilePath) {
		this.targetFilePath = targetFilePath;
	}
	
	
}
