package com.fh.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.entity.UserInfo;
import com.fh.service.UserService;
import com.fh.utils.PageInfo;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	//注入service
	private UserService userService;

	//登录验证
	@RequestMapping(value="login",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String login(UserInfo userInfo,HttpSession session){
		//通过用户名查询用户
		UserInfo user=userService.queryUserByName(userInfo.getUserName());
		//进行用户验证
		if(user!=null){
			if(user.getUserPwd().equals(userInfo.getUserPwd())){
				//判断当前登录时间是否为当天
				Date date = new Date();
				SimpleDateFormat sim2 = new SimpleDateFormat("yyyy-MM-dd");
				if(sim2.format(date).equals(sim2.format(user.getLoginTime()))){
					System.out.println(sim2.format(date));
					System.out.println(sim2.format(user.getLoginTime()));
					user.setCount(user.getCount()+1);
				}else{
					user.setCount(1);
				}
				user.setLoginTime(new Date());
				userService.updateUser(user);
				session.setAttribute("user", user);
				return "{\"success\":\"1\"}";
			}else{
				return "{\"success\":\"2\"}";
			}
		}else{
			return "{\"success\":\"2\"}";
		}
	}
	
	//去框架集页面
	@RequestMapping("toMain")
	public String toMain(){
		return "main";
	}
	//去首页
	@RequestMapping("toIndex")
	public String toIndex(){
		return "index";
	}
	
	
	
	//去用户列表
	@RequestMapping("toUserList")
	public String toUserList(){
		return "user/userList";
	}
	
	//查询用户列表 分页查询
	@RequestMapping("findUserByPage")
	@ResponseBody
	public PageInfo<UserInfo> findUserByPage(Integer pageSize,Integer cpage,String userName,String beginTime, String endTime){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		System.out.println(beginTime+endTime);
		map.put("userName", userName);
		map.put("pageSize", pageSize);
		map.put("cpage", cpage);
		PageInfo<UserInfo> pageInfo= userService.findUserByPage(map);
		return pageInfo;
	}
	
	//删除用户
	@RequestMapping(value="deleteUser",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteUser(Integer id){
		userService.deleteUser(id);
		return "{\"success\":\"删除成功\"}";
	}
	
	//去修改用户页面  加回显
	@RequestMapping("updateJsp")
	public ModelAndView updateJsp(Integer id,ModelAndView mav){
		UserInfo userInfo=userService.queryUserById(id);
		mav.setViewName("updateUser");
		mav.addObject("userInfo", userInfo);
		return mav;
	}
	
	//修改保存用户
	@RequestMapping("updateUser")
	public String updateUser(UserInfo userInfo){
		userService.updateUser(userInfo);
		return "redirect:toUserList";
	}
	
	//去新增用户页面
	@RequestMapping("toSaveUserJsp")
	public String toSaveUserJsp(){
		return "saveUser";
	}
	
	//新增用户
	@RequestMapping(value="saveUser",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String saveUser(UserInfo userInfo){
		//通过用户名查询用户 验证用户是否存在
		UserInfo user=userService.queryUserByName(userInfo.getUserName());
		if(user==null){
			//新增保存用户
			userInfo.setCount(0);
			userInfo.setLoginTime(new Date());
			userService.saveUser(userInfo);
			return "{\"success\":\"1\"}";
		}else{
			return "{\"success\":\"2\"}";
		}
	}
}