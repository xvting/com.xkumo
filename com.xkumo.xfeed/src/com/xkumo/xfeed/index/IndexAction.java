package com.xkumo.xfeed.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	public String index() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		
//		//获得登录用户的LoginID，设置到Session
//		AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal(); 
//        String name = principal.getName();
//        session.setAttribute("loginId", name);
        
		return "success";
	}
}
