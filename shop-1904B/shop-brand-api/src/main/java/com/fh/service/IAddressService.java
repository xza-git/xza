package com.fh.service;

import com.fh.bean.AddressBean;

import java.util.List;

public interface IAddressService {
    void newAddress(AddressBean addressBean);

    List<AddressBean> queryAddress();

    AddressBean queryAddressById(Integer id);

    void updateAddress(AddressBean addressBean);

    void delAddress(Integer id);
}
