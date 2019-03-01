package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.dao.PermissionDao;
import com.atguigu.atcrowdfunding.entity.Permission;
import com.atguigu.atcrowdfunding.entity.User;
import com.atguigu.atcrowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: yzy
 * @Date: 2019/2/28 17:53
 * @Description:
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Permission queryRootPermission() {
        return permissionDao.queryRootPermission();
    }

    @Override
    public List<Permission> queryChildrenPermission(Integer pid) {
        return permissionDao.queryChildrenPermission(pid);
    }

    @Override
    public List<Permission> queryAll() {
        return permissionDao.queryAll();
    }

    @Override
    public void insertPermission(Permission permission) {
        permissionDao.insertPermission(permission);
    }

    @Override
    public Permission queryBtId(Integer id) {
        return permissionDao.queryById(id);
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionDao.updatePermission(permission);
    }

    @Override
    public void deletePermission(Permission permission) {
        permissionDao.deletePermission(permission);
    }

    @Override
    public List<Integer> queryPermissionidsByRoleid(Integer roleid) {
        return permissionDao.queryPermissionidsByRoleid(roleid);
    }

    @Override
    public List<Permission> queryPermissionByUser(User dbUser) {
        return permissionDao.queryPermissionByUser(dbUser);
    }
}
