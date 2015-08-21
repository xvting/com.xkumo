package com.xkumo.xstock.webservice;

import com.xkumo.xstock.application.App;
import com.xkumo.xstock.task.TaskManager;

public class WebService {

		public void startTask(String taskID)
		{
			TaskManager tm = App.getTaskManager(); 
			
			tm.StartTaskByID(taskID);
		}
		
		public void stopTask(String taskID)
		{
			
		}
}
