package com.xkumo.core.task;

/***
 * 任务以线程方式执行
 * @author xvting
 *
 */
public class TaskThreadStart extends Thread {
	
	private ITask m_task;
	
	public TaskThreadStart(ITask task)
	{
		m_task = task;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(m_task != null)
		{
			m_task.execute();
		}
		else
		{
			//DoNothing
		}
	}
}
