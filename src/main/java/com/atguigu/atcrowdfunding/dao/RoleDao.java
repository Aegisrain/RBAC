package com.atguigu.atcrowdfunding.dao;

import com.atguigu.atcrowdfunding.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Auther: yzy
 * @Date: 2019/1/20 14:26
 * @Description:
 */
public interface RoleDao {

    List<Role> pageQueryData(Map<String, Object> map);

    int pageQueryCount(Map<String, Object> map);

    @Select("select * from role")
    List<Role> queryAll();

    void insertRolePermission(Map<String, Object> paramMap);

    void deleteRolePermissions(Map<String, Object> paramMap);

}
