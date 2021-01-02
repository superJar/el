package com.el.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.el.entity.ElProduct;
import com.el.mapper.ElProductMapper;
import com.el.service.ElProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.nio.file.Watchable;

/**
 * <p>
 * 拾壹商品表  服务实现类
 * </p>
 *
 * @author superJar
 * @since 2020-12-31
 */
@Service
public class ElProductServiceImpl extends ServiceImpl<ElProductMapper, ElProduct> implements ElProductService {

    @Resource
    private ElProductMapper productMapper;


    @Transactional
    @Override
    public Integer addOrEdit(ElProduct product) {
        return product.getId() != null ? productMapper.updateById(product) : productMapper.insert(product);
    }

    @Override
    public IPage<ElProduct> page(Integer pageNum, Integer pageSize,String queryString) {
        QueryWrapper<ElProduct> wrapper = new QueryWrapper<>();
        wrapper.like("NAME",queryString);
        IPage<ElProduct> page = new Page<>(pageNum,pageSize);
        return productMapper.selectPage(page,wrapper);
    }
}
