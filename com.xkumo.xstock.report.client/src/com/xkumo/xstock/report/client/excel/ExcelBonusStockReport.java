/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xkumo.xstock.report.client.excel;


import java.awt.Point;
import jxl.write.WritableSheet;

/**
 *
 * @author xvting
 */
public class ExcelBonusStockReport  extends ExcelStockReport {

    private Point startLocation = null;

    public ExcelBonusStockReport(WritableSheet pReportSheet, Point pStartLocation)
    {
        reportSheet = pReportSheet;
        startLocation = pStartLocation;
    }

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

        dayTitleStartLocation.translate(4, 0);

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

}
