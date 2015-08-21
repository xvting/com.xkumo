package com.xkumo.xstock.keyword.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xkumo.xstock.keyword.domain.KeywordDo;
import com.xkumo.xstock.system.DBFactory;
import com.xkumo.xstock.task.domain.TaskLogDo;
import com.xkumo.xstock.todo.domain.TodoDo;




public class KeywordService {

	public List<KeywordDo> listData(String code, String keyword) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
        sql.append("select t.* from Keyword t where 1=1 ");

        if(code == null || code.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.code = '" + code + "'");
        }
        
        if(keyword == null || keyword.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.keyword like '%" + keyword + "%'");
        }
        
        
     
        sql.append(" order by  t.updatetime desc");
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        List<KeywordDo> result = new ArrayList<KeywordDo>();
        
        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	  
	        resultSet = sSta.executeQuery(sql.toString());
	        while (resultSet.next())
	        {
	        	KeywordDo  fms= new KeywordDo();
	        	fms.setSeq(resultSet.getInt("seq"));
	        	fms.setCode(resultSet.getString("code"));
	        	fms.setKeyword(resultSet.getString("keyword"));	        
	        	 	
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

	public void insertData(KeywordDo addData) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement sStat= null;
        
        connection = DBFactory.CreateConnection();
        
        try{
                    String qzSql =  "INSERT INTO Keyword(";
                    qzSql+="Code,";
                    qzSql+="Keyword,";
                    qzSql+="CreateTime,";
                    qzSql+="UpdateTime";
                    qzSql+=" ) VALUES (?,?,now(),now())"  ; 
                    sStat = connection.prepareStatement(qzSql);
                    sStat.setString( 1,addData.getCode());
                    sStat.setString( 2,addData.getKeyword());
                   
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

	public void deleteData() throws ClassNotFoundException, SQLException {

		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement sStat= null;
        
        connection = DBFactory.CreateConnection();
        
        try{
            String qzSql ="DELETE FROM Keyword WHERE 1 ";

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
