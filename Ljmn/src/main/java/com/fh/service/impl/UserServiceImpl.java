package com.fh.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.dao.UserMapper;
import com.fh.entity.UserInfo;
import com.fh.service.UserService;
import com.fh.utils.PageInfo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userDao;

	//通过用户名查询用户
	public UserInfo queryUserByName(String userName) {
		return userDao.queryUserByName(userName);
	}

	//查询用户列表 分页查询
	public PageInfo<UserInfo> findUserByPage(Map<String, Object> map) {
		
		//求出总条数
		Integer findCount = userDao.findCount(map);
		Integer cpage=(Integer) map.get("cpage");
		Integer pageSize=(Integer) map.get("pageSize");
		PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(cpage,pageSize);
		pageInfo.setTotalCount(findCount);
		//分页
		map.put("start", pageInfo.getStart());
		List<UserInfo> findByPage = userDao.findUserByPage(map);
		pageInfo.setDataList(findByPage);
		return  pageInfo;
		
	}

	//删除用户
	public void deleteUser(Integer id) {
		userDao.deleteUser(id);
	}

	//去修改用户页面  加回显
	public UserInfo queryUserById(Integer id) {
		return userDao.queryUserById(id);
	}

	//修改保存用户
	public void updateUser(UserInfo userInfo) {
		userDao.updateUser(userInfo);
	}

	//新增保存用户
	public void saveUser(UserInfo userInfo) {
		userDao.saveUser(userInfo);
	}
}
