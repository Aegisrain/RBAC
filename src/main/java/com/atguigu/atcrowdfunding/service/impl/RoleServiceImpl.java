package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.dao.RoleDao;
import com.atguigu.atcrowdfunding.entity.Role;
import com.atguigu.atcrowdfunding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: yzy
 * @Date: 2019/1/20 14:25
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<Role> pageQueryData(Map<String, Object> map) {
        return roleDao.pageQueryData(map);
    }

    @Override
    public int pageQueryCount(Map<String, Object> map) {
        return roleDao.pageQueryCount(map);
    }

    @Override
    public List<Role> queryAll() {
        return roleDao.queryAll();
    }

    @Override
    public void insertRolePermission(Map<String, Object> paramMap) {
        roleDao.deleteRolePermissions(paramMap);
        roleDao.insertRolePermission(paramMap);
    }

}
