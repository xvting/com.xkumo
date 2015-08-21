package com.xkumo.xstock.sina.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


import com.xkumo.xstock.sina.domain.SinaDailyStockDataDo;
import com.xkumo.xstock.sina.service.StockDataSinaService;
import com.xkumo.xstock.stock.domain.StockDo;
import com.xkumo.xstock.stock.service.StockService;
import com.xkumo.xstock.todo.service.TodoService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class StockDataSinaAction extends ActionSupport {
	public String toListStockDataSinaPage() throws IOException
	{
			return Action.SUCCESS;
	}
	
	
	private Map<String, Object> listStockDataSinaMap = new HashMap<String, Object>();
	
	public Map<String, Object> getListStockDataSinaMap() {
		return listStockDataSinaMap;
	}

	public void setListStockDataSinaMap(Map<String, Object> listStockDataSinaMap) {
		this.listStockDataSinaMap = listStockDataSinaMap;
	}

	public String listStockDataSina()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        StockDataSinaService service = new StockDataSinaService();
        
        try
        {
        	
        	int limit = Integer.valueOf(request.getParameter("limit") == null ? "10" : request.getParameter("limit"));// 每页显示数量
            int pageIndex = Integer.valueOf(request.getParameter("start") == null ? "0" : request.getParameter("start"));// 开始条数
            
            listStockDataSinaMap = new HashMap<String, Object>();
            
            String stockcode = request.getParameter("s_stockcode");
            String stockname = request.getParameter("s_stockname");
            String stockexchangecode = request.getParameter("s_stockexchangecode");
            
            int totalCount = service.countStock(stockcode,stockname,stockexchangecode);
            
            List<SinaDailyStockDataDo> list = service.listStockByPage(stockcode, stockname, stockexchangecode, pageIndex, limit);
            success = true;
            message = "";
            listStockDataSinaMap.put("total", totalCount);
            listStockDataSinaMap.put("root", list);
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        listStockDataSinaMap.put("message", message);
        listStockDataSinaMap.put("success", success);

        return Action.SUCCESS;
    }
	
	private Map<String, Object> updateStockDataSinaByDayMap = new HashMap<String, Object>();
	

	public Map<String, Object> getUpdateStockDataSinaByDayMap() {
		return updateStockDataSinaByDayMap;
	}

	public void setUpdateStockDataSinaByDayMap(
			Map<String, Object> updateStockDataSinaByDayMap) {
		this.updateStockDataSinaByDayMap = updateStockDataSinaByDayMap;
	}

	/***
	 * 获取或更新当天数据
	 * @return
	 */
	public String updateStockDataSinaByDay()
	{
		//HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        StockDataSinaService service = new StockDataSinaService();
        
        try
        {
            
        	updateStockDataSinaByDayMap = new HashMap<String, Object>();
            
            service.updateStockDataSinaByDay();
            success = true;
            message = "";
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        updateStockDataSinaByDayMap.put("message", message);
        updateStockDataSinaByDayMap.put("success", success);

        return Action.SUCCESS;
	}
	
	private Map<String, Object> deleteStockDataSinaForIDMap = new HashMap<String, Object>();
	
	public Map<String, Object> getDeleteStockDataSinaForIDMap() {
		return deleteStockDataSinaForIDMap;
	}

	public void setDeleteStockDataSinaForIDMap(
			Map<String, Object> deleteStockDataSinaForIDMap) {
		this.deleteStockDataSinaForIDMap = deleteStockDataSinaForIDMap;
	}

	/***
	 * 根据UUID删除数据
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:05:41
	 * @throws ClassNotFoundException 
	 */
	public String deleteStockDataSinaForID() throws ClassNotFoundException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String idStr = request.getParameter("deleteID");
		
		boolean success = false;
		String message = "";
		StockDataSinaService service = new StockDataSinaService();
		
		try {
			idStr = idStr.replace("[", "").replace("]", "").replace("\"", "");
			String[] strList = idStr.split(",");
			if (strList.length > 0) {
				for (int i = 0; i < strList.length; i++) {
					service.deleteDataByID(strList[i]);
				}
			}
			
			success = true;
			message = "";
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
			
		deleteStockDataSinaForIDMap.put("message", message);
		deleteStockDataSinaForIDMap.put("success", success);
	        
		return SUCCESS;
	}
	
	
	private Map<String, Object> deleteStockDataSinaAllMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getDeleteStockDataSinaAllMap() {
		return deleteStockDataSinaAllMap;
	}

	public void setDeleteStockDataSinaAllMap(
			Map<String, Object> deleteStockDataSinaAllMap) {
		this.deleteStockDataSinaAllMap = deleteStockDataSinaAllMap;
	}

	/***
	 * 删除全部数据
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:09:01
	 * @throws ClassNotFoundException 
	 */
	public String deleteStockDataSinaAll() throws ClassNotFoundException {
		
		boolean success = false;
        String message = "";
        
        StockDataSinaService service = new StockDataSinaService();
                
		try {
			deleteStockDataSinaAllMap = new HashMap<String, Object>();
			
			service.deleteStockDataSinaAll();
            success = true;
            message = "";
            
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
		deleteStockDataSinaAllMap.put("message", message);
		deleteStockDataSinaAllMap.put("success", success);

        return Action.SUCCESS;
	}
	
	
	private Map<String, Object> updateStockDataSinaMAForIDMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getUpdateStockDataSinaMAForIDMap() {
		return updateStockDataSinaMAForIDMap;
	}

	public void setUpdateStockDataSinaMAForIDMap(
			Map<String, Object> updateStockDataSinaMAForIDMap) {
		this.updateStockDataSinaMAForIDMap = updateStockDataSinaMAForIDMap;
	}

	/***
	 * 更细MA数据
	 * @return
	 */
	public String updateStockDataSinaMAForID()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String idStr = request.getParameter("deleteID");
		
		boolean success = false;
		String message = "";
		StockDataSinaService service = new StockDataSinaService();
		
		try {
			idStr = idStr.replace("[", "").replace("]", "").replace("\"", "");
			String[] strList = idStr.split(",");
			if (strList.length > 0) {
				for (int i = 0; i < strList.length; i++) {
					service.updateDataMAByID(strList[i]);
				}
			}
			
			success = true;
			message = "";
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
			
		updateStockDataSinaMAForIDMap.put("message", message);
		updateStockDataSinaMAForIDMap.put("success", success);
	        
		return SUCCESS;
	}
	
	private Map<String, Object> updateStockDataSinaAllMAMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getUpdateStockDataSinaAllMAMap() {
		return updateStockDataSinaAllMAMap;
	}

	public void setUpdateStockDataSinaAllMAMap(
			Map<String, Object> updateStockDataSinaAllMAMap) {
		this.updateStockDataSinaAllMAMap = updateStockDataSinaAllMAMap;
	}

	/***
	 * 更新全部数据的MA
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:09:01
	 * @throws ClassNotFoundException 
	 */
	public String updateStockDataSinaAllMA() throws ClassNotFoundException {
		
		boolean success = false;
        String message = "";
        
        StockDataSinaService service = new StockDataSinaService();
                
		try {
			updateStockDataSinaAllMAMap = new HashMap<String, Object>();
			
			int updateCount = service.updateStockDataSinaAllMA();
            success = true;
            message = "";
            
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
		updateStockDataSinaAllMAMap.put("message", message);
		updateStockDataSinaAllMAMap.put("success", success);

        return Action.SUCCESS;
	}
}
