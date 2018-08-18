package com.rabbit.smart.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.rabbit.smart.dao.entity.SysLoginLog;
import com.rabbit.smart.dao.entity.SysLoginLogExample;
import com.rabbit.smart.dao.mapper.SysLoginLogMapper;
import com.rabbit.smart.dto.in.LogLoginDto;
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

    @RequestMapping(value = "operation", method = RequestMethod.POST)
    public ResponseEntity<Void> operation() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public LogController() {

    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<PageInfo<SysLoginLog>> login(LogLoginDto params) {
        //TODO 分页统一校验
        Validator.checkNotNull(params.getType(), "操作类型");
        Validator.checkNotNull(params.getPageIndex(), "页码");
        Validator.checkNotNull(params.getPageSize(), "页大小");
        PageHelper.startPage(params.getPageIndex(), params.getPageSize());

        SysLoginLogExample example = new SysLoginLogExample();
        SysLoginLogExample.Criteria criteria = example.createCriteria();
        //操作类型
        if (params.getType().equals(1)) {
            criteria.andIsLoginEqualTo(true);
        } else if (params.getType().equals(2)) {
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
        List<SysLoginLog> logs = loginLogMapper.selectByExample(example);
        return new ResponseEntity(new PageInfo<>(logs), HttpStatus.OK);
    }
}
