package com.el.service.impl;

import com.el.entity.User;
import com.el.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  用户表 服务实现类
 * </p>
 *
 * @author superJar
 * @since 2020-12-31
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper mapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        //加载基础用户信息
        User userDetails = mapper.findByUsername(username);

        //加载用户角色列表
        List<String> roles = mapper.findRolesByUsername(username);

        //加载对应权限 by 角色&用户名
        List<String> authorities = mapper.findAuthoritiesByRoles(roles);

        //roles前面要加ROLE_
        StringBuilder sb = new StringBuilder();
        List<String> rolesCode = roles.stream()
                .map(role ->
                        sb.append("ROLE_").append(role).toString()
                )
                .collect(Collectors.toList());
        //加上所有角色代码
        authorities.addAll(rolesCode);

        userDetails.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",",authorities)
                )
        );
        return userDetails;
    }

}
