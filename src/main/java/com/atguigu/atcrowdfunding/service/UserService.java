package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Auther: yzy
 * @Date: 2019/1/20 14:24
 * @Description:
 */
public interface UserService {
    List<User> queryAll();

    User query4Login(User user);

    List<User> pageQueryData(Map<String, Object> map);

    int pageQueryCount(Map<String, Object> map);

    void insertUser(User user);

    User queryById(Integer id);

    void update(User user);

    void deleteUserById(Integer id);

    void deleteUsers(Map<String, Object> map);

    List<Integer> queryRoleidsByUserid(Integer id);
}
