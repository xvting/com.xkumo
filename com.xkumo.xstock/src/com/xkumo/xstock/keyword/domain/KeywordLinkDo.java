package com.xkumo.xstock.keyword.domain;

import java.util.Date;

public class KeywordLinkDo {
	public int seq;
	public String keywordcode;
	public String stockcode;
	public String stockexchangecode;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getKeywordcode() {
		return keywordcode;
	}
	public void setKeywordcode(String keywordcode) {
		this.keywordcode = keywordcode;
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

	
}
