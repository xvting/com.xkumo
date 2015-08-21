package com.xkumo.vendor.sina;

import java.util.Date;

public class SinaDailyStockData {
	private String content;

	private String stockname;  //��Ʊ��
	private String stockcode;	//��Ʊ���
	private String stockexchangecode; //��Ʊ��������
	private String openprice;  //开盘价
	private String lastcloseprice; //昨日收盘价
	private String currentprice; //当前价格
	private String topprice;  //今日最高价
	private String bottomprice; //今日最低价	
	private String buyprice; //竞买价
	private String sellprice; //竞卖价
	private String transactionamount; //成交金额
	private String transactionnumber; //成交股票数
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
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getStockname() {
		return stockname;
	}
	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
	
	public String getOpenprice() {
		return openprice;
	}
	public void setOpenprice(String openprice) {
		this.openprice = openprice;
	}
	public String getLastcloseprice() {
		return lastcloseprice;
	}
	public void setLastcloseprice(String lastcloseprice) {
		this.lastcloseprice = lastcloseprice;
	}
	public String getCurrentprice() {
		return currentprice;
	}
	public void setCurrentprice(String currentprice) {
		this.currentprice = currentprice;
	}
	public String getTopprice() {
		return topprice;
	}
	public void setTopprice(String topprice) {
		this.topprice = topprice;
	}
	public String getBottomprice() {
		return bottomprice;
	}
	public void setBottomprice(String bottomprice) {
		this.bottomprice = bottomprice;
	}
	public String getBuyprice() {
		return buyprice;
	}
	public void setBuyprice(String buyprice) {
		this.buyprice = buyprice;
	}
	public String getSellprice() {
		return sellprice;
	}
	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}
	public String getTransactionamount() {
		return transactionamount;
	}
	public void setTransactionamount(String transactionamount) {
		this.transactionamount = transactionamount;
	}
	public String getTransactionnumber() {
		return transactionnumber;
	}
	public void setTransactionnumber(String transactionnumber) {
		this.transactionnumber = transactionnumber;
	}
	public String getBuy1price() {
		return buy1price;
	}
	public void setBuy1price(String buy1price) {
		this.buy1price = buy1price;
	}
	public String getBuy1number() {
		return buy1number;
	}
	public void setBuy1number(String buy1number) {
		this.buy1number = buy1number;
	}
	public String getBuy2price() {
		return buy2price;
	}
	public void setBuy2price(String buy2price) {
		this.buy2price = buy2price;
	}
	public String getBuy2number() {
		return buy2number;
	}
	public void setBuy2number(String buy2number) {
		this.buy2number = buy2number;
	}
	public String getBuy3price() {
		return buy3price;
	}
	public void setBuy3price(String buy3price) {
		this.buy3price = buy3price;
	}
	public String getBuy3number() {
		return buy3number;
	}
	public void setBuy3number(String buy3number) {
		this.buy3number = buy3number;
	}
	public String getBuy4price() {
		return buy4price;
	}
	public void setBuy4price(String buy4price) {
		this.buy4price = buy4price;
	}
	public String getBuy4number() {
		return buy4number;
	}
	public void setBuy4number(String buy4number) {
		this.buy4number = buy4number;
	}
	public String getBuy5price() {
		return buy5price;
	}
	public void setBuy5price(String buy5price) {
		this.buy5price = buy5price;
	}
	public String getBuy5number() {
		return buy5number;
	}
	public void setBuy5number(String buy5number) {
		this.buy5number = buy5number;
	}
	public String getSell1price() {
		return sell1price;
	}
	public void setSell1price(String sell1price) {
		this.sell1price = sell1price;
	}
	public String getSell1number() {
		return sell1number;
	}
	public void setSell1number(String sell1number) {
		this.sell1number = sell1number;
	}
	public String getSell2price() {
		return sell2price;
	}
	public void setSell2price(String sell2price) {
		this.sell2price = sell2price;
	}
	public String getSell2number() {
		return sell2number;
	}
	public void setSell2number(String sell2number) {
		this.sell2number = sell2number;
	}
	public String getSell3price() {
		return sell3price;
	}
	public void setSell3price(String sell3price) {
		this.sell3price = sell3price;
	}
	public String getSell3number() {
		return sell3number;
	}
	public void setSell3number(String sell3number) {
		this.sell3number = sell3number;
	}
	public String getSell4price() {
		return sell4price;
	}
	public void setSell4price(String sell4price) {
		this.sell4price = sell4price;
	}
	public String getSell4number() {
		return sell4number;
	}
	public void setSell4number(String sell4number) {
		this.sell4number = sell4number;
	}
	public String getSell5price() {
		return sell5price;
	}
	public void setSell5price(String sell5price) {
		this.sell5price = sell5price;
	}
	public String getSell5number() {
		return sell5number;
	}
	public void setSell5number(String sell5number) {
		this.sell5number = sell5number;
	}
	public Date getDataday() {
		return dataday;
	}
	public void setDataday(Date dataday) {
		this.dataday = dataday;
	}
	public String getDatatime() {
		return datatime;
	}
	public void setDatatime(String datatime) {
		this.datatime = datatime;
	}
	public String getStockcode() {
		return stockcode;
	}
	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}
	public String getStockexchangecode() {
		return stockexchangecode;
	}
	public void setStockexchangecode(String stockexchangecode) {
		this.stockexchangecode = stockexchangecode;
	}
	public float getMa5() {
		return ma5;
	}
	public void setMa5(float ma5) {
		this.ma5 = ma5;
	}
	public float getMa10() {
		return ma10;
	}
	public void setMa10(float ma10) {
		this.ma10 = ma10;
	}
	
	public float getMa20() {
		return ma20;
	}
	public void setMa20(float ma20) {
		this.ma20 = ma20;
	}
	
	
	public float getMa30() {
		return ma30;
	}
	public void setMa30(float ma30) {
		this.ma30 = ma30;
	}
	public float getMa60() {
		return ma60;
	}
	public void setMa60(float ma60) {
		this.ma60 = ma60;
	}
	public float getMa120() {
		return ma120;
	}
	public void setMa120(float ma120) {
		this.ma120 = ma120;
	}
	public float getMa240() {
		return ma240;
	}
	public void setMa240(float ma240) {
		this.ma240 = ma240;
	}
	
}
