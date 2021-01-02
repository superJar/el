package com.el.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.el.common.Result;
import com.el.entity.Member;
import com.el.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表  前端控制器
 * </p>
 *
 * @author superJar
 * @since 2021-01-01
 */
@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    /**
     * @description:新增/更新
     * @author: SuperJar
     * @date: 2021/1/1 17:31
     * @return: com.el.common.Result
     */
    @PostMapping("/addOrUpdate")
    public Result addOrUpdate(@RequestBody Member member) {
        try {
            Integer count = memberService.addOrEdit(member);

            if (count == 0) {
                return Result.fail("操作失败！");
            }

            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }

    //分页查询
    @PostMapping("/list")
    public Result<IPage<Member>> page(@RequestBody JSONObject jsonObject) {
        try {
            int pageNum = jsonObject.getInt("pageNum");
            int pageSize = jsonObject.getInt("pageSize");
            String name = jsonObject.getString("name");
            String nickname = jsonObject.getString("nickname");
            IPage<Member> page = memberService.page(pageNum, pageSize,name,nickname);

            return Result.ok(page);
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }

    /**
     * @description:充值
     * @author: SuperJar
     * @date: 2021/1/1 17:39
     * @return: com.el.common.Result
     */
    @PostMapping("/topUp")
    public Result topUp(@RequestBody Member member) {

        try {
            if (member == null || member.getId() == null) {
                return Result.fail();
            }
            boolean flag = memberService.topUp(member);
            if(!flag){
                return Result.fail();
            }
            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }


    /**
     * @description:消费
     * @author: SuperJar
     * @date: 2021/1/1 17:40
     * @return: com.el.common.Result
     */
    @PostMapping("/consume")
    public Result consume(@RequestBody Member member) {
        try {
            if (member == null) {
                return Result.fail();
            }
            boolean flag = memberService.consume(member);

            if(!flag){
                return Result.fail();
            }
            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }

}

