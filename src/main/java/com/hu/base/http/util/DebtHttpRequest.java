package com.hu.base.http.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Map;

public class DebtHttpRequest {
	/**
	 * 发送HTTP POST请求
	 * @param url
	 * @param headers
	 * @return
	 */
	public static String doPostByJson(String url, JSONObject params, Map<String, String> headers) {
		
		String result = null;
		HttpPost httpPost = null;

		try {
			httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(100000)
					.build(); 
			httpPost.setConfig(requestConfig);
			for (String key : headers.keySet()) {
				httpPost.addHeader(key, headers.get(key));
			}
			StringEntity entity = new StringEntity(params.toString(),"utf-8");
			entity.setContentEncoding("UTF-8");    
			entity.setContentType("application/json");  
			httpPost.setEntity(entity);
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpResponse response = httpclient.execute(httpPost, HttpClientContext.create());
			result = EntityUtils.toString(response.getEntity(), "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

}
