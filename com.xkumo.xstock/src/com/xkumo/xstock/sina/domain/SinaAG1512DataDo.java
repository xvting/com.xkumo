package com.xkumo.xstock.sina.domain;

import java.util.Date;

import com.xkumo.vendor.sina.SinaAG1512Data;

public class SinaAG1512DataDo {
	
	private SinaAG1512Data m_sinaAG1512Data;
	
	public SinaAG1512DataDo(SinaAG1512Data sinaAG1512Data)
	{
		m_sinaAG1512Data = sinaAG1512Data;
	}
	
	
	public String getName() {
		return m_sinaAG1512Data.getName();
	}

	public Date getDataday() {
		return m_sinaAG1512Data.getDataday();
	}

	public String getDatatime() {
		return m_sinaAG1512Data.getDatatime();
	}

	public String getOpenprice() {
		return m_sinaAG1512Data.getOpenprice();
	}

	public String getTopprice() {
		return m_sinaAG1512Data.getTopprice();
	}

	public String getBottomprice() {
		return m_sinaAG1512Data.getBottomprice();
	}

	public String getBuyprice() {
		return m_sinaAG1512Data.getBuyprice();
	}

	public String getSellprice() {
		return m_sinaAG1512Data.getSellprice();
	}

	public String getCurrentprice() {
		return m_sinaAG1512Data.getCurrentprice();
	}

	public String getChangeprice() {
		return m_sinaAG1512Data.getChangeprice();
	}

	public String getLastchangeprice() {
		return m_sinaAG1512Data.getLastchangeprice();
	}

	public String getBuynumber() {
		return m_sinaAG1512Data.getBuynumber();
	}

	public String getSellnumber() {
		return m_sinaAG1512Data.getSellnumber();
	}

	public String getHoldamount() {
		return m_sinaAG1512Data.getHoldamount();
	}

	public String getChangevolumn() {
		return m_sinaAG1512Data.getChangevolumn();
	}

	public String getMarket() {
		return m_sinaAG1512Data.getMarket();
	}

	public String getProduct() {
		return m_sinaAG1512Data.getProduct();
	}

}
