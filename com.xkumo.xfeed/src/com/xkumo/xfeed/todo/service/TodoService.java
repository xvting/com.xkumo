package com.xkumo.xfeed.todo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xkumo.util.DateFactory;
import com.xkumo.xfeed.system.DBFactory;
import com.xkumo.xfeed.todo.domain.TodoDo;



public class TodoService {

	public List<TodoDo> listData(String todogroupid, String todoname,
			String todolevel, boolean isresolved) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
        sql.append("select t.* from Todo t where 1=1");

        if(todogroupid == null || todogroupid.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.todogroupid = '" + todogroupid + "'");
        }
        
        if(todoname == null || todoname.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.todoname like '%" + todoname + "%'");
        }
        
        if(todolevel == null || todolevel.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.todolevel = '" + todolevel + "'");
        }
        
        if(isresolved)
        {
        	sql.append(" and t.ISResolved = true ");
        }
        else
        {
        	sql.append(" and t.ISResolved = false ");
        }
        
     
        sql.append(" order by  t.updatetime desc");
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        List<TodoDo> result = new ArrayList<TodoDo>();
        
        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	  
	        resultSet = sSta.executeQuery(sql.toString());
	        while (resultSet.next())
	        {
	        	TodoDo  fms= new TodoDo();
	        	fms.setTodoseq(resultSet.getInt("todoseq"));
	        	fms.setTodoname(resultSet.getString("todoname"));
	        	fms.setTodoremark(resultSet.getString("todoremark"));
	        	fms.setTodogroupid(resultSet.getString("todogroupid") );
	        	fms.setTodolevel(resultSet.getString("todolevel")  );
	        	fms.setIsresolved(resultSet.getBoolean("isresolved"));
	        	fms.setPlanresolvedtime(resultSet.getDate("planresolvedtime"));
	        	fms.setActualresolvedtime(resultSet.getDate("actualresolvedtime"));
	        	 	
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
	public void insertData(TodoDo addData) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement sStat= null;
        
        connection = DBFactory.CreateConnection();
        
        try{
                    String qzSql =  "INSERT INTO Todo(";
                    qzSql+="TodoName,";
                    qzSql+="TodoRemark,";
                    qzSql+="TodoGroupID,";
                    qzSql+="TodoLevel,";
                	qzSql+="PlanResolvedTime,";
                	qzSql+="ISResolved,";
                    qzSql+="CreateTime,";
                    qzSql+="UpdateTime";
                    qzSql+=" ) VALUES (?,?,?,?,?,false,now(),now())"  ; 
                    sStat = connection.prepareStatement(qzSql) ;
                    sStat.setString( 1,addData.getTodoname());
                    sStat.setString( 2,addData.getTodoremark());
                    sStat.setString( 3,addData.getTodogroupid());
                    sStat.setString( 4,addData.getTodolevel());
                    sStat.setDate(5, DBFactory.ConvertToSqlDate(addData.getPlanresolvedtime()));
                   
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

	public void updateData(TodoDo updateData) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement sStat= null;
        
        connection = DBFactory.CreateConnection();
        
        try{
            String qzSql ="UPDATE Todo SET ";
            qzSql+="TodoName='"+updateData.getTodoname()+"',";
            qzSql+="TodoRemark='"+updateData.getTodoremark()+"',";
            qzSql+="TodoGroupID='"+updateData.getTodogroupid()+"',";
            qzSql+="TodoLevel='"+updateData.getTodolevel()+"',";
            qzSql+="ISResolved="+updateData.isIsresolved()+",";
            if(updateData.getPlanresolvedtime() == null)
            {
            	qzSql+="PlanResolvedTime=null, ";
            }
            else
            {
            	qzSql+="PlanResolvedTime='"+DateFactory.ConvertToString(updateData.getPlanresolvedtime())+"',";
            }
            if(updateData.getActualresolvedtime() == null)
            {
            	qzSql+="ActualResolvedTime=null, ";
            }
            else
            {
            	qzSql+="ActualResolvedTime='"+DateFactory.ConvertToString(updateData.getActualresolvedtime())+"',";
            }
            
            qzSql+="UpdateTime=now() ";
            qzSql+=" where  TodoSeq='"+updateData.getTodoseq()+"' ";
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
            String qzSql ="DELETE FROM Todo WHERE 1 AND TodoSeq='"+ id+"'";

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

	public void deleteTodoGroup(String string) {
		// TODO Auto-generated method stub
		
	}

	

}
