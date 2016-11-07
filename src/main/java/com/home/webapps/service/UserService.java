package com.home.webapps.service;

import com.home.webapps.domain.User;

public interface UserService {
	
	public User findUserById(long personId);
	public User findByUserName(String name, String pass);
	public void save(User user) throws Exception;
	public void delete(User user) throws Exception;
	
}
