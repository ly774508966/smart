package com.rabbit.smart.dao.mapper;

import com.rabbit.smart.dao.entity.SysRight;
import com.rabbit.smart.dao.entity.SysRightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRightMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbggenerated
     */
    int countByExample(SysRightExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbggenerated
     */
    int deleteByExample(SysRightExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbggenerated
     */
    int insert(SysRight record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbggenerated
     */
    int insertSelective(SysRight record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbggenerated
     */
    List<SysRight> selectByExample(SysRightExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbggenerated
     */
    SysRight selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysRight record, @Param("example") SysRightExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysRight record, @Param("example") SysRightExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysRight record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_right
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysRight record);
}