package com.rabbit.smart.dao.db2.mapper;

import com.rabbit.smart.dao.db2.entity.XhxtCode;
import com.rabbit.smart.dao.db2.entity.XhxtCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XhxtCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UTCDBA.XHXT_CODE
     *
     * @mbggenerated
     */
    int countByExample(XhxtCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UTCDBA.XHXT_CODE
     *
     * @mbggenerated
     */
    int deleteByExample(XhxtCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UTCDBA.XHXT_CODE
     *
     * @mbggenerated
     */
    int insert(XhxtCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UTCDBA.XHXT_CODE
     *
     * @mbggenerated
     */
    int insertSelective(XhxtCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UTCDBA.XHXT_CODE
     *
     * @mbggenerated
     */
    List<XhxtCode> selectByExample(XhxtCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UTCDBA.XHXT_CODE
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") XhxtCode record, @Param("example") XhxtCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UTCDBA.XHXT_CODE
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") XhxtCode record, @Param("example") XhxtCodeExample example);
}