package com.hu.base.http.util.iqianjin;

public final class MD5Utils {
	/** MD5鍔犲瘑瀛楃涓插悗鍐峢ex缂栫爜杩斿洖锛屼緥濡� 111111 = 96e79218965eb72c92a549dd5a330112 */
	public static String md5Hex(String input) {
		return StringEncodeUtils.hexEncode(md5Bytes(input));
	}

	/** 闀垮害16 */
	public static String md5HexWith16(String input) {
		return md5Hex(input).substring(0, 16);
	}
	
	/**鑾峰彇16浣峬d5鍊�*/
	public static String md5Hex16(String input){
	    return md5Hex(input).substring(8, 24);
	}

	/** MD5鍔犲瘑瀛楃涓插悗鍐峛ase64缂栫爜杩斿洖锛屼緥濡� 111111 = lueSGJZetyySpUndWjMBEg== */
	public static String md5Base64(String input) {
		return StringEncodeUtils.base64Encode(md5Bytes(input));
	}


	//
	// ========================== 绉佹湁鏂规硶 ========================= //
	//

	/** MD5鍔犲瘑瀛楃涓诧紝渚嬪 111111 = 锟斤拷锟絕锟�,锟斤拷I锟絑3 */
	private static String md5(String input) {
		return new String(md5Bytes(input));
	}

	/** MD5鍔犲瘑瀛楃涓� */
	private static byte[] md5Bytes(String input) {
		return DigestUtils.md5(input);
	}


}
