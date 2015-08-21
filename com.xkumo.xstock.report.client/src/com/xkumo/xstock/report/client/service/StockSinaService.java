/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xkumo.xstock.report.client.service;

/**
 *
 * @author xvting
 */
public class StockSinaService {
    public String getStockSinaURL(String StockCode, String StockExchangeCode)
	{
		return "http://finance.sina.com.cn/realstock/company/"+StockExchangeCode+StockCode+"/nc.shtml";
	}
}
