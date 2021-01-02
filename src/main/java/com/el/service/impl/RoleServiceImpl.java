package com.el.service.impl;

import com.el.entity.Role;
import com.el.mapper.RoleMapper;
import com.el.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author superJar
 * @since 2020-12-31
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Integer deleteById(Integer id) {
        return roleMapper.deleteById(id);
    }

    @Transactional
    @Override
    public Integer addOrUpdate(Role role) {
        return role.getId() != null ? roleMapper.updateById(role) : roleMapper.insert(role);
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.selectList(null);
    }
}
