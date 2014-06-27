package com.avihs.movie.business.user.dao;

import com.avihs.movie.business.dao.CommonDao;
import com.avihs.movie.business.user.model.User;

public interface UserDao extends CommonDao {

	User getUser(String userId, String pwd);
}
