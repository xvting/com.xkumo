package com.xkumo.xstock.weixin.ai;

public class AIManager {
	
	private static AIService service;
	
	public static  AIService getAI()
	{
		if (service == null)
		{
			service = new TulingAIService();
		}
		else
		{
			//Do Nothing
		}
		
		return service;
	}
	
}
