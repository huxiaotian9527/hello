package com.hu.base.http.util.wolaidian;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.security.Key;
import java.security.SecureRandom;

/**
 * AES鍔犲瘑瑙ｅ瘑瀹夊叏缁勪欢
 *
 * <p>
 * Created by guanlichang@kingdee.com on 2017/8/15.
 * </p>
 **/
public abstract class AESCoderBak {

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
     * 瑙ｅ�?
     *
     * @param data 寰呰В瀵嗘暟鎹�?
     * @param key 瀵嗛�?
     * @return byte[]瑙ｅ瘑鏁版嵁
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 杩樺師�?�嗛�?
        Key k = toKey(initKey(key));
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
     * 瑙ｅ�?
     *
     * @param data 寰呰В瀵嗘暟鎹�?
     * @param key 瀵嗛�?
     * @return String 瑙ｅ瘑鏁版嵁
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws Exception {
        byte[] decrypt = decrypt(Base64.decodeBase64(data), Base64.decodeBase64(key));
        return new String(decrypt);
    }

    /**
     * 鍔犲�?
     *
     * @param data 寰呭姞�?�嗘暟鎹�?
     * @param key 瀵嗛�?
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
     * 鐢熸垚�?�嗛挜锛渂r锛�
     *
     * @return byte[]浜岃繘鍒跺瘑閽�
     * @throws Exception
     */
    public static byte[] initKey(byte[] key) throws Exception {
        // 瀹炰緥鍖�?
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        // AES瑕佹眰�?�嗛挜闀垮害涓�128浣嶃��192浣嶆�?256浣�
        kg.init(128,new SecureRandom(key));
        // 鐢熸垚绉樺瘑瀵嗛�?
        SecretKey secretKey = kg.generateKey();
        // 鑾峰緱�?�嗛挜鐨勪簩杩涘埗缂栫爜褰㈠�?
        return secretKey.getEncoded();
    }

    public static void main(String[] args) {
        String key = "UCXX0ihQEFnysrHyPC30pQ==";
        String encryCon = "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";
        try {
            String dencryCon = AESUtils.decrypt(encryCon,key);
            String decodeUTFStr = URLDecoder.decode(dencryCon, "UTF-8");
            System.out.println(decodeUTFStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



