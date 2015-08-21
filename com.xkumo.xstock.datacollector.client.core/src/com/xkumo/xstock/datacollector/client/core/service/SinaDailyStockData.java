package com.xkumo.xstock.datacollector.client.core.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for sinaDailyStockData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="sinaDailyStockData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bottomprice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buy1number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buy1price" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buy2number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buy2price" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buy3number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buy3price" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buy4number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buy4price" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buy5number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buy5price" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buyprice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentprice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataday" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="datatime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastcloseprice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ma10" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ma120" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ma20" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ma240" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ma30" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ma5" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ma60" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="openprice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sell1number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sell1price" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sell2number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sell2price" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sell3number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sell3price" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sell4number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sell4price" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sell5number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sell5price" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sellprice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stockcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stockexchangecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stockname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="topprice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionamount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionnumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sinaDailyStockData", propOrder = { "bottomprice",
		"buy1Number", "buy1Price", "buy2Number", "buy2Price", "buy3Number",
		"buy3Price", "buy4Number", "buy4Price", "buy5Number", "buy5Price",
		"buyprice", "content", "currentprice", "dataday", "datatime",
		"lastcloseprice", "ma10", "ma120", "ma20", "ma240", "ma30", "ma5",
		"ma60", "openprice", "sell1Number", "sell1Price", "sell2Number",
		"sell2Price", "sell3Number", "sell3Price", "sell4Number", "sell4Price",
		"sell5Number", "sell5Price", "sellprice", "stockcode",
		"stockexchangecode", "stockname", "topprice", "transactionamount",
		"transactionnumber" })
public class SinaDailyStockData {

	protected String bottomprice;
	@XmlElement(name = "buy1number")
	protected String buy1Number;
	@XmlElement(name = "buy1price")
	protected String buy1Price;
	@XmlElement(name = "buy2number")
	protected String buy2Number;
	@XmlElement(name = "buy2price")
	protected String buy2Price;
	@XmlElement(name = "buy3number")
	protected String buy3Number;
	@XmlElement(name = "buy3price")
	protected String buy3Price;
	@XmlElement(name = "buy4number")
	protected String buy4Number;
	@XmlElement(name = "buy4price")
	protected String buy4Price;
	@XmlElement(name = "buy5number")
	protected String buy5Number;
	@XmlElement(name = "buy5price")
	protected String buy5Price;
	protected String buyprice;
	protected String content;
	protected String currentprice;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar dataday;
	protected String datatime;
	protected String lastcloseprice;
	protected float ma10;
	protected float ma120;
	protected float ma20;
	protected float ma240;
	protected float ma30;
	protected float ma5;
	protected float ma60;
	protected String openprice;
	@XmlElement(name = "sell1number")
	protected String sell1Number;
	@XmlElement(name = "sell1price")
	protected String sell1Price;
	@XmlElement(name = "sell2number")
	protected String sell2Number;
	@XmlElement(name = "sell2price")
	protected String sell2Price;
	@XmlElement(name = "sell3number")
	protected String sell3Number;
	@XmlElement(name = "sell3price")
	protected String sell3Price;
	@XmlElement(name = "sell4number")
	protected String sell4Number;
	@XmlElement(name = "sell4price")
	protected String sell4Price;
	@XmlElement(name = "sell5number")
	protected String sell5Number;
	@XmlElement(name = "sell5price")
	protected String sell5Price;
	protected String sellprice;
	protected String stockcode;
	protected String stockexchangecode;
	protected String stockname;
	protected String topprice;
	protected String transactionamount;
	protected String transactionnumber;

