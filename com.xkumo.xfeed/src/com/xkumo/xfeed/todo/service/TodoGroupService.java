package com.xkumo.xfeed.todo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xkumo.xfeed.system.DBFactory;
import com.xkumo.xfeed.todo.domain.TodoGroupDo;


public class TodoGroupService {

	public List<TodoGroupDo> listData(String todogroupid, String todogroupname) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
        sql.append("select t.* from TodoGroup t where 1=1");

        if(todogroupid == null || todogroupid.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.todogroupid like '%" + todogroupid + "%'");
        }
        
        if(todogroupname == null || todogroupname.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.todogroupname like '%" + todogroupname + "%'");
        }
     
        sql.append(" order by t.createtime");
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        List<TodoGroupDo> result = new ArrayList<TodoGroupDo>();
        
        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	  
	        resultSet = sSta.executeQuery(sql.toString());
	        while (resultSet.next())
	        {
	        	TodoGroupDo  fms= new TodoGroupDo();
	        	fms.setTodogroupid(resultSet.getString("TODOGROUPID"));
	        	fms.setTodogroupname(resultSet.getString("TODOGROUPNAME"));
	        	fms.setTodogroupremark(resultSet.getString("TODOGROUPREMARK"));
	        	
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

	public void insertData(TodoGroupDo addData) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement sStat= null;
        
        connection = DBFactory.CreateConnection();
        
        try{
                    String qzSql =  "INSERT INTO TodoGroup(";
                    qzSql+="TodoGroupID,";
                    qzSql+="TodoGroupName,";
                    qzSql+="TodoGroupRemark,";
                    qzSql+="CreateTime,";
                    qzSql+="UpdateTime";
                    qzSql+=" ) VALUES (?,?,?,now(),now())"  ; 
                    sStat = connection.prepareStatement(qzSql) ;
                    sStat.setString( 1,addData.getTodogroupid());
                    sStat.setString( 2,addData.getTodogroupname());
                    sStat.setString( 3,addData.getTodogroupremark());
                   
                    // System.out.println(qzSql);
                    sStat.executeUpdate() ;
   
          }finally
  	    {
  	    	try
  	        {
  	            if (connection != null)
  	            {
  	            	connection.close();
  	            }
  	            if (sStat != null)
  	            {
  	            	sStat.close();
  	            }
  	        }
  	        catch (SQLException e)
  	        {
  	            e.printStackTrace();
  	        }
  	    }
        
	}

	public void updateData(TodoGroupDo updateData) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement sStat= null;
        
        connection = DBFactory.CreateConnection();
        
        try{
            String qzSql ="UPDATE TodoGroup SET ";
            qzSql+="TodoGroupName='"+updateData.getTodogroupname()+"',";
            qzSql+="TodoGroupRemark='"+updateData.getTodogroupremark()+"',";
            qzSql+="UpdateTime=now() ";
            qzSql+=" where  TodoGroupID='"+updateData.getTodogroupid()+"' ";
             sStat=connection.prepareStatement(qzSql);
               
             sStat.executeUpdate();
             
        }finally
  	    {
  	    	try
  	        {
  	            if (connection != null)
  	            {
  	            	connection.close();
  	            }
  	            if (sStat != null)
  	            {
  	            	sStat.close();
  	            }
  	        }
  	        catch (SQLException e)
  	        {
  	            e.printStackTrace();
  	        }
  	    }
	}

	public void deleteDataByID(String id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement sStat= null;
        
        connection = DBFactory.CreateConnection();
        
        try{
            String qzSql ="DELETE FROM TodoGroup WHERE 1 AND TodoGroupID='"+ id+"'";

             sStat=connection.prepareStatement(qzSql);
               
             sStat.executeUpdate();
             
        }finally
  	    {
  	    	try
  	        {
  	            if (connection != null)
  	            {
  	            	connection.close();
  	            }
  	            if (sStat != null)
  	            {
  	            	sStat.close();
  	            }
  	        }
  	        catch (SQLException e)
  	        {
  	            e.printStackTrace();
  	        }
  	    }
	}

	public void deleteTodoGroup(String string) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement sStat= null;
        
        connection = DBFactory.CreateConnection();
        
        try{
            String qzSql ="DELETE FROM TodoGroup WHERE 1 ";

             sStat=connection.prepareStatement(qzSql);
               
             sStat.executeUpdate();
             
        }finally
  	    {
  	    	try
  	        {
  	            if (connection != null)
  	            {
  	            	connection.close();
  	            }
  	            if (sStat != null)
  	            {
  	            	sStat.close();
  	            }
  	        }
  	        catch (SQLException e)
  	        {
  	            e.printStackTrace();
  	        }
  	    }
	}

}
