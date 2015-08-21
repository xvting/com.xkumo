package com.xkumo.xfeed.task;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.mysql.jdbc.PreparedStatement;
import com.xkumo.core.task.ITaskProgressable;
import com.xkumo.core.task.ITaskTerminatable;
import com.xkumo.core.task.TaskBase;
import com.xkumo.xfeed.system.DBFactory;
import com.xkumo.xfeed.torrentkittyso.dao.TorrentKittySoDo;
import com.xkumo.xfeed.yahoo.domain.YahooDailyStockDataDo;
import com.xkumo.xfeed.yahoo.domain.YahooDailyStockDataFlagDo;
import com.xkumo.xfeed.yahoo.service.YahooStockService;

public class TaskTorrentKittySoIndex extends TaskBase implements ITaskTerminatable,ITaskProgressable {

	private TaskManager m_TaskManager;
	private boolean m_terminate;
	private int m_progress = 0;
	
	private String m_progressDescription = "";
	
	private String m_TableName;
	private List<String> m_KeyList;
	
	public TaskTorrentKittySoIndex(TaskManager pTaskManager, 
			
			String taskName,
			String taskDescription,
			String taskID,
			String tableName, List<String> keyList)
	{
		this.m_executing = false;
		this.m_taskName = taskName;
		this.m_taskDescription = taskDescription;
		this.m_taskID = taskID;
		
		m_TableName = tableName;
		m_KeyList = keyList;
		
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
			count = updateDataTorrentKittySoIndex();
			
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
			} catch (Exception e) {	
				// TODO Auto-generated catch block	
				try {
					m_TaskManager.saveTaskExecuteLog(m_taskID, m_taskName, m_taskDescription,
							m_lastStartTime, m_lastFinishTime, false, "错误保存异常", "");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	
	private int updateDataTorrentKittySoIndex() throws ClassNotFoundException, SQLException, IOException
	{
		int result = 0;
		
		Connection connection = null;
        
        Statement searchSta = null;

        ResultSet rs =null;
        
		//当前index最大值
		int indexmaxPageNum = getMaxTorrentkittyIndexPageNum();
		
		try
	    {
        	connection = DBFactory.CreateConnection();
        	
        	searchSta = connection.createStatement();
        	
        	while(indexmaxPageNum < getMaxTorrentkittyPageNum())
    		{
        		List<TorrentKittySoDo> currentKittySoList = new ArrayList<TorrentKittySoDo>();
        		
    			//检索
    			String searchSql = " SELECT * FROM torrentkitty_so where PageNum > '" + indexmaxPageNum + "' order by PageNum asc  limit 0,500";

    			try{
	    			rs = searchSta.executeQuery(searchSql);
		        	
		        	while(rs.next())
		        	{
		        		TorrentKittySoDo temp = TorrentKittySoDo.create(rs);
		        		currentKittySoList.add(temp);
		        		if(indexmaxPageNum < temp.getPagenum())
		        		{
		        			indexmaxPageNum = temp.getPagenum();
		        		}
		        		m_progress++;
		        	}
    			}
    			finally
    			{
    				try
    				{
	    				 if (rs != null)
	 		            {
	    					 rs.close();
	    					 rs = null;
	 		            }
    				}
    				catch(Exception ex)
    				{
    					ex.printStackTrace();
    				}
    			}
	        	
    			List<TorrentKittySoDo> okList = new ArrayList<TorrentKittySoDo>();
        		
    			if(currentKittySoList.size() > 0)
    			{
    				for(TorrentKittySoDo kittyItem : currentKittySoList)
    				{
    					m_progressDescription = "处理 PageNum:"+ kittyItem.getPagenum() ;
    					if(kittyItem.getTitle() == null)
    					{
    						//Do Nothing
    					}
    					else
    					{
    						
    						if(contianKey(kittyItem.getTitle(),this.m_KeyList))
    						{
    							okList.add(kittyItem);
    						}
    						else
    						{
    							//Do Nothing
    						}
    					}
    				}
    			}
    			else
    			{
    				//Do Nothing
    			}
    			
    			if(okList.size() > 0)
    			{
    			    PreparedStatement  insertSta = null;
    				
    				for(TorrentKittySoDo kittyItem : okList)
    				{
    					 String sql = "insert into "+ this.m_TableName +" (pagenum, url, title, magnetlink, createtime,updatetime)"
    				        	+ " values (?,?,?,?, now(),now()) " ;
    					
    					 try
    					 {
    					 	insertSta = (PreparedStatement) connection.prepareStatement(sql);
    			        	insertSta.setInt(1, kittyItem.getPagenum());
    			        	insertSta.setString(2, kittyItem.getUrl());
    			        	insertSta.setString(3, kittyItem.getTitle());
    			        	insertSta.setString(4, kittyItem.getMagnetlink());
    			        	
    			      
    			        	result += insertSta.executeUpdate();
    			        	  
    			        	  
    					 }
    					 finally
    					 {
    						 try
    						 {
    						  if (insertSta != null)
    				            {
    				            	insertSta.close();
    				            	insertSta = null;
    				            }
    						 }
    						 catch(Exception ex)
    						 {
    							 ex.printStackTrace();
    						 }
    				            
    					 }
    			        	  
    				}
    				
    			}
    			else
    			{
    				//DO Nothing
    			}
	        	
    		}
        	
        	
        	
        	return result;
	        
	    }
        finally
	    {
        	try
	        {
	            if (connection != null)
	            {
	            	connection.close();
	            }
	            if(searchSta != null)
	            {
	            	searchSta.close();
	            }
	            
	        }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	        }
	    }
        
        
	}
	
	private boolean contianKey(String strValue, List<String> keylist)
	{
		if(strValue == null || keylist == null || keylist.size() <= 0)
		{
			return false;
		}
		else
		{
			for(String key : keylist)
			{
				if(key == null)
				{
					continue;
				}
				else
				{
					if(strValue.toUpperCase().indexOf(key.toUpperCase()) >=0)
					{
						return true;
					}
					else
					{
						continue;
					}
				}
			}
		}
		
		return false;
	}
	
	
	/**
	 * 获得当前最大
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private int getMaxTorrentkittyPageNum() throws ClassNotFoundException, SQLException
	{
		int result = 0;
		
        

        Connection connection = null;
        
        Statement delSta = null;

        PreparedStatement  insertSta = null;
  	
        ResultSet resultSet =null;

        
        
        String sql = "SELECT MAX(pagenum) FROM torrentkitty_so ";
        
        
        try
	    {
        	connection = DBFactory.CreateConnection();
        	
        	delSta = connection.createStatement();
        	
        	ResultSet rs =delSta.executeQuery(sql);
        	
        	while(rs.next())
        	{
        		result =  rs.getInt(1);
        	}
        	
        	
        	return result;
	        
	    }
        finally
	    {
        	try
	        {
	            if (connection != null)
	            {
	            	connection.close();
	            }
	            
	            if(delSta != null)
	            {
	            	delSta.close();
	            }
	            
	            if (insertSta != null)
	            {
	            	insertSta.close();
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

	
	private int getMaxTorrentkittyIndexPageNum() throws ClassNotFoundException, SQLException
	{
		int result = 0;
		
        Connection connection = null;
        
        Statement delSta = null;

        PreparedStatement  insertSta = null;
  	
        ResultSet resultSet =null;

        
        
        String sql = "SELECT MAX(pagenum) FROM " + this.m_TableName +" ";
        
        
        try
	    {
        	connection = DBFactory.CreateConnection();
        	
        	delSta = connection.createStatement();
        	
        	ResultSet rs =delSta.executeQuery(sql);
        	
        	while(rs.next())
        	{
        		result =  rs.getInt(1);
        	}
        	
        	
        	return result;
	        
	    }
        finally
	    {
        	try
	        {
	            if (connection != null)
	            {
	            	connection.close();
	            }
	            
	            if(delSta != null)
	            {
	            	delSta.close();
	            }
	            
	            if (insertSta != null)
	            {
	            	insertSta.close();
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
	
	/**
	 * 插入数据
	 * @param pagenum
	 * @param url
	 * @param content
	 * @param title
	 * @param maglinkText
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private int InsertYahooDailyStockDataDo(int pagenum, String url, String  content, String title, String maglinkText) throws ClassNotFoundException, SQLException
	{
		int result = 0;
		
        

        Connection connection = null;
        
        Statement delSta = null;

        PreparedStatement  insertSta = null;
  	
        ResultSet resultSet =null;

        
        
        String sql = "insert into torrentkitty_so (pagenum, url, title, magnetlink, createtime,updatetime)"
        	+ " values (?,?,?,?, now(),now()) " ;
        
        
        try
	    {
        	connection = DBFactory.CreateConnection();
        	
        	delSta = connection.createStatement();
        	delSta.executeUpdate("DELETE FROM torrentkitty_so WHERE pagenum = '" + pagenum + "'");
        	
        	insertSta = (PreparedStatement) connection.prepareStatement(sql);
        	insertSta.setInt(1, pagenum);
        	insertSta.setString(2, url);
        	insertSta.setString(3, title);
        	insertSta.setString(4, maglinkText);
        	
      
        	  result = insertSta.executeUpdate();
	        
	    }
        finally
	    {
        	try
	        {
	            if (connection != null)
	            {
	            	connection.close();
	            }
	            
	            if(delSta != null)
	            {
	            	delSta.close();
	            }
	            
	            if (insertSta != null)
	            {
	            	insertSta.close();
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

	public String getExecuteProgressDescription() {
		// TODO Auto-generated method stub
		return m_progressDescription;
	}	
}
