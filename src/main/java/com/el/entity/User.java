package com.el.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 *  用户表
 * </p>
 *
 * @author superJar
 * @since 2020-12-31
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User extends Model<User> implements UserDetails {

    private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 创建时间
     */
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    /**
     * 0无效用户，1有效用户
     */
    @TableField("ENABLED")
    private Integer enabled;

    private Collection<? extends GrantedAuthority>  authorities;

    /**
     * 备用字段
     */
    @TableField("SPARE")
    private String spare;

    @TableField(exist = false)
    private boolean accountNonExpired;
    @TableField(exist = false)
    private boolean accountNonLock;
    @TableField(exist = false)
    private boolean credentialsNonExpired;






    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled == 1? true : false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setSpare(String spare) {
        this.spare = spare;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLock(boolean accountNonLock) {
        this.accountNonLock = accountNonLock;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public User() {
    }

    public User(Integer id, String username, String password, LocalDateTime createdTime, Integer enabled, Collection<? extends GrantedAuthority> authorities, String spare, boolean accountNonExpired, boolean accountNonLock, boolean credentialsNonExpired) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdTime = createdTime;
        this.enabled = enabled;
        this.authorities = authorities;
        this.spare = spare;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLock = accountNonLock;
        this.credentialsNonExpired = credentialsNonExpired;
    }
}
