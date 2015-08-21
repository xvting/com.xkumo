package com.xkumo.core.task;

import java.util.Date;

/**
 * 任务接口
 * @author xvting
 *
 */
public interface ITask {

	/**
	 * 任务ID
	 * @return
	 */
	public String getTaskID();
	
	/**
	 * 任务名称
	 * @return
	 */
	public String getTaskName();
	
	/**
	 * 任务描述
	 * @return
	 */
	public String getTaskDescription();
	
	/**
	 * 任务是否在执行中
	 * @return
	 */
	public boolean getExecuting();
	
	/**
	 * 最新执行开始时间
	 * @return
	 */
	public Date getLastStartTime();
	
	/**
	 * 最新执行结束时间
	 * @return
	 */
	public Date getLastFinishTime();
	
	/**
	 * 执行任务
	 * @throws  
	 * @throws Exception 
	 */
	public void execute();
	
	
}
