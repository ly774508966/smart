package com.rabbit.smart.dao.first.mapper;

import com.rabbit.smart.dao.first.entity.SysUserLog;
import com.rabbit.smart.dao.first.entity.SysUserLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WWJCPT.SYS_USER_LOG
     *
     * @mbggenerated
     */
    int countByExample(SysUserLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WWJCPT.SYS_USER_LOG
     *
     * @mbggenerated
     */
    int deleteByExample(SysUserLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WWJCPT.SYS_USER_LOG
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String logid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WWJCPT.SYS_USER_LOG
     *
     * @mbggenerated
     */
    int insert(SysUserLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WWJCPT.SYS_USER_LOG
     *
     * @mbggenerated
     */
    int insertSelective(SysUserLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WWJCPT.SYS_USER_LOG
     *
     * @mbggenerated
     */
    List<SysUserLog> selectByExample(SysUserLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WWJCPT.SYS_USER_LOG
     *
     * @mbggenerated
     */
    SysUserLog selectByPrimaryKey(String logid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WWJCPT.SYS_USER_LOG
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysUserLog record, @Param("example") SysUserLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WWJCPT.SYS_USER_LOG
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysUserLog record, @Param("example") SysUserLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WWJCPT.SYS_USER_LOG
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysUserLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table WWJCPT.SYS_USER_LOG
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysUserLog record);
}