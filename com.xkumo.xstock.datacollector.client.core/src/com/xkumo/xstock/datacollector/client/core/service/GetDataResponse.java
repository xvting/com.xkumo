package com.xkumo.xstock.datacollector.client.core.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for getDataResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="getDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.datacollector.xstock.xkumo.com/}sinaDailyStockData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDataResponse", propOrder = { "_return" })
public class GetDataResponse {

	@XmlElement(name = "return")
	protected SinaDailyStockData _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link SinaDailyStockData }
	 * 
	 */
	public SinaDailyStockData getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link SinaDailyStockData }
	 * 
	 */
	public void setReturn(SinaDailyStockData value) {
		this._return = value;
	}

}
