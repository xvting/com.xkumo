package com.xkumo.xfeed.task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xkumo.core.task.ITask;
import com.xkumo.core.task.TaskManagerBase;

import com.xkumo.util.DateFactory;
import com.xkumo.xfeed.system.DBFactory;

public class TaskManager  extends TaskManagerBase{

    private TaskUpdateDataTorrentKittySo m_TaskUpdateStockDataYahoo = null;
    
    private TaskTorrentKittySoIndex m_TaskTorrentKittySoIndex10musume = null;
    
    private TaskTorrentKittySoIndex m_TaskTorrentKittySoIndexSCute = null;
    
    private TaskTorrentKittySoIndex m_TaskTorrentKittySoIndexTokyoHot = null;
    
    private TaskTorrentKittySoIndex m_TaskTorrentKittySoIndexTV = null;
    
//    private TaskUpdateStockDataYahooFlag m_TaskUpdateStockDataYahooFlag = null;
//    private TaskTimerUpdateStockInfo m_TaskScheduleUpdateStock = null;
//    private TaskTimerUpdateStockDataSina m_TaskScheduleUpdateStockDataSina = null;
//    private TaskTimerUpdateStockDataYahoo m_TaskScheduleUpdateStockDataYahoo = null;
//    private TaskTimerUpdateStockDataYahooFlag m_TaskScheduleUpdateStockDataYahooFlag = null;
	
    public TaskManager()
    {
    	super();
   
        
        m_TaskUpdateStockDataYahoo = new TaskUpdateDataTorrentKittySo(this);
        this.add(m_TaskUpdateStockDataYahoo);
        
        List<String> musumeKeylist = new ArrayList<String>();
        musumeKeylist.add("10musume");
        musumeKeylist.add("天然素人");
        musumeKeylist.add("天然萌妹");
        m_TaskTorrentKittySoIndex10musume = new TaskTorrentKittySoIndex(
        		this, 
        		TaskEnum.UpdateDataTorrentKittySoIndex10musume.getName(),
        		TaskEnum.UpdateDataTorrentKittySoIndex10musume.getName(),
        		TaskEnum.UpdateDataTorrentKittySoIndex10musume.getID(),
        		"torrentkitty_index_10musume",
        		musumeKeylist
        		);
        this.add(m_TaskTorrentKittySoIndex10musume);
        
        List<String> scuteKeylist = new ArrayList<String>();
        scuteKeylist.add("scute");
        scuteKeylist.add("s-cute");
        scuteKeylist.add("s cute");
        m_TaskTorrentKittySoIndexSCute = new TaskTorrentKittySoIndex(
        		this, 
        		TaskEnum.UpdateDataTorrentKittySoIndexSCute.getName(),
        		TaskEnum.UpdateDataTorrentKittySoIndexSCute.getName(),
        		TaskEnum.UpdateDataTorrentKittySoIndexSCute.getID(),
        		"torrentkitty_index_scute",
        		scuteKeylist
        		);
        this.add(m_TaskTorrentKittySoIndexSCute);
        
        List<String> tokyoHotKeylist = new ArrayList<String>();
        tokyoHotKeylist.add("tokyo hot");
        tokyoHotKeylist.add("tokyohot");
        tokyoHotKeylist.add("tokyo-hot");
        tokyoHotKeylist.add("东京热");
        tokyoHotKeylist.add("東京熱");
        tokyoHotKeylist.add("东热");
        m_TaskTorrentKittySoIndexTokyoHot = new TaskTorrentKittySoIndex(
        		this, 
        		TaskEnum.UpdateDataTorrentKittySoIndexTokyoHot.getName(),
        		TaskEnum.UpdateDataTorrentKittySoIndexTokyoHot.getName(),
        		TaskEnum.UpdateDataTorrentKittySoIndexTokyoHot.getID(),
        		"torrentkitty_index_tokyohot",
        		tokyoHotKeylist
        		);
        this.add(m_TaskTorrentKittySoIndexTokyoHot);
        
        List<String> tvKeyList = new ArrayList<String>();
        tvKeyList.add("BluRay");
        tvKeyList.add("DVDRiP");
        tvKeyList.add("XviD");
        tvKeyList.add("HDTV");
        tvKeyList.add("人人影视");
        tvKeyList.add("翔空字幕组");
        m_TaskTorrentKittySoIndexTV = new TaskTorrentKittySoIndex(
        		this, 
        		TaskEnum.UpdateDataTorrentKittySoIndexTV.getName(),
        		TaskEnum.UpdateDataTorrentKittySoIndexTV.getName(),
        		TaskEnum.UpdateDataTorrentKittySoIndexTV.getID(),
        		"torrentkitty_index_tv",
        		tvKeyList
        		);
        this.add(m_TaskTorrentKittySoIndexTV);
        
        //加勒比
        
//        m_TaskScheduleUpdateStockDataYahoo = new TaskTimerUpdateStockDataYahoo(this,m_TaskUpdateStockDataYahoo, 60 * 60 * 1000, 72 * 60*60 * 1000);
//        this.add(m_TaskScheduleUpdateStockDataYahoo);
//        
//        m_TaskUpdateStockDataYahooFlag = new TaskUpdateStockDataYahooFlag(this);
//        this.add(m_TaskUpdateStockDataYahooFlag);
//        m_TaskScheduleUpdateStockDataYahooFlag = new TaskTimerUpdateStockDataYahooFlag(this,m_TaskUpdateStockDataYahooFlag, 3 * 60* 60 * 1000, 71 * 60*60 * 1000);
//        this.add(m_TaskScheduleUpdateStockDataYahooFlag);
      
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
   		 + " ? "
   		 + ",? "
   		 + ",? "
   		 
   		 + ",? "
   		 + ",? "
   		 
   		 
   		 + ",? "
   		 + ",? "
   		 + ",? "
   		 +", now(), now())" ;
		
	
   	 	Connection connection = null;

        PreparedStatement sSta = null;
  	
        ResultSet resultSet =null;
  
        connection = DBFactory.CreateConnection();
        
        
        try
	    {
        	
        	
        	sSta = connection.prepareStatement(insertSql);
        	
        	sSta.setString(1, taskID);
        	sSta.setString(2, taskName);
        	sSta.setString(3, taskDescription);
        	sSta.setString(4, DateFactory.ConvertToDatetimeString(startDate));
        	sSta.setString(5, DateFactory.ConvertToDatetimeString(endDate));
        	sSta.setBoolean(6, isDone);
        	sSta.setString(7, message);
        	sSta.setString(8, description);
        	
        	 int insertResultCount = sSta.executeUpdate(); 
        	 
	         
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
