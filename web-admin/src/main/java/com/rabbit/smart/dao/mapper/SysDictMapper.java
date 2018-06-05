package com.rabbit.smart.dao.mapper;

import com.rabbit.smart.dao.entity.SysDict;
import com.rabbit.smart.dao.entity.SysDictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDictMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbggenerated
     */
    int countByExample(SysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbggenerated
     */
    int deleteByExample(SysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbggenerated
     */
    int insert(SysDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbggenerated
     */
    int insertSelective(SysDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbggenerated
     */
    List<SysDict> selectByExample(SysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbggenerated
     */
    SysDict selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysDict record, @Param("example") SysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysDict record, @Param("example") SysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysDict record);
}