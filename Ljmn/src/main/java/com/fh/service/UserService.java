package com.fh.service;

import java.util.Map;

import com.fh.entity.UserInfo;
import com.fh.utils.PageInfo;

public interface UserService {

	//通过用户名查询用户
	UserInfo queryUserByName(String userName);
	//查询用户列表 分页查询
	PageInfo<UserInfo> findUserByPage(Map<String, Object> map);
	//删除用户
	void deleteUser(Integer id);
	//去修改用户页面  加回显
	UserInfo queryUserById(Integer id);
	//修改保存用户
	void updateUser(UserInfo userInfo);
	//新增保存用户
	void saveUser(UserInfo userInfo);

}
