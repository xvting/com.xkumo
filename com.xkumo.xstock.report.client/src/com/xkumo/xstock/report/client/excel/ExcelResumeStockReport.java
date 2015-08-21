/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xkumo.xstock.report.client.excel;


import java.awt.Point;
import java.util.Map;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

/**
 *
 * @author xvting
 */
public class ExcelResumeStockReport  extends ExcelStockReport {

    
    private Point startLocation = null;

    public ExcelResumeStockReport(WritableSheet pReportSheet, Point pStartLocation)
    {
        reportSheet = pReportSheet;
        startLocation = pStartLocation;
    }

    /**
     * 更新指定股票停牌日数
     * @param stockSuspensionDaysMap
     * @throws jxl.write.WriteException
     */
    public void updateStockSuspensionDays(Map<String, String> stockSuspensionDaysMap) throws WriteException
    {
        for(String stockCode : stockSuspensionDaysMap.keySet())
        {
           Point point = this.getStockSuspensionDaysLocation(stockCode);

           if(point == null)
           {
               //DO Nothing
           }
           else
           {
               setStockSuspensionDays(stockSuspensionDaysMap.get(stockCode), point);
           }
        }
    }

    /**
     * 设置停牌日数据
     * @param days
     * @param location
     * @throws jxl.write.WriteException
     */
    private void setStockSuspensionDays(String days, Point location) throws WriteException
    {
        Label stockSuspensionDaysLabel=new Label((int)location.getX(),(int)location.getY(),days,getCellFormatText());

        reportSheet.addCell(stockSuspensionDaysLabel);
    }

    private Point getStockSuspensionDaysLocation(String stockCode)
    {
        Point stockCodeLocation = this.getStockCodeLocation(stockCode);

        return new Point(getStockSuspensionDaysColumn(), (int)stockCodeLocation.getY());
    }

//    /**
//     * 获得股票名列Index
//     * @return
//     */
//    private int getStockNameColumn()
//    {
//        return (int)startLocation.getX();
//    }

    /**
     * 获得股票编号列Index
     * @return
     */
    private int getStockCodeColumn()
    {
        return (int)startLocation.getX() + 1;
    }

    /**
     * 获得股票编号开始位置
     * @return
     */
    @Override
    protected   Point getStockCodeStartLocation()
    {
        return new Point(getStockCodeColumn(), (int)startLocation.getY() + 1);
    }

    /**
     * 获得日期标题的开始位置
     * @return
     */
    @Override
    protected Point getDayTitleStartLocation()
    {
        Point dayTitleStartLocation =  startLocation.getLocation();

        dayTitleStartLocation.translate(7, 0);

        return dayTitleStartLocation;
    }    


    /**
     * 获得价格所在的列
     * @return
     */
    @Override
    protected int getStockPriceColumn() {
        return (int)startLocation.getX() + 3;
    }

    /**
     * 获得停牌日数列索引
     * @return
     */
    private int getStockSuspensionDaysColumn()
    {
        return (int)startLocation.getX() + 4;
    }

   

}
