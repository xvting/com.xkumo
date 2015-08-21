package com.xkumo.vendor;

import java.util.List;

public interface IStockExchangeInfo {
	public String getName();
	public String getCode();
	public List<String> getStockCodeList();
	public List<String> getStockCodeListAll();
}
