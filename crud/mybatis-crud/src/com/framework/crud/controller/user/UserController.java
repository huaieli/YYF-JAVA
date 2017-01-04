package com.framework.crud.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.crud.bean.user.User;
import com.framework.crud.service.user.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController<User, UserService> {
	@Autowired
	private UserService userService;

	/**
	 * 查（获取所有）
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String show(Model model){
		try{
			List<User> users=userService.getUser();
			model.addAttribute("users",users);
			}
		 catch (Exception e) {
			}
		return "show";
	}

	@RequestMapping(value = "/add")
	public @ResponseBody
	String add(User user) {
		return "";
	}
}
