package com.xkumo.xstock.datacollector.portal.index;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	
	public String index() {
	
		return Action.SUCCESS;
	}
}
