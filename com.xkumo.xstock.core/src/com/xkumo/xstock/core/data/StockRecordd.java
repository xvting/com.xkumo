package com.xkumo.xstock.core.data;

import java.util.Date;

/**
 * 股票交易数据
 * @author xvting
 *
 */
public class StockRecordd {

	private String StockName;  //��Ʊ��
	private String StockCode;	//��Ʊ���
	private String StockExchangeCode; //��Ʊ��������
	private String OpenPrice;  //开盘价
	private String LastClosePrice; //昨日收盘价
	private String CurrentPrice; //当前价格
	private String TopPrice;  //今日最高价
	private String BottomPrice; //今日最低价	
	private String BuyPrice; //竞买价
	private String SellPrice; //竞卖价
	private String TransactionAmount; //成交金额
	private String TransactionNumber; //成交股票数
	private String buy1price;  //买一报价
	private String buy1number; //买一
	private String buy2price;  //买二报价
	private String buy2number; //买二
	private String buy3price;  //买三报价
	private String buy3number; //买三
	private String buy4price;  //买四报价
	private String buy4number; //买四
	private String buy5price;  //买五报价
	private String buy5number; //买五
	private String sell1price; //卖一报价
	private String sell1number;//卖一
	private String sell2price; //卖二报价
	private String sell2number;//卖二
	private String sell3price; //卖三报价
	private String sell3number;//卖三
	private String sell4price; //卖四报价
	private String sell4number;//卖四
	private String sell5price; //卖五报价
	private String sell5number;//卖五
	private Date dataday;	   //数据日期
	private String datatime;   //数据时间
	private float ma5;
	private float ma10;
	private float ma20;
	private float ma30;
	private float ma60;
	private float ma120;
	private float ma240;
}
