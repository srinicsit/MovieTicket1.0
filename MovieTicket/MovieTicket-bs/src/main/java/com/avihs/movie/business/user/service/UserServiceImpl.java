package com.avihs.movie.business.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avihs.movie.business.user.dao.UserDao;
import com.avihs.movie.business.user.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User getUser(String userId, String pwd) {
		return userDao.getUser(userId, pwd);
	}

}
