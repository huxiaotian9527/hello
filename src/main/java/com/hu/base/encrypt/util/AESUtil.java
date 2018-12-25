package com.hu.base.encrypt.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * AES加解密工具类
 *
 */
public class AESUtil {

	private static Cipher encryptCipher = null;
	
	private static Cipher decryptCipher = null;
	
	public static void initEncryptCipher(String aes_key) throws Exception {
		if(encryptCipher == null){
			//1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator kgen = KeyGenerator.getInstance("AES");//
            //2.利用用户密码作为随机数初始化出
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(aes_key.getBytes());
            kgen.init(128, secureRandom);
            //3.产生原始对称密钥
            SecretKey secretKey = kgen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] enCodeFormat = secretKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            //6.根据指定算法AES自成密码器
            encryptCipher = Cipher.getInstance("AES");
            //7.初始化密码器
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		}
	}
	
	public static void initDecryptCipher(String aes_key) throws Exception {
		if(decryptCipher == null){
			//1.构造密钥生成器，指定为AES算法,不区分大小写
	        KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
	        //2.利用用户密码作为随机数初始化出
	        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
	        secureRandom.setSeed(aes_key.getBytes());
	        kgen.init(128, secureRandom);
	        //3.产生原始对称密钥
	        SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
	        //4.获得原始对称密钥的字节数组
	        byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
	        //5.根据字节数组生成AES密钥
	        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
	        //6.根据指定算法AES自成密码器
	        decryptCipher = Cipher.getInstance("AES");// 创建密码器
	        //7.初始化密码器
	        decryptCipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
		}
	}
	
	/**
     * AES加密字符串
     */
    public static synchronized byte[] encryptData(String aes_key,byte[] data) throws Exception{
    	initEncryptCipher(aes_key);
        //获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
        //byte[] byteContent = content.getBytes("utf-8");
        //加密：将数据加密
        byte[] result = encryptCipher.doFinal(data);
        return result;
    }
    
    /**
     * 解密AES加密过的字符串
     */
    public static synchronized byte[] decryptData(String aes_key,byte[] data) throws Exception{
    	initDecryptCipher(aes_key);
    	//解密：将数据解密
        byte[] resultByte = decryptCipher.doFinal(data);  
        return resultByte; // 明文   
    }
}
