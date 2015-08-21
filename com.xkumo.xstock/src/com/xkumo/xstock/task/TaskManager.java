package com.xkumo.xstock.task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import com.xkumo.core.task.ITask;
import com.xkumo.core.task.TaskManagerBase;

import com.xkumo.xstock.core.TaskEnum;
import com.xkumo.xstock.system.DBFactory;
import com.xkumo.util.DateFactory;

public class TaskManager  extends TaskManagerBase{

    private TaskUpdateStockInfo m_TaskUpdateStockInfo = null;
    private TaskUpdateStockDataSina m_TaskUpdateStockDataSina = null;
    private TaskUpdateStockDataYahoo m_TaskUpdateStockDataYahoo = null;
    private TaskUpdateStockDataYahooFlag m_TaskUpdateStockDataYahooFlag = null;
    private TaskTimerUpdateStockInfo m_TaskScheduleUpdateStock = null;
    private TaskTimerUpdateStockDataSina m_TaskScheduleUpdateStockDataSina = null;
    private TaskTimerUpdateStockDataYahoo m_TaskScheduleUpdateStockDataYahoo = null;
    private TaskTimerUpdateStockDataYahooFlag m_TaskScheduleUpdateStockDataYahooFlag = null;
    
    private TaskUpdateBonusStock m_TaskUpdateBonusStock = null;
    
    private TaskUpdateAG1512DataSina m_TaskUpdateAG1512DataSina = null;
    private TaskTimerUpdateAG1512DataSina m_TaskTimerUpdateAG1512DataSina = null;
	
    public TaskManager()
    {
    	super();
    
    	m_TaskUpdateStockInfo = new TaskUpdateStockInfo(this);
        this.add(m_TaskUpdateStockInfo);
        m_TaskScheduleUpdateStock = new TaskTimerUpdateStockInfo(this, m_TaskUpdateStockInfo,  1 * 60 * 60 * 1000, 12* 60*60 * 1000);
        this.add(m_TaskScheduleUpdateStock);
        
        m_TaskUpdateStockDataSina = new TaskUpdateStockDataSina(this);
        this.add(m_TaskUpdateStockDataSina);
        m_TaskScheduleUpdateStockDataSina = new TaskTimerUpdateStockDataSina(this, m_TaskUpdateStockDataSina,30 * 60 * 1000, 4* 60*60 * 1000);
        this.add(m_TaskScheduleUpdateStockDataSina);
        
        m_TaskUpdateStockDataYahoo = new TaskUpdateStockDataYahoo(this);
        this.add(m_TaskUpdateStockDataYahoo);
        m_TaskScheduleUpdateStockDataYahoo = new TaskTimerUpdateStockDataYahoo(this,m_TaskUpdateStockDataYahoo, 60 * 60 * 1000, 72 * 60*60 * 1000);
        this.add(m_TaskScheduleUpdateStockDataYahoo);
        
        m_TaskUpdateStockDataYahooFlag = new TaskUpdateStockDataYahooFlag(this);
        this.add(m_TaskUpdateStockDataYahooFlag);
        m_TaskScheduleUpdateStockDataYahooFlag = new TaskTimerUpdateStockDataYahooFlag(this,m_TaskUpdateStockDataYahooFlag, 3 * 60* 60 * 1000, 71 * 60*60 * 1000);
        this.add(m_TaskScheduleUpdateStockDataYahooFlag);
        
        m_TaskUpdateBonusStock = new TaskUpdateBonusStock(this);
        this.add(m_TaskUpdateBonusStock);
        
        m_TaskUpdateAG1512DataSina = new TaskUpdateAG1512DataSina(this);
        this.add(m_TaskUpdateAG1512DataSina);
        m_TaskTimerUpdateAG1512DataSina = new TaskTimerUpdateAG1512DataSina(this, m_TaskUpdateAG1512DataSina, 1 * 60 * 1000, 1*59 * 1000);
        this.add(m_TaskTimerUpdateAG1512DataSina);
      
    }
    
    
	public  void saveTaskExecuteLog(String taskID, String taskName, String taskDescription, 
			Date startDate, Date endDate, 
			boolean isDone, String message, String description) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		String insertSql = "insert into TaskLog " 
   		 + " (TaskID, TaskName,TaskDescription, " 
   		 + " StartTime, EndTime, IsDone, "
   		 + " Message, Description, "
   		 + " createtime,updatetime) " 
   		 + " values (" 
   		 + " '" + taskID + "' "
   		 + ",  '" + taskName + "' "
   		 + ",  '" + taskDescription + "' "
   		 
   		 + ",  '" + DateFactory.ConvertToDatetimeString(startDate) + "' "
   		 + ",  '" + DateFactory.ConvertToDatetimeString(endDate) + "' "
   		 
   		 
   		 + ",  " + isDone + " "
   		 + ",  '" + message + "' "
   		 + ",  '" + description+ "' "
   		 +", now(), now())" ;
		
	
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
  
        connection = DBFactory.CreateConnection();
        
        
        try
	    {
        	
        	sSta = connection.createStatement();
        	
        	 int insertResultCount = sSta.executeUpdate(insertSql); 
        	 
	         
	    }finally
	    {
	    	try
	        {
	            if (connection != null)
	            {
	            	connection.close();
	            }
	            if (sSta != null)
	            {
	            	sSta.close();
	            }
	           
	            if (resultSet != null)
	            {
	            	resultSet.close();
	            }
	        }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	        }
	    }
	}
	

	/***
	 * 根据Task类型获得执行Task对象
	 * @param id
	 * @return
	 * @throws DocumentException 
	 * @throws DocumentException 
	 */
	public ITask getTaskByTaskEnum(TaskEnum taskEnum)
	{
		return getTaskByID(taskEnum.getID());
	}


	
}
