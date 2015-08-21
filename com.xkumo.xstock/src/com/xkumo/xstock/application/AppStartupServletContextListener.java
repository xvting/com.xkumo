package com.xkumo.xstock.application;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xkumo.xstock.core.TaskEnum;
import com.xkumo.xstock.task.TaskManager;


/**
 * 系统启动时的处理
 * @author xvting
 *
 */
public class AppStartupServletContextListener implements ServletContextListener {

	 	
//	//定时器  
//    Timer timer = null;  
//    Timer updateStockTimer = null; 
//    Timer updateStockDataYahooFlagTimer = null;

    public void contextInitialized(ServletContextEvent event)
    {
    	//设置在App内可访问次Listener
    	App.setAppStartupServletContextListener(this);
    	
    	TaskManager taskManager = App.getTaskManager();
    	
    	taskManager.StartTaskByID(TaskEnum.ScheduleUpdateStockInfo.getID());
    	taskManager.StartTaskByID(TaskEnum.ScheduleUpdateStockDataSina.getID());
    	taskManager.StartTaskByID(TaskEnum.ScheduleUpdateStockDataYahooFlag.getID());
    	taskManager.StartTaskByID(TaskEnum.ScheduleUpdateStockDataYahoo.getID());
    	taskManager.StartTaskByID(TaskEnum.ScheduleUpdateAG1512DataSina.getID());
//    	// TODO Auto-generated method stub
//		timer = new Timer();  
//		updateStockTimer = new Timer();
//		updateStockDataYahooFlagTimer = new Timer();
//        event.getServletContext().log("定时器已启动");  
//        //设置在每晚19:15分执行任务  
////        Calendar calendar = Calendar.getInstance();  
////        calendar.set(Calendar.HOUR_OF_DAY, 16);  
////        calendar.set(Calendar.MINUTE, 16);  
////        calendar.set(Calendar.SECOND, 0);  
////        Date date = calendar.getTime();  
//        updateStockDataYahooFlagTimer.schedule(App.getTaskManager().getUpdateStockDataYahooFlagTask(), 10 * 60 * 1000, 24* 60*60 * 1000); 
//        updateStockTimer.schedule(App.getTaskManager().getUpdateStockTask(), 20 * 60 * 1000, 12* 60*60 * 1000); 
//         timer.schedule(App.getTaskManager().getSinaDataTask(), 30 * 60 * 1000, 4* 60*60 * 1000);  
//        event.getServletContext().log("已经添加任务调度表");   
    }

    public void contextDestroyed(ServletContextEvent event)
    {
    	// TODO Auto-generated method stub
//		timer.cancel();  
//		updateStockTimer.cancel();
//		updateStockDataYahooFlagTimer.cancel();
//        event.getServletContext().log("定时器以销毁");  
	}


}
