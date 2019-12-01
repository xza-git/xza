package com.fh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.dao.AddressMapper;
import com.fh.entity.AddressInfo;
import com.fh.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressMapper addressDao;

	//查询省市县中省的集合
	public List<AddressInfo> queryAddressList(Integer pid) {
		return addressDao.queryAddressList(pid);
	}
	//根据id查询省市县
	public AddressInfo queryAddressById(int parseInt) {
		return addressDao.queryAddressById(parseInt);
	}
	//查询省市县中省的集合 不用pid
	public List<AddressInfo> queryAddressLists() {
		return addressDao.queryAddressLists();
	}
	
}
