package com.xkumo.xstock.task.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.xkumo.core.task.ITask;
import com.xkumo.xstock.application.App;
import com.xkumo.xstock.task.TaskManager;

public class TaskAction extends ActionSupport {
	
	public String toListTaskPage() throws IOException
	{
			return Action.SUCCESS;
	}
	
	private Map<String, Object> listTaskMap = new HashMap<String, Object>();
	
	public Map<String, Object> getListTaskMap() {
		return listTaskMap;
	}

	public void setListTaskMap(Map<String, Object> listTaskMap) {
		this.listTaskMap = listTaskMap;
	}

	public String listTask()
    {
        boolean success = false;
        String message = "";
        
        try
        {
        	List<ITask> list = App.getTaskManager().getTaskList();
            success = true;
            message = "";
            listTaskMap.put("total", list.size());
            listTaskMap.put("root", list);
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        listTaskMap.put("message", message);
        listTaskMap.put("success", success);

        return Action.SUCCESS;
    }
	
	private Map<String, Object>  executeTaskForIDMap = new HashMap<String, Object>();

	public Map<String, Object> getExecuteTaskForIDMap() {
		return executeTaskForIDMap;
	}

	public void setExecuteTaskForIDMap(Map<String, Object> executeTaskForIDMap) {
		this.executeTaskForIDMap = executeTaskForIDMap;
	}

	/***
	 * 根据ID执行任务处理
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:05:41
	 * @throws ClassNotFoundException 
	 */
	public String executeTaskForID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		boolean success = false;
	    String message = "";
		String idStr = request.getParameter("deleteID");
		
		
		try {
			
			TaskManager taskManager = App.getTaskManager();
			
			idStr = idStr.replace("[", "").replace("]", "").replace("\"", "");
			String[] strList = idStr.split(",");
			if (strList.length > 0) {
				for (int i = 0; i < strList.length; i++) {
					taskManager.StartTaskByID(strList[i]);
				}
			}
			
			success = true;
			message = "";
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
			
		executeTaskForIDMap.put("message", message);
		executeTaskForIDMap.put("success", success);
	        
		return SUCCESS;
	}
	
	private Map<String, Object>  terminateTaskForIDMap = new HashMap<String, Object>();

	public Map<String, Object> getTerminateTaskForIDMap() {
		return terminateTaskForIDMap;
	}

	public void setTerminateTaskForIDMap(Map<String, Object> terminateTaskForIDMap) {
		this.terminateTaskForIDMap = terminateTaskForIDMap;
	}

	/***
	 * 根据ID终止任务处理
	 * 
	 * @return
	 * @author ZhuChenChong create on 2014-1-1 下午05:05:41
	 * @throws ClassNotFoundException 
	 */
	public String terminateTaskForID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		boolean success = false;
	    String message = "";
		String idStr = request.getParameter("deleteID");
		
		
		try {
			
			TaskManager taskManager = App.getTaskManager();
			
			idStr = idStr.replace("[", "").replace("]", "").replace("\"", "");
			String[] strList = idStr.split(",");
			if (strList.length > 0) {
				for (int i = 0; i < strList.length; i++) {
					taskManager.TerminateTaskByID(strList[i]);
				}
			}
			
			success = true;
			message = "";
		} catch (Exception e) {
			message = e.getMessage();
            success = false;
            e.printStackTrace();
		}
			
		terminateTaskForIDMap.put("message", message);
		terminateTaskForIDMap.put("success", success);
	        
		return SUCCESS;
	}

}
