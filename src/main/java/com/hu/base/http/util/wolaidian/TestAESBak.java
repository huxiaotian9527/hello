package com.hu.base.http.util.wolaidian;


import java.net.URLDecoder;
import java.util.Map;

/**
 * aes娴嬭�?
 * <p>
 * Created by guanlichang@kingdee.com on 2017/8/23.
 * </p>
 **/
public class TestAESBak {

    public static void  main(String args[]) throws Exception {

        Map<String, String> params = RSAUtils.generateKeyPair();
        String publicKey = params.get("publicKey");
        String privateKey = params.get("privateKey");
        String modulus = params.get("modulus");
        System.out.println("鍏�?:" + publicKey);
        System.out.println("绉侀�?:" + privateKey);
        System.out.println("modulus:" + modulus);

        // resultKey杩涜RSA鍔犲�?
        //String key = RandomUtil.generateNumber(16);
        String key = "";
        System.out.println("鏄庢枃key锛�"+ key);
        String encryptkey = RSAUtils.encrypt(key, publicKey);
        System.out.println("鍔犲瘑鍚巏ey锛�" + encryptkey);

        String content ="绠℃潕闀縜adfadfsd";

        byte[]  contentAes = AESCoderBak.encrypt(content.getBytes("UTF-8"), key.getBytes("UTF-8"));
//        byte[] base64Text = Base64.encodeBase64(contentAes);  // 鍐岯ase64缂栫�?
        System.out.println("鍔犲瘑鍚庣殑鍐呭锛�?"+contentAes.toString());
        System.out.println("瑙ｅ�?-----------------------------------------------------");

        String dekey = RSAUtils.decrypt(encryptkey, privateKey);
        System.out.println("瑙ｅ瘑鍚庣殑key锛�"+ dekey);

        String decontent = AESCoderBak.decrypt(contentAes.toString(),dekey);

        System.out.println("瑙ｅ瘑鍚庣殑鍐呭锛�?"+ URLDecoder.decode(decontent, "UTF-8"));

        // 绛惧悕淇℃伅
//        String signString = MySecurityMD5Utils.md5Hex(URLEncoder.encode(resultContentStr, "UTF-8"));
//        System.out.println("绛惧悕淇℃伅:"+signString);
    }
}
