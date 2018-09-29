package com.hu.base.http.util.wolaidian;

/**
 * aes娴嬭�?
 * <p>
 * Created by guanlichang@kingdee.com on 2017/8/23.
 * </p>
 **/
public class TestAES {
	
	public static final String key = "McySmg7s5h6hw82WFKoe6iCzpKOGht7shq6GjHkpSl8nctVhup//1MoBF5aLoLutu0c+3lXemMl2bNSyqX0t91iiC0B1ND+Yc9T7HgUHjJXq0hxEqq5Na11YzPWTNtQAjkQIjmyk3RvEWS+fVkdvhI0Koe5NIM6cw50XXHkpSl8nctVhup//1MoBF5aLoLutu0c+3lXemMl2bNSyqX0t91iiC0B1ND+Yc9T7HgUHjJXq0hxEqq5Na11YzPWTNtQAjkQIjmyk3RvEWS+fVkdvhI0Koe5NIM6cw50XXOTmKZU=";
	public static final String content = "1e57e3d9dbe39fc9cf533960fa9d4abffb75975417a2357dc432862c8af80f4e62738d2b217e33b51619bc780483bd0a97d348fe961f5f13e2ba3c972248e90efb75975417a2357dc432862c8af80f4e426c1b4deceda4bd1191ecaa953b8ae77d4ac28b4411787b3ca6fd6c9df93f0eb43e46f1d79628c869703103800762786a3c7cc12e2801775efc1666939cf8e42c2bbd7e30d36de39096d0d0e894981536fb737decb51d243969e8a285a3db39";
	public final static String privateKey ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJDuHR+wORXURcqxw/fefkSd0A0Pq6It2Nk0oP3wUSLMMKrJI98UTkMO3KlE/9/p3jefAw2N8ul+bCOMbZlwQ8pnp+MLiSk3iZtNjd1KIH3GfPfyyZObaY88O9uopkxyHq09UiW5wvnFz1qe948IQDyQ9clGs8Pc1uNtdiwgu/NzAgMBAAECgYBsWUd2oEUdF7QRg/JwxWp6m8ylZxi2RzY9tAXDsrzFmgesNnFV3hVgNqUq+sWcPXf+9ybOJFf8zaHEtcBeHLrgX7pt/FH9HOCUROV3l51VwsI2JuufEmGZlTiK3wWHv5jiCzGatgZuZtgKcmT1mhVCNE4g2xQ+2JgkAGAmrNtgiQJBAMDioJBEk0IG4VL7jhxONRF1fNzGHmUgWvu7Rnh7+j94CDOoeT4gl5kOCVZeixgBGL84CfmkmSB5LcF+Eo3VFsUCQQDAWnDZhFrcB6J2tYCjfUQFEk1jItLe3ijErysotluuPDEiyVqDxNhzxXb0J2xkRN1zct3bGdjm/0D5MF+Fa8TXAkBSHGfT4o15ryoexF3T6OsoeoZkCacIz8RLo4a1Zbm7aSK8svyiGQfQLSyGawr1dgoN5RJCBvL04wE6fKvzWwItAkEAvg1eLPziDT6Z3jKNTHvrZhtkTQxOT5gvrc3plctAIJImdll+Tm2C9DhpxOji5ttH9tbKr74FGo+fsdaCur/1dwJAaq+pHM8Wf2lHtSUHTIMZgd/5YrDu7146+o4eteMpZI+7eLWmZ1ljuvg8MTUYpStH80F8vw2uxX4m1SMR81CG4g==";


    public static void  main(String args[]) throws Exception {
    	String deKey = RSAUtils.decrypt(key, privateKey);
    	String deContent = AESUtils.decrypt(content, deKey);
    	System.out.println(deContent);
    	
    }
}
