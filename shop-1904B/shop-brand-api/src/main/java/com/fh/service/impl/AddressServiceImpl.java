package com.fh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.bean.AddressBean;
import com.fh.dao.IAddressMapper;
import com.fh.service.IAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Resource
    private IAddressMapper addressMapper;

    @Override
    public void newAddress(AddressBean addressBean) {
        addressMapper.insert(addressBean);
    }

    @Override
    public List<AddressBean> queryAddress() {

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.orderByDesc("addTime");
        List list = addressMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public AddressBean queryAddressById(Integer id) {
        AddressBean addressBean = addressMapper.selectById(id);
        return addressBean;
    }

    @Override
    public void updateAddress(AddressBean addressBean) {
        addressMapper.updateById(addressBean);
    }

    @Override
    public void delAddress(Integer id) {
        addressMapper.deleteById(id);
    }


}
