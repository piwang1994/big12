package com.oldboy.ssm.service;

import com.oldboy.ssm.domain.User;

/**
 * userService
 */
public interface UserService {
	public void insertUser(User user);

	public void delete(Integer id) ;

	public User findById(Integer id) ;
}
