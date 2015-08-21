package com.xkumo.core.task;

public class TaskThreadTerminate  extends Thread {
	
	
	private ITaskTerminatable m_task;
	
	public TaskThreadTerminate(ITaskTerminatable task)
	{
		m_task = task;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		m_task.terminate();
	}
}
