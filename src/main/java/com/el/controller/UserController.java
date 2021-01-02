package com.el.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.el.common.Result;
import com.el.entity.User;
import com.el.service.UserService;
import com.el.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  用户表 前端控制器
 * </p>
 *
 * @author superJar
 * @since 2020-12-31
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * @description:新增/更新
     * @author: SuperJar
     * @date: 2021/1/1 16:32
     * @return:
     */
    @PostMapping("/addOrUpdate")
    public Result addOrUpdate(@RequestBody User user){
        try {

            Integer count = userService.addOrUpdate(user);
            if(count == null || count == 0){
                return Result.fail("没新增成功！");
            }
            return Result.ok();
        } catch (Exception e) {
            log.error("新增失败！{}",e.getMessage(),e);
            return Result.fail("新增失败！");
        }

    }

    /**
     * @description:失效用户
     * @author: SuperJar
     * @date: 2021/1/1 16:36
     * @return: com.el.common.Result
     */
    @GetMapping("/disable/{id}")
    public Result disable(@PathVariable Integer id){
        try {

            Integer count = userService.disable(id);
            if(count == null || count == 0){
                return Result.fail("操作失败！");
            }
            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}",e.getMessage(),e);
            return Result.fail("操作失败！");
        }

    }

    /**
     * @description:列表分页
     * @author: SuperJar
     * @date: 2021/1/1 17:01
     * @return: com.el.common.Result<com.baomidou.mybatisplus.core.metadata.IPage<com.el.entity.User>>
     */
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result<IPage<User>> list(@PathVariable("pageNum")Integer pageNum,
                                    @PathVariable("pageSize")Integer pageSize){

        try {
            IPage<User> page = userService.page(pageNum,pageSize);
            return Result.ok(page);
        } catch (Exception e) {
            log.error("查询失败！{}",e.getMessage(),e);
            return Result.fail("查询失败！");
        }


    }

}

