package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.entity.AJAXResult;
import com.atguigu.atcrowdfunding.entity.Permission;
import com.atguigu.atcrowdfunding.entity.User;
import com.atguigu.atcrowdfunding.service.PermissionService;
import com.atguigu.atcrowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Auther: yzy
 * @Date: 2019/1/20 19:27
 * @Description:
 */
@Controller
public class DispatcherController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/error")
    public String error() {
        return "error";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/doAJAXLogin")
    @ResponseBody
    public Object doAJAXLogin(User user, HttpSession session) {
        User dbUser = userService.query4Login(user);
        AJAXResult ajaxResult = new AJAXResult();
        if (dbUser != null) {
            session.setAttribute("User", dbUser);

            //获取用户权限信息
            List<Permission> permissions = permissionService.queryPermissionByUser(dbUser);
            Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
            Permission root = null;
            Set<String> uriSet = new HashSet<String>();
            for (Permission permission : permissions) {
                permissionMap.put(permission.getId(), permission);
                if (permission.getUrl() != null && !"".equals(permission.getUrl())) {
                    uriSet.add(session.getServletContext().getContextPath() + permission.getUrl());
                }
            }
            session.setAttribute("authUriSet", uriSet);
            for (Permission child : permissions) {
                if (child.getPid() == 0) {
                    root = child;
                } else {
                    Permission parent = permissionMap.get(child.getPid());
                    parent.getChildren().add(child);
                }
            }

            session.setAttribute("rootPermission", root);
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    /**
     * 执行登陆功能
     *
     * @return
     */
    @RequestMapping("/doLogin")
    public String doLogin(User user, Model model) {

        //查询用户信息
        User dbUser = userService.query4Login(user);
        //判断用户信息是否存在
        if (dbUser != null) {
            return "main";
        } else {
            String errMsg = "登陆账号或密码不正确，请重新输入";
            model.addAttribute("errMsg", errMsg);
            return "redirect:login";
        }
    }
}
