package com.xkumo.xfeed.todo.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.xkumo.xfeed.todo.domain.TodoGroupDo;
import com.xkumo.xfeed.todo.service.TodoGroupService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TodoGroupAction extends ActionSupport {
	public String toListTodoGroupPage() throws IOException
	{
			return Action.SUCCESS;
	}
	
	private Map<String, Object> listTodoGroupMap = new HashMap<String, Object>();
	
	public Map<String, Object> getListTodoGroupMap() {
		return listTodoGroupMap;
	}

	public void setListTodoGroupMap(Map<String, Object> listTodoGroupMap) {
		this.listTodoGroupMap = listTodoGroupMap;
	}

	public String listTodoGroup()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        TodoGroupService service = new TodoGroupService();
        
        try
        {
        	listTodoGroupMap = new HashMap<String, Object>();
            int totalCount = 10000;
            
            String todogroupid = request.getParameter("s_todogroupid");
            String todogroupname = request.getParameter("s_todogroupname");
             
            List<TodoGroupDo> list = service.listData(todogroupid,todogroupname);
            success = true;
            message = "";
            listTodoGroupMap.put("total", totalCount);
            listTodoGroupMap.put("root", list);
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        listTodoGroupMap.put("message", message);
        listTodoGroupMap.put("success", success);

        return Action.SUCCESS;
    }
	
	private Map<String, Object>  addTodoGroupMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getAddTodoGroupMap() {
		return addTodoGroupMap;
	}

	public void setAddTodoGroupMap(Map<String, Object> addTodoGroupMap) {
		this.addTodoGroupMap = addTodoGroupMap;
	}

	/***
	 * 增加
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-2 下午04:57:52
	 */
	public String addTodoGroup() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		boolean success = false;
		String message = "";
	       
		TodoGroupService service = new TodoGroupService();
	        
		try {
		 
		 TodoGroupDo addData = new TodoGroupDo();
		 addData.setTodogroupid(request.getParameter("todogroupid"));
		 addData.setTodogroupname(request.getParameter("todogroupname"));
		 addData.setTodogroupremark(request.getParameter("todogroupremark"));
		 
		 service.insertData(addData);
		 
		 success = true;
	     message = "";
			
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
		
		addTodoGroupMap.put("message", message);
		addTodoGroupMap.put("success", success);
	        
		return SUCCESS;
	}

	private Map<String, Object>  updateTodoGroupMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getUpdateTodoGroupMap() {
		return updateTodoGroupMap;
	}

	public void setUpdateTodoGroupMap(Map<String, Object> updateTodoGroupMap) {
		this.updateTodoGroupMap = updateTodoGroupMap;
	}

	/***
	 * 修改
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-2 下午04:57:59
	 */
	public String updateTodoGroup() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		boolean success = false;
		String message = "";
		TodoGroupService service = new TodoGroupService();
		
		
		try {
			
			TodoGroupDo updateData = new TodoGroupDo();
			updateData.setTodogroupid(request.getParameter("todogroupid"));
			updateData.setTodogroupname(request.getParameter("todogroupname"));
			updateData.setTodogroupremark(request.getParameter("todogroupremark"));
			 
			service.updateData(updateData);
			 
			 success = true;
		     message = "";
				
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
			
		updateTodoGroupMap.put("message", message);
		updateTodoGroupMap.put("success", success);
	        
		return SUCCESS;
	}
	
	private Map<String, Object>  deleteTodoGroupForIDMap = new HashMap<String, Object>();
	
	
	
	public Map<String, Object> getDeleteTodoGroupForIDMap() {
		return deleteTodoGroupForIDMap;
	}

	public void setDeleteTodoGroupForIDMap(Map<String, Object> deleteTodoGroupMap) {
		this.deleteTodoGroupForIDMap = deleteTodoGroupMap;
	}

	/***
	 * 根据UUID删除数据
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:05:41
	 * @throws ClassNotFoundException 
	 */
	public String deleteTodoGroupForID() throws ClassNotFoundException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String idStr = request.getParameter("deleteID");
		
		boolean success = false;
		String message = "";
		TodoGroupService service = new TodoGroupService();
		
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
			
		deleteTodoGroupForIDMap.put("message", message);
		deleteTodoGroupForIDMap.put("success", success);
	        
		return SUCCESS;
	}
	
	
	private Map<String, Object>  deleteTodoGroupMap = new HashMap<String, Object>();
	

	public Map<String, Object> getDeleteTodoGroupMap() {
		return deleteTodoGroupMap;
	}

	public void setDeleteTodoGroupMap(Map<String, Object> deleteTodoGroupMap) {
		this.deleteTodoGroupMap = deleteTodoGroupMap;
	}

	/***
	 * 删除全部数据
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:09:01
	 * @throws ClassNotFoundException 
	 */
	public String deleteTodoGroup() throws ClassNotFoundException {
		
		boolean success = false;
		String message = "";
		TodoGroupService service = new TodoGroupService();
		
		try {
			service.deleteTodoGroup("");
			success = true;
			message = "";
			
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
			
		deleteTodoGroupMap.put("message", message);
		deleteTodoGroupMap.put("success", success);
	        
		return SUCCESS;
	}
}
