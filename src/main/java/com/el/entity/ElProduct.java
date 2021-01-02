package com.el.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 拾壹商品表 
 * </p>
 *
 * @author superJar
 * @since 2020-12-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("el_product")
public class ElProduct extends Model<ElProduct> {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    @TableField("ID")
    private Integer id;

    /**
     * 商品名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 商品类型 0-酒水小吃类，1-桌游类，2-包房类
     */
    @TableField("TYPE")
    private Integer type;

    /**
     * 单价价格
     */
    @TableField("PRICE")
    private BigDecimal price;

    /**
     * 附加价格
     */
    @TableField("ADDITIONAL_PRICE")
    private BigDecimal additionalPrice;

    /**
     * 商品图片
     */
    @TableField("PIC")
    private String pic;

    /**
     * 创建人
     */
    @TableField("CREATED_BY")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField("UPDATED_BY")
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    /**
     * 备用字段1
     */
    @TableField("SPARE1")
    private String spare1;

    /**
     * 备用字段2
     */
    @TableField("SPARE2")
    private String spare2;

    /**
     * 备用字段3
     */
    @TableField("SPARE3")
    private String spare3;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
