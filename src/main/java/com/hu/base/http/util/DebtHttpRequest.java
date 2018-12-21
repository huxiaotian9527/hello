package com.hu.base.http.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
			if(httpPost!=null){
				httpPost.releaseConnection();
			}
		}
		return result;
	}

	/**
	 * 根据url下载文件
	 * @param url
	 * @return
	 */
	public static ByteArrayOutputStream download(String url) {
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget, HttpClientContext.create());
			InputStream is = response.getEntity().getContent();
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			byte[] buffer=new byte[1024];
			int ch;
			while ((ch = is.read(buffer)) != -1) {
				bao.write(buffer,0,ch);
			}
			is.close();
			bao.flush();
			return bao;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
