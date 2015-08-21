/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xkumo.xstock.report.client.service;

import java.sql.Connection;
import com.xkumo.xstock.core.data.StockInfo;
import com.xkumo.xstock.report.client.data.DBFactory;
import com.xkumo.xstock.report.client.data.SinaDailyStockDataDo;
import com.xkumo.xstock.report.client.excel.ExcelResumeStockReport;
import com.xkumo.xstock.report.client.util.StringHelper;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author xvting
 */
public class ResumeStockService {

    private StockSuspensionService stockSuspensionService = null;
    private StockService stockService = null;

    public ResumeStockService()
    {
        stockSuspensionService = new StockSuspensionService();
        stockService = new StockService();
    }

    /**
     * 生成报表
     * @param text
     * @param text0
     */
    public void report(String dataString, String templateFileFullPathAndName) throws Exception {
        if(StringHelper.isEmpty(dataString))
        {
            throw new Exception("没有录入报表数据日期");
        }

        if(StringHelper.isEmpty(templateFileFullPathAndName))
        {
            throw new Exception("没有录入报表文件");
        }

        File reportFile = new File(templateFileFullPathAndName);

        Workbook tempLateWorkBook = loadTemplateReportFile(templateFileFullPathAndName);

        jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(new File(templateFileFullPathAndName.substring(0, templateFileFullPathAndName.length()-4) + dataString + ".xls"), tempLateWorkBook);

        WritableSheet ws = getReportSheet(wwb);

        ExcelResumeStockReport report = new ExcelResumeStockReport(ws, new Point(1,8));

       List<StockInfo> stockInfoList = stockSuspensionService.getStockResumeTradeDayList(dataString);

        report.addStockInfo(stockInfoList);

        Map<String, String> stockPriceMap = new HashMap<String, String>();
        Map<String, String> stockSuspensionDaysMap = new HashMap<String, String>();
        Map<String, SinaDailyStockDataDo> stockDayValueMap = new HashMap<String, SinaDailyStockDataDo>();

        Connection connection = null;
		connection = DBFactory.CreateConnection();
        List<String> stockCodeListInReport = report.getStockCodeListInReport();
        
        try
		{
            for(String stockCode : stockCodeListInReport)
            {
                String price = stockService.getStockPrice(stockCode,  dataString, connection);
            
                stockPriceMap.put(stockCode, price);
            }
            
            for(StockInfo info : stockInfoList)
            {
                int sdays = stockSuspensionService.getStockSuspensionDays(info.getStockCode(), info.getStockExchangeCode(), dataString, connection);
            
                stockSuspensionDaysMap.put(info.getStockCode(), String.valueOf(sdays));
            }

             for(String stockCode : stockCodeListInReport)
            {
                SinaDailyStockDataDo stockDayValue = stockService.getStockRecord(stockCode,  dataString, connection);

                stockDayValueMap.put(stockCode, stockDayValue);
            }
            
        }
        finally
		{
		    if (connection != null)
            {
            	connection.close();
            }
		}


        report.updateStockPrice(stockPriceMap);
        report.updateStockSuspensionDays(stockSuspensionDaysMap);
        report.updateDayValue(stockDayValueMap, dataString);

        wwb.write();
        wwb.close();

        System.out.println("完成report");
    }

    /**
     * 读入报表文件
     * @param reportFileFullNameAndPath
     * @return
     * @throws java.io.IOException
     */
    private Workbook loadTemplateReportFile(String reportFileFullNameAndPath) throws Exception
    {
        File reportFile = new File(reportFileFullNameAndPath);
        
         Workbook result = Workbook.getWorkbook(reportFile); //创建工作簿
         
         return result;
    }

    /**
     * 获得可写报表Sheet
     * @return
     */
    private WritableSheet  getReportSheet(WritableWorkbook workBook)
    {
        WritableSheet  readsheet = workBook.getSheet("report");
        
        return readsheet;
    }

    

}
