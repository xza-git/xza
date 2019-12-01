package com.fh.service;

import java.util.List;
import com.fh.entity.AddressInfo;

public interface AddressService {
	//查询省市县中省的集合
	List<AddressInfo> queryAddressList(Integer pid);
	//根据id查询省市县
	AddressInfo queryAddressById(int parseInt);
	//查询省市县中省的集合 不用pid
	List<AddressInfo> queryAddressLists();
}
