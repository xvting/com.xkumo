package com.xkumo.xstock.data.action;

import java.io.*;
import java.net.*;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class firstAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	public String toFirstJspPage() throws IOException
	{
			return Action.SUCCESS;
	}
	
	
}
