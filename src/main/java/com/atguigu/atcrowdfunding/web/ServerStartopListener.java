package com.atguigu.atcrowdfunding.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Auther: yzy
 * @Date: 2019/1/26 15:02
 * @Description:
 */
public class ServerStartopListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //将web应用名称（路径）保存到application范围中
        ServletContext application = servletContextEvent.getServletContext();
        String path = application.getContextPath();
        application.setAttribute("APP_PATH", path);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
