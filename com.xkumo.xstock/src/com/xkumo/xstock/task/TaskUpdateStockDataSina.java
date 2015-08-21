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
import com.xkumo.xstock.core.TaskEnum;
import com.xkumo.xstock.sina.domain.SinaDailyStockDataDo;
import com.xkumo.xstock.stock.domain.StockDo;
import com.xkumo.xstock.system.DBFactory;
import com.xkumo.util.DateFactory;
import com.xkumo.vendor.sina.SinaDailyStockData;
import com.xkumo.vendor.sina.SinaVendor;



public class TaskUpdateStockDataSina extends TaskBase implements ITaskTerminatable,ITaskProgressable {
		
	
		private TaskManager m_TaskManager;
		private boolean m_Terminate;
		private int m_Progress;
		
		private String m_ProgressDescription;
		
		public TaskUpdateStockDataSina(TaskManager pTaskManager)
		{
			this.m_executing = false;
			this.m_taskID = TaskEnum.UpdateStockDataSina.getID();
			this.m_taskName = TaskEnum.UpdateStockDataSina.getName();
			this.m_taskDescription = TaskEnum.UpdateStockDataSina.getName();
			
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
				count = updateStockDataSinaByDay();
				
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

	   
	   
	   private int updateStockDataSinaByDay() throws Exception {
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
		        	
		        	SinaDailyStockData sinadailystockdata = sinaVendor.getDailyStockData(resultSet.getString("STOCKCODE"), resultSet.getString("STOCKEXCHANGECODE"));
		        	
		        	SinaDailyStockDataDo sinaData = null;
		        	if (sinadailystockdata == null)
		        	{
		        		//Do Nothing
		        	}
		        	else
		        	{
		        		 sinaData = new SinaDailyStockDataDo(sinadailystockdata);
		        	}
		        	
		        	if(sinaData == null)
		        	{
		        		//Do Nothing
		        	}
		        	else
		        	{
		        		String sinaSeq = getSeq(sinaData,connection );
		        		
		        		m_ProgressDescription = "处理每日股票数据：" + sinaData.getStockcode()+" " + sinaData.getStockname();
		        		
		        		if(sinaSeq == null)
		        		{
		        			InsertSinaDailyStockDataDo(sinaData, connection);
		        		}
		        		else
		        		{
		        			UpdateSinaDailyStockDataDo(sinaData, sinaSeq, connection);
		        		}
		        		
		        	}
		        	
		        	resultCount++;
		        	m_Progress++;
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
	   
	   /**
	    * 获得已经存在的Seq
	    * @param dataDo
	    * @param connection
	    * @return
	    * @throws SQLException
	    */
	   private String getSeq(SinaDailyStockDataDo dataDo, Connection connection) throws SQLException
	   {
		   if(dataDo == null)
	        {
	        	return null;
	        }
	        else
	        {
	        	//Do Nothing
	        }
		   
		   String sql = "select * from StockDataSina  where  stockCode='" + dataDo.getStockcode() 
	        + "' and stockExchangeCode = '" + dataDo.getStockexchangecode()
	        + "' and DataDay = '" + DateFactory.ConvertToDatetimeString(dataDo.getDataday())+"'" ;
		   
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
	   
	   private int InsertSinaDailyStockDataDo(SinaDailyStockDataDo dataDo, Connection connection) throws SQLException
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
		        	 String insertSql = "insert into StockDataSina " 
		        		 + " (StockCode, stockName,stockExchangeCode, " 
		        		 + " OpenPrice, LastClosePrice, CurrentPrice, TopPrice, BottomPrice, BuyPrice, SellPrice, TransactionAmount, TransactionNumber, "
		        		 + " Buy1Price, Buy1Number, Buy2Price, Buy2Number, Buy3Price, Buy3Number, Buy4Price, Buy4Number, Buy5Price, Buy5Number, "
		        		 + " Sell1Price, Sell1Number, Sell2Price, Sell2Number, Sell3Price, Sell3Number, Sell4Price, Sell4Number, Sell5Price, Sell5Number, "
		        		 + " DataDay, DataTime, "
		        		 + " createtime,updatetime) " 
		        		 + " values (" 
		        		 + " '" + dataDo.getStockcode() + "' "
		        		 + ",  '" + dataDo.getStockname() + "' "
		        		 + ",  '" + dataDo.getStockexchangecode() + "' "
		        		 
		        		 + ",  '" + dataDo.getOpenprice() + "' "
		        		 + ",  '" + dataDo.getLastcloseprice() + "' "
		        		 + ",  '" + dataDo.getCurrentprice() + "' "
		        		 + ",  '" + dataDo.getTopprice() + "' "
		        		 + ",  '" + dataDo.getBottomprice()+ "' "
		        		 + ",  '" + dataDo.getBuyprice() + "' "
		        		 + ",  '" + dataDo.getSellprice() + "' "
		        		 + ",  '" + dataDo.getTransactionamount() + "' "
		        		 + ",  '" + dataDo.getTransactionnumber() + "' "
		        		 
		        		 + ",  '" + dataDo.getBuy1price() + "' "
		        		 + ",  '" + dataDo.getBuy1number() + "' "
		        		 + ",  '" + dataDo.getBuy2price() + "' "
		        		 + ",  '" + dataDo.getBuy2number() + "' "
		        		 + ",  '" + dataDo.getBuy3price() + "' "
		        		 + ",  '" + dataDo.getBuy3number() + "' "
		        		 + ",  '" + dataDo.getBuy4price() + "' "
		        		 + ",  '" + dataDo.getBuy4number() + "' "
		        		 + ",  '" + dataDo.getBuy5price() + "' "
		        		 + ",  '" + dataDo.getBuy5number() + "' "
		        		 
		        		 + ",  '" + dataDo.getSell1price() + "' "
		        		 + ",  '" + dataDo.getSell1number() + "' "
		        		 + ",  '" + dataDo.getSell2price() + "' "
		        		 + ",  '" + dataDo.getSell2number() + "' "
		        		 + ",  '" + dataDo.getSell3price() + "' "
		        		 + ",  '" + dataDo.getSell3number() + "' "
		        		 + ",  '" + dataDo.getSell4price() + "' "
		        		 + ",  '" + dataDo.getSell4number() + "' "
		        		 + ",  '" + dataDo.getSell5price() + "' "
		        		 + ",  '" + dataDo.getSell5number() + "' "
		        		 
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
	   
	   private void UpdateSinaDailyStockDataDo(SinaDailyStockDataDo dataDo, String sinaSeq, Connection connection) throws ClassNotFoundException, SQLException
		{
			
	        if(dataDo == null)
	        {
	        	return;
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
		        	 String updateSql = "update StockDataSina set "  
		        		 +" stockCode = '"+ dataDo.getStockcode() 
		        		 +"' , stockName = '"+ dataDo.getStockname()
		        		 +"' , StockExchangeCode = '"+ dataDo.getStockexchangecode()
		        		 +"' , OpenPrice = '"+ dataDo.getOpenprice()
		        		 +"' , LastClosePrice = '"+ dataDo.getLastcloseprice()
		        		 +"' , CurrentPrice = '"+ dataDo.getCurrentprice()
		        		 +"' , TopPrice = '"+ dataDo.getTopprice()
		        		 +"' , BottomPrice = '"+ dataDo.getBottomprice()
		        		 +"' , BuyPrice = '"+ dataDo.getBuyprice()
		        		 +"' , SellPrice = '"+ dataDo.getSellprice()
		        		 +"' , TransactionAmount = '"+ dataDo.getTransactionamount()
		        		 +"' , TransactionNumber = '"+ dataDo.getTransactionnumber()
		        		 
		        		 +"' , Buy1Price = '"+ dataDo.getBuy1price()
		        		 +"' , Buy1Number = '"+ dataDo.getBuy1number()
		        		 +"' , Buy2Price = '"+ dataDo.getBuy2price()
		        		 +"' , Buy2Number = '"+ dataDo.getBuy2number()
		        		 +"' , Buy3Price = '"+ dataDo.getBuy3price()
		        		 +"' , Buy3Number = '"+ dataDo.getBuy3number()
		        		 +"' , Buy4Price = '"+ dataDo.getBuy4price()
		        		 +"' , Buy4Number = '"+ dataDo.getBuy4number()
		        		 +"' , Buy5Price = '"+ dataDo.getBuy5price()
		        		 +"' , Buy5Number = '"+ dataDo.getBuy5number()
		        		 
		        		 +"' , Sell1Price = '"+ dataDo.getSell1price()
		        		 +"' , Sell1Number = '"+ dataDo.getSell1number()
		        		 +"' , Sell2Price = '"+ dataDo.getSell2price()
		        		 +"' , Sell2Number = '"+ dataDo.getSell2number()
		        		 +"' , Sell3Price = '"+ dataDo.getSell3price()
		        		 +"' , Sell3Number = '"+ dataDo.getSell3number()
		        		 +"' , Sell4Price = '"+ dataDo.getSell4price()
		        		 +"' , Sell4Number = '"+ dataDo.getSell4number()
		        		 +"' , Sell5Price = '"+ dataDo.getSell5price()
		        		 +"' , Sell5Number = '"+ dataDo.getSell5number()
		        		 
		        		 +"' , DataDay = '"+ DateFactory.ConvertToDatetimeString(dataDo.getDataday())
		        		 +"' , DataTime = '"+ dataDo.getDatatime()
		        		 
		        		 +"' , updatetime = now() where  sinaseq = '" + sinaSeq + "' ";
		        	  int updateResultCount = sSta.executeUpdate(updateSql); 
		        	  
		        	  updateDataMAByID(sinaSeq, connection);
		      
		        
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
	   
	   /**
		 * 更新MA数据
		 * @param string
		 * @throws SQLException 
		 * @throws ClassNotFoundException 
		 */
		public int  updateDataMAByID(String seq, Connection connection) throws ClassNotFoundException, SQLException {
			// TODO Auto-generated method stub
	        String sql = "select * from StockDataSina  where sinaseq ='" + seq + "'";

	        Statement sSta = null;
	  	
	        ResultSet rs =null;
	        
	        try
	        {
	        
			    //获得当前的详细数据
		        SinaDailyStockDataDo currentData = null;
		        sSta = connection.createStatement();
		        
		        try
			    {

		        	rs = sSta.executeQuery(sql);
			        while (rs.next())
			        {
			        	currentData = new SinaDailyStockDataDo();
			        	currentData.setSinaseq(rs.getInt("SINASEQ"));
			        	currentData.setStockname(rs.getString("STOCKNAME"));
			        	currentData.setStockcode(rs.getString("STOCKCODE"));
			        	currentData.setStockexchangecode(rs.getString("STOCKEXCHANGECODE"));
			        	currentData.setOpenprice(rs.getString("OPENPRICE"));
			        	currentData.setLastcloseprice(rs.getString("LASTCLOSEPRICE"));
			        	currentData.setCurrentprice(rs.getString("CURRENTPRICE"));
			        	currentData.setTopprice(rs.getString("TOPPRICE"));
			        	currentData.setBottomprice(rs.getString("BOTTOMPRICE"));
			        	currentData.setBuyprice(rs.getString("BUYPRICE"));
			        	currentData.setSellprice(rs.getString("SELLPRICE"));
			        	currentData.setTransactionamount(rs.getString("TRANSACTIONAMOUNT"));
			        	currentData.setTransactionnumber(rs.getString("TRANSACTIONNUMBER"));
			        	currentData.setDataday(rs.getDate("DATADAY"));
			        	currentData.setDatatime(rs.getString("DATATIME"));
			        	
			        	break;
			        }
			    }finally
			    {
			    	try
			        {
			           
			            if (rs != null)
			            {
			            	rs.close();
			            	rs = null;
			            }
			        }
			        catch (SQLException e)
			        {
			            e.printStackTrace();
			        }
			    }
			    
			    return updateMa(currentData, connection);
	        }
	        finally
	        {
	        	try
	        	{
		        	
		            if (sSta != null)
		            {
		            	sSta.close();
		            }
		      
	        	 }
		        catch (SQLException e)
		        {
		            e.printStackTrace();
		        }
	        }
		    
		}
		
		private float getMAValue(int count , float[] dataList)
		{
			if(dataList == null || dataList.length <= 0)
			{
				return 0.0f;
			}
			else
			{
				//Do Nothing
			}
				
			 	float ma = 0.0f;
			    float matotal = 0.0f;
			    int macount = 0;
			    
			    for(int i = 0; i < count && i <dataList.length ; i++)
			    {
			    	float curr = dataList[i];
			    	if (curr <= 0.0f)
			    	{
			    		//Do Nothing
			    	}
			    	else
			    	{
			    		matotal += curr;
			    		macount++;
			    	}
			    }
			    
			    if(macount == 0)
			    {
			    	ma = 0;
			    }
			    else
			    {
			    	ma = matotal / macount;
			    }
			    
			    return ma;
		}
		
		private int updateMa(SinaDailyStockDataDo  currentData, Connection connection) throws SQLException
		{
			int result = 0;
			
	        ResultSet resultSet =null;
	        
			if (currentData == null)
		    {
		    	return 0 ;
		    }
		    else
		    {
		    	//Do Nothing
		    }
		    
			Statement sSta = connection.createStatement();
		  	

	    	int seq =  currentData.getSinaseq();
		    
		    List<SinaDailyStockDataDo> pricelist = new ArrayList<SinaDailyStockDataDo>();
		    //获得最近的120个数据
		    String select120SQL = "SELECT SINASEQ, STOCKCODE, STOCKEXCHANGECODE, DATADAY, CURRENTPRICE "
		    	+ " FROM StockDataSina WHERE " 
		    	+ " STOCKCODE = '" + currentData.getStockcode() + "' "
		    	+ " AND STOCKEXCHANGECODE = '" +currentData.getStockexchangecode() + "' " 
		    	+ " AND STRCMP(DATADAY,'"+ DateFactory.ConvertToString(currentData.getDataday())+"') <= 0 "
		    	+ " order by DATADAY desc limit 0, 120 ";
		    try
		    {

		         resultSet = sSta.executeQuery(select120SQL);
		        while (resultSet.next())
		        {
		        	SinaDailyStockDataDo fms = new SinaDailyStockDataDo();
		        	fms.setSinaseq(resultSet.getInt("SINASEQ"));
		        	fms.setStockcode(resultSet.getString("STOCKCODE"));
		        	fms.setStockexchangecode(resultSet.getString("STOCKEXCHANGECODE"));
		        	fms.setCurrentprice(resultSet.getString("CURRENTPRICE"));
		        	fms.setDataday(resultSet.getDate("DATADAY"));
		        	
		        	pricelist.add(fms);
		        }
		     
		    }finally
		    {
		    	try
		        {
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
		    
		    float[] pricefloatList = new float[120];
		    for(int j = 0; j < pricefloatList.length; j++)
		    {
		    	pricefloatList[j] = 0.0f;
		    }
		    
		    for(int j = 0; j < pricefloatList.length && j < pricelist.size(); j++)
		    {
		    	SinaDailyStockDataDo fms = pricelist.get(j);
		    	
		    	pricefloatList[j] = fms.Currentpricefloat();
		    }
		    
		    float ma5 = getMAValue(5, pricefloatList);
		    float ma10 = getMAValue(10, pricefloatList);
		    float ma20 = getMAValue(20, pricefloatList);
		    float ma30 = getMAValue(30, pricefloatList);
		    float ma60 = getMAValue(60, pricefloatList);
		    float ma120 = getMAValue(120, pricefloatList);
		    float ma240 = getMAValue(240, pricefloatList);
		    
		    String updateMASQL = "UPDATE StockDataSina SET MA5='"+ma5+"', MA10='"+ma10+"', MA20='"+ma20+"', MA30='"+ma30+"', MA60='"+ma60+"', MA120='"+ma120+"', MA240='"+ma240+"', updatetime=now() WHERE sinaseq ='" + seq + "'";
		
		    try
		    {
		         int updateCount = sSta.executeUpdate(updateMASQL);
		        
		         result += updateCount;
		     
		    }finally
		    {
		    	try
		        {
		             
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
			return m_ProgressDescription;
		}


		
		
}
