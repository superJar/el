package com.el.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author superJar
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("permission")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 父ID，0-此节点为根节点
     */
    @TableField("PID")
    private Integer pid;

    /**
     * 资源类型（1：菜单，2：按钮，3：操作）
     */
    @TableField("TYPE")
    private Integer type;

    /**
     * 资源名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 资源标识（或者叫权限字符串）
     */
    @TableField("CODE")
    private String code;

    /**
     * 资源URI
     */
    @TableField("URI")
    private String uri;

    /**
     * 序号
     */
    @TableField("SEQ")
    private Integer seq;

    @TableField("CREATED_BY")
    private String createdBy;

    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @TableField("UPDATED_BY")
    private String updatedBy;

    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
