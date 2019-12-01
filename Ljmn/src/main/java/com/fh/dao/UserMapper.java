package com.fh.dao;

import java.util.List;
import java.util.Map;

import com.fh.entity.UserInfo;

public interface UserMapper {

	//通过用户名查询用户
	UserInfo queryUserByName(String userName);
	//分页查询
	List<UserInfo> findUserByPage(Map<String, Object> map);
	//查询总条数
	Integer findCount(Map<String, Object> map);
	//删除用户
	void deleteUser(Integer id);
	//去修改用户页面  加回显
	UserInfo queryUserById(Integer id);
	//修改保存用户
	void updateUser(UserInfo userInfo);
	//新增保存用户
	void saveUser(UserInfo userInfo);

}
