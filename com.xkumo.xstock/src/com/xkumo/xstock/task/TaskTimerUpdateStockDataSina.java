package com.xkumo.xstock.task;

import com.xkumo.core.task.ITask;
import com.xkumo.xstock.core.TaskEnum;

public class TaskTimerUpdateStockDataSina extends TaskTimerBase {

	public TaskTimerUpdateStockDataSina(TaskManager pTaskManager,
			ITask pTask, long pdelay, long pperiod) {
		super(pTaskManager, pTask, pdelay, pperiod);
		// TODO Auto-generated constructor stub
		
		this.m_executing = false;
		this.m_taskDescription = TaskEnum.ScheduleUpdateStockDataSina.getName();
		this.m_taskID = TaskEnum.ScheduleUpdateStockDataSina.getID();
		this.m_taskName = TaskEnum.ScheduleUpdateStockDataSina.getName();
	}

}
