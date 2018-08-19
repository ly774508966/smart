package com.rabbit.smart.test.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 系统就绪消息
 * Created by xuzhang on 2018/4/23.
 */

@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseLink extends Response {
    public ResponseLink() {
    }

    @XmlElement(name = "vendor")
    private String vendor;

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
