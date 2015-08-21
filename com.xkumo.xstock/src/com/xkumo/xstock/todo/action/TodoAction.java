package com.xkumo.xstock.todo.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;


import com.xkumo.xstock.stock.domain.StockDo;
import com.xkumo.xstock.stock.service.StockService;
import com.xkumo.xstock.todo.domain.TodoDo;
import com.xkumo.xstock.todo.domain.TodoGroupDo;
import com.xkumo.xstock.todo.service.TodoGroupService;
import com.xkumo.xstock.todo.service.TodoService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.xkumo.util.DateFactory;

public class TodoAction extends ActionSupport {
	public String toListTodoPage() throws IOException
	{
			return Action.SUCCESS;
	}
	
	private Map<String, Object> listTodoMap = new HashMap<String, Object>();
	
	public Map<String, Object> getListTodoMap() {
		return listTodoMap;
	}

	public void setListTodoMap(Map<String, Object> listTodoMap) {
		this.listTodoMap = listTodoMap;
	}

	public String listTodo()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        TodoService service = new TodoService();
        
        try
        {
        	listTodoMap = new HashMap<String, Object>();
            int totalCount = 10000;
            
            String todoname = request.getParameter("s_todoname");
            String todogroupid = request.getParameter("s_todogroupid");
            String todolevel = request.getParameter("s_todolevel");
            boolean isresolved = Boolean.parseBoolean(request.getParameter("s_isresolved")) ;
            
            List<TodoDo> list = service.listData(todogroupid,todoname,todolevel,isresolved);
            success = true;
            message = "";
            listTodoMap.put("total", totalCount);
            listTodoMap.put("root", list);
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        listTodoMap.put("message", message);
        listTodoMap.put("success", success);

        return Action.SUCCESS;
    }

	private Map<String, Object>  addTodoMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getAddTodoMap() {
		return addTodoMap;
	}

	public void setAddTodoMap(Map<String, Object> addTodoMap) {
		this.addTodoMap = addTodoMap;
	}

	/***
	 * 增加
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-2 下午04:57:52
	 */
	public String addTodo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		boolean success = false;
		String message = "";
	       
		TodoService service = new TodoService();
	        
		try {
		 
		 TodoDo addData = new TodoDo();
		 
		 addData.setTodoname(request.getParameter("todoname"));
		 addData.setTodoremark(request.getParameter("todoremark"));
		 addData.setTodogroupid(request.getParameter("todogroupid"));
		 addData.setTodolevel(request.getParameter("todolevel"));
		 addData.setPlanresolvedtime(DateFactory.ConvertToData(request.getParameter("planresolvedtime")));
		 
		 service.insertData(addData);
		 
		 success = true;
	     message = "";
			
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
		
		addTodoMap.put("message", message);
		addTodoMap.put("success", success);
	        
		return SUCCESS;
	}

	private Map<String, Object>  updateTodoMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getUpdateTodoMap() {
		return updateTodoMap;
	}

	public void setUpdateTodoMap(Map<String, Object> updateTodoMap) {
		this.updateTodoMap = updateTodoMap;
	}

	/***
	 * 修改
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-2 下午04:57:59
	 */
	public String updateTodo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		boolean success = false;
		String message = "";
		TodoService service = new TodoService();
		
		
		try {
			
			TodoDo updateData = new TodoDo();
			updateData.setTodoseq(Integer.parseInt(request.getParameter("todoseq")));
			updateData.setTodoname(request.getParameter("todoname"));
			updateData.setTodoremark(request.getParameter("todoremark"));
			updateData.setTodogroupid(request.getParameter("todogroupid"));
			updateData.setTodolevel(request.getParameter("todolevel"));
			updateData.setIsresolved(Boolean.parseBoolean(request.getParameter("isresolved")));
			updateData.setPlanresolvedtime(DateFactory.ConvertToData(request.getParameter("planresolvedtime")));
			updateData.setActualresolvedtime(DateFactory.ConvertToData(request.getParameter("actualresolvedtime")));
			 
				 
			service.updateData(updateData);
			 
			 success = true;
		     message = "";
				
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
			
		updateTodoMap.put("message", message);
		updateTodoMap.put("success", success);
	        
		return SUCCESS;
	}
	
	private Map<String, Object>  deleteTodoForIDMap = new HashMap<String, Object>();
	
	
	
	public Map<String, Object> getDeleteTodoForIDMap() {
		return deleteTodoForIDMap;
	}

	public void setDeleteTodoForIDMap(Map<String, Object> deleteTodoMap) {
		this.deleteTodoForIDMap = deleteTodoMap;
	}

	/***
	 * 根据UUID删除数据
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:05:41
	 * @throws ClassNotFoundException 
	 */
	public String deleteTodoForID() throws ClassNotFoundException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String idStr = request.getParameter("deleteID");
		
		boolean success = false;
		String message = "";
		TodoService service = new TodoService();
		
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
			
		deleteTodoForIDMap.put("message", message);
		deleteTodoForIDMap.put("success", success);
	        
		return SUCCESS;
	}
	
	
	private Map<String, Object>  deleteTodoMap = new HashMap<String, Object>();
	

	public Map<String, Object> getDeleteTodoMap() {
		return deleteTodoMap;
	}

	public void setDeleteTodoMap(Map<String, Object> deleteTodoMap) {
		this.deleteTodoMap = deleteTodoMap;
	}

	/***
	 * 删除全部数据
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:09:01
	 * @throws ClassNotFoundException 
	 */
	public String deleteTodo() throws ClassNotFoundException {
		
		boolean success = false;
		String message = "";
		TodoService service = new TodoService();
		
		try {
			service.deleteTodoGroup("");
			success = true;
			message = "";
			
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
			
		deleteTodoMap.put("message", message);
		deleteTodoMap.put("success", success);
	        
		return SUCCESS;
	}
}
