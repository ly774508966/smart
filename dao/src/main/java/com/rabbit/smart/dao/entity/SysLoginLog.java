package com.rabbit.smart.dao.entity;

import java.util.Date;

public class SysLoginLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.logname
     *
     * @mbggenerated
     */
    private String logname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.userid
     *
     * @mbggenerated
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.succeed
     *
     * @mbggenerated
     */
    private String succeed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.ip
     *
     * @mbggenerated
     */
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.message
     *
     * @mbggenerated
     */
    private String message;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.id
     *
     * @return the value of sys_login_log.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.id
     *
     * @param id the value for sys_login_log.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.logname
     *
     * @return the value of sys_login_log.logname
     *
     * @mbggenerated
     */
    public String getLogname() {
        return logname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.logname
     *
     * @param logname the value for sys_login_log.logname
     *
     * @mbggenerated
     */
    public void setLogname(String logname) {
        this.logname = logname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.userid
     *
     * @return the value of sys_login_log.userid
     *
     * @mbggenerated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.userid
     *
     * @param userid the value for sys_login_log.userid
     *
     * @mbggenerated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.createtime
     *
     * @return the value of sys_login_log.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.createtime
     *
     * @param createtime the value for sys_login_log.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.succeed
     *
     * @return the value of sys_login_log.succeed
     *
     * @mbggenerated
     */
    public String getSucceed() {
        return succeed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.succeed
     *
     * @param succeed the value for sys_login_log.succeed
     *
     * @mbggenerated
     */
    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.ip
     *
     * @return the value of sys_login_log.ip
     *
     * @mbggenerated
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.ip
     *
     * @param ip the value for sys_login_log.ip
     *
     * @mbggenerated
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.message
     *
     * @return the value of sys_login_log.message
     *
     * @mbggenerated
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.message
     *
     * @param message the value for sys_login_log.message
     *
     * @mbggenerated
     */
    public void setMessage(String message) {
        this.message = message;
    }
}