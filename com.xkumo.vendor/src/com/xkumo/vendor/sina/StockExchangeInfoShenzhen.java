package com.xkumo.vendor.sina;

import java.util.ArrayList;
import java.util.List;

import com.xkumo.vendor.IStockExchangeInfo;

public class StockExchangeInfoShenzhen implements IStockExchangeInfo  {

	public String getCode() {
		// TODO Auto-generated method stub
		return "sz";
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "深圳证券交易所";
	}

	public List<String> getStockCodeList() {
		// TODO Auto-generated method stub
		
		List<String> result = new ArrayList<String>();
		
		//000000 - 000999
		for(int i = 0 ; i <= 3000; i++)
		{
			result.add(String.format("%1$06d", i));
		}
		
		//150000 - 150200 
		for(int i = 0 ; i <= 200; i++)
		{
			result.add(String.valueOf( 150000 + i));
		}
		
		
		//159000 - 159999 
		for(int i = 0 ; i <= 999; i++)
		{
			result.add(String.valueOf( 159000 + i));
		}
	
		
		//160000  - 167000
		for(int i = 0 ; i <= 7000; i++)
		{
			result.add(String.valueOf( 160000 + i));
		}
		
		//184000 - 184999
		for(int i = 0 ; i <= 999; i++)
		{
			result.add(String.valueOf( 184000 + i));
		}
		
		//200000 - 201000
		for(int i = 0 ; i <= 1000; i++)
		{
			result.add(String.valueOf( 200000 + i));
		}
		
		//300000 - 300500)
		for(int i = 0 ; i <= 500; i++)
		{
			result.add(String.valueOf( 300000 + i));
		}
		
		return result;
	}

	@Override
	public List<String> getStockCodeListAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
