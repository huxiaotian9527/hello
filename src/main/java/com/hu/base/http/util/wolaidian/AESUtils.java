package com.hu.base.http.util.wolaidian;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;

/**
 * AES鍔犲瘑瑙ｅ瘑瀹夊叏缁勪欢
 * <p>
 * <p>
 * Created by guanlichang@kingdee.com on 2017/8/15.
 * </p>
 **/
public abstract class AESUtils {

    /**
     * 缂栫爜鏂瑰紡
     */
    public static final String EN_CODE = "UTF-8";

    /**
     * 绉橀挜绠楁硶
     */
    public static final String KEY_ALGORITHM = "AES";

    /**
     * 鍔犲�?/瑙ｅ瘑绠楁硶/宸ヤ綔妯″紡/濉厖鏂瑰紡 Java 6�?寔PKCS5PADDING濉厖鏂瑰紡 Bouncy
     * Castle�?寔PKCS7Padding濉厖鏂瑰紡
     */

    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * AES瑕佹眰�?�嗛挜闀垮害涓�128浣嶃��192浣嶆�?256浣�
     *
     * @param key
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getKey(String key) throws UnsupportedEncodingException {
        // 浣跨�?256浣嶅瘑鐮�?
        if (key.length() > 16) {
            key = key.substring(0, 16);
        } else if (key.length() < 16) {
            int count = (16 - key.length());
            for (int i = 0; i < count; i++) {
                key += "0";
            }
        }
        return key;
    }

    /**
     * 杞崲�?�嗛�?
     *
     * @param key 浜岃繘鍒跺瘑閽�
     * @return Key瀵嗛�?
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        // 瀹炰緥鍖朌ES瀵嗛挜鏉愭枡
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    /**
     * 鍔犲�?
     *
     * @param data 寰呭姞�?�嗘暟鎹�?
     * @param key  瀵嗛�?
     * @return String 鍔犲瘑鏁版嵁
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] encrypt = encrypt(data.getBytes(EN_CODE), getKey(key).getBytes(EN_CODE));
        return toHex(encrypt);
    }

    public static String toHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 3);
        for (int i = 0; i < bytes.length; i++) {
            int val = (bytes[i]) & 0xff;
            if (val < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString();
    }

    /**
     * 鍔犲�?
     *
     * @param data 寰呭姞�?�嗘暟鎹�?
     * @param key  瀵嗛�?
     * @return byte[]鍔犲瘑鏁版嵁
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 杩樺師�?�嗛�?
        Key k = toKey(key);
        /*
         *
         * 瀹炰緥鍖�?
         *
         * 浣跨敤PKCS7Padding濉厖鏂瑰紡锛屾寜濡備笅鏂瑰紡�?�炵�?
         *
         * Cipher.getInstance(CIPHER_ALGORITHM锛�"BC");
         *
         */
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 鍒濆鍖栵紝璁剧疆涓哄姞瀵嗘ā寮�
        cipher.init(Cipher.ENCRYPT_MODE, k);

        // 鎵ц鎿嶄�?
        return cipher.doFinal(data);
    }

    /**
     * 瑙ｅ�?
     *
     * @param data 寰呰В瀵嗘暟鎹�?
     * @param key  瀵嗛�?
     * @return String 瑙ｅ瘑鏁版嵁
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws Exception {
        byte[] decrypt = decrypt(hexTobytes(data), getKey(key).getBytes(EN_CODE));
        return new String(decrypt, EN_CODE);
    }

    public static byte[] hexTobytes(String str) {
        int l = str.length();
        if ((l % 2) != 0) {
            throw new IllegalArgumentException("�?垮害涓嶆槸鍋舵暟!");
        }
        byte[] bytes = new byte[l / 2];
        for (int i = 0; i < l; i = i + 2) {
            String item = str.substring(i, i + 2);
            bytes[i / 2] = (byte) Integer.parseInt(item, 16);
        }
        return bytes;
    }

    /**
     * 瑙ｅ�?
     *
     * @param data 寰呰В瀵嗘暟鎹�?
     * @param key  瀵嗛�?
     * @return byte[]瑙ｅ瘑鏁版嵁
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 杩樺師�?�嗛�?
        Key k = toKey(key);
        /*
         * 
         * 瀹炰緥鍖�?
         * 
         * 浣跨敤PKCS7Padding濉厖鏂瑰紡锛屾寜濡備笅鏂瑰紡�?�炵�?
         * 
         * Cipher.getInstance(CIPHER_ALGORITHM锛�"BC");
         * 
         */
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 鍒濆鍖栵紝璁剧疆涓鸿В�?�嗘ā寮�
        cipher.init(Cipher.DECRYPT_MODE, k);
        // 鎵ц鎿嶄�?
        return cipher.doFinal(data);
    }

    /**
     * 鐢熸垚�?�嗛挜锛渂r锛�
     *
     * @return byte[]浜岃繘鍒跺瘑閽�
     * @throws Exception
     */
    public static byte[] initKey() throws Exception {
        // 瀹炰緥鍖�?
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        // AES瑕佹眰�?�嗛挜闀垮害涓�128浣嶃��192浣嶆�?256浣�
        kg.init(128);
        // 鐢熸垚绉樺瘑瀵嗛�?
        SecretKey secretKey = kg.generateKey();
        // 鑾峰緱�?�嗛挜鐨勪簩杩涘埗缂栫爜褰㈠�?
        return secretKey.getEncoded();
    }
}



