package com.rabbit.smart.service;

import com.rabbit.smart.dao.entity.SysDepartment;
import com.rabbit.smart.dao.entity.SysDepartmentExample;
import com.rabbit.smart.dao.entity.SysRole;
import com.rabbit.smart.dao.mapper.SysDepartmentMapper;
import com.rabbit.smart.dto.Recursion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysDepartmentService {

    @Autowired
    private SysDepartmentMapper departmentMapper;

    //查询树形结构
    public Recursion<SysDepartment> queryTree() {
        List<SysDepartment> departments = departmentMapper.selectByExample(new SysDepartmentExample());
        SysDepartment department = new SysDepartment();
        department.setId(0);
        Recursion<SysDepartment> results = new Recursion<>(department);
        return recursion(departments, results);
    }

    private Recursion<SysDepartment> recursion(List<SysDepartment> departments, Recursion<SysDepartment> results) {
        List<Recursion<SysDepartment>> subs = new ArrayList<>();
        results.setSubs(subs);//子元素
        for (SysDepartment department : departments) {
            if (department.getParentId().equals(results.getNode().getId())) {
                Recursion sub = new Recursion<>(department);
                subs.add(sub);
                recursion(departments, sub);
            }
        }
        return results;
    }
}
