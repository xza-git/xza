package com.fh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.entity.AddressInfo;
import com.fh.service.AddressService;

@Controller
@RequestMapping("address")
public class AddressController {
	
	
	@Autowired
	private AddressService addressService;
	
	//查询省市县中省的集合
	@RequestMapping("getAddress")
	@ResponseBody
	public List<AddressInfo> getAddress(Integer pid) {
		List<AddressInfo> list=addressService.queryAddressList(pid);
		return list;
	}
	
	//跳转地区列表页面
	@RequestMapping("toAddressList")
	public String toAddressList() {
		return "address/addressList";
	}
	
	//Ztree集合
	@RequestMapping(value="queryAddressList",produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String, Object>> queryAddressList(){
		List<AddressInfo> addressList = addressService.queryAddressLists();
		List<Map<String, Object>> mapList=new ArrayList<Map<String, Object>>();
		for (int i = 0; i < addressList.size(); i++) {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("id", addressList.get(i).getId());
			map.put("pId", addressList.get(i).getPid());
			map.put("name", addressList.get(i).getName());
			mapList.add(map);
		}
		return mapList;
	}
}