	/**
	 * Gets the value of the bottomprice property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBottomprice() {
		return bottomprice;
	}

	/**
	 * Sets the value of the bottomprice property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBottomprice(String value) {
		this.bottomprice = value;
	}

	/**
	 * Gets the value of the buy1Number property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBuy1Number() {
		return buy1Number;
	}

	/**
	 * Sets the value of the buy1Number property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBuy1Number(String value) {
		this.buy1Number = value;
	}

	/**
	 * Gets the value of the buy1Price property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBuy1Price() {
		return buy1Price;
	}

	/**
	 * Sets the value of the buy1Price property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBuy1Price(String value) {
		this.buy1Price = value;
	}

	/**
	 * Gets the value of the buy2Number property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBuy2Number() {
		return buy2Number;
	}

	/**
	 * Sets the value of the buy2Number property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBuy2Number(String value) {
		this.buy2Number = value;
	}

	/**
	 * Gets the value of the buy2Price property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBuy2Price() {
		return buy2Price;
	}

	/**
	 * Sets the value of the buy2Price property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBuy2Price(String value) {
		this.buy2Price = value;
	}

	/**
	 * Gets the value of the buy3Number property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBuy3Number() {
		return buy3Number;
	}

	/**
	 * Sets the value of the buy3Number property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBuy3Number(String value) {
		this.buy3Number = value;
	}

	/**
	 * Gets the value of the buy3Price property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBuy3Price() {
		return buy3Price;
	}

	/**
	 * Sets the value of the buy3Price property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBuy3Price(String value) {
		this.buy3Price = value;
	}

	/**
	 * Gets the value of the buy4Number property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBuy4Number() {
		return buy4Number;
	}

	/**
	 * Sets the value of the buy4Number property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBuy4Number(String value) {
		this.buy4Number = value;
	}

	/**
	 * Gets the value of the buy4Price property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBuy4Price() {
		return buy4Price;
	}

	/**
	 * Sets the value of the buy4Price property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBuy4Price(String value) {
		this.buy4Price = value;
	}

	/**
	 * Gets the value of the buy5Number property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBuy5Number() {
		return buy5Number;
	}

	/**
	 * Sets the value of the buy5Number property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBuy5Number(String value) {
		this.buy5Number = value;
	}

	/**
	 * Gets the value of the buy5Price property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBuy5Price() {
		return buy5Price;
	}

	/**
	 * Sets the value of the buy5Price property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBuy5Price(String value) {
		this.buy5Price = value;
	}

	/**
	 * Gets the value of the buyprice property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBuyprice() {
		return buyprice;
	}

	/**
	 * Sets the value of the buyprice property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBuyprice(String value) {
		this.buyprice = value;
	}

	/**
	 * Gets the value of the content property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the value of the content property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContent(String value) {
		this.content = value;
	}

	/**
	 * Gets the value of the currentprice property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCurrentprice() {
		return currentprice;
	}

	/**
	 * Sets the value of the currentprice property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCurrentprice(String value) {
		this.currentprice = value;
	}

	/**
	 * Gets the value of the dataday property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getDataday() {
		return dataday;
	}

	/**
	 * Sets the value of the dataday property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setDataday(XMLGregorianCalendar value) {
		this.dataday = value;
	}

	/**
	 * Gets the value of the datatime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDatatime() {
		return datatime;
	}

	/**
	 * Sets the value of the datatime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDatatime(String value) {
		this.datatime = value;
	}

	/**
	 * Gets the value of the lastcloseprice property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLastcloseprice() {
		return lastcloseprice;
	}

	/**
	 * Sets the value of the lastcloseprice property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLastcloseprice(String value) {
		this.lastcloseprice = value;
	}

	/**
	 * Gets the value of the ma10 property.
	 * 
	 */
	public float getMa10() {
		return ma10;
	}

	/**
	 * Sets the value of the ma10 property.
	 * 
	 */
	public void setMa10(float value) {
		this.ma10 = value;
	}

	/**
	 * Gets the value of the ma120 property.
	 * 
	 */
	public float getMa120() {
		return ma120;
	}

	/**
	 * Sets the value of the ma120 property.
	 * 
	 */
	public void setMa120(float value) {
		this.ma120 = value;
	}

	/**
	 * Gets the value of the ma20 property.
	 * 
	 */
	public float getMa20() {
		return ma20;
	}

	/**
	 * Sets the value of the ma20 property.
	 * 
	 */
	public void setMa20(float value) {
		this.ma20 = value;
	}

	/**
	 * Gets the value of the ma240 property.
	 * 
	 */
	public float getMa240() {
		return ma240;
	}

	/**
	 * Sets the value of the ma240 property.
	 * 
	 */
	public void setMa240(float value) {
		this.ma240 = value;
	}

	/**
	 * Gets the value of the ma30 property.
	 * 
	 */
	public float getMa30() {
		return ma30;
	}

	/**
	 * Sets the value of the ma30 property.
	 * 
	 */
	public void setMa30(float value) {
		this.ma30 = value;
	}

	/**
	 * Gets the value of the ma5 property.
	 * 
	 */
	public float getMa5() {
		return ma5;
	}

