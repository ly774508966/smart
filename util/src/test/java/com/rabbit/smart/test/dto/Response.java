package com.rabbit.smart.test.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by xuzhang on 2018/4/23.
 */
@XmlAccessorType(XmlAccessType.FIELD)
// 用了这个之后就会自动忽略setter/getter方法。不用这个就需要去掉注解，需要保证属性名和xml里的表签名一致
public class Response {
    public Response() {
    }

    @XmlAttribute(name = "System")
    private String system;
    @XmlAttribute(name = "Ver")
    private String ver;

    @XmlElement(name = "systemtype")
    private String systemtype;
    @XmlElement(name = "messagetype")
    private String messagetype;
    @XmlElement(name = "sourceIP")
    private String sourceIP;
    @XmlElement(name = "targetIP")
    private String targetIP;
    @XmlElement(name = "user")
    private String user;
    @XmlElement(name = "password")
    private String password;

    public String getSystemtype() {
        return systemtype;
    }

    public void setSystemtype(String systemtype) {
        this.systemtype = systemtype;
    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }

    public String getTargetIP() {
        return targetIP;
    }

    public void setTargetIP(String targetIP) {
        this.targetIP = targetIP;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }
}
