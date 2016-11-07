package com.home.webapps.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.home.webapps.dao.RoleDao;
import com.home.webapps.dao.UserDao;
import com.home.webapps.domain.Role;
import com.home.webapps.domain.User;

@Service("loginService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	/*
    @Autowired
    private AccessDAO accessDao;

	@Transactional
	public boolean hasPermission(String username, String password, String accessCode) {
		User user = userDao.findByUserName(username, password);
        Access access = accessDao.findByCode(accessCode);
	}
    */
	
	@PostConstruct
	public void init() throws Exception {
	}
	
	@PreDestroy
	public void destroy() {
	}

	public User findUserById(long userId) {
		
		return userDao.findById(userId);
	}
	
	public User findByUserName(String name, String pass) {
		
		return userDao.findByUserName(name, pass);
		
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void save(User user) throws Exception 
	{
		userDao.save(user);
	}
		
	//@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(User user) throws Exception {
		
		user = userDao.findById(user.getId());
		
		if(user != null) {
			userDao.delete(user);
		}
	}
	

}