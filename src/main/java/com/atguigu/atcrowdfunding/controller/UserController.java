package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.entity.AJAXResult;
import com.atguigu.atcrowdfunding.entity.Page;
import com.atguigu.atcrowdfunding.entity.Role;
import com.atguigu.atcrowdfunding.entity.User;
import com.atguigu.atcrowdfunding.service.RoleService;
import com.atguigu.atcrowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: yzy
 * @Date: 2019/1/26 14:32
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/deletes")
    @ResponseBody
    public Object deletes(Integer[] userid) {
        AJAXResult ajaxResult = new AJAXResult();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userids", userid);
            userService.deleteUsers(map);

            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {
        AJAXResult ajaxResult = new AJAXResult();
        try {
            userService.deleteUserById(id);
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(User user) {
        AJAXResult ajaxResult = new AJAXResult();
        try {
            userService.update(user);
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    @RequestMapping("/edit")
    public String edit(Integer id, Model model) {
        User user = userService.queryById(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @RequestMapping("/assgin")
    public String assgin(Integer id, Model model) {
        User user = userService.queryById(id);
        model.addAttribute("user", user);

        List<Role> roles = roleService.queryAll();

        List<Role> assginedRoles = new ArrayList<Role>();
        List<Role> unassginRoles = new ArrayList<Role>();

        model.addAttribute("assginedRoles", assginedRoles);
        model.addAttribute("unassginRoles", unassginRoles);

        //获取关系表数据
        List<Integer> roleids = userService.queryRoleidsByUserid(id);
        for (Role role : roles) {
            if (roleids.contains(role.getId())) {
                assginedRoles.add(role);
            } else {
                unassginRoles.add(role);
            }
        }
        return "user/assgin";
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(User user) {
        AJAXResult ajaxResult = new AJAXResult();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setCreatetime(sdf.format(new Date()));
            userService.insertUser(user);
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }


    @RequestMapping("/pageQuery")
    @ResponseBody
    public Object pageQuery(String queryText, Integer pageNo, Integer pageSize) {

        AJAXResult ajaxResult = new AJAXResult();
        try {
            //分页查询
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("start", (pageNo - 1) * pageSize);
            map.put("size", pageSize);
            map.put("queryText", queryText);

            List<User> users = userService.pageQueryData(map);
            //总的数据条数
            int totalSize = userService.pageQueryCount(map);
            //总页码
            int totalNo = 0;
            if (totalSize % pageSize == 0) {
                totalNo = totalSize / pageSize;
            } else {
                totalNo = totalSize / pageSize + 1;
            }
            Page<User> userPage = new Page<User>();
            userPage.setDatas(users);
            userPage.setPageNo(pageNo);
            userPage.setTotalNo(totalNo);
            userPage.setTotalSize(totalSize);

            ajaxResult.setData(userPage);
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }


    @RequestMapping("/index")
    public String index() {
        return "user/index";
    }

    /**
     * 用户首页
     *
     * @return
     */
    @RequestMapping("/index1")
    public String index1(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                         @RequestParam(required = false, defaultValue = "2") Integer pageSize, Model model) {
        //分页查询
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (pageNo - 1) * pageSize);
        map.put("size", pageSize);

        List<User> users = userService.pageQueryData(map);
        model.addAttribute("users", users);
        //当前页码
        model.addAttribute("pageNo", pageNo);
        //总的数据条数
        int totalSize = userService.pageQueryCount(map);
        //总页码
        int totalNo = 0;
        if (totalSize % pageSize == 0) {
            totalNo = totalSize / pageSize;
        } else {
            totalNo = totalSize / pageSize + 1;
        }
        model.addAttribute("totalNo", totalNo);

        return "user/index";
    }
}
