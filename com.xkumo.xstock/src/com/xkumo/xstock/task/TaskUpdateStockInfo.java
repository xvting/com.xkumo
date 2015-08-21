package com.xkumo.xstock.task;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import com.xkumo.vendor.IStockExchangeInfo;
import com.xkumo.vendor.StockExchangeFactory;
import com.xkumo.vendor.sina.SinaDailyStockData;
import com.xkumo.vendor.sina.SinaVendor;

import com.xkumo.core.task.ITaskProgressable;
import com.xkumo.core.task.ITaskTerminatable;
import com.xkumo.core.task.TaskBase;
import com.xkumo.xstock.core.TaskEnum;
import com.xkumo.xstock.sina.domain.SinaDailyStockDataDo;
import com.xkumo.xstock.system.DBFactory;

public class TaskUpdateStockInfo extends TaskBase implements ITaskProgressable,ITaskTerminatable {

	private TaskManager m_TaskManager;
	private boolean m_Terminate;
	private int m_ExecuteProgress;
	private String m_ExecuteProgressDescription;
	
	public TaskUpdateStockInfo(TaskManager pTaskManager)
	{
		this.m_executing = false;
		this.m_taskDescription = TaskEnum.UpdateStockInfo.getName();
		this.m_taskID = TaskEnum.UpdateStockInfo.getID();
		this.m_taskName =  TaskEnum.UpdateStockInfo.getName();
		
		m_ExecuteProgress = 0;
		m_Terminate = false;
		m_TaskManager = pTaskManager;
	}
	
	public int getExecuteProgress() {
		// TODO Auto-generated method stub
		return m_ExecuteProgress;
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
		m_ExecuteProgress = 0;
		
		m_lastStartTime = new Date();
		m_lastFinishTime = null;

		try
		{
			int totalCount = refreshStockAll();
			
			this.m_lastFinishTime= new Date();
			
			if(this.m_Terminate)
			{
				m_TaskManager.saveTaskExecuteLog(m_taskID, m_taskName, m_taskDescription, 
						m_lastStartTime, m_lastFinishTime, true, "被终止完成", "处理量" + totalCount);
			}
			else
			{
				m_TaskManager.saveTaskExecuteLog(m_taskID, m_taskName, m_taskDescription, 
					m_lastStartTime, m_lastFinishTime, true, "正常完成", "处理量" + totalCount);
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
	

	public int refreshStockAll() throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		Connection connection = null;
		
		try
		{
			connection = DBFactory.CreateConnection();
			
			//��ù�Ʊ��� - �Ϻ�
			IStockExchangeInfo shanghai = StockExchangeFactory.getStockExchangInfoShanghai();
			
			List<String> shanghaiCodeList = shanghai.getStockCodeList();
			
			SinaVendor sinaVendor = new SinaVendor();

			for(int i = 0; i < shanghaiCodeList.size();i++)
			{
				if(m_Terminate) 
				{
					return result;
				}
				else
				{
					result++;
				}
				
				
				m_ExecuteProgress++;
				
				String code = shanghaiCodeList.get(i);
				
				SinaDailyStockData sinadailystockdata = sinaVendor.getDailyStockData(code, "sh");
				
				if(sinadailystockdata == null || sinadailystockdata.getStockcode() == null || sinadailystockdata.getStockcode().equalsIgnoreCase(""))
				{
					continue;
				}
				else
				{
					//Do Nothing
				}
				
				SinaDailyStockDataDo dataDO = new SinaDailyStockDataDo(sinadailystockdata);
				
				if(dataDO == null )
				{
					continue;
				}
				else
				{
					try
					{
						InsertOrUpdateStockDataDo(dataDO.getStockcode(),dataDO.getStockname(),dataDO.getStockexchangecode(), connection);
					}
					catch(Exception ex)
					{
						
					}
				}
				
			}
			

			if(m_Terminate) 
			{
				return result;
			}
			else
			{
				//Do Nothing
			}
			
			
			//��ù�Ʊ��� - ����
			IStockExchangeInfo shenzhen = StockExchangeFactory.getStockExchangInfoShenzhen();
			
			List<String> shenzhengCodeList = shenzhen.getStockCodeList();
			
			
			for(int i = 0; i < shenzhengCodeList.size();i++)
			{
				if(m_Terminate) 
				{
					return result;
				}
				else
				{
					result++;
				}
				
				m_ExecuteProgress++;
				
				String code = shenzhengCodeList.get(i);
				
				SinaDailyStockData sinadailystockdata = sinaVendor.getDailyStockData(code, "sz");
				
				SinaDailyStockDataDo dataDO = new SinaDailyStockDataDo(sinadailystockdata);
				
				if(dataDO == null )
				{
					continue;
				}
				else
				{
					try
					{
						InsertOrUpdateStockDataDo(dataDO.getStockcode(),dataDO.getStockname(),dataDO.getStockexchangecode(), connection);
					}
					catch(Exception ex)
					{
						
					}
				}
				
			}
			
		}finally
	    {
	    	try
	        {
	            if (connection != null)
	            {
	            	connection.close();
	            }
	        }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	        }
	    }

		
		
		
		return result;
	}
	
	//��������
	private void InsertOrUpdateStockDataDo(String stockCode, String stockName, String stockExchangeCode, Connection connection) throws ClassNotFoundException, SQLException
	{
		
        if(stockCode == null || stockCode.equalsIgnoreCase(""))
        {
        	return;
        }
        else
        {
        	//Do Nothing
        }
        
        if(stockName == null || stockName.equalsIgnoreCase(""))
        {
        	return;
        }
        else
        {
        	//Do Nothing
        }
        
        m_ExecuteProgressDescription = "处理股票：" + stockCode + " " + stockName;
        
        String sql = "select * from Stock  where  stockCode='" +stockCode + "' and stockName ='" +stockName + "' and stockExchangeCode = '" + stockExchangeCode+"'" ;    

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        
        try
	    {
        	  sSta = connection.createStatement();
   	        
	      
	         resultSet = sSta.executeQuery(sql);
	         
	         boolean hasValue = false;
	         
	        while (resultSet.next())
	        {
	        	hasValue = true;
	        	
	        	break;
	        	
	        }
	        
	        if(hasValue)
	        {
	        	//����
	        	 String updateSql = "update Stock set stockName = '"+stockName+"' , updatetime = now() where  stockCode = '" +stockCode + "' and  stockExchangeCode = '"+stockExchangeCode+"' ";
	        	  int updateResultCount = sSta.executeUpdate(updateSql); 
	        }
	        else
	        {
	        	
	        	//����
	        	 String insertSql = "insert into Stock (stockCode, stockName,stockExchangeCode, createtime,updatetime) values ('" +stockCode + "' ,  '" +stockName + "' ,  '" + stockExchangeCode+"', now(), now())" ;
	        	  int insertResultCount = sSta.executeUpdate(insertSql); 
	        }
	        
	    }finally
	    {
	    	try
	        {
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

	public String getExecuteProgressDescription() {
		// TODO Auto-generated method stub
		return m_ExecuteProgressDescription;
	}

	

	
}
