package com.xkumo.xstock.task;

import java.io.IOException;
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
import com.xkumo.xstock.system.DBFactory;
import com.xkumo.xstock.yahoo.domain.YahooDailyStockDataDo;
import com.xkumo.xstock.yahoo.domain.YahooDailyStockDataFlagDo;
import com.xkumo.xstock.yahoo.service.YahooStockService;

public class TaskUpdateStockDataYahoo extends TaskBase implements ITaskTerminatable,ITaskProgressable {

	private TaskManager m_TaskManager;
	private boolean m_terminate;
	private int m_progress = 0;
	private String m_ProgressDescription = "";
	
	public TaskUpdateStockDataYahoo(TaskManager pTaskManager)
	{
		this.m_executing = false;
		this.m_taskName = TaskEnum.UpdateStockDataYahoo.getName();
		this.m_taskDescription = TaskEnum.UpdateStockDataYahoo.getName();
		this.m_taskID = TaskEnum.UpdateStockDataYahoo.getID();
		
		m_terminate = false;
		m_TaskManager = pTaskManager;
		m_progress = 0;
	}
	
	public int getExecuteProgress() {
		// TODO Auto-generated method stub
		return m_progress;
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
		m_progress = 0;
		m_lastStartTime = new Date();
		m_lastFinishTime = null;

		int count = 0;
		try
		{
			count = updateStockDataYahooByDay();
			
			this.m_lastFinishTime= new Date();
			
			if(m_terminate)
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
		}
	}

	public void terminate() {
		// TODO Auto-generated method stub
		if(m_executing)
		{
			m_terminate = true;
		}
		else
		{
			m_terminate = false;
		}
	}
	
	private int updateStockDataYahooByDay() throws ClassNotFoundException, SQLException, IOException
	{
		int result = 0;
		
		//获得更新列表Flag
		List<YahooDailyStockDataFlagDo>  flagList = getYahooDailyStockDataFlagList();
		
		if (flagList == null || flagList.size() <= 0)
		{
			return result;
		}
		else
		{
			//Do Nothing
		}
		

		for(int i= 0; i < flagList.size(); i++)
		{
			m_progress++;
			
			if(m_terminate)
			{
				break;
			}
			else
			{
				//Do Nothing
			}
			
			YahooDailyStockDataFlagDo currentFlag = flagList.get(i);
			
			//获得已下载yahoo数据的日期
			List<String> dayList = getYahooDailyStockDataDayList(currentFlag.getStockcode(),currentFlag.getStockexchangecode());
			
			//获得当前yahoo数据文件的数据
			List<YahooDailyStockDataDo> yahooDataList =null;
			
			try
			{
				yahooDataList = YahooStockService.getDailyStockData(
						currentFlag.getStockcode(),
						currentFlag.getStockname(), 
						currentFlag.getStockexchangecode());
			}
			catch(Exception yahoofileloadex)
			{
				yahoofileloadex.printStackTrace();
			}
			
			if(yahooDataList == null || yahooDataList.size() <=0) 
			{
				//Do Nothing
			}
			else
			{
				List<YahooDailyStockDataDo> insertDataList = new ArrayList<YahooDailyStockDataDo>();
				
				if(dayList == null || dayList.size() <=0)
				{
					insertDataList = yahooDataList;
				}
				else
				{
					for(int j = 0;j < yahooDataList.size(); j++)
					{
						YahooDailyStockDataDo insertData = yahooDataList.get(j);
						
						
						if(dayList.contains(insertData.getDate()))
						{
							//Do Nothing
						}
						else
						{
							insertDataList.add(insertData);
						}
					}
				}
				
				
				
				result += InsertYahooDailyStockDataDo(insertDataList);
			}
			
			//更新flag
			UpdateYahooDailyStockFlag(currentFlag.getStockcode(),currentFlag.getStockexchangecode());
		}
		
		return result;
	}
	
