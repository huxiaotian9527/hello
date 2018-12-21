package com.hu.base.credit.encrypt;

import com.hu.base.credit.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加解密工具类
 *
 */
public class RSAUtil {
	
	//加密算法
	public static final String DEFAULT_ENCRYPT_ALGORITHM = "RSA";
	
	//RSA公钥
	private static java.security.PublicKey rsa_public_key = null;
	
	//RSA私钥
	private static java.security.PrivateKey rsa_private_key = null;
	
	private static Cipher encryptCipher = null;
	
	private static Cipher decryptCipher = null;

	/**
	 * 加载RSAPrivateKey
	 * @param fileName
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey readRSAPrivateKey(String fileName) throws NoSuchAlgorithmException, IOException,
	InvalidKeySpecException {
		InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName));
		BufferedReader br = new BufferedReader(isr);
		StringBuilder builder = new StringBuilder();
		boolean inKey = false;
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			line = line.trim();
			if (!inKey) {
				if (line.startsWith("-----BEGIN ") && line.endsWith(" PRIVATE KEY-----")) {
					inKey = true;
				}
				continue;
			} else {
				if (line.startsWith("-----END ") && line.endsWith(" PRIVATE KEY-----")) {
					inKey = false;
					break;
				}
				builder.append(line);
			}
		}
		br.close();
		isr.close();
		byte[] keyBytes = Base64.base64ToByteArray((builder.toString()));

		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey key = kf.generatePrivate(spec);
		return key;
	}
	
	/**
	 * 加载RSAPublicKey
	 * @param fileName
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey readRSAPublicKey(String fileName)
			throws NoSuchAlgorithmException, IOException,
			InvalidKeySpecException {
		InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName));
		BufferedReader br = new BufferedReader(isr);
		StringBuilder builder = new StringBuilder();
		boolean inKey = false;
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			line = line.trim();
			if (!inKey) {
				if (line.startsWith("-----BEGIN ") && line.endsWith(" PUBLIC KEY-----")) {
					inKey = true;
				}
				continue;
			} else {
				if (line.startsWith("-----END ") && line.endsWith(" PUBLIC KEY-----")) {
					inKey = false;
					break;
				}
				builder.append(line);
			}
		}
		br.close();
		isr.close();
		byte[] keyBytes = Base64.base64ToByteArray((builder.toString()));

		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey key = kf.generatePublic(spec);
		return key;
	}
	
	/**
	 * 初始化
	 * @param publicKey
	 * @param privateKey
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static void init(java.security.PublicKey publicKey,java.security.PrivateKey privateKey) throws NoSuchAlgorithmException,
    NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		if(rsa_public_key == null && publicKey != null){
			rsa_public_key = publicKey;
			encryptCipher = Cipher.getInstance(DEFAULT_ENCRYPT_ALGORITHM);
			encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
		}
		if(rsa_private_key == null && privateKey != null){
			rsa_private_key = privateKey;
			decryptCipher = Cipher.getInstance(DEFAULT_ENCRYPT_ALGORITHM);
			decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
		}
	}
	
	/**
	 * 加密数据
	 * @param dataToEncrypt
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static synchronized byte[] encryptData(byte[] dataToEncrypt) throws IllegalBlockSizeException, BadPaddingException {
		return encryptCipher.doFinal(dataToEncrypt);
	}
	
	/**
	 * 解密数据
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static synchronized byte[] decryptData(byte[] data) throws IllegalBlockSizeException, BadPaddingException {
		return decryptCipher.doFinal(data);
	}
}
