package com.fh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fh.entity.UserInfo;

public class LoginInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		UserInfo userInfo = (UserInfo) arg0.getSession().getAttribute("user");
		if(userInfo!=null){
			return true;
		}else{
			arg1.sendRedirect(arg0.getContextPath()+"/login.jsp");
			return false;
		}
	}
	
}
