package com.xkumo.xfeed.yahoo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.xkumo.xfeed.yahoo.domain.YahooDailyStockDataFlagDo;
import com.xkumo.xfeed.yahoo.service.StockDataYahooFlagService;

public class StockDataYahooFlagAction {
	public String toListStockDataYahooFlagPage() throws IOException
	{
			return Action.SUCCESS;
	}
	
	private Map<String, Object> listStockDataYahooFlagMap = new HashMap<String, Object>();
	

	public Map<String, Object> getListStockDataYahooFlagMap() {
		return listStockDataYahooFlagMap;
	}

	public void setListStockDataYahooFlagMap(
			Map<String, Object> listStockDataYahooFlagMap) {
		this.listStockDataYahooFlagMap = listStockDataYahooFlagMap;
	}

	public String listStockDataYahooFlag()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        StockDataYahooFlagService service = new StockDataYahooFlagService();
        
        try
        {
        	int limit = Integer.valueOf(request.getParameter("limit") == null ? "10" : request.getParameter("limit"));// 每页显示数量
            int pageIndex = Integer.valueOf(request.getParameter("start") == null ? "0" : request.getParameter("start"));// 开始条数
            
            listStockDataYahooFlagMap = new HashMap<String, Object>();
            
            String stockcode = request.getParameter("s_stockcode");
            String stockname = request.getParameter("s_stockname");
            //String date = request.getParameter("s_date");
            
            int totalCount = service.countStock(stockcode,stockname);
            
            List<YahooDailyStockDataFlagDo> list = service.listDate(stockcode, stockname, pageIndex, limit);
            success = true;
            message = "";
            listStockDataYahooFlagMap.put("total", totalCount);
            listStockDataYahooFlagMap.put("root", list);
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        listStockDataYahooFlagMap.put("message", message);
        listStockDataYahooFlagMap.put("success", success);

        return Action.SUCCESS;
    }
	
	private Map<String, Object> updateStockInfoYahooFlagMap = new HashMap<String, Object>();

	public Map<String, Object> getUpdateStockInfoYahooFlagMap() {
		return updateStockInfoYahooFlagMap;
	}

	public void setUpdateStockInfoYahooFlagMap(
			Map<String, Object> updateStockInfoYahooFlagMap) {
		this.updateStockInfoYahooFlagMap = updateStockInfoYahooFlagMap;
	}

	/***
	 * 获取或更新股票
	 * @return
	 */
	public String updateStockInfoYahooFlag()
	{
		//HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        StockDataYahooFlagService service = new StockDataYahooFlagService();
        
        try
        {
            
        	updateStockInfoYahooFlagMap = new HashMap<String, Object>();
            
            service.updateStockInfoYahooFlag();
            success = true;
            message = "";
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        updateStockInfoYahooFlagMap.put("message", message);
        updateStockInfoYahooFlagMap.put("success", success);

        return Action.SUCCESS;
	}
	
	private Map<String, Object> deleteStockDataYahooFlagAllMap = new HashMap<String, Object>();

	
	public Map<String, Object> getDeleteStockDataYahooFlagAllMap() {
		return deleteStockDataYahooFlagAllMap;
	}

	public void setDeleteStockDataYahooFlagAllMap(
			Map<String, Object> deleteStockDataYahooFlagAllMap) {
		this.deleteStockDataYahooFlagAllMap = deleteStockDataYahooFlagAllMap;
	}

	/***
	 * 删除全部数据
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:09:01
	 * @throws ClassNotFoundException 
	 */
	public String deleteStockDataYahooFlagAll() throws ClassNotFoundException {
		
		boolean success = false;
        String message = "";
        
        StockDataYahooFlagService service = new StockDataYahooFlagService();
                
		try {
			deleteStockDataYahooFlagAllMap = new HashMap<String, Object>();
			
			service.deleteData();
            success = true;
            message = "";
            
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
		deleteStockDataYahooFlagAllMap.put("message", message);
		deleteStockDataYahooFlagAllMap.put("success", success);

        return Action.SUCCESS;
	}
}
