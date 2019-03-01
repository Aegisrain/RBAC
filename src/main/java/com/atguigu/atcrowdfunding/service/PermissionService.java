package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.entity.Permission;
import com.atguigu.atcrowdfunding.entity.User;

import java.util.List;

/**
 * @Auther: yzy
 * @Date: 2019/2/28 17:53
 * @Description:
 */
public interface PermissionService {
    Permission queryRootPermission();

    List<Permission> queryChildrenPermission(Integer pid);

    List<Permission> queryAll();

    void insertPermission(Permission permission);

    Permission queryBtId(Integer id);

    void updatePermission(Permission permission);

    void deletePermission(Permission permission);

    List<Integer> queryPermissionidsByRoleid(Integer roleid);

    List<Permission> queryPermissionByUser(User dbUser);
}
