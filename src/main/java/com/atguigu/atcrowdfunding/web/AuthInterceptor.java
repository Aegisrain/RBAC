package com.atguigu.atcrowdfunding.web;

import com.atguigu.atcrowdfunding.entity.Permission;
import com.atguigu.atcrowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: yzy
 * @Date: 2019/3/1 12:39
 * @Description:
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户的请求地址
        String uri = request.getRequestURI();
        String path = request.getSession().getServletContext().getContextPath();

        //判断当前路劲是否需要进行权限验证
        //查询所有需要验证的路径集合
        List<Permission> permissions = permissionService.queryAll();
        Set<String> uriSet = new HashSet<String>();
        for (Permission permission : permissions) {
            if (permission.getUrl() != null && !"".equals(permission.getUrl())) {
                uriSet.add(request.getSession().getServletContext().getContextPath() + permission.getUrl());
            }
        }

        if (uriSet.contains(uri)) {
            //权限验证
            //判断当前用户是否拥有对应的权限
            Set<String> authUriSet = (Set<String>) request.getSession().getAttribute("authUriSet");
            if (authUriSet.contains(uri)) {
                return true;
            } else {
                response.sendRedirect(path + "/error");
                return false;
            }
        } else {
            return true;
        }
    }
}