package com.el.service;

import com.el.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author superJar
 * @since 2020-12-31
 */
public interface RoleService extends IService<Role> {

    Integer deleteById(Integer id);

    Integer addOrUpdate(Role role);

    List<Role> findAll();
}
