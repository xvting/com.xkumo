package com.xkumo.xstock.weixin.util;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 

import org.json.JSONException;
import org.json.JSONObject;
 
public class MenuUtil {
    private static String getAccess_token(){   
    	 String APPSECRET="528cb0897b6c02af0e27e9e5d32d80ae"; //填写自己的AppSecret
    	 String APPID="wx6ddef4de75422559";//填写自己的AppId
         String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ APPID + "&secret=" +APPSECRET;
         String accessToken = null;
         try {
                URL urlGet = new URL(url);
                HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();    
                  
                http.setRequestMethod("GET");      //必须是get方式请求    
                http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
                http.setDoOutput(true);        
                http.setDoInput(true);
                System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
                System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
 
                http.connect();
              
                InputStream is =http.getInputStream();
                int size =is.available();
                byte[] jsonBytes =new byte[size];
                is.read(jsonBytes);
                String message=new String(jsonBytes,"UTF-8");
                  
                JSONObject demoJson = new JSONObject(message);
                accessToken = demoJson.getString("access_token");
                
                
                System.out.println(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
           return accessToken;
        }
     
    /**
     * @throws JSONException 
     * 创建Menu
    * @Title: createMenu
    * @Description: 创建Menu
    * @param @return
    * @param @throws IOException    设定文件
    * @return int    返回类型
    * @throws
     */
    public static String createMenu() throws JSONException {
    	String menu = "{\"button\":["
                + "{\"name\":\"主页\",\"sub_button\":["
                + "{\"type\":\"view\",\"name\":\"主页\",\"url\":\"http://www.baidu.com/\"},"
                + "{\"type\":\"view\",\"name\":\"微观网\",\"url\":\"http://www.baidu.com/\"}"
                + "]},"
                + "{\"name\":\"婚礼人\",\"sub_button\":["
                + "{\"type\":\"view\",\"name\":\"结婚\",\"url\":\"http://www.baidu.com/\"},"
                + "{\"type\":\"view\",\"name\":\"哈哈\",\"url\":\"http://www.baidu.com/\"}"
                + "]},"
                + "{\"name\":\"普通用户\",\"sub_button\":["
                + "{\"type\":\"view\",\"name\":\"看看\",\"url\":\"http://www.baidu.com/\"},"
                + "{\"type\":\"click\",\"name\":\"测试\",\"key\":\"WEIFUWU\"}"
                + "]}" + "]}";
    	String access_token= getAccess_token();
        String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
        try {
           URL url = new URL(action);
           HttpURLConnection http =(HttpURLConnection) url.openConnection();    
           http.setRequestMethod("POST");        
           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
           http.setDoOutput(true);        
           http.setDoInput(true);
           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
 
           http.connect();
           OutputStream os= http.getOutputStream();    
           os.write(menu.getBytes("UTF-8"));//传入参数    
           os.flush();
           os.close();
         
           InputStream is =http.getInputStream();
           int size =is.available();
           byte[] jsonBytes =new byte[size];
           is.read(jsonBytes);
           String message=new String(jsonBytes,"UTF-8");
           
           JSONObject demoJson = new JSONObject(message);
           String code = demoJson.getString("errcode");
         
           
           return "返回信息"+ReturnCodeUtil.getMessage(code);
           } catch (MalformedURLException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }    
        return "createMenu 失败";
   }
    
    public static void main(String[] args) {
         
        try {
			System.out.println(createMenu());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
    }
}