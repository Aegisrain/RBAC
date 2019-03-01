package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.dao.UserDao;
import com.atguigu.atcrowdfunding.entity.User;
import com.atguigu.atcrowdfunding.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryAll() {
        return userDao.queryAll();
    }

    @Override
    public User query4Login(User user) {
        return userDao.query4Login(user);
    }

    @Override
    public List<User> pageQueryData(Map<String, Object> map) {
        return userDao.pageQueryData(map);
    }

    @Override
    public int pageQueryCount(Map<String, Object> map) {
        return userDao.pageQueryCount(map);
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public User queryById(Integer id) {
        return userDao.queryById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void deleteUsers(Map<String, Object> map) {
        userDao.deleteUsers(map);
    }

    @Override
    public List<Integer> queryRoleidsByUserid(Integer id) {
       return userDao.queryRoleidsByUserid(id);
    }
}
