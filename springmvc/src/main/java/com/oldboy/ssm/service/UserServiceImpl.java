package com.oldboy.ssm.service;

import com.oldboy.ssm.dao.UserDao;
import com.oldboy.ssm.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	//注入dao对象给service
	@Resource(name="userDao")
	private UserDao dao ;

	public void insertUser(User user) {
		dao.insert(user);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public User findById(Integer id) {
		return dao.findById(id);
	}
}
