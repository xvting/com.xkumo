package com.xkumo.xstock.yahoo.domain;

public class YahooDailyStockDataDo {
	private int yahooseq;
	private String stockname;  //��Ʊ��
	private String stockcode;	//��Ʊ���
	private String stockexchangecode; //��Ʊ��������
	private String date;
	private String open;
	private String high;
	private String low;
	private String close;
	private String volumn;
	private String adjclose;
	
	public int getYahooseq() {
		return yahooseq;
	}
	public void setYahooseq(int yahooseq) {
		this.yahooseq = yahooseq;
	}
	public String getStockname() {
		return stockname;
	}
	public void setStockname(String stockname) {
		this.stockname = stockname;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getClose() {
		return close;
	}
	public void setClose(String close) {
		this.close = close;
	}
	
	public String getVolumn() {
		return volumn;
	}
	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}
	public String getAdjclose() {
		return adjclose;
	}
	public void setAdjclose(String adjclose) {
		this.adjclose = adjclose;
	}
	
	
}
