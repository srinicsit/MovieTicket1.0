package com.avihs.movie.business.seat_class_type.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avihs.movie.business.seat_class_type.dao.SeatClassTypeDao;
import com.avihs.movie.business.seat_class_type.model.SeatClassType;

@Service
public class SeatClassTypeServiceImpl implements SeatClassTypeService {

	@Autowired
	private SeatClassTypeDao seatClassTypeDao;

	@Transactional
	public void save(SeatClassType seatClassType) {
		seatClassTypeDao.save(seatClassType);
	}

	@Transactional
	public void update(SeatClassType seatClassType) {
		seatClassTypeDao.update(seatClassType);
	}
}
