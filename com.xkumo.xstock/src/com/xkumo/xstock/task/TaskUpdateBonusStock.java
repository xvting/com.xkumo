package com.xkumo.xstock.task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.xkumo.core.task.ITaskProgressable;
import com.xkumo.core.task.ITaskTerminatable;
import com.xkumo.core.task.TaskBase;
import com.xkumo.xstock.core.TaskEnum;
import com.xkumo.xstock.core.data.StockInfo;
import com.xkumo.xstock.service.StockBonusService;
import com.xkumo.xstock.service.StockService;
import com.xkumo.xstock.sina.domain.SinaDailyStockDataDo;
import com.xkumo.xstock.stock.domain.StockDo;
import com.xkumo.xstock.system.DBFactory;
import com.xkumo.util.DateFactory;
import com.xkumo.vendor.sina.SinaDailyStockData;
import com.xkumo.vendor.sina.SinaVendor;



public class TaskUpdateBonusStock extends TaskBase implements ITaskTerminatable,ITaskProgressable {
		
	
		private TaskManager m_TaskManager;
		private boolean m_Terminate;
		private int m_Progress;
		
		private StockService stockService;
		private StockBonusService stockBonusService;
		
		private String m_ProgressDescription;
		
		public TaskUpdateBonusStock(TaskManager pTaskManager)
		{
			this.m_executing = false;
			this.m_taskID = TaskEnum.UpdateBonusStock.getID();
			this.m_taskName = TaskEnum.UpdateBonusStock.getName();
			this.m_taskDescription = TaskEnum.UpdateBonusStock.getName();
			
			stockService = new StockService();
			stockBonusService = new StockBonusService();
			
			m_TaskManager = pTaskManager;
			m_Terminate = false;
			m_Progress = 0;
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
			m_Progress = 0;
			m_lastStartTime = new Date();
			m_lastFinishTime = null;

			int count = 0;
			try
			{
				count = updateBonusStock();
				
				this.m_lastFinishTime= new Date();
				
				if(m_Terminate)
				{
					m_TaskManager.saveTaskExecuteLog(m_taskID, m_taskName, m_taskDescription, 
							m_lastStartTime, m_lastFinishTime, true, "被中断完成", "处理[" + count + "]条");
				}
				else
				{
					m_TaskManager.saveTaskExecuteLog(m_taskID, m_taskName, m_taskDescription, 
						m_lastStartTime, m_lastFinishTime, true, "正常完成", "处理[" + count + "]条");
				}
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
				m_Terminate = false;
			}
		}
		
		public void terminate() {
			// TODO Auto-generated method stub
			if(m_executing)
			{
				m_Terminate = true;
			}
			else
			{
				m_Terminate = false;
			}
		}
		
		public int getExecuteProgress() {
			// TODO Auto-generated method stub
			return m_Progress;
		}

	   
	   
	   private int updateBonusStock() throws Exception {
			// TODO Auto-generated method stub
			
		   int resultCount = 0;
		   
		   List<StockInfo> stockInfoList = new ArrayList<StockInfo>();
		   
	   	 	Connection connection = null;

	        Statement sSta = null;
	  	
	        ResultSet resultSet =null;
	        List<StockDo> result = new ArrayList<StockDo>();

	        connection = DBFactory.CreateConnection();
	        
	       String dataString = DateFactory.ConvertToString(new Date());
	        
	        try
		    {
	        	stockInfoList = stockBonusService.getStockBonusDayList(dataString,stockService, connection);
	        	
		        sSta = connection.createStatement();
		        
		        String delSQL = "DELETE FROM STOCKBONUS WHERE BONUSDAY = '"+ dataString +"'";
		        
		        sSta.executeUpdate(delSQL); 
		        
		        if (stockInfoList == null)
		        {
		        	//Do Nothing
		        }
		        else
		        {
		        	for(StockInfo info :stockInfoList )
		        	{
		        		String insertSql = "insert into StockBonus " 
		        			+ " (StockCode, stockName,stockExchangeCode, " 
	
		        			+ " BonusDay, "
		        			+ " createtime,updatetime) " 
		        			 + " values (" 
		        			 + " '" + info.getStockCode() + "' "
			        		 + ",  '" + info.getStockName() + "' "
			        		 + ",  '" + info.getStockExchangeCode() + "' "
			        		 
			        		 + ",  '" + dataString + "' "
			        		 
			        		 +", now(), now())" ;
		        		
		        		sSta.executeUpdate(insertSql); 
		        		
		        		resultCount++;
			        	m_Progress++;
		        	}
		        }
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
	       
		    return resultCount;
		}

		public String getExecuteProgressDescription() {
			// TODO Auto-generated method stub
			return m_ProgressDescription;
		}


		
		
}
