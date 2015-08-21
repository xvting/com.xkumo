package com.xkumo.xstock.task;

import com.xkumo.core.task.ITask;
import com.xkumo.xstock.core.TaskEnum;

public class TaskTimerUpdateAG1512DataSina extends TaskTimerBase {

	public TaskTimerUpdateAG1512DataSina(TaskManager pTaskManager,
			ITask pTask, long pdelay, long pperiod) {
		super(pTaskManager, pTask, pdelay, pperiod);
		// TODO Auto-generated constructor stub
		
		this.m_executing = false;
		this.m_taskDescription = TaskEnum.ScheduleUpdateAG1512DataSina.getName();
		this.m_taskID = TaskEnum.ScheduleUpdateAG1512DataSina.getID();
		this.m_taskName = TaskEnum.ScheduleUpdateAG1512DataSina.getName();
	}

}
