package com.xkumo.core.task;

/**
 * 任务有暂停和继续的功能
 * @author xvting
 *
 */
public interface ITaskPauseable {
	
	/**
	 * 暂停
	 */
	public void pause();
	
	/**
	 * 继续执行
	 */
	public void resume();
}
