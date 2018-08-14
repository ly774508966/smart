package com.rabbit.smart.service;

import com.rabbit.smart.dao.diy.entity.DiySysUser;
import com.rabbit.smart.dao.diy.mapper.DiySysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiySysUserService {
    @Autowired
    private DiySysUserMapper diySysUserMapper;

    public DiySysUser getByAccount(String account) {
        return diySysUserMapper.selectByAccount(account);
    }
}
