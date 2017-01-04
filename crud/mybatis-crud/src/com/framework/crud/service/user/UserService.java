package com.framework.crud.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.framework.crud.bean.user.User;
import com.framework.crud.dao.user.UserDao;

@Service
public class UserService extends BaseService<User, UserDao>{
	
	public List<User> getUser() throws Exception {
		return baseSelectAll(new User());
		
	}

}
