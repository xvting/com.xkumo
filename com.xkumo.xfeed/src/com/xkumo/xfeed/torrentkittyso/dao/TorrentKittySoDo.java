package com.xkumo.xfeed.torrentkittyso.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TorrentKittySoDo {
	private int pagenum; 
	private String url;
	private String title;
	private String magnetlink;
	private String content;
	
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMagnetlink() {
		return magnetlink;
	}
	public void setMagnetlink(String magnetlink) {
		this.magnetlink = magnetlink;
	}
	
	public static TorrentKittySoDo create(ResultSet rs) throws SQLException
	{
		if(rs == null)
		{
			return null;
		}
		else
		{
			//Do Ntohing
		}
		
		TorrentKittySoDo result = new TorrentKittySoDo();
		
		result.setPagenum(rs.getInt("PageNum"));
		result.setUrl(rs.getString("URL"));
		result.setTitle(rs.getString("title"));
		result.setMagnetlink(rs.getString("magnetlink"));
		result.setContent(rs.getString("Content"));
		
		return result;
	}
	
}
