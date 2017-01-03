package com.framework.crud.service;

import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.framework.crud.bean.User;
import com.framework.crud.dao.UserDao;

@Service
public class UserService extends BaseService<User, UserDao>{
	
	public void add() {
		// TODO Auto-generated method stub
		System.out.println("service add ");
	}

}
