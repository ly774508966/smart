package com.rabbit.smart.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.rabbit.smart.dao.diy.mapper.DiySysUserMapper;
import com.rabbit.smart.dao.entity.SysLoginLog;
import com.rabbit.smart.dao.entity.SysLoginLogExample;
import com.rabbit.smart.dao.entity.SysOperationLog;
import com.rabbit.smart.dao.entity.SysOperationLogExample;
import com.rabbit.smart.dao.mapper.SysLoginLogMapper;
import com.rabbit.smart.dao.mapper.SysOperationLogMapper;
import com.rabbit.smart.dto.in.LogLoginDto;
import com.rabbit.smart.dto.in.LogOperationDto;
import com.rabbit.smart.util.param.Validator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 日志管理
 */
@Api(value = "log", tags = "日志管理")
@RestController
@RequestMapping("log")
public class LogController {
    @Autowired
    private SysLoginLogMapper loginLogMapper;
    @Autowired
    private SysOperationLogMapper operationLogMapper;
    @Autowired
    private DiySysUserMapper diySysUserMapper;

    @RequestMapping(value = "operation", method = RequestMethod.POST)
    public ResponseEntity<Void> operation(LogOperationDto params) {
        Validator.checkNotNull(params.getPageIndex(), "页码");
        Validator.checkNotNull(params.getPageSize(), "页大小");

        SysOperationLogExample example = new SysOperationLogExample();
        SysOperationLogExample.Criteria criteria = example.createCriteria();
        //操作类型
        String type = params.getType();
        if (type != null && !type.equals("0")) {
            criteria.andPermissionNameEqualTo(type);
        }
        //操作结果
        Integer result = params.getResult();
        if (result != null && result.equals(1)) {
            criteria.andSuccessEqualTo(true);
        } else if (result != null && result.equals(2)) {
            criteria.andSuccessEqualTo(false);
        }
        //操作用时
        Integer cost = params.getCost();
        if (cost != null) {
            criteria.andCostGreaterThanOrEqualTo(cost);
        }
        //用户账号
        String account = params.getAccount();
        if (StringUtil.isNotEmpty(account)) {
            criteria.andAccountLike(account + "%");
        }
        //开始时间
        Date fromDate = params.getFromDate();
        if (fromDate != null) {
            criteria.andCreatetimeGreaterThan(fromDate);
        }
        //结束时间
        Date toDate = params.getToDate();
        if (toDate != null) {
            criteria.andCreatetimeLessThan(toDate);
        }
        //排序
        example.setOrderByClause(" id desc ");

        PageHelper.startPage(params.getPageIndex(), params.getPageSize());
        List<SysOperationLog> logs = operationLogMapper.selectByExample(example);
        return new ResponseEntity(new PageInfo<>(logs), HttpStatus.OK);
    }

    @RequestMapping(value = "operation/type", method = RequestMethod.POST)
    public ResponseEntity<List<String>> operation_type() {
        return new ResponseEntity(diySysUserMapper.queryRequestPermissionName(), HttpStatus.OK);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<PageInfo<SysLoginLog>> login(LogLoginDto params) {
        //TODO 分页统一校验
        Validator.checkNotNull(params.getPageIndex(), "页码");
        Validator.checkNotNull(params.getPageSize(), "页大小");


        SysLoginLogExample example = new SysLoginLogExample();
        SysLoginLogExample.Criteria criteria = example.createCriteria();
        //操作类型
        Integer type = params.getType();
        if (type != null && type.equals(1)) {
            criteria.andIsLoginEqualTo(true);
        } else if (type != null && params.getType().equals(2)) {
            criteria.andIsLoginEqualTo(false);
        }
        //用户账号
        String account = params.getAccount();
        if (StringUtil.isNotEmpty(account)) {
            criteria.andAccountLike(account + "%");
        }
        //开始时间
        Date fromDate = params.getFromDate();
        if (fromDate != null) {
            criteria.andCreatetimeGreaterThan(fromDate);
        }
        //结束时间
        Date toDate = params.getToDate();
        if (toDate != null) {
            criteria.andCreatetimeLessThan(toDate);
        }
        //排序
        example.setOrderByClause(" id desc ");
        PageHelper.startPage(params.getPageIndex(), params.getPageSize());
        List<SysLoginLog> logs = loginLogMapper.selectByExample(example);
        return new ResponseEntity(new PageInfo<>(logs), HttpStatus.OK);
    }
}
