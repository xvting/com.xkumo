package com.xkumo.xfeed.yahoo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.Action;
import com.xkumo.xfeed.yahoo.domain.YahooDailyStockDataDo;
import com.xkumo.xfeed.yahoo.service.StockDataYahooService;

public class StockDataYahooAction {
	public String toListStockDataYahooPage() throws IOException
	{
			return Action.SUCCESS;
	}
	
	private Map<String, Object> listStockDataYahooMap = new HashMap<String, Object>();
	
	public Map<String, Object> getListStockDataYahooMap() {
		return listStockDataYahooMap;
	}

	public void setListStockDataYahooMap(Map<String, Object> listStockDataYahooMap) {
		this.listStockDataYahooMap = listStockDataYahooMap;
	}

	public String listStockDataYahoo()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        StockDataYahooService service = new StockDataYahooService();
        
        try
        {
        	int limit = Integer.valueOf(request.getParameter("limit") == null ? "10" : request.getParameter("limit"));// 每页显示数量
            int pageIndex = Integer.valueOf(request.getParameter("start") == null ? "0" : request.getParameter("start"));// 开始条数
            
            listStockDataYahooMap = new HashMap<String, Object>();
            
            String stockcode = request.getParameter("s_stockcode");
            String stockname = request.getParameter("s_stockname");
            String date = request.getParameter("s_date");
            
            int totalCount = service.countStock(stockcode,stockname,date);
            
            List<YahooDailyStockDataDo> list = service.listDate(stockcode, stockname, date, pageIndex, limit);
            success = true;
            message = "";
            listStockDataYahooMap.put("total", totalCount);
            listStockDataYahooMap.put("root", list);
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        listStockDataYahooMap.put("message", message);
        listStockDataYahooMap.put("success", success);

        return Action.SUCCESS;
    }
	
	
	private Map<String, Object> updateStockDataYahooByDayMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getUpdateStockDataYahooByDayMap() {
		return updateStockDataYahooByDayMap;
	}


	public void setUpdateStockDataYahooByDayMap(
			Map<String, Object> updateStockDataYahooByDayMap) {
		this.updateStockDataYahooByDayMap = updateStockDataYahooByDayMap;
	}


	/***
	 * 获取或更新当天数据
	 * @return
	 */
	public String updateStockDataYahooByDay()
	{
		//HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        StockDataYahooService service = new StockDataYahooService();
        
        try
        {
            
        	updateStockDataYahooByDayMap = new HashMap<String, Object>();
            
            service.updateStockDataYahooByDay();
            success = true;
            message = "";
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        updateStockDataYahooByDayMap.put("message", message);
        updateStockDataYahooByDayMap.put("success", success);

        return Action.SUCCESS;
	}
	
	private Map<String, Object> deleteStockDataYahooAllMap = new HashMap<String, Object>();
	

	public Map<String, Object> getDeleteStockDataYahooAllMap() {
		return deleteStockDataYahooAllMap;
	}

	public void setDeleteStockDataYahooAllMap(
			Map<String, Object> deleteStockDataYahooAllMap) {
		this.deleteStockDataYahooAllMap = deleteStockDataYahooAllMap;
	}

	/***
	 * 删除全部数据
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:09:01
	 * @throws ClassNotFoundException 
	 */
	public String deleteStockDataYahooAll() throws ClassNotFoundException {
		
		boolean success = false;
        String message = "";
        
        StockDataYahooService service = new StockDataYahooService();
                
		try {
			deleteStockDataYahooAllMap = new HashMap<String, Object>();
			
			service.deleteData();
            success = true;
            message = "";
            
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
		deleteStockDataYahooAllMap.put("message", message);
		deleteStockDataYahooAllMap.put("success", success);

        return Action.SUCCESS;
	}
}
