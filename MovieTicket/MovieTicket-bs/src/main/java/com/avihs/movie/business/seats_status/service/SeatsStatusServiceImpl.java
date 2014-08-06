package com.avihs.movie.business.seats_status.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avihs.movie.business.model.SeatStatus;
import com.avihs.movie.business.model.SeatsStatusCount;
import com.avihs.movie.business.seats_status.dao.SeatsStatusDao;
import com.avihs.movie.business.seats_status.model.SeatsStatus;

@Service
public class SeatsStatusServiceImpl implements SeatsStatusService {

	@Autowired
	private SeatsStatusDao seatsStatusDao;

	@Override
	public void save(List<SeatsStatus> seatsStatusList) {
		seatsStatusDao.save(seatsStatusList);
	}

	@Override
	public List<SeatsStatusCount> getSeatsStatusCount(Integer movieScreenId,
			SeatStatus seatsStatus) {
		return seatsStatusDao.getSeatsStatusCount(movieScreenId, seatsStatus);
	}

	@Override
	public List<SeatsStatus> getSeatsStatus(Integer movieScreenId) {
		return seatsStatusDao.getSeatsStatus(movieScreenId);
	}

	@Override
	@Transactional
	public void save(SeatsStatus seatsStatus) {
		seatsStatusDao.save(seatsStatus);
	}

	@Override
	public void update(SeatsStatus seatsStatus) {
		seatsStatusDao.update(seatsStatus);
	}

	@Override
	public SeatsStatus loadSeatsStatus(Integer seatStautsId) {
		return seatsStatusDao.load(SeatsStatus.class, seatStautsId);
	}

}
