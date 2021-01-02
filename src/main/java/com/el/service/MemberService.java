package com.el.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.el.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表  服务类
 * </p>
 *
 * @author superJar
 * @since 2021-01-01
 */
public interface MemberService extends IService<Member> {
    IPage<Member> page(Integer pageNum, Integer pageSize,String name,String nickname);

    Integer addOrEdit(Member member);

    boolean topUp(Member member);

    boolean consume(Member member);
}
