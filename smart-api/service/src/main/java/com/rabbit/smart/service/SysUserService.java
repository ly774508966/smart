package com.rabbit.smart.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.diy.mapper.DiySysUserMapper;
import com.rabbit.smart.dao.entity.SysUser;
import com.rabbit.smart.dao.entity.SysUserExample;
import com.rabbit.smart.dao.mapper.SysUserMapper;
import com.rabbit.smart.dto.in.UserQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserService {
    public static final Integer STATUS_USE = 1;//启用
    public static final Integer STATUS_NO_USE = 2;//冻结
    public static final Integer STATUS_DEL = 3;//删除

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private DiySysUserMapper diySysUserMapper;


    //查询用户信息
    public SysUser getByAccount(String account) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andAccountEqualTo(account);
        List<SysUser> users = sysUserMapper.selectByExample(example);
        return users != null && users.size() > 0 ? users.get(0) : null;
    }

    //更新用户状态
    public void updateUserStatus(String account, int status) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andAccountEqualTo(account);
        SysUser user = new SysUser();
        user.setStatus(status);
        sysUserMapper.updateByExampleSelective(user, example);
    }

    public PageInfo<DiySysUser> querySysUser(UserQueryDto params) {
        PageHelper.startPage(params.getPageIndex(), params.getPageSize());
        List<DiySysUser> users = diySysUserMapper.queryDiySysUser(params);
        return new PageInfo<>(users);
    }

}
