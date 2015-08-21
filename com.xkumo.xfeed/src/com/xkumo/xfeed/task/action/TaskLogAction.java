package com.xkumo.xfeed.task.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.xkumo.xfeed.task.domain.TaskLogDo;
import com.xkumo.xfeed.task.service.TaskLogService;
import com.xkumo.xfeed.todo.domain.TodoDo;
import com.xkumo.xfeed.todo.service.TodoService;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TaskLogAction extends ActionSupport {
	public String toListTaskLogPage() throws IOException
	{
			return Action.SUCCESS;
	}
	
	private Map<String, Object> listTaskLogMap = new HashMap<String, Object>();
	


	public Map<String, Object> getListTaskLogMap() {
		return listTaskLogMap;
	}



	public void setListTaskLogMap(Map<String, Object> listTaskLogMap) {
		this.listTaskLogMap = listTaskLogMap;
	}



	public String listTaskLog()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        boolean success = false;
        String message = "";
        
        TaskLogService service = new TaskLogService();
        
        try
        {
        	listTaskLogMap = new HashMap<String, Object>();
        	
        	int limit = Integer.valueOf(request.getParameter("limit") == null ? "10" : request.getParameter("limit"));// 每页显示数量
            int pageIndex = Integer.valueOf(request.getParameter("start") == null ? "0" : request.getParameter("start"));// 开始条数
          
            
            String taskid = request.getParameter("s_taskid");
            String taskname = request.getParameter("s_taskname");
            
            //boolean isresolved = Boolean.parseBoolean(request.getParameter("s_isresolved")) ;
            int totalCount = service.countStock(taskid,taskname);
            
            List<TaskLogDo> list = service.listData(taskid,taskname,pageIndex, limit);
            success = true;
            message = "";
            listTaskLogMap.put("total", totalCount);
            listTaskLogMap.put("root", list);
        }
        catch (Exception e)
        {
            message = e.getMessage();
            success = false;
            e.printStackTrace();
        }
        listTaskLogMap.put("message", message);
        listTaskLogMap.put("success", success);

        return Action.SUCCESS;
    }

}
