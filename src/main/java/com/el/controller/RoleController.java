package com.el.controller;


import com.el.common.Result;
import com.el.entity.Role;
import com.el.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author superJar
 * @since 2020-12-31
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * @description:新增/更新
     * @author: SuperJar
     * @date: 2021/1/1 17:09
     * @return: com.el.common.Result
     */
    @PostMapping("addOrUpdate")
    public Result addOrUpdate(@RequestBody Role role) {

        try {

            Integer count = roleService.addOrUpdate(role);

            if (count == 0) {
                return Result.fail("操作失败！");
            }
            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }

    }

    @DeleteMapping("delete/{id}")
    public Result addOrUpdate(@PathVariable Integer id) {
        try {
            Integer count = roleService.deleteById(id);

            if (count == 0) {
                return Result.fail("操作失败！");
            }
            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }

    @GetMapping("/findAll")
    public Result<List<Role>> findAll() {
        try {
            List<Role> roles = roleService.findAll();
            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }
}

