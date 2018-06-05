package com.rabbit.smart.dao.mapper;

import com.rabbit.smart.dao.entity.SysOperationLog;
import com.rabbit.smart.dao.entity.SysOperationLogExample;
import com.rabbit.smart.dao.entity.SysOperationLogWithBLOBs;
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
    int insert(SysOperationLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int insertSelective(SysOperationLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    List<SysOperationLogWithBLOBs> selectByExampleWithBLOBs(SysOperationLogExample example);

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
    SysOperationLogWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysOperationLogWithBLOBs record, @Param("example") SysOperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") SysOperationLogWithBLOBs record, @Param("example") SysOperationLogExample example);

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
    int updateByPrimaryKeySelective(SysOperationLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(SysOperationLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysOperationLog record);
}