package com.atguigu.atcrowdfunding.entity;

/**
 * @Auther: yzy
 * @Date: 2019/1/20 14:38
 * @Description:
 */
public class User {

    private Integer id;
    private String username;
    private String loginattc;
    private String password;
    private String email;
    private String createtime;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginattc() {
        return loginattc;
    }

    public void setLoginattc(String loginattc) {
        this.loginattc = loginattc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
