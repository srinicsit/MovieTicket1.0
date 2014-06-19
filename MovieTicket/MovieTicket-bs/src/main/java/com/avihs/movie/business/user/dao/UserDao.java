package com.avihs.movie.business.user.dao;

import com.avihs.movie.business.dao.CommonDao;
import com.avihs.movie.business.user.model.User;

public interface UserDao<User> extends CommonDao<User>{

	User getUser(String userId,String pwd);
}
