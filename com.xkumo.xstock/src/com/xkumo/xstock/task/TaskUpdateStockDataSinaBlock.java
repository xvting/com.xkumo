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
import com.xkumo.vendor.sina.SinaDailyStockData;
import com.xkumo.vendor.sina.SinaVendor;
import com.xkumo.xstock.core.TaskEnum;
import com.xkumo.xstock.sina.domain.SinaDailyStockDataBlockDo;
import com.xkumo.xstock.sina.domain.SinaDailyStockDataDo;
import com.xkumo.xstock.stock.domain.StockDo;
import com.xkumo.xstock.system.DBFactory;

public class TaskUpdateStockDataSinaBlock extends TaskBase implements ITaskTerminatable,ITaskProgressable {

	private TaskManager m_TaskManager;
	private boolean m_Terminate;
	private int m_Progress;
	private String m_ProgressDescription;
	
	public TaskUpdateStockDataSinaBlock(TaskManager pTaskManager)
	{
		this.m_executing = false;
		this.m_taskID = TaskEnum.UpdateStockDataSinaBlock.getID();
		this.m_taskName = TaskEnum.UpdateStockDataSinaBlock.getName();
		this.m_taskDescription = TaskEnum.UpdateStockDataSinaBlock.getName();
		
		m_TaskManager = pTaskManager;
		m_Terminate = false;
		m_Progress = 0;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
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
			count = updateStockDataSinaBlockByStock();
			
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
	
	private int updateStockDataSinaBlockByStock() throws Exception {
		// TODO Auto-generated method stub
		
	   int resultCount = 0;
	   
	   	StringBuilder sql = new StringBuilder();
        sql.append("select t.* from Stock t where 1=1 order by createtime desc ");
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        List<StockDo> result = new ArrayList<StockDo>();

        connection = DBFactory.CreateConnection();
        
      
        
        try
	    {
	        sSta = connection.createStatement();
	        
	      
	         resultSet = sSta.executeQuery(sql.toString());
	         
	         SinaVendor sinaVendor = new SinaVendor();
	         
	        while (resultSet.next())
	        {
	        	if(m_Terminate)
	        	{
	        		break;
	        	}
	        	else
	        	{
	        		//Do Nothing
	        	}
	        	
	        	//检索遍历股票
	        	
	        	//获得分析开始日
	        	Date startDate = getBlockCheckStartDay(resultSet.getString("STOCKCODE"), resultSet.getString("STOCKEXCHANGECODE"));
	        	
	        	//获得从分析开始日后20条数据
	        	List<SinaDailyStockDataDo> sinaDailyStockDataList = getStockSinaDataList(startDate);
	        	
	        	if(sinaDailyStockDataList != null && sinaDailyStockDataList.size()> 0)
        		{
	        		SinaDailyStockDataDo firstDayData = sinaDailyStockDataList.remove(0);
	        		SinaDailyStockDataBlockDo block = new SinaDailyStockDataBlockDo(firstDayData, firstDayData.isDown());
        			
	        		
	        		for(SinaDailyStockDataDo dayStock : sinaDailyStockDataList)
        			{
        				//遍历分析保存block
        				if(block.isIsdown() == dayStock.isDown())
        				{
        					//都是同一趋势的
        					//if()
        				}
        			}
        		}
        		else
        		{
        			//Do Nothing
        		}
	        	
	        	do
	        	{
	        		SinaDailyStockDataDo startDayStock = null;
	        		SinaDailyStockDataDo endDayStock = null;
	        		boolean isdown = false;
	        		
	        		
	        		sinaDailyStockDataList = getStockSinaDataList(startDate);
	        	}
	        	while(true);
	        	
	        	
	        	
	        	//resultCount++;
	        	//m_Progress++;
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
	
	private Date getBlockCheckStartDay(String stockCode, String stockExchangeCode)
	{
		return null;
	}
	
	private List<SinaDailyStockDataDo> getStockSinaDataList(Date startDay)
	{
		return null;
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

	public String getExecuteProgressDescription() {
		// TODO Auto-generated method stub
		return m_ProgressDescription;
	}

}
