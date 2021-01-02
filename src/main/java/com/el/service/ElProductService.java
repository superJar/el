package com.el.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.el.entity.ElProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 拾壹商品表  服务类
 * </p>
 *
 * @author superJar
 * @since 2020-12-31
 */
public interface ElProductService extends IService<ElProduct> {

    Integer addOrEdit(ElProduct product);

    IPage<ElProduct> page(Integer pageNum,Integer pageSize,String queryString);
}
