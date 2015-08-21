package com.xkumo.xstock.datacollector.service;

public class StockSinaService {

	public String getStockSinaURL(String StockCode, String StockExchangeCode)
	{
		return "http://finance.sina.com.cn/realstock/company/"+StockExchangeCode+StockCode+"/nc.shtml";
	}
	
}
