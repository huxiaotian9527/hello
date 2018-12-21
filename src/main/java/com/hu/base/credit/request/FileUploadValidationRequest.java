package com.hu.base.credit.request;

/**
 * 文件上传数据验证请求类
 *
 */
public class FileUploadValidationRequest {

	/**
	 * 压缩加密数据文件
	 */
	private String dataFile = "";
	
	/**
	 * 解密解压文件输出路径
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
