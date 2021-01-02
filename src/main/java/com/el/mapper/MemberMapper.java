package com.el.mapper;

import com.el.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表  Mapper 接口
 * </p>
 *
 * @author superJar
 * @since 2021-01-01
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

}
