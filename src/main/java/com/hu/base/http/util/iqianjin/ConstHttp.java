package com.hu.base.http.util.iqianjin;


public class ConstHttp {
	public static final RequestMethod GET = RequestMethod.GET;
	public static final RequestMethod POST = RequestMethod.POST;

	public enum RequestMethod {
		GET, POST
	}

	public static final String PROTOCOL_HTTP = "http";
	public static final String PROTOCOL_HTTPS = "https";

	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";

	public static final String EXPIRES = "Expires";
	public static final String USER_AGENT = "User-Agent";
	public static final String CACHE_CONTROL = "Cache-Control";

	/**
	 * 浠嶮yHttpResponse鎴朚yHttpResponse涓緱鍒癱ookie鍊肩殑key
	 */
	public static final String COOKIE = "Cookie";

	/**
	 * 浠巙rlConnection璇锋眰涓彇寰梒ookie鍊肩殑key
	 */
	public static final String SET_COOKIE = "Set-Cookie";
	public static final String CONTENT_LENGTH = "Content-Length";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String LOCATION = "Location";
	public static final String ACCEPT_ENCODING = "Accept-Encoding";
	public static final String ACCEPT_CHARSET = "Accept-Charset";
	public static final String ACCEPT_LANGUAGE = "Accept-Language";
	public static final String CONNECTION = "Connection";
	public static final String ACCEPT = "Accept";

	public static final String HOST = "Host";
	public static final String ETAG = "ETag";
	public static final String DATE = "Date";
	public static final String IF_MATCH = "If-Match";
	public static final String IF_MODIFIED_SINCE = "If-Modified-Since";
	public static final String IF_NONE_MATCH = "If-None-Match";
	public static final String REFRESH = "Refresh";
	public static final String REFERER = "Referer";
	public static final String ORIGIN = "Origin";
	public static final String CHARSET = "charset";

	/**
	 * 璇锋眰璧勬簮绫诲瀷锛岄粯璁や负璇锋眰html锛屽叾瀹冭繕鏈塲son銆� 娴併�佸ご閮ㄤ俊鎭瓑
	 */
	public static final int RESPONSE_RESOURCE_TYPE_HTML = 0;
	public static final int RESPONSE_RESOURCE_TYPE_BYTES = 1;
	public static final int RESPONSE_RESOURCE_TYPE_HEADER = 2;

	public static final String CONTENT_TYPE_TEXT_HTML = "text/html";
	public static final String CONTENT_TYPE_IMAGE = "image/jpeg";
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";

	public static final String CONTENT_ENCODING_GZIP = "gzip";


	/**
	 * 鐘舵�佺爜
	 */
	public static final int STATUS_CODE_SUCCESS = 200;
	public static final int STATUS_CODE_REDIRECT_301 = 301;
	public static final int STATUS_CODE_REDIRECT_302 = 302;
	
	/**
	 * 鍗＄墰浠ｇ悊鏈嶅姟璇锋眰
	 */
	public static final String PROXY_AUTHORIZATION="Proxy-Authorization";
	public static final String PROXY_USERNAME="cardniu";
	public static final String PROXY_PASSWORD="iek*(202KJ";
}
