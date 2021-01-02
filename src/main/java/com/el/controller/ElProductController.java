package com.el.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.el.common.Result;
import com.el.entity.ElProduct;
import com.el.service.ElProductService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 * 拾壹商品表  前端控制器
 * </p>
 *
 * @author superJar
 * @since 2020-12-31
 */
@Slf4j
@Controller
@RequestMapping("/elProduct")
public class ElProductController {

    @Resource
    private ElProductService productService;

    /**
     * @description:新增/更新
     * @author: SuperJar
     * @date: 2021/1/1 17:45
     * @return: com.el.common.Result
     */
    @PostMapping("/addOrUpdate")
    public Result addOrUpdate(@RequestBody ElProduct product){
        try {
            productService.addOrEdit(product);
            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }

    /**
     * @description:分页+模糊查询
     * @author: SuperJar
     * @date: 2021/1/2 14:27
     * @return: com.el.common.Result<com.baomidou.mybatisplus.core.metadata.IPage<com.el.entity.ElProduct>>
     */
    @PostMapping("/list")
    public Result<IPage<ElProduct>> list(@RequestBody JSONObject jsonObject){
        try {

            int pageNum = jsonObject.getInt("pageNum");
            int pageSize = jsonObject.getInt("pageSize");
            String queryString = jsonObject.getString("queryString");

            IPage<ElProduct> page = productService.page(pageNum,pageSize,queryString);
            return Result.ok(page);
        } catch (JSONException e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }

}

