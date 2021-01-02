package com.el.mapper;

import com.el.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author superJar
 * @since 2020-12-31
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
