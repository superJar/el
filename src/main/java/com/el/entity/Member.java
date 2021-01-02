package com.el.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.bytebuddy.asm.Advice;

/**
 * <p>
 * 会员表 
 * </p>
 *
 * @author superJar
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("member")
public class Member extends Model<Member> {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 卡号
     */
    @TableField("CARD_NUM")
    private String cardNum;

    /**
     * 姓名
     */
    @TableField("NAME")
    private String name;

    /**
     * 昵称
     */
    @TableField("NICKNAME")
    private String nickname;

    /**
     * 性别 0-女性，1-男性
     */
    @TableField("GENDER")
    private String gender;

    /**
     * 手机号
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 余额
     */
    @TableField("BALANCE")
    private BigDecimal balance;

    /**
     * 附加余额 该余额不算作正式余额
     */
    @TableField("ADDITIONAL_BALANCE")
    private BigDecimal additionalBalance;

    /**
     * 充值总计
     */
    @TableField("SUM_OF_TOP_UP")
    private BigDecimal sumOfTopUp;

    /**
     * 消费总计
     */
    @TableField("SUM_OF_CONSUMPTION")
    private BigDecimal sumOfConsumption;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

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

    /**
     * 本次充值的金额
     */
    @TableField(exist = false)
    private BigDecimal topUpAmount;


    /**
     * 会员对应的消费
     */
    @TableField(exist = false)
    private List<ElProduct> products;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
