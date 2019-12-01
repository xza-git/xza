package com.fh.dao;

import java.util.List;

import com.fh.entity.AddressInfo;

public interface AddressMapper {

	//查询省市县集合
	List<AddressInfo> queryAddressList(Integer pid);
	//根据id查询省市县
	AddressInfo queryAddressById(int parseInt);
	//查询省市县中省的集合 不用pid
	List<AddressInfo> queryAddressLists();
}
