package com.rabbit.smart.dao.diy.mapper;

import com.rabbit.smart.dao.diy.entity.DiySysUser;

public interface DiySysUserMapper {
    DiySysUser selectByAccount(String account);
}
