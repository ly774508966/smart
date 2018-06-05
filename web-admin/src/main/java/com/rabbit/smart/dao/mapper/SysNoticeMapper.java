package com.rabbit.smart.dao.mapper;

import com.rabbit.smart.dao.entity.SysNotice;
import com.rabbit.smart.dao.entity.SysNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysNoticeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    int countByExample(SysNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    int deleteByExample(SysNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    int insert(SysNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    int insertSelective(SysNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    List<SysNotice> selectByExampleWithBLOBs(SysNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    List<SysNotice> selectByExample(SysNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    SysNotice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysNotice record, @Param("example") SysNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") SysNotice record, @Param("example") SysNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysNotice record, @Param("example") SysNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(SysNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_notice
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysNotice record);
}