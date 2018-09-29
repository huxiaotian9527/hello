package com.hu.base.http.util.iqianjin;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public abstract class AESUtils
{
  public static final String KEY_ALGORITHM = "AES";
  public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

  private static Key toKey(byte[] key)
    throws Exception
  {
    SecretKey secretKey = new SecretKeySpec(key, "AES");
    return secretKey;
  }

  public static byte[] decrypt(byte[] data, byte[] key)
    throws Exception
  {
	  Key k = toKey(key);

    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

    cipher.init(2, k);

    return cipher.doFinal(data);
  }

  public static String decrypt(String data, String key)
    throws Exception
  {
    byte[] decrypt = decrypt(Base64.decodeBase64(data), Base64.decodeBase64(key));
    return new String(decrypt);
  }

  public static byte[] encrypt(byte[] data, byte[] key)
    throws Exception
  {
    Key k = toKey(key);

    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipher.init(1, k);

    return cipher.doFinal(data);
  }
  
  public static String encrypt(String content, String key) throws Exception {
      // ���÷�����
      byte[] aesText = AESUtils.encrypt(content.getBytes(), Base64Utils.decodeBase64(key.getBytes()));  // ��AES����
      byte[] base64Text = Base64Utils.encodeBase64(aesText);  // ��Base64����
      String result = new String(base64Text);
      return result;
  }

  public static byte[] initKey()
    throws Exception
  {
    KeyGenerator kg = KeyGenerator.getInstance("AES");

    kg.init(128);

    SecretKey secretKey = kg.generateKey();

    return secretKey.getEncoded();
  }

}