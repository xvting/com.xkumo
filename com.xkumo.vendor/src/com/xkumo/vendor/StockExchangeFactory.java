package com.xkumo.vendor;

import com.xkumo.vendor.sina.StockExchangeInfoShanghai;
import com.xkumo.vendor.sina.StockExchangeInfoShenzhen;

public class StockExchangeFactory {
	public static IStockExchangeInfo getStockExchangInfoShanghai()
	{
		return new StockExchangeInfoShanghai();
	}
	
	public static IStockExchangeInfo getStockExchangInfoShenzhen()
	{
		return new StockExchangeInfoShenzhen();
	}
}
