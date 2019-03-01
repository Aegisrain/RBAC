package com.atguigu.atcrowdfunding.dao;

import com.atguigu.atcrowdfunding.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Auther: yzy
 * @Date: 2019/1/20 14:26
 * @Description:
 */
public interface UserDao {

    @Select("SELECT * FROM USER")
    List<User> queryAll();

    @Select("SELECT * FROM USER WHERE loginattc = #{loginattc} AND password = #{password}")
    User query4Login(User user);

    List<User> pageQueryData(Map<String, Object> map);

    int pageQueryCount(Map<String, Object> map);

    void insertUser(User user);

    @Select("SELECT * FROM USER WHERE id = #{id}")
    User queryById(Integer id);

    void update(User user);

    void deleteUserById(Integer id);

    void deleteUsers(Map<String, Object> map);

    @Select("SELECT roleid FROM user_role WHERE userid = #{userid}")
    List<Integer> queryRoleidsByUserid(Integer id);
}
