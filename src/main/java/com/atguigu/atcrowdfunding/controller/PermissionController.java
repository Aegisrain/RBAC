package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.entity.AJAXResult;
import com.atguigu.atcrowdfunding.entity.Permission;
import com.atguigu.atcrowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: yzy
 * @Date: 2019/2/28 15:14
 * @Description:
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Permission permission) {
        AJAXResult ajaxResult = new AJAXResult();
        try {
            permissionService.deletePermission(permission);
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(Permission permission) {
        AJAXResult ajaxResult = new AJAXResult();
        try {
            permissionService.updatePermission(permission);
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Object insert(Permission permission) {
        AJAXResult ajaxResult = new AJAXResult();
        try {
            permissionService.insertPermission(permission);
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    @RequestMapping("/edit")
    public String edit(Integer id, Model model) {
        Permission permission = permissionService.queryBtId(id);
        model.addAttribute("permission", permission);
        return "permission/edit";
    }

    @RequestMapping("/add")
    public String add() {
        return "permission/add";
    }

    @RequestMapping("/index")
    public String index() {
        return "permission/index";
    }

    @RequestMapping("loadAssignData")
    @ResponseBody
    public Object loadAssignData(Integer roleid) {
        List<Permission> permissions = new ArrayList<Permission>();
        List<Permission> ps = permissionService.queryAll();
        System.out.println(roleid);
        //获取当前角色已经分配的许可信息
        List<Integer> permissionsids = permissionService.queryPermissionidsByRoleid(roleid);
        System.out.println(permissionsids);
        Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
        for (Permission p : ps) {
            if (permissionsids.contains(p.getId())) {
                p.setChecked(true);
            } else {
                p.setChecked(false);
            }
            permissionMap.put(p.getId(), p);
        }
        for (Permission child : ps) {
            if (child.getPid() == 0) {
                permissions.add(child);
            } else {
                Permission parent = permissionMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return permissions;
    }

    @RequestMapping("loadData")
    @ResponseBody
    public Object loadData() {
        List<Permission> permissions = new ArrayList<Permission>();
        List<Permission> ps = permissionService.queryAll();

        /*
        //改进1：使用循环获取许可信息
        //由于没有使用索引，该方法效率依然较低
        for (Permission child : ps) {
            if (child.getPid() == 0) {
                permissions.add(child);
            } else {
                for (Permission innerPermission : ps) {
                    if (innerPermission.getId().equals(child.getPid())) {
                        Permission parent = innerPermission;
                        parent.getChildren().add(child);
                        break;
                    }
                }
            }
        }
        */

        //改进2：使用Map中的索引提高效率
        Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
        for (Permission p : ps) {
            permissionMap.put(p.getId(), p);
        }
        for (Permission child : ps) {
            if (child.getPid() == 0) {
                permissions.add(child);
            } else {
                Permission parent = permissionMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return permissions;
    }

    /**
     * 递归查询许可信息
     * 1)递归算法的效率比较低
     *
     * @param parent
     */
    private void queryChildPermissions(Permission parent) {
        List<Permission> childrenPermission = permissionService.queryChildrenPermission(parent.getId());

        for (Permission permission : childrenPermission) {
            queryChildPermissions(permission);
        }

        parent.setChildren(childrenPermission);
    }
}
