package com.el.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.el.entity.User;

/**
 * @author:superJar
 * @date:2021/1/1
 * @time:16:23
 * @details:
 */
public interface UserService extends IService<User> {
    Integer addOrUpdate(User user);

    Integer disable(Integer id);

    IPage<User> page(Integer pageNum, Integer pageSize);
}
