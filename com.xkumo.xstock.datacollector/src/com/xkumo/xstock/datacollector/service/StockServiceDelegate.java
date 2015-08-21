package com.xkumo.xstock.datacollector.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.xkumo.util.DateFactory;
import com.xkumo.vendor.IVendor;
import com.xkumo.vendor.sina.SinaDailyStockData;
import com.xkumo.vendor.sina.SinaVendor;
import com.xkumo.xstock.core.data.StockInfo;
import com.xkumo.xstock.datacollector.system.DBFactory;

@javax.jws.WebService(targetNamespace = "http://service.datacollector.xstock.xkumo.com/", serviceName = "StockServiceService", portName = "StockServicePort")
public class StockServiceDelegate {

	com.xkumo.xstock.datacollector.service.StockService stockService = new com.xkumo.xstock.datacollector.service.StockService();

	public SinaDailyStockData getData(String stockcode, String stockexchangecode)
			throws Exception {
		return stockService.getData(stockcode, stockexchangecode);
	}

	public boolean isStockMarketClosed(String dayFormatString) throws Exception {
		return stockService.isStockMarketClosed(dayFormatString);
	}


	public void main(String[] args) throws Exception {
		stockService.main(args);
	}

}