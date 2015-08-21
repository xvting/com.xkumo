
package com.xkumo.xstock.datacollector.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getDataResponse", namespace = "http://service.datacollector.xstock.xkumo.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDataResponse", namespace = "http://service.datacollector.xstock.xkumo.com/")
public class GetDataResponse {

    @XmlElement(name = "return", namespace = "")
    private com.xkumo.vendor.sina.SinaDailyStockData _return;

    /**
     * 
     * @return
     *     returns SinaDailyStockData
     */
    public com.xkumo.vendor.sina.SinaDailyStockData getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.xkumo.vendor.sina.SinaDailyStockData _return) {
        this._return = _return;
    }

}
