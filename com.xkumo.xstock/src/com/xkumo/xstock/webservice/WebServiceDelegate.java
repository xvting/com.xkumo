package com.xkumo.xstock.webservice;

import com.xkumo.xstock.application.App;
import com.xkumo.xstock.task.TaskManager;

@javax.jws.WebService(targetNamespace = "http://webservice.xstock.xkumo.com/", serviceName = "WebServiceService", portName = "WebServicePort", wsdlLocation = "WEB-INF/wsdl/WebServiceService.wsdl")
public class WebServiceDelegate {

	com.xkumo.xstock.webservice.WebService webService = new com.xkumo.xstock.webservice.WebService();

	public void startTask(String taskID) {
		webService.startTask(taskID);
	}

	public void stopTask(String taskID) {
		webService.stopTask(taskID);
	}

}