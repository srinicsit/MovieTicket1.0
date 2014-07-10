package com.avihs.movie.business.seats_status.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.dao.CommonDaoImpl;
import com.avihs.movie.business.model.SeatStatus;
import com.avihs.movie.business.model.SeatsStatusCount;
import com.avihs.movie.business.seats_status.model.SeatsStatus;

@Repository
public class SeatsStatusDaoImpl extends CommonDaoImpl implements SeatsStatusDao {

	@Override
	public void save(List<SeatsStatus> seatsStatusList) {
		Session session = getCurrentSession();
		for (SeatsStatus seatsStatus : seatsStatusList) {
			session.save(seatsStatus);
		}
	}

	@Override
	public List<SeatsStatusCount> getSeatsStatusCount(Integer movieScreenId,
			SeatStatus seatsStatus) {
		Query query = getCurrentSession().getNamedQuery("getSeatsStatusCount");
		query.setInteger("movieScreenId", movieScreenId);
		query.setInteger("seatStatus", seatsStatus.ordinal());
		query.setResultTransformer(Transformers
				.aliasToBean(SeatsStatusCount.class));
		List<SeatsStatusCount> seatsStatusCounts = query.list();

		return seatsStatusCounts;
	}

	@Override
	public List<SeatsStatus> getSeatsStatus(Integer movieScreenId) {
		Query query = getCurrentSession().getNamedQuery("getMovieScreenSeats");
		query.setInteger("movieScreenId", movieScreenId);
		List<SeatsStatus> seatsList = query.list();
		return seatsList;
	}
}
