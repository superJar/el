package com.el.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.el.entity.User;
import com.el.mapper.UserMapper;
import com.el.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author:superJar
 * @date:2021/1/1
 * @time:16:24
 * @details:
 */
@Service
public class ElUserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Transactional
    @Override
    public Integer addOrUpdate(User user) {

        return user.getId() != null? userMapper.updateById(user) : userMapper.insert(user);
    }

    @Override
    public Integer disable(Integer id) {
        User user = userMapper.selectById(id);
        user.setEnabled(0);
        return userMapper.updateById(user);
    }

    @Override
    public IPage<User> page(Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<>(pageNum,pageSize);
        return userMapper.selectPage(page, null);
    }

}