	/**
	 * Sets the value of the ma5 property.
	 * 
	 */
	public void setMa5(float value) {
		this.ma5 = value;
	}

	/**
	 * Gets the value of the ma60 property.
	 * 
	 */
	public float getMa60() {
		return ma60;
	}

	/**
	 * Sets the value of the ma60 property.
	 * 
	 */
	public void setMa60(float value) {
		this.ma60 = value;
	}

	/**
	 * Gets the value of the openprice property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOpenprice() {
		return openprice;
	}

	/**
	 * Sets the value of the openprice property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOpenprice(String value) {
		this.openprice = value;
	}

	/**
	 * Gets the value of the sell1Number property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSell1Number() {
		return sell1Number;
	}

	/**
	 * Sets the value of the sell1Number property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSell1Number(String value) {
		this.sell1Number = value;
	}

	/**
	 * Gets the value of the sell1Price property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSell1Price() {
		return sell1Price;
	}

	/**
	 * Sets the value of the sell1Price property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSell1Price(String value) {
		this.sell1Price = value;
	}

	/**
	 * Gets the value of the sell2Number property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSell2Number() {
		return sell2Number;
	}

	/**
	 * Sets the value of the sell2Number property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSell2Number(String value) {
		this.sell2Number = value;
	}

	/**
	 * Gets the value of the sell2Price property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSell2Price() {
		return sell2Price;
	}

	/**
	 * Sets the value of the sell2Price property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSell2Price(String value) {
		this.sell2Price = value;
	}

	/**
	 * Gets the value of the sell3Number property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSell3Number() {
		return sell3Number;
	}

	/**
	 * Sets the value of the sell3Number property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSell3Number(String value) {
		this.sell3Number = value;
	}

	/**
	 * Gets the value of the sell3Price property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSell3Price() {
		return sell3Price;
	}

	/**
	 * Sets the value of the sell3Price property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSell3Price(String value) {
		this.sell3Price = value;
	}

	/**
	 * Gets the value of the sell4Number property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSell4Number() {
		return sell4Number;
	}

	/**
	 * Sets the value of the sell4Number property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSell4Number(String value) {
		this.sell4Number = value;
	}

	/**
	 * Gets the value of the sell4Price property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSell4Price() {
		return sell4Price;
	}

	/**
	 * Sets the value of the sell4Price property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSell4Price(String value) {
		this.sell4Price = value;
	}

	/**
	 * Gets the value of the sell5Number property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSell5Number() {
		return sell5Number;
	}

	/**
	 * Sets the value of the sell5Number property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSell5Number(String value) {
		this.sell5Number = value;
	}

	/**
	 * Gets the value of the sell5Price property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSell5Price() {
		return sell5Price;
	}

	/**
	 * Sets the value of the sell5Price property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSell5Price(String value) {
		this.sell5Price = value;
	}

	/**
	 * Gets the value of the sellprice property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSellprice() {
		return sellprice;
	}

	/**
	 * Sets the value of the sellprice property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSellprice(String value) {
		this.sellprice = value;
	}

	/**
	 * Gets the value of the stockcode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStockcode() {
		return stockcode;
	}

	/**
	 * Sets the value of the stockcode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStockcode(String value) {
		this.stockcode = value;
	}

	/**
	 * Gets the value of the stockexchangecode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStockexchangecode() {
		return stockexchangecode;
	}

	/**
	 * Sets the value of the stockexchangecode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStockexchangecode(String value) {
		this.stockexchangecode = value;
	}

	/**
	 * Gets the value of the stockname property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStockname() {
		return stockname;
	}

	/**
	 * Sets the value of the stockname property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStockname(String value) {
		this.stockname = value;
	}

	/**
	 * Gets the value of the topprice property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTopprice() {
		return topprice;
	}

	/**
	 * Sets the value of the topprice property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTopprice(String value) {
		this.topprice = value;
	}

	/**
	 * Gets the value of the transactionamount property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTransactionamount() {
		return transactionamount;
	}

	/**
	 * Sets the value of the transactionamount property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTransactionamount(String value) {
		this.transactionamount = value;
	}

	/**
	 * Gets the value of the transactionnumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTransactionnumber() {
		return transactionnumber;
	}

	/**
	 * Sets the value of the transactionnumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTransactionnumber(String value) {
		this.transactionnumber = value;
	}

}
