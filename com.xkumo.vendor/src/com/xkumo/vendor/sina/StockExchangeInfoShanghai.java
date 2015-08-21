package com.xkumo.vendor.sina;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.xkumo.vendor.IStockExchangeInfo;

public class StockExchangeInfoShanghai implements IStockExchangeInfo  {

	public String getCode() {
		// TODO Auto-generated method stub
		return "sh";
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "上海证券交易所";
	}

	public List<String> getStockCodeList() {
		// TODO Auto-generated method stub
		
		List<String> result = new ArrayList<String>();
		
		//000001 - 000200
		for(int i = 0 ; i <= 200; i++)
		{
			result.add(String.format("%1$06d", i));
		}
		
		//500000 - 500100 //���
		for(int i = 0 ; i <= 100; i++)
		{
			result.add(String.valueOf( 500000 + i));
		}
		
		
		//510000 - 513500 //ETF
		for(int i = 0 ; i <= 3500; i++)
		{
			result.add(String.valueOf( 510000 + i));
		}
	
		
		//600000 - 604000
		for(int i = 0 ; i <= 4000; i++)
		{
			result.add(String.valueOf( 600000 + i));
		}
		
		//900900 - 901500
		for(int i = 0 ; i <= 1500; i++)
		{
			result.add(String.valueOf( 900900 + i));
		}
		
		return result;
	}
	
	public List<String> getStockCodeListAll() {
		// TODO Auto-generated method stub
		
		List<String> result = new ArrayList<String>();
		
		for(int i0 = 0 ; i0 < 10 ; i0++)
		{
			for(int i1 = 0 ; i1 < 10 ; i1++)
			{
				for(int i2 = 0 ; i2 < 10 ; i2++)
				{
					for(int i3 = 0 ; i3 < 10 ; i3++)
					{
						for(int i4 = 0 ; i4 < 10 ; i4++)
						{
							for(int i5 = 0 ; i5 < 10 ; i5++)
							{
								String code = String.valueOf(i0) + String.valueOf(i1) + String.valueOf(i2) + String.valueOf(i3) + String.valueOf(i4) + String.valueOf(i5);
							
								result.add(code);
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		
		SinaVendor sinaVendor = new SinaVendor();
		
		System.out.println("Begin:");
		
		boolean needln = true;
		
		int waitcount = 0;
		
		StockExchangeInfoShanghai shanghai = new StockExchangeInfoShanghai();
		
		List<String> codeList = shanghai.getStockCodeListAll();
		
		for(String code : codeList)
		{
			SinaDailyStockData sinadailystockdata = sinaVendor.getDailyStockData(code, "sh");
			
			if(sinadailystockdata== null || sinadailystockdata.getStockcode() == null || sinadailystockdata.getStockcode().equalsIgnoreCase("")  )
			{
				//Do Nothing
				if( needln)
				{
					System.out.println();
				}
				else if (waitcount >= 1000)
				{
					System.out.println();
					
					waitcount = 0;
				}
				
				System.out.print(".");
				needln = false;
				waitcount++;
			}
			else
			{
				System.out.println();
				System.out.print(sinadailystockdata.getStockcode());
				
				needln = true;
			}
		}
		
		
		System.out.println("End:");
		
	}
	
	

}
