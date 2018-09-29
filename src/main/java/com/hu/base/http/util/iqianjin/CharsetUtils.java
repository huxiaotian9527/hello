package com.hu.base.http.util.iqianjin;

import java.nio.charset.Charset;


public class CharsetUtils {
	public static final Charset UTF_8 = Charset.forName(ConstCharset.UTF_8);
	public static final Charset UTF_16 = Charset.forName(ConstCharset.UTF_16);
	public static final Charset ISO_8859_1 = Charset.forName(ConstCharset.ISO_8859_1);
	public static final Charset GBK = Charset.forName(ConstCharset.GBK);

	private CharsetUtils() {
	}

	public static Charset getCharset(String charset) {
		if (StringUtils.isEmpty(charset)) {
			return Charset.defaultCharset();
		}
		if (charset.contains(ConstHttp.CONTENT_TYPE_TEXT_HTML)) {
			return UTF_8;
		}
		if (charset.contains(ConstHttp.CONTENT_TYPE_JSON)) {
			return UTF_8;
		}
		if (charset.contains(ConstHttp.CONTENT_TYPE_TEXT_PLAIN)) {
			return UTF_8;
		}
		try {
			return Charset.forName(charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UTF_8;
	}

	public static Charset getCharset(Charset charset) {
		return charset == null ? UTF_8 : charset;
	}
}
