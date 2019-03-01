package com.atguigu.atcrowdfunding.dao;

import com.atguigu.atcrowdfunding.entity.Permission;
import com.atguigu.atcrowdfunding.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: yzy
 * @Date: 2019/2/28 17:53
 * @Description:
 */
public interface PermissionDao {

    @Select("SELECT * FROM permission WHERE pid IS NULL")
    Permission queryRootPermission();

    @Select("SELECT * FROM permission WHERE pid = #{pid}")
    List<Permission> queryChildrenPermission(Integer pid);

    @Select("SELECT * FROM permission")
    List<Permission> queryAll();

    void insertPermission(Permission permission);

    @Select("SELECT * FROM permission WHERE id = #{id}")
    Permission queryById(Integer id);

    void updatePermission(Permission permission);

    void deletePermission(Permission permission);

    @Select("SELECT permissionid FROM role_permission WHERE roleid = #{roleid}")
    List<Integer> queryPermissionidsByRoleid(Integer roleid);

    List<Permission> queryPermissionByUser(User dbUser);
}
