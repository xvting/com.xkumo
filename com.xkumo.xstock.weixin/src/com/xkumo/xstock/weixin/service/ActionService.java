package com.xkumo.xstock.weixin.service;

import com.xkumo.xstock.core.TaskEnum;
import com.xkumo.xstock.weixin.ai.AIManager;
import com.xkumo.xstock.weixin.message.resp.TextMessage;
import com.xkumo.xstock.weixin.webservice.client.WebServiceService;

public class ActionService {

	SinaStockService sinaStockService = new SinaStockService();
	SinaFutureService sinaFutureService = new SinaFutureService();
	
	public String doAction(TextMessage textMessage) throws Exception {
		// TODO Auto-generated method stub
		
		String respContent = "您发送的是文本消息！" + textMessage.getContent();
		
		String actionType = textMessage.getContent();
		
		if("sina".equalsIgnoreCase(actionType))
		{
			respContent = sinaStockService.getStockDataReport();
		}
		else if("dosina".equalsIgnoreCase(actionType))
		{
			try
			{
				WebServiceService webService = new WebServiceService();
				
				webService.getWebServicePort().startTask(TaskEnum.UpdateStockDataSina.getID());
				
				return "开始新浪数据读入处理：成功";
			}
			catch(Exception ex)
			{
				return "开始新浪数据读入处理：失败 [" + ex.toString() + "]";
			}
		}
		else if("ag".equalsIgnoreCase(actionType))
		{
			respContent = sinaFutureService.getFutureAG1512DataReport();
		}
		else if("h".equalsIgnoreCase(actionType))
		{
			respContent = "sina:股票数据采集状态" + "\r\n";
			respContent += "dosina:执行股票数据采集" + "\r\n";
			respContent += "ag:白银数据采集状态" + "\r\n";
		}
		else
		{
			return AIManager.getAI().getResult(actionType);
		}
		
		return respContent;
	}

}
