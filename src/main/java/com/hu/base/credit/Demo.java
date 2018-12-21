package com.hu.base.credit;

import com.hu.base.credit.encrypt.RSAUtil;
import com.hu.base.credit.request.InterfaceUploadRequest;
import com.hu.base.credit.request.InterfaceUploadValidationRequest;
import com.hu.base.credit.response.InterfaceUploadResponse;
import com.hu.base.credit.response.InterfaceUploadValidationResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 示例代码
 *
 */
public class Demo {

	public static void main(String args[]) throws Exception{
		//接口方式上报的客户端代码
		interfaceUploadClient();
		
		//接口方式上报的客户端本地测试代码
		//interfaceUploadValidationClient();
	}
	
	//接口方式上报的客户端代码
	public static void interfaceUploadClient() throws Exception{
		//RSA公钥文件路径
		String RSA_PUBLIC_KEY = "d:\\rsa_public_key.pem";

		InterfaceUploadRequest req = new InterfaceUploadRequest();
		List<String> datas = new ArrayList<String>();
		String name = "张三";
		String pid = "31010119900101001X";
		String mobile= "13812345678";
		datas.add(name);
		datas.add(pid);
		datas.add(mobile);
		//设置待加密的原始数据集合
		req.setData(datas);
		BhCreditApiClient client = new BhCreditApiClient();
		//初始化设置RSA公钥
		client.init(RSAUtil.readRSAPublicKey(RSA_PUBLIC_KEY));
		//执行加密操作
		InterfaceUploadResponse response = client.execute(req);
		if(response.isSuccess){
			List<String> data = response.getEncryptData();
			for(int i=0;i<data.size();i++){
				System.out.println(data.get(i));
			}
			System.out.println("encrypt success;encrypt data = "+response.getEncryptData());
		}else{
			System.out.println("encrypt fail;errorMessage = "+response.getErrorMessage());
		}
	}
	
	//接口方式上报的客户端本地测试代码
	public static void interfaceUploadValidationClient() throws Exception{
		//RSA私钥文件路径（百行提供测试环境私钥文件）
		String RSA_PRIVATE_KEY = "d:\\rsa_private_key.pem";

		InterfaceUploadValidationRequest req = new InterfaceUploadValidationRequest();
		List<String> datas = new ArrayList<String>();
		String name = "EhgcpppDjim8jT8BQZ+k1LN0ishFwv2awzutl3+xWnirjuZD3Hjv9Hi08hayQrTfOuiDhetYScXRdpp5zRIaw+9JfAR2K6f6CFg7PTJpFtrLWItsBUCkzBx2KRkBe7ZGMAeXGK6FkLX+rSstOgjAMd3U7eIDhQk/z9BJilH2KP4=";//加密后的密文
		String pid = "DPJkRBMLea49Em9rsmo8eNtOwL9Unfc9JyNzkskFJB7seGvHqnyc3wpq/tJZindYJ9Lh71bJGTstfxD/jAllnNsk8Q6v7rk1WMsa2zS/l9s7Ct+A1UE5gYgkzDnOIaxrnaWzT3M34LeRirJp7yLCyq1SmvSObxCVC8maz4U+vdM=";//加密后的密文
		String mobile= "YNpkcIRRlCNsnIjD/Aynn1HZYQIpyprSvEAwzLaE1YqaowsR8q/WV141hx7Qzrlo6dyyWKKotfMtkOIfLns7662os0tiL0tb48qBNUL//apBVoNLcm0GuIi1gFEFIDdyaOjrYMupNpBA4DCnEiuxItBH/mbCQZxzV7p+/D9kQGs=";//加密后的密文
		datas.add(name);
		datas.add(pid);
		datas.add(mobile);
		//设置待解密的密文数据集合
		req.setData(datas);
		BhCreditApiClient client = new BhCreditApiClient();
		//初始化设置RSA私钥
		client.init(RSAUtil.readRSAPrivateKey(RSA_PRIVATE_KEY));
		//执行解密操作
		InterfaceUploadValidationResponse response = client.execute(req);
		if(response.isSuccess){
			List<String> data = response.getDecryptData();
			for(int i=0;i<data.size();i++){
				System.out.println(data.get(i));
			}
			System.out.println("decrypt success;decrypt data = "+response.getDecryptData());
		}else{
			System.out.println("decrypt fail;errorMessage = "+response.getErrorMessage());
		}
	}
}
