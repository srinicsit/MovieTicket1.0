package com.avihs.movie.business.user.service;

import com.avihs.movie.business.user.model.User;

public interface UserService {
	User getUser(String userId,String pwd);
}
