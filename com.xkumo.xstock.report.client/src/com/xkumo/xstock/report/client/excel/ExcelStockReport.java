/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xkumo.xstock.report.client.excel;

import com.xkumo.xstock.core.data.StockInfo;
import com.xkumo.xstock.report.client.data.SinaDailyStockDataDo;
import com.xkumo.xstock.report.client.service.ResumeStockService;
import com.xkumo.xstock.report.client.service.StockSuspensionService;
import com.xkumo.xstock.report.client.stock.EnumStockDayValueType;
import com.xkumo.xstock.report.client.util.StringHelper;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jxl.Cell;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

/**
 *
 * @author xvting
 */
public abstract class ExcelStockReport {

    protected WritableSheet reportSheet = null;

    private WritableCellFormat cellFormatText = null;

    /**
     * 在报表中添加制定日期的数据
     * @param dateFormat
     */
    public void updateDayValue(Map<String, SinaDailyStockDataDo> stockDayValueMap, String dateFormat) throws WriteException, Exception
    {
        for(String stockCode : stockDayValueMap.keySet())
        {
           Point point = this.getStockValueLocation(stockCode, dateFormat);

           if(point == null)
           {
               //DO Nothing
           }
           else
           {
               setStockDayValue(stockDayValueMap.get(stockCode), point);
           }
        }
    }

    /**
     * 获得股票值所在的位置
     * @param stockCode
     * @param dayFormat
     * @return
     */
    protected Point getStockValueLocation(String stockCode, String dayFormat)
    {
        Point dayTitleLocation = this.getDayTitleLocation(dayFormat);
        if(dayTitleLocation == null)
        {
            return null;
        }
        else
        {
            //Do Nothing
        }

        Point stockCodeLocation = this.getStockCodeLocation(stockCode);
        if(stockCodeLocation == null)
        {
            return null;
        }
        else
        {
            //Do Nothing
        }

        return new Point((int)dayTitleLocation.getX(), (int)stockCodeLocation.getY());

    }

    /**
     * 获得价格所在的列
     * @return
     */
    protected abstract int getStockPriceColumn();
            
    /**
     * 获得股票的价格位置
     * @param stockCode
     * @return
     */
    private Point getStockPriceLocation(String stockCode)
    {
        Point stockCodeLocation = this.getStockCodeLocation(stockCode);

        return new Point(getStockPriceColumn(), (int)stockCodeLocation.getY());
    }
    
    /**
     * 更新指定股票价格
     */
    public void updateStockPrice(Map<String, String> stockPriceMap) throws WriteException
    {
       for(String stockCode : stockPriceMap.keySet())
       {
           Point point = this.getStockPriceLocation(stockCode);

           if(point == null)
           {
               //DO Nothing
           }
           else
           {
               this.setStockPrice(stockPriceMap.get(stockCode), point);
           }
       }
    }

     /**
     * 添加股票到报表中
     */
    public void addStockInfo(List<StockInfo> stockInfoList) throws WriteException
    {
        Point lastStockCodeLocation = getStockCodeEndLocation();
        if(lastStockCodeLocation == null)
        {
            lastStockCodeLocation = getStockCodeStartLocation();
            lastStockCodeLocation.translate(0, -1);
        }
        else
        {
            //Do Nohting
        }

        List<String> existStockCodeList =  getStockCodeListInReport();

        List<StockInfo> addStockInfoList = new ArrayList<StockInfo>();

        for(StockInfo stockInfo : stockInfoList)
        {
            boolean find = false;
            for(String stockCode : existStockCodeList)
            {
                if(stockCode.equalsIgnoreCase(stockInfo.getStockCode()))
                {
                    find = true;
                    break;
                }
                else
                {
                    //Do Nothing
                }
            }

            if(find)
            {
                //Do Nothing
            }
            else
            {
                addStockInfoList.add(stockInfo);
            }
        }

        int currentX = (int)lastStockCodeLocation.getX();
        int currentY = (int)lastStockCodeLocation.getY() + 1;

        for(StockInfo newStockInfo : addStockInfoList)
        {
            setStockName(newStockInfo.getStockName(), new Point(currentX - 1, currentY));
            setStockCode(newStockInfo.getStockCode(), new Point(currentX, currentY));
            currentY++;
        }
    }

