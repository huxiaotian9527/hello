package com.hu.base.http.util.iqianjin;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class StringEncodeUtils {
	//
	// ========================== base64缂栫爜 ========================= //
	//

	/**
	 * base64缂栫爜
	 */
	public static String base64Encode(String input) {
		byte[] bytes = getBytesUtf8(input);
		return base64Encode(bytes);
	}

	/**
	 * base64缂栫爜
	 */
	public static String base64Encode(byte[] bytes) {
		byte[] encodeBase64 = Base64Encoder.encodeBase64(bytes);
		return new String(encodeBase64);
	}

	/**
	 * base64瑙ｇ爜 鏅��
	 */
	public static String base64DecodeNormal(String text) {
		return urlDecode(new String(Base64Encoder.decodeBase64(text)));
	}

	/**
	 * base64瑙ｇ爜 鏅��  GBK
	 */
	public static String base64DecodeNormalByGbk(String text) {
		try {
			return urlDecode(new String(Base64Encoder.decodeBase64(text), ConstCharset.GBK));
		} catch (UnsupportedEncodingException e) {
			return urlDecode(new String(Base64Encoder.decodeBase64(text)));
		}
	}

	/**
	 * base64瑙ｇ爜  URL-safe
	 */
	public static String base64Decode(String text) {
		return new String(Base64Encoder.decodeBase64(text));
	}

	/**
	 * base64瑙ｇ爜  URL-safe  GBK
	 */
	public static String base64DecodeByGbk(String text) {
		return new String(Base64Encoder.decodeBase64(text));
	}

	/**
	 * base64瑙ｇ爜
	 */
	public static byte[] base64DecodeToByte(String text) {
		return Base64Encoder.decodeBase64(text);
	}

	//
	// ========================== url缂栫爜 ========================= //
	//

	/**
	 * url缂栫爜
	 * <pre>
	 * urlEncode(null) = null
	 * urlEncode("") = "";
	 * urlEncode("aa") = "aa";
	 * urlEncode("鍟婂晩鍟婂晩") = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
	 * </pre>
	 */
	public static String urlEncode(final String input) {
		if (StringCompareUtils.isNullOrEmpty(input)) {
			return input;
		}
		try {
			return URLEncoder.encode(input, ConstCharset.UTF_8);
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return input;
	}

	/**
	 * URL瑙ｇ爜
	 */
	public static String urlDecode(final String input) {
		if (StringCompareUtils.isNullOrEmpty(input)) {
			return input;
		}
		try {
			return URLDecoder.decode(input, ConstCharset.UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return input;
	}

	//
	// ========================== hex缂栫爜 ========================= //
	//

	/**
	 * hex缂栫爜
	 */
	public static String hexEncode(final String input) {
		if (StringUtils.isEmpty(input)) {
			return input;
		}
		byte[] bytes = getBytesUtf8(input);
		return hexEncode(bytes);
	}

	/**
	 * hex缂栫爜
	 */
	public static String hexEncode(final byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		return HexEncoder.encodeHexString(bytes);
	}

	/**
	 * hex瑙ｇ爜
	 */
	public static String hexDecode(final String input) {
		if (StringUtils.isEmpty(input)) {
			return input;
		}
		return StringEncodeUtils.getStringUtf8(hexDecodeToByte(input));
	}

	/**
	 * hex瑙ｇ爜
	 */
	public static byte[] hexDecodeToByte(String input) {
		try {
			return HexEncoder.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		return null;
	}

	//
	// ========================== 瀛楄妭杞崲 ========================= //
	//

	/**
	 * 杞崲鎴恥tf-8缂栫爜鐨勫瓧鑺�
	 */
	public static byte[] getBytesUtf8(final String input) {
		if (input == null) {
			return null;
		}
		return input.getBytes(CharsetUtils.UTF_8);
	}

	/**
	 * 杞崲鎴恥tf-8缂栫爜鐨勫瓧鑺�
	 */
	public static String getStringUtf8(final byte[] bytes) {
		return bytes == null ? null : new String(bytes, CharsetUtils.UTF_8);
	}
}
