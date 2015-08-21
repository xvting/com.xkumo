package com.xkumo.core.task;

public interface ITaskProgressable {
	/**
	 * 任务执行进度
	 * @return
	 */
	public int getExecuteProgress();
	
	public String getExecuteProgressDescription();
}
