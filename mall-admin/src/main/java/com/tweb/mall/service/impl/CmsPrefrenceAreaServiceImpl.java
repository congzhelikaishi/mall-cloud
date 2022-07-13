package com.tweb.mall.service.impl;

import com.tweb.mall.service.CmsPrefrenceAreaService;
import com.tweb.mall.mapper.CmsPrefrenceAreaMapper;
import com.tweb.mall.model.CmsPrefrenceArea;
import com.tweb.mall.model.CmsPrefrenceAreaExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品优选Service实现类
 */
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {
    @Autowired
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
    }
}
