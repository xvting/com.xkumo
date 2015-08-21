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
import com.xkumo.xfeed.yahoo.domain.YahooDailyStockDataDo;
import com.xkumo.xfeed.yahoo.domain.YahooDailyStockDataFlagDo;
import com.xkumo.xfeed.yahoo.service.YahooStockService;

public class TaskUpdateDataTorrentKittySo extends TaskBase implements ITaskTerminatable,ITaskProgressable {

	private TaskManager m_TaskManager;
	private boolean m_terminate;
	private int m_progress = 0;
	private String m_progressDescriptoin = "";
	
	public TaskUpdateDataTorrentKittySo(TaskManager pTaskManager)
	{
		this.m_executing = false;
		this.m_taskName = TaskEnum.UpdateDataTorrentKittySo.getName();
		this.m_taskDescription = TaskEnum.UpdateDataTorrentKittySo.getName();
		this.m_taskID = TaskEnum.UpdateDataTorrentKittySo.getID();
		
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
			count = updateDataTorrentKittySoByDay();
			
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
	
	private int updateDataTorrentKittySoByDay() throws ClassNotFoundException, SQLException, IOException
	{
		int result = 0;
		
//		//获得更新列表Flag
//		List<Integer>  pageNumberList = getPageNumberList();
//		
//		if (pageNumberList == null || pageNumberList.size() <= 0)
//		{
//			return result;
//		}
//		else
//		{
//			//Do Nothing
//		}
		
		int maxPageNum = getMaxTorrentkittyPageNum();

		for(int pageNum = maxPageNum+1; pageNum <= 9816523; pageNum++)
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
			
			HttpClient httpClient = new HttpClient();
			
			String url = getURL(pageNum);
			String content = "";
			String title = "";
			String maglinkText = "";
			
			GetMethod getMethod = new GetMethod(url);
			 try 
			 {
			        int statusCode = httpClient.executeMethod(getMethod);
		            if (statusCode != HttpStatus.SC_OK) {
		                System.err.println("Method failed: "
		                        + getMethod.getStatusLine());
		            }
		            // 读取内容
		            //getMethod.getResponseBodyAsString()
		           // byte[] responseBody = getMethod.getResponseBody();
		            // 处理内容
		            content = getMethod.getResponseBodyAsString();
		            
		            Document doc =  Jsoup.parse(content);
		            
		            Element maglink = doc.getElementById("magnetLink");
		           
		            maglinkText = maglink.text();
		            
		            title =  maglink.previousElementSibling().text();
		            
		            m_progressDescriptoin = "处理 PageNum：" + pageNum;
		           
		            InsertYahooDailyStockDataDo(pageNum, url, content, title, maglinkText);
		            
		    } catch (Exception e) {
		      	e.printStackTrace();
		    }
		    finally
		    {
	        	try
	        	{
	        		getMethod.releaseConnection();
	        	}
	        	catch(Exception ex)
	        	{
	        		
	        	}
	        }
			
		}
		
		return result;
	}
	
	
	private String getURL(int pageNumber)
	{
		return "http://www.torrentkitty.so/bt/" + pageNumber  + ".htm";
	}

	
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
		return m_progressDescriptoin;
	}	
}
