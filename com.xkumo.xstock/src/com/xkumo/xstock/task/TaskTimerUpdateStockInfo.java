package com.xkumo.xstock.task;

import com.xkumo.core.task.ITask;
import com.xkumo.xstock.core.TaskEnum;


public class TaskTimerUpdateStockInfo extends TaskTimerBase  {

	
	public TaskTimerUpdateStockInfo(TaskManager pTaskManager, ITask pTask, long pdelay, long pperiod)
	{
		super(pTaskManager,pTask, pdelay, pperiod );
		
		this.m_executing = false;
		this.m_taskDescription = TaskEnum.ScheduleUpdateStockInfo.getName();
		this.m_taskID = TaskEnum.ScheduleUpdateStockInfo.getID();
		this.m_taskName = TaskEnum.ScheduleUpdateStockInfo.getName();
	}
	
	

}
