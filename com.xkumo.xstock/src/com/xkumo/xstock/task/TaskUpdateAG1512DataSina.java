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
import com.xkumo.util.DateFactory;
import com.xkumo.vendor.sina.SinaAG1512Data;
import com.xkumo.vendor.sina.SinaDailyStockData;
import com.xkumo.vendor.sina.SinaVendor;
import com.xkumo.xstock.core.TaskEnum;
import com.xkumo.xstock.sina.domain.SinaAG1512DataDo;
import com.xkumo.xstock.sina.domain.SinaDailyStockDataDo;
import com.xkumo.xstock.stock.domain.StockDo;
import com.xkumo.xstock.system.DBFactory;

public class TaskUpdateAG1512DataSina extends TaskBase implements ITaskTerminatable,ITaskProgressable {
	
	
	private TaskManager m_TaskManager;
	private boolean m_Terminate;
	private int m_Progress;
	
	private String m_ProgressDescription;
	
	public TaskUpdateAG1512DataSina(TaskManager pTaskManager)
	{
		this.m_executing = false;
		this.m_taskID = TaskEnum.UpdateAG1512DataSina.getID();
		this.m_taskName = TaskEnum.UpdateAG1512DataSina.getName();
		this.m_taskDescription = TaskEnum.UpdateAG1512DataSina.getName();
		
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
			count = updateAG1512DataSina();
			
			this.m_lastFinishTime= new Date();
			
			if(m_Terminate)
			{
				m_TaskManager.saveTaskExecuteLog(m_taskID, m_taskName, m_taskDescription, 
						m_lastStartTime, m_lastFinishTime, true, "被中断完成", "处理[" + count + "]条");
			}
			else
			{
			
//				m_TaskManager.saveTaskExecuteLog(m_taskID, m_taskName, m_taskDescription, 
//					m_lastStartTime, m_lastFinishTime, true, "正常完成", "处理[" + count + "]条");
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

   
   
   private int updateAG1512DataSina() throws Exception {
		// TODO Auto-generated method stub
		
	   int resultCount = 0;
	   
   	 	Connection connection = null;

        connection = DBFactory.CreateConnection();
        
        
        try
	    {
	         
	        SinaVendor sinaVendor = new SinaVendor();
	         
        	SinaAG1512Data sinaAGdata = sinaVendor.getDailyAGData("AG1512");
        	
        	SinaAG1512DataDo agData = null;
        	if (sinaAGdata == null)
        	{
        		//Do Nothing
        	}
        	else
        	{
        		agData = new SinaAG1512DataDo(sinaAGdata);
        	}
        	
        	if(agData == null)
        	{
        		//Do Nothing
        	}
        	else
        	{
        		String sinaSeq = getSeq(agData,connection );
        		
        		m_ProgressDescription = "处理白银1512数据：" + "AG1512";
        		
        		if(sinaSeq == null)
        		{
        			InsertSinaAG1512DataDo(agData, connection);
        		}
        		else
        		{
        			//Do nothing
        		}
        		
        	}
        	
        	resultCount++;
	    }
        finally
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
       
	    return resultCount;
	}
   
   /**
    * 获得已经存在的Seq
    * @param dataDo
    * @param connection
    * @return
    * @throws SQLException
    */
   private String getSeq(SinaAG1512DataDo dataDo, Connection connection) throws SQLException
   {
	   if(dataDo == null)
        {
        	return null;
        }
        else
        {
        	//Do Nothing
        }
	   
	   String sql = "select * from futuredatasinaminute  where  FutureName='" + dataDo.getName() 
	   		+ "' and DataTime = '" + dataDo.getDatatime()
	   		+ "' and DataDay = '" + DateFactory.ConvertToDatetimeString(dataDo.getDataday())
	   		+ "' and Market = '" + dataDo.getMarket()
	   		+ "' and Product = '" + dataDo.getProduct()
	   		+ "'";
	   Statement sSta = null;
	  	
        ResultSet resultSet =null;
        
        try
	    {
        	sSta = connection.createStatement();
	        resultSet = sSta.executeQuery(sql);
	         
	        String currentsinaseq = null;
	         
	        while (resultSet.next())
	        {
	        	currentsinaseq = resultSet.getString("sinaseq");
	        	break;	
	        }
	        
	        return currentsinaseq;
	    }
        finally
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
   
   private int InsertSinaAG1512DataDo(SinaAG1512DataDo dataDo, Connection connection) throws SQLException
   {
	   if(dataDo == null)
        {
        	return 0;
        }
        else
        {
        	//Do Nothing
        }
        
       
        Statement sSta = null;
  	
        ResultSet resultSet =null;

        
        try
	    {
        	  sSta = connection.createStatement();

	        	//����
	        	 String insertSql = "insert into futuredatasinaminute " 
	        		 + " (FutureName, " 
	        		 + " OpenPrice, LastChangePrice, CurrentPrice, TopPrice, BottomPrice, BuyPrice, SellPrice, ChangePrice, SellNumber, BuyNumber, HoldAmount, ChangeVolumn, "
	        		 + " Market, Product, "
	        		 + " DataDay, DataTime, "
	        		 + " createtime,updatetime) " 
	        		 + " values (" 
	        		 + " '" + dataDo.getName() + "' "
	   
	        		 
	        		 + ",  '" + dataDo.getOpenprice() + "' "
	        		 + ",  '" + dataDo.getLastchangeprice() + "' "
	        		 + ",  '" + dataDo.getCurrentprice() + "' "
	        		 + ",  '" + dataDo.getTopprice() + "' "
	        		 + ",  '" + dataDo.getBottomprice()+ "' "
	        		 + ",  '" + dataDo.getBuyprice() + "' "
	        		 + ",  '" + dataDo.getSellprice() + "' "
	        		 + ",  '" + dataDo.getChangeprice() + "' "
	        		 + ",  '" + dataDo.getSellnumber() + "' "
	        		 + ",  '" + dataDo.getBuynumber() + "' "
	        		 + ",  '" + dataDo.getHoldamount() + "' "
	        		 + ",  '" + dataDo.getChangevolumn() + "' "
	        		 
	        		 + ",  '" + dataDo.getMarket() + "' "
	        		 + ",  '" + dataDo.getProduct() + "' "
	        		 	        		 
	        		 + ",  '" + DateFactory.ConvertToDatetimeString(dataDo.getDataday()) + "' "
	        		 + ",  '" + dataDo.getDatatime() + "' "
	        		 
	        		 +", now(), now())" ;
	        	  int insertResultCount = sSta.executeUpdate(insertSql); 
	        	  
	        	  return insertResultCount;
	        
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
		return m_ProgressDescription;
	}


	
}
