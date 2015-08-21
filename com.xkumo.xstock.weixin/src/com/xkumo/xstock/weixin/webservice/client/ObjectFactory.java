package com.xkumo.xstock.weixin.webservice.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.xkumo.xstock.weixin.webservice.client
 * package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _StartTask_QNAME = new QName(
			"http://webservice.xstock.xkumo.com/", "startTask");
	private final static QName _StopTask_QNAME = new QName(
			"http://webservice.xstock.xkumo.com/", "stopTask");
	private final static QName _StartTaskResponse_QNAME = new QName(
			"http://webservice.xstock.xkumo.com/", "startTaskResponse");
	private final static QName _StopTaskResponse_QNAME = new QName(
			"http://webservice.xstock.xkumo.com/", "stopTaskResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.xkumo.xstock.weixin.webservice.client
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link StopTaskResponse }
	 * 
	 */
	public StopTaskResponse createStopTaskResponse() {
		return new StopTaskResponse();
	}

	/**
	 * Create an instance of {@link StartTaskResponse }
	 * 
	 */
	public StartTaskResponse createStartTaskResponse() {
		return new StartTaskResponse();
	}

	/**
	 * Create an instance of {@link StartTask }
	 * 
	 */
	public StartTask createStartTask() {
		return new StartTask();
	}

	/**
	 * Create an instance of {@link StopTask }
	 * 
	 */
	public StopTask createStopTask() {
		return new StopTask();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link StartTask }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.xstock.xkumo.com/", name = "startTask")
	public JAXBElement<StartTask> createStartTask(StartTask value) {
		return new JAXBElement<StartTask>(_StartTask_QNAME, StartTask.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link StopTask }{@code
	 * >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.xstock.xkumo.com/", name = "stopTask")
	public JAXBElement<StopTask> createStopTask(StopTask value) {
		return new JAXBElement<StopTask>(_StopTask_QNAME, StopTask.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link StartTaskResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.xstock.xkumo.com/", name = "startTaskResponse")
	public JAXBElement<StartTaskResponse> createStartTaskResponse(
			StartTaskResponse value) {
		return new JAXBElement<StartTaskResponse>(_StartTaskResponse_QNAME,
				StartTaskResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link StopTaskResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.xstock.xkumo.com/", name = "stopTaskResponse")
	public JAXBElement<StopTaskResponse> createStopTaskResponse(
			StopTaskResponse value) {
		return new JAXBElement<StopTaskResponse>(_StopTaskResponse_QNAME,
				StopTaskResponse.class, null, value);
	}

}
