package com.xkumo.xstock.application;

import com.xkumo.xstock.task.TaskManager;



public class App {
	
	private static TaskManager m_TaskManager = null;
	
	/**
	 * 获得任务管理服务
	 * @return
	 * @throws DocumentException 
	 */
	public static TaskManager getTaskManager()
	{
		if (m_TaskManager == null )
		{
			m_TaskManager = new TaskManager();
		}
		else
		{
			//Do Nothing
		}
		
		return m_TaskManager;
	}
	
	private static  AppStartupServletContextListener m_AppStartupServletContextListener = null;
	/**
	 * 获得启动处理ContextListener对象
	 * @return
	 */
	public static AppStartupServletContextListener getAppStartupServletContextListener()
	{
		return m_AppStartupServletContextListener;
	}
	/**
	 * 获得启动处理ContextListener对象
	 * @return
	 */
	public static void setAppStartupServletContextListener(AppStartupServletContextListener pAppStartupServletContextListener)
	{
		m_AppStartupServletContextListener = pAppStartupServletContextListener;
	}
}
