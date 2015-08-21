package com.xkumo.xstock.yahoo.domain;

public class YahooDailyStockDataFlagDo {
	private int seq;
	private String stockname;  //��Ʊ��
	private String stockcode;	//��Ʊ���
	private String stockexchangecode; //��Ʊ��������
	private int dataloadcount;
	

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	public int getDataloadcount() {
		return dataloadcount;
	}
	public void setDataloadcount(int dataloadcount) {
		this.dataloadcount = dataloadcount;
	}
	
	
}
