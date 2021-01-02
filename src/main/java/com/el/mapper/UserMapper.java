package com.el.mapper;

import com.el.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  用户表 Mapper 接口
 * </p>
 *
 * @author superJar
 * @since 2020-12-31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findByUsername(@Param("username") String username);

    List<String> findRolesByUsername(@Param("username")String username);

    List<String> findAuthoritiesByRoles(@Param("list") List<String> roles);
}
