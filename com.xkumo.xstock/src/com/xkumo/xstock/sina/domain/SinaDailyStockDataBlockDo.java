package com.xkumo.xstock.sina.domain;

public class SinaDailyStockDataBlockDo {
	
	private SinaDailyStockDataDo startDayStockData;
	private SinaDailyStockDataDo finishDayStockData;
	private boolean isdown;
	
	public SinaDailyStockDataBlockDo(SinaDailyStockDataDo startDayStockData, boolean isDown)
	{
		this.startDayStockData = startDayStockData;
		this.finishDayStockData = startDayStockData;
		this.isdown = isDown;
	}
	
	public SinaDailyStockDataDo getStartDayStockData() {
		return startDayStockData;
	}



	public void setStartDayStockData(SinaDailyStockDataDo startDayStockData) {
		this.startDayStockData = startDayStockData;
	}



	public SinaDailyStockDataDo getFinishDayStockData() {
		return finishDayStockData;
	}



	public void setFinishDayStockData(SinaDailyStockDataDo finishDayStockData) {
		this.finishDayStockData = finishDayStockData;
	}



	public boolean isIsdown() {
		return isdown;
	}



	public void setIsdown(boolean isdown) {
		this.isdown = isdown;
	}



	public int getChangeRage()
	{
		if(startDayStockData == null || finishDayStockData == null)
		{
			return 0;
		}
		else
		{
			int result = (int)( (Float.parseFloat(startDayStockData.getOpenprice()) - Float.parseFloat(finishDayStockData.getCurrentprice())) / Float.parseFloat(startDayStockData.getOpenprice()));
		
			return Math.abs(result);
		}
	}
	
}
