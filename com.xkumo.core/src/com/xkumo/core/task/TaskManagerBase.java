package com.xkumo.core.task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManagerBase {
	
	private Map<String, ITask>  m_TaskMap ;
	
	public TaskManagerBase()
	{
		m_TaskMap = new HashMap<String, ITask>();
	}
	
	public void add(ITask task)
	{
		m_TaskMap.put(task.getTaskID(), task);
	}
	/***
	 * 
	 * @param id
	 * @return
	 * @throws DocumentException 
	 */
	protected ITask getTaskByID(String id)
	{
		if(this.m_TaskMap.containsKey(id))
		{
			return m_TaskMap.get(id);
		}
		else
		{
			return null;
		}
	}
	
	public List<ITask> getTaskList()  {
		// TODO Auto-generated method stub
		List<ITask> result = new ArrayList<ITask>();
		
		for(Map.Entry<String, ITask> entry:this.m_TaskMap.entrySet()){    
		     result.add(entry.getValue());
		}   

		return result;
	}
	
	/**
	 * 开始执行指定任务，此方法在任务开始后马上返回
	 * @param id
	 * @throws DocumentException 
	 */
	public void StartTaskByID(String id)
	{
		TaskThreadStart thread = new TaskThreadStart(getTaskByID(id));
		
		thread.start();
	}
	
	/**
	 * 终止执行中的指定任务
	 * @param id
	 * @throws DocumentException 
	 */
	public void TerminateTaskByID(String id)
	{
		
		ITask task = getTaskByID(id);
		
		if(task != null && task instanceof ITaskTerminatable)
		{
			TaskThreadTerminate thread = new TaskThreadTerminate((ITaskTerminatable)task);
			
			thread.start();
		}
		else
		{
			//Do Nothing
		}
		
	}
}
