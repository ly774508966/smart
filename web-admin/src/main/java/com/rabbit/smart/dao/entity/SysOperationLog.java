package com.rabbit.smart.dao.entity;

import java.util.Date;

public class SysOperationLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_operation_log.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_operation_log.logtype
     *
     * @mbggenerated
     */
    private String logtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_operation_log.logname
     *
     * @mbggenerated
     */
    private String logname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_operation_log.userid
     *
     * @mbggenerated
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_operation_log.classname
     *
     * @mbggenerated
     */
    private String classname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_operation_log.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_operation_log.succeed
     *
     * @mbggenerated
     */
    private String succeed;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_operation_log.id
     *
     * @return the value of sys_operation_log.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_operation_log.id
     *
     * @param id the value for sys_operation_log.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_operation_log.logtype
     *
     * @return the value of sys_operation_log.logtype
     *
     * @mbggenerated
     */
    public String getLogtype() {
        return logtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_operation_log.logtype
     *
     * @param logtype the value for sys_operation_log.logtype
     *
     * @mbggenerated
     */
    public void setLogtype(String logtype) {
        this.logtype = logtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_operation_log.logname
     *
     * @return the value of sys_operation_log.logname
     *
     * @mbggenerated
     */
    public String getLogname() {
        return logname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_operation_log.logname
     *
     * @param logname the value for sys_operation_log.logname
     *
     * @mbggenerated
     */
    public void setLogname(String logname) {
        this.logname = logname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_operation_log.userid
     *
     * @return the value of sys_operation_log.userid
     *
     * @mbggenerated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_operation_log.userid
     *
     * @param userid the value for sys_operation_log.userid
     *
     * @mbggenerated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_operation_log.classname
     *
     * @return the value of sys_operation_log.classname
     *
     * @mbggenerated
     */
    public String getClassname() {
        return classname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_operation_log.classname
     *
     * @param classname the value for sys_operation_log.classname
     *
     * @mbggenerated
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_operation_log.createtime
     *
     * @return the value of sys_operation_log.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_operation_log.createtime
     *
     * @param createtime the value for sys_operation_log.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_operation_log.succeed
     *
     * @return the value of sys_operation_log.succeed
     *
     * @mbggenerated
     */
    public String getSucceed() {
        return succeed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_operation_log.succeed
     *
     * @param succeed the value for sys_operation_log.succeed
     *
     * @mbggenerated
     */
    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }
}