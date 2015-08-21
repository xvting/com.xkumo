package com.xkumo.xstock.keyword.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.xkumo.xstock.keyword.domain.KeywordLinkDo;
import com.xkumo.xstock.keyword.domain.KeywordLinkDoShow;
import com.xkumo.xstock.keyword.service.KeywordLinkService;

public class KeywordLinkAction extends ActionSupport {
	public String toListKeywordLinkPage() throws IOException
	{
			return Action.SUCCESS;
	}
	
	private Map<String, Object> listKeywordLinkMap = new HashMap<String, Object>();
	
	

	public Map<String, Object> getListKeywordLinkMap() {
		return listKeywordLinkMap;
	}



	public void setListKeywordLinkMap(Map<String, Object> listKeywordLinkMap) {
		this.listKeywordLinkMap = listKeywordLinkMap;
	}


	public String listKeywordLink()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        KeywordLinkService service = new KeywordLinkService();
        
        try
        {
        	listKeywordLinkMap = new HashMap<String, Object>();
            int totalCount = 10000;
            
            String keyword = request.getParameter("s_keyword");
            String stockcode = request.getParameter("s_stockcode");
            String stockname = request.getParameter("s_stockname");
            
            //boolean isresolved = Boolean.parseBoolean(request.getParameter("s_isresolved")) ;
            
            List<KeywordLinkDoShow> list = service.listData(keyword,stockcode,stockname);
            success = true;
            message = "";
            listKeywordLinkMap.put("total", totalCount);
            listKeywordLinkMap.put("root", list);
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        listKeywordLinkMap.put("message", message);
        listKeywordLinkMap.put("success", success);

        return Action.SUCCESS;
    }
	
	private Map<String, Object>  addKeywordLinkMap = new HashMap<String, Object>();
	
	public Map<String, Object> getAddKeywordLinkMap() {
		return addKeywordLinkMap;
	}

	public void setAddKeywordLinkMap(Map<String, Object> addKeywordLinkMap) {
		this.addKeywordLinkMap = addKeywordLinkMap;
	}

	/***
	 * 增加
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-2 下午04:57:52
	 */
	public String addKeywordLink() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		boolean success = false;
		String message = "";
	       
		KeywordLinkService service = new KeywordLinkService();
	        
		try {
		 
		 KeywordLinkDo addData = new KeywordLinkDo();
		 
		 addData.setKeywordcode(request.getParameter("keywordcode"));
		 addData.setStockcode(request.getParameter("stockcode"));
		 addData.setStockexchangecode(request.getParameter("stockexchangecode"));
		 
		 service.insertData(addData);
		 
		 success = true;
	     message = "";
			
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
		
		addKeywordLinkMap.put("message", message);
		addKeywordLinkMap.put("success", success);
	        
		return SUCCESS;
	}
	
	private Map<String, Object>  deleteKeywordLinkMap = new HashMap<String, Object>();

	public Map<String, Object> getDeleteKeywordLinkMap() {
		return deleteKeywordLinkMap;
	}

	public void setDeleteKeywordLinkMap(Map<String, Object> deleteKeywordLinkMap) {
		this.deleteKeywordLinkMap = deleteKeywordLinkMap;
	}



	/***
	 * 删除全部数据
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:09:01
	 * @throws ClassNotFoundException 
	 */
	public String deleteKeywordLink() throws ClassNotFoundException {
		
		boolean success = false;
		String message = "";
		KeywordLinkService service = new KeywordLinkService();
		
		try {
			service.deleteData();
			success = true;
			message = "";
			
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
			
		deleteKeywordLinkMap.put("message", message);
		deleteKeywordLinkMap.put("success", success);
	        
		return SUCCESS;
	}
}
