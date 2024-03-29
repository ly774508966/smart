package com.rabbit.smart.dao.mapper;

import com.rabbit.smart.dao.entity.SysOperationLog;
import com.rabbit.smart.dao.entity.SysOperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOperationLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int countByExample(SysOperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int deleteByExample(SysOperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int insert(SysOperationLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int insertSelective(SysOperationLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    List<SysOperationLog> selectByExample(SysOperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    SysOperationLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysOperationLog record, @Param("example") SysOperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysOperationLog record, @Param("example") SysOperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysOperationLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysOperationLog record);
}