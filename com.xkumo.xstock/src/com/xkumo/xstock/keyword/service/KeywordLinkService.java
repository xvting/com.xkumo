package com.xkumo.xstock.keyword.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xkumo.xstock.keyword.domain.KeywordLinkDo;
import com.xkumo.xstock.keyword.domain.KeywordLinkDoShow;
import com.xkumo.xstock.system.DBFactory;
import com.xkumo.xstock.task.domain.TaskLogDo;
import com.xkumo.xstock.todo.domain.TodoDo;




public class KeywordLinkService {

	public List<KeywordLinkDoShow> listData(String keyword,String stockcode,String stockname) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
        sql.append("select t.*, s.stockname from KeywordStockLink t, Stock s, keyword k where 1=1 and t.StockCode = s.StockCode and t.StockExchangeCode = s.StockExchangeCode and k.code = t.keywordcode ");

        if(keyword == null || keyword.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and k.keyword like '%" + keyword + "%'");
        }
        
        if(stockcode == null || stockcode.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.stockcode like '%" + stockcode + "%'");
        }
        
        if(stockname == null || stockname.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and s.stockname like '%" + stockname + "%'");
        }
        
        
     
        sql.append(" order by  t.updatetime desc");
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        List<KeywordLinkDoShow> result = new ArrayList<KeywordLinkDoShow>();
        
        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement();
	  
	        resultSet = sSta.executeQuery(sql.toString());
	        while (resultSet.next())
	        {
	        	KeywordLinkDoShow  fms= new KeywordLinkDoShow();
	        	fms.setSeq(resultSet.getInt("seq"));
	        	fms.setKeywordcode(resultSet.getString("keywordcode"));
	        	fms.setStockcode(resultSet.getString("stockcode"));	  
	        	fms.setStockname(resultSet.getString("stockname"));
	        	fms.setStockexchangecode(resultSet.getString("stockexchangecode"));
	        	
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

	public void insertData(KeywordLinkDo addData) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement sStat= null;
        
        connection = DBFactory.CreateConnection();
        
        try{
                    String qzSql =  "INSERT INTO KeywordStockLink (";
                    qzSql+="keywordcode,";
                    qzSql+="stockcode,";
                    qzSql+="stockexchangecode,";
                    qzSql+="CreateTime,";
                    qzSql+="UpdateTime";
                    qzSql+=" ) VALUES (?,?,?,now(),now())"  ; 
                    sStat = connection.prepareStatement(qzSql);
                    sStat.setString( 1,addData.getKeywordcode());
                    sStat.setString( 2,addData.getStockcode());
                    sStat.setString( 3,addData.getStockexchangecode());
                   
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
            String qzSql ="DELETE FROM KeywordStockLink WHERE 1 ";

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
