package com.xkumo.xstock.stock.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.xkumo.xstock.stock.domain.StockDo;
import com.xkumo.xstock.stock.service.StockExchangeService;
import com.xkumo.xstock.stock.service.StockService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class StockAction extends ActionSupport {
	
	public String toListStockPage() throws IOException
	{
			return Action.SUCCESS;
	}
	
	private Map<String, Object> listStockMap = new HashMap<String, Object>();
	private Object service;
	
	
	
	public Map<String, Object> getListStockMap() {
		return listStockMap;
	}

	public void setListStockMap(Map<String, Object> listStockMap) {
		this.listStockMap = listStockMap;
	}



	public String listStock()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        StockService service = new StockService();
        
        try
        {
        	
        	int limit = Integer.valueOf(request.getParameter("limit") == null ? "10" : request.getParameter("limit"));// 每页显示数量
            int pageIndex = Integer.valueOf(request.getParameter("start") == null ? "0" : request.getParameter("start"));// 开始条数
            
        	listStockMap = new HashMap<String, Object>();
            
            String stockcode = request.getParameter("s_stockcode");
            String stockname = request.getParameter("s_stockname");
            String stockexchangecode = request.getParameter("s_stockexchangecode");
            
            int totalCount = service.countStock(stockcode,stockname,stockexchangecode);
            
            List<StockDo> list = service.listStockByPage(stockcode, stockname, stockexchangecode, pageIndex, limit);
            success = true;
            message = "";
            listStockMap.put("total", totalCount);
            listStockMap.put("root", list);
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        listStockMap.put("message", message);
        listStockMap.put("success", success);

        return Action.SUCCESS;
    }
	
	private Map<String, Object> refreshStockAllMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getRefreshStockAllMap() {
		return refreshStockAllMap;
	}

	public void setRefreshStockAllMap(Map<String, Object> refreshStockAllMap) {
		this.refreshStockAllMap = refreshStockAllMap;
	}

	public String refreshStockAll()
	{
		//HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        StockService service = new StockService();
        
        try
        {
            
        	refreshStockAllMap = new HashMap<String, Object>();
            
            service.refreshStockAll();
            success = true;
            message = "";
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        refreshStockAllMap.put("message", message);
        refreshStockAllMap.put("success", success);

        return Action.SUCCESS;
	}
	
	private Map<String, Object> deleteStockAllMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getDeleteStockAllMap() {
		return deleteStockAllMap;
	}

	public void setDeleteStockAllMap(Map<String, Object> deleteStockAllMap) {
		this.deleteStockAllMap = deleteStockAllMap;
	}

	/***
	 * 删除全部数据
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:09:01
	 * @throws ClassNotFoundException 
	 */
	public String deleteStockAll() throws ClassNotFoundException {
		
		boolean success = false;
        String message = "";
        
        StockService service = new StockService();
        
        
		try {
			deleteStockAllMap = new HashMap<String, Object>();
			
			service.deleteStockAll();
            success = true;
            message = "";
            
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
		deleteStockAllMap.put("message", message);
		deleteStockAllMap.put("success", success);

        return Action.SUCCESS;
	}
}
