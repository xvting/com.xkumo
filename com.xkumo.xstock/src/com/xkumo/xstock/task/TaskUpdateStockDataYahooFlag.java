package com.xkumo.xstock.task;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.TimerTask;

import com.xkumo.core.task.TaskBase;
import com.xkumo.xstock.core.TaskEnum;
import com.xkumo.xstock.system.DBFactory;


public class TaskUpdateStockDataYahooFlag extends TaskBase {

	private TaskManager m_TaskManager;
	
	public TaskUpdateStockDataYahooFlag(TaskManager pTaskManager)
	{
		this.m_executing = false;
		this.m_taskDescription = "更新雅虎股票数据下载标志";
		this.m_taskID = TaskEnum.UpdateStockDataYahooFlag.getID();
		this.m_taskName = "更新雅虎股票数据下载标志";
		
		m_TaskManager = pTaskManager;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if (m_executing)
		{
			//任务处理已经在执行中
			return;
		}
		else
		{
			//Do Nothing
		}
		
		m_executing = true;
		
		m_lastStartTime = new Date();
		m_lastFinishTime = null;

		int count = 0;
		
		try
		{
			count = updateStockInfoYahooFlag();
			
			this.m_lastFinishTime= new Date();
			
			m_TaskManager.saveTaskExecuteLog(m_taskID, m_taskName, m_taskDescription, 
					m_lastStartTime, m_lastFinishTime, true, "完成", "更新["+ count +"]条");
			//保存日志
		}
		catch(Exception ex)
		{
			m_lastFinishTime = new Date();
			
			//保存日志
			try {
				m_TaskManager.saveTaskExecuteLog(m_taskID, m_taskName, m_taskDescription,
						m_lastStartTime, m_lastFinishTime, false, ex.getMessage(), ex.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		finally
		{
			m_executing = false;
		}
	}
	
	public int updateStockInfoYahooFlag() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
        sql.append("insert into stockdatayahooflag  (stockname, stockcode, stockexchangecode, DataLoadCount,CreateTime,UpdateTime) ");
        sql.append(" SELECT s.stockname , s.stockcode, s.stockexchangecode , 0, now(), now() FROM  stock s  ");
        sql.append(" where ( s.stockname, s.stockcode, s.stockexchangecode ) not in ( ");
        sql.append(" select stockname, stockcode, stockexchangecode from stockdatayahooflag) ");
        // sql.append(" order by t.updatetime");
        
   	 	Connection connection = null;

        Statement sSta = null;

        connection = DBFactory.CreateConnection();
        
        int resultCount = 0;
        
        try
	    {
        	sSta = connection.createStatement();
        	
        	resultCount = sSta.executeUpdate(sql.toString());
        	
	    }
        finally
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
	           
	        }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	        }
	    }
       
	    return resultCount;
	}
	

}
