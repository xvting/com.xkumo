/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xkumo.xstock.report.client.service;

import java.util.ArrayList;

import com.xkumo.xstock.core.data.StockInfo;
import com.xkumo.xstock.report.client.data.SinaDailyStockDataDo;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xvting
 */
public class StockBonusService {

	/**
	 * 指定日期为复牌第一交易日的股票
	 * @param dayFormatString yyyy-MM-dd
	 * @return
	 * @throws Exception
	 */
	public List<StockInfo> getStockBonusDayList(String dayFormatString, StockService stockService, Connection connection) throws Exception
	{
		StockService service = new StockService();

		if(service.isStockMarketClosed(dayFormatString))
		{
			throw new Exception(dayFormatString + "是休市");
		}
		else
		{
			//Do Nothing
		}

		//获得指定日期交易的股票
		List<StockInfo> tradeDayStrockInfo =  service.getStockInfoTradeList(dayFormatString);
        Map<String , StockInfo> map = new HashMap<String , StockInfo>();
		for(StockInfo info : tradeDayStrockInfo)
		{
			map.put(info.getStockCode() + info.getStockExchangeCode(), info);
		}

		//获得上个交易交易的股票
        String lastTradeDay = service.getLastStockMarketOpenDay(dayFormatString, 1);
		List<StockInfo> lastDayStrockInfoList =  service.getStockInfoTradeList(lastTradeDay);

        //获得半配股的股票
        List<StockInfo> bonusStockInfotList = new ArrayList<StockInfo>();

        for(StockInfo laskInfo : lastDayStrockInfoList)
        {
            StockInfo todayInfo = map.get(laskInfo.getStockCode()+ laskInfo.getStockExchangeCode());

            if(todayInfo == null)
            {
                continue;
            }
            else
            {
                //Do nothing
            }

           SinaDailyStockDataDo todayStockRecord =  stockService.getStockRecord(todayInfo.getStockCode(),  dayFormatString, connection);
           if (todayStockRecord == null ) 
           {
               continue;
           }
           else if (todayStockRecord.getStockname().startsWith("DR"))
           {
               bonusStockInfotList.add(todayInfo);
               continue;
           }
           else
           {
               //Do Nothing
           }
           
            SinaDailyStockDataDo lastStockRecord =  stockService.getStockRecord(laskInfo.getStockCode(),  lastTradeDay, connection);
            if(lastStockRecord == null) continue;



            float todayOpenPrice = todayStockRecord.getOpenpricefloat();
            float lastClosePrice = lastStockRecord.Currentpricefloat();

            if((todayOpenPrice/lastClosePrice)<=0.6)
            {
                bonusStockInfotList.add(todayInfo);
            }
            else
            {
                continue;
            }

        }
        return bonusStockInfotList;

	}
}
