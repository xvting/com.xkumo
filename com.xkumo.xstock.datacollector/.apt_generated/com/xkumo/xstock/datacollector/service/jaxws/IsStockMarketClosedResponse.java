
package com.xkumo.xstock.datacollector.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "isStockMarketClosedResponse", namespace = "http://service.datacollector.xstock.xkumo.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "isStockMarketClosedResponse", namespace = "http://service.datacollector.xstock.xkumo.com/")
public class IsStockMarketClosedResponse {

    @XmlElement(name = "return", namespace = "")
    private boolean _return;

    /**
     * 
     * @return
     *     returns boolean
     */
    public boolean isReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(boolean _return) {
        this._return = _return;
    }

}
