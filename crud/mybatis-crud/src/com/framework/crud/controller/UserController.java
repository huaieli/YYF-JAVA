package com.framework.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.crud.bean.User;
import com.framework.crud.service.UserService;
@Controller
@RequestMapping(value ="/user")
public class UserController extends BaseController<User, UserService>{
	@Autowired 
	private UserService userService;
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String test(MockHttpServletRequest request, MockHttpServletResponse response){
		System.out.println(request.getParameter("username"));
		return "";
	}
	
	@RequestMapping (value = "/add")
	public @ResponseBody String  add( User user){
		userService.add();
		System.out.println(user.getName());
		return "";
	}
}
