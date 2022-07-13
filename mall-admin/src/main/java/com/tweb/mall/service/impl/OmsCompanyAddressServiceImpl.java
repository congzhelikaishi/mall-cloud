package com.tweb.mall.service.impl;

import com.tweb.mall.service.OmsCompanyAddressService;
import com.tweb.mall.mapper.OmsCompanyAddressMapper;
import com.tweb.mall.model.OmsCompanyAddress;
import com.tweb.mall.model.OmsCompanyAddressExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址管理Service实现类
 */
@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {
    @Autowired
    private OmsCompanyAddressMapper companyAddressMapper;

    @Override
    public List<OmsCompanyAddress> list() {
        return companyAddressMapper.selectByExample(new OmsCompanyAddressExample());
    }
}