	//获得当前YahooDataFlag状态
	private List<YahooDailyStockDataFlagDo> getYahooDailyStockDataFlagList() throws SQLException, ClassNotFoundException
	{
		List<YahooDailyStockDataFlagDo> result = new ArrayList<YahooDailyStockDataFlagDo>();
		
		StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from stockdatayahooflag t where 1=1 order by DataLoadCount asc ");
       // sql.append(" order by t.updatetime");
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;

        connection = DBFactory.CreateConnection();
        
        try
	    {
        	sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	      
	        resultSet = sSta.executeQuery(sql.toString());
	         
	        while (resultSet.next())
	        {
	        	YahooDailyStockDataFlagDo  fms= new YahooDailyStockDataFlagDo();
	        	
	        	fms.setSeq(resultSet.getInt("seq"));
	        	fms.setStockname(resultSet.getString("STOCKNAME"));
	        	fms.setStockcode(resultSet.getString("STOCKCODE"));
	        	fms.setStockexchangecode(resultSet.getString("STOCKEXCHANGECODE"));
	        	
	        	fms.setDataloadcount(resultSet.getInt("dataloadcount"));
	        	
	            result.add(fms);
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
        
		return result;
	}
	
	private List<String> getYahooDailyStockDataDayList(String stockcode, String stockexchangecode) throws ClassNotFoundException, SQLException
	{
		List<String> result = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
        sql.append("select distinct t.date from StockDataYahoo t where 1=1 ");

        if(stockcode == null || stockcode.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.stockcode = '" + stockcode + "'");
        }
        
        
        if(stockexchangecode == null || stockexchangecode.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.stockexchangecode = '" + stockexchangecode + "'");
        }
        
        

        Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;

        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        
	      
	         resultSet = sSta.executeQuery(sql.toString());
	         
	        while (resultSet.next())
	        {
	        	result.add(resultSet.getString("date"));
	        }
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
        
        return result;
	}
	
	
	private int InsertYahooDailyStockDataDo(List<YahooDailyStockDataDo> dataDoList) throws ClassNotFoundException, SQLException
	{
		int result = 0;
		
        if(dataDoList == null || dataDoList.size() <= 0)
        {
        	return result;
        }
        else
        {
        	//Do Nothing
        }
            

        Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;

        connection = DBFactory.CreateConnection();
        
        try
	    {
        	  sSta = connection.createStatement();
   	        
        	  for(int i = 0 ; i < dataDoList.size(); i++)
        	  {
        		  YahooDailyStockDataDo dataDo = dataDoList.get(i);
        		  
        		  if(dataDo == null) continue;
        		  
        		  m_ProgressDescription = "更新yahoo数据：" + dataDo.getStockcode() + " " + dataDo.getStockname(); 
        		  
        		  //����
	        	 String insertSql = "insert into StockDataYahoo " 
	        		 + " (StockCode, stockName,stockExchangeCode, " 
	        		 + " Date, "
	        		 + " Open, High, Low, Close, Volumn, AdjClose, "
	        		+ " createtime,updatetime) " 
	        		 + " values (" 
	        		 + " '" + dataDo.getStockcode() + "' "
	        		 + ",  '" + dataDo.getStockname() + "' "
	        		 + ",  '" + dataDo.getStockexchangecode() + "' "
	        		 
	        		 + ",  '" + dataDo.getDate() + "' "
	        		 
	        		 + ",  '" + dataDo.getOpen() + "' "
	        		 + ",  '" + dataDo.getHigh() + "' "
	        		 + ",  '" + dataDo.getLow() + "' "
	        		 + ",  '" + dataDo.getClose()+ "' "
	        		 + ",  '" + dataDo.getVolumn() + "' "
	        		 + ",  '" + dataDo.getAdjclose() + "' "
	        		 	        		 
	        		 +", now(), now())" ;
	        	 result += sSta.executeUpdate(insertSql); 
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
        
        return result;
	}

	
	private void UpdateYahooDailyStockFlag(String stockcode, String stockexchangecode) throws ClassNotFoundException, SQLException
	{
        Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;

        connection = DBFactory.CreateConnection();
        
        try
	    {
        	  sSta = connection.createStatement();
   	        
        	 
        	 String updateSQL = "update StockDataYahooFlag set DataLoadCount = DataLoadCount + 1 , updatetime = now() where stockcode ='" + stockcode 
        	 + "' and stockexchangecode='" + stockexchangecode + "'";
        		
        	 sSta.executeUpdate(updateSQL); 
         
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

	}

	public String getExecuteProgressDescription() {
		// TODO Auto-generated method stub
		return m_ProgressDescription;
	}



	
}
