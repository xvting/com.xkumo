package com.xkumo.xstock.weixin.ai;

import java.io.IOException;  
import java.io.UnsupportedEncodingException;  
import java.net.URLEncoder;  
import org.apache.http.HttpResponse;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.util.EntityUtils;  
import org.json.JSONException;  
import org.json.JSONObject;  


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 调用图灵机器人api接口，获取智能回复内容
 * @author pamchen-1
 *
 */
public class TulingAIService implements AIService {
	/**
	 * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果
	 * @param content
	 * @return
	 */
	public String getResult(String content){
		/** 此处为图灵api接口，参数key需要自己去注册申请，先以11111111代替 */
		String apiUrl = "http://www.tuling123.com/openapi/api?key=33638d2fd8637b7855e291f6c2f674a4&info=";
		String param = "";
		try {
			param = apiUrl+URLEncoder.encode(content,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //将参数转为url编码
		
		/** 发送httpget请求 */
		HttpGet request = new HttpGet(param);
		String result = "";
		try {
			HttpResponse response = HttpClients.createDefault().execute(request);
			if(response.getStatusLine().getStatusCode()==200){
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/** 请求失败处理 */
		if(null==result){
			return "对不起，你说的话真是太高深了……";
		}
		
		try {
			JSONObject json = new JSONObject(result);
			//以code=100000为例，参考图灵机器人api文档
			
			int code = json.getInt("code");
			
			if(100000==code){
				result = json.getString("text");
			}
			else if(305000==code)
			{
				result = "[列车]数据未对应";
			}
			else if(306000==code)
			{
				result = "[航班 ]数据未对应";
			}
			else if(200000==code)
			{
				result = "[网址类数据  ]数据未对应";
			}
			else if(302000==code)
			{
				result = "[新闻 ]数据未对应";
			}
			else if(308000==code)
			{
				result = "[菜谱、视频、小说  ]数据未对应";
			}
			else if(40001==code)
			{
				result = "key的长度错误（32位） ";
			}
			else if(40002==code)
			{
				result = "请求内容为空 ";
			}
			else if(40003==code)
			{
				result = "key错误或帐号未激活  ";
			}
			else if(40004==code)
			{
				result = "当天请求次数已用完   ";
			}
			else if(40005==code)
			{
				result = "暂不支持该功能    ";
			}
			else if(40006==code)
			{
				result = "服务器升级中  ";
			}
			else if(40007==code)
			{
				result = "服务器数据格式异常";
			}
			else
			{
				result = "未对应的返回代码["+code+"]";
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			result = e.toString();
		}
		return result;
	}
}

