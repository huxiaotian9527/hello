package com.hu.base.http.util.iqianjin;

import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
	
	public static JSONObject toJSONObject(String content, String key, String signStr){
		JSONObject jsonParams = new JSONObject();
		jsonParams.put("content", content);
		jsonParams.put("key", key);
		jsonParams.put("sign_string", signStr);
		return jsonParams;
	}

}
