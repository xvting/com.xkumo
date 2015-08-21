package com.xkumo.xstock.keyword.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


import com.xkumo.xstock.keyword.domain.KeywordDo;
import com.xkumo.xstock.keyword.service.KeywordService;
import com.xkumo.xstock.task.domain.TaskLogDo;
import com.xkumo.xstock.task.service.TaskLogService;
import com.xkumo.xstock.todo.domain.TodoDo;
import com.xkumo.xstock.todo.service.TodoGroupService;
import com.xkumo.xstock.todo.service.TodoService;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class KeywordAction extends ActionSupport {
	public String toListKeywordPage() throws IOException
	{
			return Action.SUCCESS;
	}
	
	private Map<String, Object> listKeywordMap = new HashMap<String, Object>();
	
	

	public Map<String, Object> getListKeywordMap() {
		return listKeywordMap;
	}



	public void setListKeywordMap(Map<String, Object> listKeywordMap) {
		this.listKeywordMap = listKeywordMap;
	}


	public String listKeyword()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        KeywordService service = new KeywordService();
        
        try
        {
        	listKeywordMap = new HashMap<String, Object>();
            int totalCount = 10000;
            
            String code = request.getParameter("s_code");
            String keyword = request.getParameter("s_keyword");
            
            //boolean isresolved = Boolean.parseBoolean(request.getParameter("s_isresolved")) ;
            
            List<KeywordDo> list = service.listData(code,keyword);
            success = true;
            message = "";
            listKeywordMap.put("total", totalCount);
            listKeywordMap.put("root", list);
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        listKeywordMap.put("message", message);
        listKeywordMap.put("success", success);

        return Action.SUCCESS;
    }
	
	private Map<String, Object>  addKeywordMap = new HashMap<String, Object>();
	
	public Map<String, Object> getAddKeywordMap() {
		return addKeywordMap;
	}

	public void setAddKeywordMap(Map<String, Object> addKeywordMap) {
		this.addKeywordMap = addKeywordMap;
	}

	/***
	 * 增加
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-2 下午04:57:52
	 */
	public String addKeyword() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		boolean success = false;
		String message = "";
	       
		KeywordService service = new KeywordService();
	        
		try {
		 
		 KeywordDo addData = new KeywordDo();
		 
		 addData.setCode(request.getParameter("code"));
		 addData.setKeyword(request.getParameter("keyword"));
		
		 service.insertData(addData);
		 
		 success = true;
	     message = "";
			
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
		
		addKeywordMap.put("message", message);
		addKeywordMap.put("success", success);
	        
		return SUCCESS;
	}
	
	private Map<String, Object>  deleteKeywordMap = new HashMap<String, Object>();

	public Map<String, Object> getDeleteKeywordMap() {
		return deleteKeywordMap;
	}

	public void setDeleteKeywordMap(Map<String, Object> deleteKeywordMap) {
		this.deleteKeywordMap = deleteKeywordMap;
	}



	/***
	 * 删除全部数据
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:09:01
	 * @throws ClassNotFoundException 
	 */
	public String deleteKeyword() throws ClassNotFoundException {
		
		boolean success = false;
		String message = "";
		KeywordService service = new KeywordService();
		
		try {
			service.deleteData();
			success = true;
			message = "";
			
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
			
		deleteKeywordMap.put("message", message);
		deleteKeywordMap.put("success", success);
	        
		return SUCCESS;
	}
}
