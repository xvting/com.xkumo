package com.xkumo.xstock.stock.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.xkumo.xstock.stock.domain.StockExchangeDo;
import com.xkumo.xstock.stock.service.StockExchangeService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class StockExchangeAction extends ActionSupport {

	public String toListStockExchangePage() throws IOException
	{
			return Action.SUCCESS;
	}
	
	private Map<String, Object> listStockExchangeMap = new HashMap<String, Object>();

	public Map<String, Object> getListStockExchangeMap() {
		return listStockExchangeMap;
	}

	public void setListStockExchangeMap(Map<String, Object> listStockExchangeMap) {
		this.listStockExchangeMap = listStockExchangeMap;
	}

	public String listStockExchange()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        StockExchangeService service = new StockExchangeService();
        
        try
        {
            
        	listStockExchangeMap = new HashMap<String, Object>();
            int totalCount = 10000;
            String stockexchangecode = request.getParameter("s_stockexchangecode");
            String stockexchangename = request.getParameter("s_stockexchangename");
            List<StockExchangeDo> list = service.listStockExchange(stockexchangecode,stockexchangename);
            success = true;
            message = "";
            listStockExchangeMap.put("total", totalCount);
            listStockExchangeMap.put("root", list);
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        listStockExchangeMap.put("message", message);
        listStockExchangeMap.put("success", success);

        return Action.SUCCESS;
    }
}
