package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @Auther: yzy
 * @Date: 2019/2/28 13:56
 * @Description:
 */
public interface RoleService {
    List<Role> pageQueryData(Map<String, Object> map);

    int pageQueryCount(Map<String, Object> map);

    List<Role> queryAll();

    void insertRolePermission(Map<String, Object> paramMap);


}

