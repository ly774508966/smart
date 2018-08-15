package com.rabbit.smart.service;

import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.diy.mapper.DiySysUserMapper;
import com.rabbit.smart.dao.entity.SysUser;
import com.rabbit.smart.dao.entity.SysUserExample;
import com.rabbit.smart.dao.mapper.SysUserMapper;
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

    public DiySysUser getDiyByAccount(String account) {
        return diySysUserMapper.selectDiyUserByAccount(account);
    }

    public SysUser getByAccount(String account) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andAccountEqualTo(account);
        List<SysUser> users = sysUserMapper.selectByExample(example);
        return users != null && users.size() > 0 ? users.get(0) : null;
    }

    public void updateUserStatus(int userid, int status) {
        SysUser user = new SysUser();
        user.setId(userid);
        user.setStatus(status);
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

}
