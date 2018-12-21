package com.hu.base.credit;

import com.hu.base.credit.encrypt.RSAUtil;
import com.hu.base.credit.request.InterfaceUploadRequest;
import com.hu.base.credit.request.InterfaceUploadValidationRequest;
import com.hu.base.credit.response.InterfaceUploadResponse;
import com.hu.base.credit.response.InterfaceUploadValidationResponse;
import com.hu.base.credit.util.Base64;

import java.util.List;

public class BhCreditApiClient {
	
	private static final String CHARSET = "UTF-8";
	
	//RSA公钥
	private static java.security.PublicKey rsa_public_key = null;
	
	//RSA私钥
	private static java.security.PrivateKey rsa_private_key = null;
	
	//AES密钥
	private static String aes_key = null;
	
	/**
	 * 初始化
	 * @param rsa_public_key
	 * @param aes_key
	 */
	public void init(java.security.PublicKey rsa_public_key,String aes_key){
		if(this.rsa_public_key == null){
			this.rsa_public_key = rsa_public_key;
		}
		if(this.aes_key == null){
			this.aes_key = aes_key;
		}
	}

	/**
	 * 初始化
	 * @param rsa_public_key
	 */
	public void init(java.security.PublicKey rsa_public_key){
		if(this.rsa_public_key == null){
			this.rsa_public_key = rsa_public_key;
		}
	}
	
	/**
	 * 初始化
	 * @param rsa_private_key
	 */
	public void init(java.security.PrivateKey rsa_private_key){
		if(this.rsa_private_key == null){
			this.rsa_private_key = rsa_private_key;
		}
	}
	
	/**
	 * 接口数据加密
	 * @param req
	 * @return
	 */
	public InterfaceUploadResponse execute(InterfaceUploadRequest req){
		InterfaceUploadResponse response = new InterfaceUploadResponse();
		try{
			RSAUtil.init(rsa_public_key, null);
			List<String> datas = req.getData();
			for(int i=0;i<datas.size();i++){
				byte[] bytes = RSAUtil.encryptData(datas.get(i).getBytes(CHARSET));
				response.addEncryptData(Base64.byteArrayToBase64(bytes));
			}
		}catch(Exception e){
			response.setSuccess(false);
			response.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * 接口数据解密
	 * @param req
	 * @return
	 */
	public InterfaceUploadValidationResponse execute(InterfaceUploadValidationRequest req){
		InterfaceUploadValidationResponse response = new InterfaceUploadValidationResponse();
		try{
			RSAUtil.init(null, rsa_private_key);
			List<String> datas = req.getData();
			for(int i=0;i<datas.size();i++){
				byte[] bytes = RSAUtil.decryptData(Base64.base64ToByteArray(datas.get(i)));
				response.addDecryptData(new String(bytes,CHARSET));
			}
		}catch(Exception e){
			response.setSuccess(false);
			response.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
}
