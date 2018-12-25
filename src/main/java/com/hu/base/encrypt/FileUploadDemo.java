package com.hu.base.encrypt;

import com.hu.base.encrypt.util.RSAUtil;
import com.hu.base.encrypt.request.FileUploadRequest;
import com.hu.base.encrypt.request.FileUploadValidationRequest;
import com.hu.base.encrypt.response.FileUploadResponse;
import com.hu.base.encrypt.response.FileUploadValidationResponse;

/**
 * 示例代码
 *
 */
public class FileUploadDemo {

	public static void main(String args[]) throws Exception{
		//文件方式上报的客户端代码
		fileUploadClient();
		
		//文件方式上报的客户端本地测试代码
		//fileUploadValidationClient();

	}
	
	//文件方式上报的客户端代码
	public static void fileUploadClient() throws Exception{
		//RSA公钥文件路径（公钥文件）
		String RSA_PUBLIC_KEY = "d:\\rsa_public_key.pem";
		//AES密钥（机构自行设置）
		String AES_KEY = "123456";

		FileUploadRequest req = new FileUploadRequest();
		//设置未作压缩加密前的原始数据文件路径
		req.setDataFile("d:\\测试.txt");
		//设置压缩加密后的密文文件输出路径(为空表示与原始数据文件同目录)
		req.setTargetFilePath("d:\\");

		BhCreditApiClient client = new BhCreditApiClient();
		//初始化设置AES密钥和RSA公钥
		client.init(RSAUtil.readRSAPublicKey(RSA_PUBLIC_KEY),AES_KEY);
		//执行压缩加密操作
		FileUploadResponse response = client.execute(req);
		if(response.isSuccess){
			System.out.println("zip And encrypt success;fileName = "+response.getEncryptFilePath() + response.getEncryptFileName());
		}else{
			System.out.println("zip And encrypt fail;errorMessage = "+response.getErrorMessage());
		}
	}

	//文件方式上报的客户端本地测试代码
	public static void fileUploadValidationClient() throws Exception{
		//RSA私钥文件路径
		String RSA_PRIVATE_KEY = "d:\\rsa_private_key.pem";
		FileUploadValidationRequest req = new FileUploadValidationRequest();
		//设置压缩加密后的数据文件路径
		req.setDataFile("d:\\测试.cry");
		//设置解密解压的原始文件输出路径(为空表示与加密数据文件同目录)
		req.setTargetFilePath("d:\\");

		BhCreditApiClient client = new BhCreditApiClient();
		//初始化设置RSA私钥
		client.init(RSAUtil.readRSAPrivateKey(RSA_PRIVATE_KEY));
		//执行解密解压操作
		FileUploadValidationResponse response = client.execute(req);
		if(response.isSuccess){
			System.out.println("decrypt And unzip success;fileName = "+response.getDecryptFilePath() + response.getDecryptFileName());
		}else{
			System.out.println("decrypt And unzip fail;errorMessage = "+response.getErrorMessage());
		}
	}
}
