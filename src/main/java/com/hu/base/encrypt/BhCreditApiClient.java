package com.hu.base.encrypt;

import com.hu.base.encrypt.util.AESUtil;
import com.hu.base.encrypt.util.RSAUtil;
import com.hu.base.encrypt.request.FileUploadRequest;
import com.hu.base.encrypt.request.FileUploadValidationRequest;
import com.hu.base.encrypt.request.InterfaceUploadRequest;
import com.hu.base.encrypt.request.InterfaceUploadValidationRequest;
import com.hu.base.encrypt.response.FileUploadResponse;
import com.hu.base.encrypt.response.FileUploadValidationResponse;
import com.hu.base.encrypt.response.InterfaceUploadResponse;
import com.hu.base.encrypt.response.InterfaceUploadValidationResponse;
import com.hu.base.encrypt.util.Base64;
import com.hu.base.encrypt.util.ZipUtil;

import java.io.*;
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
	/**
	 * 文件压缩和加密
	 * @param req
	 * @return
	 */
	public FileUploadResponse execute(FileUploadRequest req){
		FileUploadResponse response = new FileUploadResponse();
		File srcFile = new File(req.getDataFile());
		if(!srcFile.exists()){
			response.setSuccess(false);
			response.setErrorMessage("待压缩加密的文件不存在");
			return response;
		}
		if(req.getTargetFilePath().equals("")){
			req.setTargetFilePath(srcFile.getParent());
		}
		File zipFile = null;
		FileInputStream in = null;
		PrintWriter pw = null;
		try{
			//压缩后的zip数据文件
			zipFile = new File(req.getTargetFilePath()+ File.separator +srcFile.getName().substring(0,srcFile.getName().lastIndexOf("."))+".zip");
			//压缩文件
			response = ZipUtil.zipFile(new File(req.getDataFile()),zipFile);
			//压缩失败
			if(!response.isSuccess()){
				return response;
			}
			//设置压缩加密后的文件路径
			response.setEncryptFilePath(req.getTargetFilePath());
			//设置压缩加密后的文件名
			response.setEncryptFileName(srcFile.getName().substring(0,srcFile.getName().lastIndexOf("."))+".cry");
			//压缩文件
			File encryptFile = new File(response.getEncryptFilePath()+ File.separator +response.getEncryptFileName());
			if(encryptFile.exists()){
				encryptFile.delete();
			}
			pw = new PrintWriter(new FileWriter(encryptFile,true));
			//初始化RSA公钥
			RSAUtil.init(rsa_public_key, null);
			//RSA加密
			byte[] bytes = RSAUtil.encryptData(aes_key.getBytes());
			String aes_key_str = Base64.byteArrayToBase64(bytes);
			//将ASE密钥加密后放入加密文件的第一行
			pw.println(aes_key_str);
			//将压缩文件的数据报文加密后放入加密文件
			in = new FileInputStream(zipFile);
			int len;
			byte[] buf = new byte[1024];
			while ((len = in.read(buf)) > 0) {
				pw.println(Base64.byteArrayToBase64(AESUtil.encryptData(aes_key, buf)));
			}
			pw.flush();
		}catch (Exception e) {
			response.setSuccess(false);
			response.setErrorMessage(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (pw != null)
					pw.close();
				if(zipFile.exists()){
					zipFile.delete();
		        }
			} catch (Exception e) {


				e.printStackTrace();
			}
		}
		return response;
	}

	/**
	 * 文件解密和解压
	 * @param req
	 * @return
	 */
	public FileUploadValidationResponse execute(FileUploadValidationRequest req){
		FileUploadValidationResponse response = new FileUploadValidationResponse();
		File srcFile = new File(req.getDataFile());
		if(!srcFile.exists()){
			response.setSuccess(false);
			response.setErrorMessage("待解密解压的文件不存在");
			return response;
		}
		if(req.getTargetFilePath().equals("")){
			req.setTargetFilePath(srcFile.getParent());
		}
		File zipFile = null;
		FileOutputStream out = null;
		BufferedReader br = null;
		try{
			//解密后的压缩文件
			zipFile = new File(req.getTargetFilePath()+ File.separator +srcFile.getName().substring(0,srcFile.getName().lastIndexOf("."))+".zip");
			response.setDecryptFilePath(req.getTargetFilePath());
			response.setDecryptFileName(srcFile.getName().substring(0,srcFile.getName().lastIndexOf("."))+".txt");
			if(zipFile.exists()){
				zipFile.delete();
			}
			out = new FileOutputStream(zipFile);
			br = new BufferedReader(new FileReader(srcFile));
			//加密文件的第一行为AES密钥经过RSA加密后的报文
			String aes_key_encrypt = br.readLine();
			if(aes_key_encrypt==null || aes_key_encrypt.equals("")){
				response.setSuccess(false);
				response.setErrorMessage("待解密解压的文件内容不符合规范");
				return response;
			}
			RSAUtil.init(null, rsa_private_key);
			//AES密钥明文
			String aes_key_str = new String(RSAUtil.decryptData(Base64.base64ToByteArray(aes_key_encrypt)));
			String str = br.readLine();
			while(str!=null){
				out.write(AESUtil.decryptData(aes_key_str, Base64.base64ToByteArray(str)));
				str = br.readLine();
			}
			out.flush();
			out.close();
			FileUploadResponse fileUploadResponse = ZipUtil.unzipFile(zipFile,req.getTargetFilePath());
			response.setSuccess(fileUploadResponse.isSuccess());
			response.setErrorMessage(fileUploadResponse.getErrorMessage());
		}catch (Exception e) {
			response.setSuccess(false);
			response.setErrorMessage(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (br != null)
					br.close();
				if(zipFile.exists()){
					zipFile.delete();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return response;
	}
}
