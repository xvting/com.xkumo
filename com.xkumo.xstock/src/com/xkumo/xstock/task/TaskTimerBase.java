package com.xkumo.xstock.task;

import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.xkumo.core.task.ITask;
import com.xkumo.core.task.ITaskProgressable;
import com.xkumo.core.task.ITaskTerminatable;
import com.xkumo.core.task.TaskBase;

public abstract class TaskTimerBase extends TaskBase  implements ITaskProgressable, ITaskTerminatable{
	
	protected TaskManager m_TaskManager;
	protected Timer executeTimer;
	protected ExecuteTimerTask executeTimerTask = null;
	protected ITask task = null;
	
	
	
	protected long delay = 1 * 60; 
	protected long period = 60 * 60;
	
	public TaskTimerBase(TaskManager pTaskManager, ITask pTask, long pdelay, long pperiod)
	{
		m_TaskManager = pTaskManager;
		task = pTask;
		this.delay = pdelay;
		this.period = pperiod;
		
		this.m_executing = false;
		this.m_taskDescription = "定时执行处理";
		this.m_taskID = "";
		this.m_taskName = "定时执行处理";
		
		
		executeTimer = null;
		executeTimerTask = null;
		
		
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (m_executing)
		{
			//任务处理已经在执行中
			return;
		}
		else
		{
			//Do Nothing
		}
		
		m_executing = true;
		executeTimer = new Timer();
		executeTimerTask = new ExecuteTimerTask(task);
		m_lastStartTime = new Date();
		m_lastFinishTime = null;

		try
		{
			executeTimer.schedule(executeTimerTask, delay, period);
		}
		catch(Exception ex)
		{
			m_lastFinishTime = new Date();
			
			//保存日志
			try {
				m_TaskManager.saveTaskExecuteLog(m_taskID, m_taskName, m_taskDescription,
						m_lastStartTime, m_lastFinishTime, false, ex.getMessage(), ex.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

	public void terminate() {
		// TODO Auto-generated method stub
		if(m_executing)
		{
			try {
				
				
				executeTimerTask.terminate();
				
				if(executeTimer == null)
				{
					//Do Nothing
				}
				else
				{
					executeTimer.cancel();
					executeTimer = null;
				}
				
				m_lastFinishTime = new Date();
				
				m_TaskManager.saveTaskExecuteLog(m_taskID, m_taskName, m_taskDescription, 
						m_lastStartTime, m_lastFinishTime, true, "被终止完成", "已执行次数" + executeTimerTask.getCountRun());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				m_executing = false;
			}
		}
		else
		{
			//Do Nothing
		}
	}

	public int getExecuteProgress() {
		// TODO Auto-generated method stub
		if(executeTimerTask == null)
		{
			return 0;
		}
		else
		{
			return executeTimerTask.getCountRun();
		}
	}
	
	public String getExecuteProgressDescription()
	{
		if(executeTimerTask == null)
		{
			return "";
		}
		else
		{
			return executeTimerTask.getExecuteProgressDescription();
		}
	}
	
	private class ExecuteTimerTask extends TimerTask
	{
		private ITask task = null;
		
		private int m_CountRun = 0;
		
		public ExecuteTimerTask(ITask task)
		{
			this.task = task;
			this.m_CountRun = 0;
		}
		
		public String getExecuteProgressDescription() {
			// TODO Auto-generated method stub
			if(task == null)
			{
				return "";
			}
			else
			{
				return  "执行:" + task.getTaskName();
			}
		}

		public int getCountRun()
		{
			return m_CountRun;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			this.task.execute();
			
			this.m_CountRun++;
		}
		
		public void terminate()
		{
			if (task instanceof ITaskTerminatable)
			{
				((ITaskTerminatable)task).terminate();
			}
			else
			{
				//Do Nothing
			}
		}
		
	}

}
