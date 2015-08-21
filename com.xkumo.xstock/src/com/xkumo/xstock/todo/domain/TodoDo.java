package com.xkumo.xstock.todo.domain;

import java.util.Date;

public class TodoDo {
	public int todoseq;
	public String todoname;
	public String todoremark;
	public String todogroupid;
	public String todolevel;
	public boolean isresolved = false;
	public Date planresolvedtime = new Date();
	public Date actualresolvedtime;
	
	
	public int getTodoseq() {
		return todoseq;
	}
	public void setTodoseq(int todoseq) {
		this.todoseq = todoseq;
	}
	public String getTodoname() {
		return todoname;
	}
	public void setTodoname(String todoname) {
		this.todoname = todoname;
	}
	public String getTodoremark() {
		return todoremark;
	}
	public void setTodoremark(String todoremark) {
		this.todoremark = todoremark;
	}
	public String getTodogroupid() {
		return todogroupid;
	}
	public void setTodogroupid(String todogroupid) {
		this.todogroupid = todogroupid;
	}
	public String getTodolevel() {
		return todolevel;
	}
	public void setTodolevel(String todolevel) {
		this.todolevel = todolevel;
	}
	public boolean isIsresolved() {
		return isresolved;
	}
	public void setIsresolved(boolean isresolved) {
		this.isresolved = isresolved;
	}
	public Date getPlanresolvedtime() {
		return planresolvedtime;
	}
	public void setPlanresolvedtime(Date planresolvedtime) {
		this.planresolvedtime = planresolvedtime;
	}
	public Date getActualresolvedtime() {
		return actualresolvedtime;
	}
	public void setActualresolvedtime(Date actualresolvedtime) {
		this.actualresolvedtime = actualresolvedtime;
	}
	
	
}
