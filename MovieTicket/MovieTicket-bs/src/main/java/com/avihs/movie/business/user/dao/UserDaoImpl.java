package com.avihs.movie.business.user.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;
import com.avihs.movie.business.user.model.User;

@Repository
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao<User> {

	@Override
	public User getUser(String userId, String pwd) {
		User user = null;
		Query query = getCurrentSession().getNamedQuery(
				"getUserForUserIdAndPwd");
		query.setString("userId", userId);
		query.setString("pwd", pwd);
		if (!query.list().isEmpty()) {
			user = (User) query.list().get(0);
		}
		return user;
	}

}
