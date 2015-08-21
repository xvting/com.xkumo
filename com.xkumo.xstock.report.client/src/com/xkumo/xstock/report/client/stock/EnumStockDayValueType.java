/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xkumo.xstock.report.client.stock;

import com.xkumo.xstock.report.client.data.SinaDailyStockDataDo;

/**
 * 股票当天走势类型
 * @author xvting
 */
public enum EnumStockDayValueType {
    TOP, //开盘涨停
    TOPDOWNTOP,//T子涨停
    OPENTOP, //开盘上涨涨停
    OPENUP, //开盘上涨
    OPENDOWN, //开盘下跌
    OPENBOTTOM, //开盘下跌停
    BOTTOMUPBOTTOM, //T子跌停
    BOTTOM, //开盘跌停
    NONE;

    public static EnumStockDayValueType convertFromCode(SinaDailyStockDataDo stockRecord) throws Exception
    {
        if(stockRecord == null)
        {
            return EnumStockDayValueType.NONE;
        }
        else
        {
            //Do Nothing
        }

    	if(
                stockRecord.getSellprice().startsWith("0.00")
                && stockRecord.getOpenprice().equalsIgnoreCase(stockRecord.getTopprice())
                && stockRecord.getTopprice().equalsIgnoreCase(stockRecord.getBottomprice())
                )
    	{
    		return EnumStockDayValueType.TOP;
    	}
        else if(
                stockRecord.getSellprice().startsWith("0.00")
                && stockRecord.getOpenprice().equalsIgnoreCase(stockRecord.getTopprice())
                && stockRecord.getCurrentprice().equalsIgnoreCase(stockRecord.getTopprice())
                && !stockRecord.getTopprice().equalsIgnoreCase(stockRecord.getBottomprice())
                )
    	{
    		return EnumStockDayValueType.TOPDOWNTOP;
    	}
        else if(
                stockRecord.getSellprice().startsWith("0.00")
                && !stockRecord.getOpenprice().equalsIgnoreCase(stockRecord.getTopprice())
                && stockRecord.getCurrentprice().equalsIgnoreCase(stockRecord.getTopprice())
                )
    	{
    		return EnumStockDayValueType.OPENTOP;
    	}
         else if(
                !stockRecord.getSellprice().startsWith("0.00")
                && !stockRecord.getBuyprice().startsWith("0.00")
                && stockRecord.getLastclosepricefloat() <= stockRecord.Currentpricefloat()
                )
    	{
    		return EnumStockDayValueType.OPENUP;
    	}
        else if(
                 !stockRecord.getSellprice().startsWith("0.00")
                && !stockRecord.getBuyprice().startsWith("0.00")
                && stockRecord.getLastclosepricefloat() > stockRecord.Currentpricefloat()
                )
    	{
    		return EnumStockDayValueType.OPENDOWN;
    	}
    	else
    	{
            return EnumStockDayValueType.NONE;
    	}
    }
}
