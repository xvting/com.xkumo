package com.xkumo.xstock.task.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xkumo.xstock.system.DBFactory;
import com.xkumo.xstock.system.SQLFactory;
import com.xkumo.xstock.task.domain.TaskLogDo;
import com.xkumo.xstock.todo.domain.TodoDo;


public class TaskLogService {

	public int countStock(String taskid, String taskname) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int result = -1;
		
		StringBuilder sql = new StringBuilder();
        sql.append("select count(*) as ct from TaskLog t where 1=1 ");

        if(taskid == null || taskid.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.taskid = '" + taskid + "'");
        }
        
        if(taskname == null || taskname.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.taskname like '%" + taskname + "%'");
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
	        	result = resultSet.getInt("ct");
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

	
	public List<TaskLogDo> listData(String taskid, String taskname, int pageIndex, int limit) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
        sql.append("select t.* from TaskLog t where 1=1 ");

        if(taskid == null || taskid.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.taskid = '" + taskid + "'");
        }
        
        if(taskname == null || taskname.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.taskname like '%" + taskname + "%'");
        }
        
        
     
        sql.append(" order by  t.updatetime desc");
        
        
        String pageSQL = SQLFactory.getPageSql(sql.toString(), pageIndex, limit);
       
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        List<TaskLogDo> result = new ArrayList<TaskLogDo>();
        
        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	  
	        resultSet = sSta.executeQuery(pageSQL);
	        while (resultSet.next())
	        {
	        	TaskLogDo  fms= new TaskLogDo();
	        	fms.setLogseq(resultSet.getInt("logseq"));
	        	fms.setTaskid(resultSet.getString("taskid"));
	        	fms.setTaskname(resultSet.getString("taskname"));
	        	fms.setTaskdescription(resultSet.getString("taskdescription"));
	        	fms.setStarttime(resultSet.getTimestamp("starttime"));
	        	fms.setEndtime(resultSet.getTimestamp("endtime"));
	        	fms.setIsdone(resultSet.getBoolean("isdone"));
	        	fms.setMessage(resultSet.getString("message"));
	        	fms.setDescription(resultSet.getString("description"));
	        
	        	 	
	            result.add(fms);
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

	
}
