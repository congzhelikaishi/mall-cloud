package com.tweb.mall.service.impl;

import com.tweb.mall.service.UmsResourceCategoryService;
import com.tweb.mall.mapper.UmsResourceCategoryMapper;
import com.tweb.mall.model.UmsResourceCategory;
import com.tweb.mall.model.UmsResourceCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {

    @Autowired
    private UmsResourceCategoryMapper resourceCategoryMapper;

    /**
     * 获取所有资源分类
     */
    @Override
    public List<UmsResourceCategory> listAll() {
        UmsResourceCategoryExample example = new UmsResourceCategoryExample();
        example.setOrderByClause("sort desc");
        return resourceCategoryMapper.selectByExample(example);
    }

    /**
     * 创建资源分类
     *
     * @param umsResourceCategory
     */
    @Override
    public int create(UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(new Date());
        return resourceCategoryMapper.insert(umsResourceCategory);
    }

    /**
     * 修改资源分类
     *
     * @param id
     * @param umsResourceCategory
     */
    @Override
    public int update(Long id, UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setId(id);
        return resourceCategoryMapper.updateByPrimaryKeySelective(umsResourceCategory);
    }

    /**
     * 删除资源分类
     *
     * @param id
     */
    @Override
    public int delete(Long id) {
        return resourceCategoryMapper.deleteByPrimaryKey(id);
    }
}