    /**
     * 设置数据Cell
     * @param value
     * @param location
     * @param dayValueType
     */
    public void setStockValue(String value, Point location, EnumStockDayValueType dayValueType) throws WriteException {
        Label stockValueLabel = new Label((int) location.getX(), (int) location.getY(), value, getCellFormatValueType(dayValueType));

        reportSheet.addCell(stockValueLabel);
    }

    /**
     * 根据当日走势类型获得cell的主题
     * @param dayValueType
     * @return
     * @throws jxl.write.WriteException
     */
    protected WritableCellFormat getCellFormatValueType(EnumStockDayValueType dayValueType) throws WriteException {
        WritableFont font1 = new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);

        cellFormatText = new WritableCellFormat(font1);

        if (dayValueType == EnumStockDayValueType.TOP) {
            cellFormatText.setBackground(Colour.RED);
        } else if (dayValueType == EnumStockDayValueType.TOPDOWNTOP) {
            cellFormatText.setBackground(ColourUtil.getNearestColour(255, 102, 0));
        } else if (dayValueType == EnumStockDayValueType.OPENTOP) {
            cellFormatText.setBackground(ColourUtil.getNearestColour(255, 204, 0));
        } else if (dayValueType == EnumStockDayValueType.OPENUP) {
            cellFormatText.setBackground(ColourUtil.getNearestColour(255, 255, 204));
        } else if (dayValueType == EnumStockDayValueType.OPENDOWN) {
            cellFormatText.setBackground(ColourUtil.getNearestColour(204, 204, 255));
        } else {
            //DO Nothing
        }

