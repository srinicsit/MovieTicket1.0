package com.avihs.movie.business.theatermgmt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.theatermgmt.model.Theater;

@Repository
public class TheaterMgmtDaoImpl  implements
		TheaterMgmtDao {

    @Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//           this.sessionFactory = sessionFactory;
//    }
//   


	public TheaterMgmtDaoImpl() {
		System.out.println("TheaterMgmtDaoImpl");
	}

	public void create(Theater theater) {

	}

	public void update(Theater theater) {
		// TODO Auto-generated method stub

	}

	public Theater getTheater(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Theater> getTheaters(String location) {
		// TODO Auto-generated method stub
		return null;
	}

}
