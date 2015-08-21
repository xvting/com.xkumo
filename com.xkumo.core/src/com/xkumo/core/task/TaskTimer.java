package com.xkumo.core.task;

import java.util.Date;
import java.util.TimerTask;

public abstract class TaskTimer extends TimerTask implements ITask {

	protected boolean m_executing = false;
	protected String m_taskDescription ="";
	protected String m_taskID = "";
	protected String m_taskName = "";
	protected Date m_lastStartTime = null;
	protected Date m_lastFinishTime = null;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		execute();
	}

	@Override
	public abstract void execute();

	@Override
	public boolean getExecuting() {
		// TODO Auto-generated method stub
		return m_executing;
	}

	@Override
	public String getTaskDescription() {
		// TODO Auto-generated method stub
		return m_taskDescription;
	}

	@Override
	public String getTaskID() {
		// TODO Auto-generated method stub
		return m_taskID;
	}

	@Override
	public String getTaskName() {
		// TODO Auto-generated method stub
		return m_taskName;
	}
	
	@Override
	public Date getLastStartTime() {
		// TODO Auto-generated method stub
		return m_lastStartTime;
	}
	
	@Override
	public Date getLastFinishTime() {
		// TODO Auto-generated method stub
		return m_lastFinishTime;
	}

}