        return cellFormatText;
    }

     /**
     * 获得文字主题Cell
     * @return
     */
    protected WritableCellFormat getCellFormatText()
    {
        if (cellFormatText == null)
        {
            WritableFont font1 = new WritableFont(WritableFont.ARIAL,11,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);

            cellFormatText = new WritableCellFormat(font1);
        }
        else
        {
            //Do Nothing
        }

        return cellFormatText;
    }

    /**
     * 设置股票数据
     * @param value
     * @param location
     */
    protected void setStockDayValue(SinaDailyStockDataDo value, Point location) throws WriteException, Exception
    {
       EnumStockDayValueType valueType =  EnumStockDayValueType.convertFromCode(value);

       if(valueType == EnumStockDayValueType.NONE)
       {
           return ;
       }
       else if(valueType == EnumStockDayValueType.TOP || valueType == EnumStockDayValueType.TOPDOWNTOP || valueType == EnumStockDayValueType.OPENTOP )
       {
           int buynumber = value.getBuy1numberInt();
           float buyNumberT = buynumber / 100 / 10000;
           String result = String .format("%.2f",buyNumberT);
         Label stockSuspensionDaysLabel=new Label((int)location.getX(),(int)location.getY(),result,getCellFormatValueType(valueType));
         reportSheet.addCell(stockSuspensionDaysLabel);
       }
       else if(valueType == EnumStockDayValueType.OPENUP)
       {
         Label stockSuspensionDaysLabel=new Label((int)location.getX(),(int)location.getY(),"",getCellFormatValueType(valueType));
         reportSheet.addCell(stockSuspensionDaysLabel);
       }
       else if(valueType == EnumStockDayValueType.OPENDOWN )
       {
         Label stockSuspensionDaysLabel=new Label((int)location.getX(),(int)location.getY(),"",getCellFormatValueType(valueType));
         reportSheet.addCell(stockSuspensionDaysLabel);
       }

    }

    /**
     * 设置股票名称
     * @param stockName
     * @param location
     * @throws jxl.write.WriteException
     */
    protected void setStockName(String stockName, Point location) throws WriteException
    {
        Label stockNameLabel=new Label((int)location.getX(),(int)location.getY(),stockName,getCellFormatText());

        reportSheet.addCell(stockNameLabel);
    }

    /**
     * 设置股票代码
     * @param stockCode
     * @param location
     * @throws jxl.write.WriteException
     */
    protected void setStockCode(String stockCode, Point location) throws WriteException
    {
        Label stockCodeLabel=new Label((int)location.getX(),(int)location.getY(),stockCode,getCellFormatText());

        reportSheet.addCell(stockCodeLabel);
    }

    /**
     * 设置股票价格
     * @param stockName
     * @param location
     * @throws jxl.write.WriteException
     */
    protected void setStockPrice(String stockPrice, Point location) throws WriteException
    {
        Label stockPriceLabel=new Label((int)location.getX(),(int)location.getY(),stockPrice,getCellFormatText());

        reportSheet.addCell(stockPriceLabel);
    }



    /**
     * 获得已经存在报表中的所有股票代码
     * @return
     */
    public List<String> getStockCodeListInReport()
    {
        List<String> result = new ArrayList<String>();

        Point stockCodeStartLocation = getStockCodeStartLocation();

        int currentX = (int)stockCodeStartLocation.getX();
        int currentY = (int)stockCodeStartLocation.getY();

        Cell currentCell = null;

        while(true)
        {
            currentCell =  reportSheet.getCell(currentX,currentY );

            if(currentCell == null)
            {
                return null;
            }
            else
            {
                //DO Nothing
            }

           String cellContent =  currentCell.getContents();

           if(StringHelper.isEmpty(cellContent))
           {
               return result;
           }
           else
           {
               result.add(cellContent);
               currentY++;
           }
        }
    }

    /**
     * 获得日期标题的开始位置
     * @return
     */
    protected abstract Point getDayTitleStartLocation();

     /**
     * 获得指定的日期标题的位置，如果没有找到返回NULL
     * @param dayFormat
     * @return
     */
    /**
     * 获得指定的日期标题的位置，如果没有找到返回NULL
     * @param dayFormat
     * @return
     */
    protected Point getDayTitleLocation(String dayFormat)
    {
        Point dayTitleStartLocation =  getDayTitleStartLocation();

        int currentX = (int)dayTitleStartLocation.getX();
        int currentY = (int)dayTitleStartLocation.getY();

        Cell  currentCell = null;

        while(true)
        {
            currentCell =  reportSheet.getCell(currentX,currentY );

            if(currentCell == null)
            {
                return null;
            }
            else
            {
                //DO Nothing
            }

           String cellContent =  currentCell.getContents();

           if(StringHelper.isEmpty(cellContent))
           {
               return null;
           }
           else if(dayFormat.equalsIgnoreCase(cellContent))
           {
               return new Point(currentX, currentY);
           }
           else
           {
               currentX++;
           }
        }
    }

    /**
     * 获得股票编号开始位置
     * @return
     */
    protected abstract  Point getStockCodeStartLocation();

    /**
     * 获得股票编号结束的位置,如果没有就返回NULL
     * @return
     */
    private Point getStockCodeEndLocation()
    {
        Point result = null;

        Point stockCodeStartLocation = getStockCodeStartLocation();

        int currentX = (int)stockCodeStartLocation.getX();
        int currentY = (int)stockCodeStartLocation.getY();

        Cell currentCell = null;

        while(true)
        {
            currentCell =  reportSheet.getCell(currentX,currentY );

            if(currentCell == null)
            {
                return result;
            }
            else
            {
                //DO Nothing
            }

           String cellContent =  currentCell.getContents();

           if(StringHelper.isEmpty(cellContent))
           {
               return result;
           }
           else
           {
               result = new Point(currentX, currentY);

               currentY++;
           }
        }
    }

     /**
     * 获得指定StockCode的位置
     * @return
     */
    protected  Point getStockCodeLocation(String stockCode)
    {
        Point stockCodeStartLocation = getStockCodeStartLocation();

        int currentX = (int)stockCodeStartLocation.getX();
        int currentY = (int)stockCodeStartLocation.getY();

        Cell  currentCell = null;

        while(true)
        {
            currentCell =  reportSheet.getCell(currentX,currentY );

            if(currentCell == null)
            {
                return null;
            }
            else
            {
                //DO Nothing
            }

           String cellContent =  currentCell.getContents();

           if(StringHelper.isEmpty(cellContent))
           {
               return null;
           }
           else if(stockCode.equalsIgnoreCase(cellContent))
           {
               return new Point(currentX, currentY);
           }
           else
           {
               currentY++;
           }
        }
    }

}
