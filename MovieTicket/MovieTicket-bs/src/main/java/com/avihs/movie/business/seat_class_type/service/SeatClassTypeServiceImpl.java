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

	@Override
	@Transactional
	public void save(SeatClassType seatClassType) {
		seatClassTypeDao.save(seatClassType);
	}

	@Override
	@Transactional
	public void update(SeatClassType seatClassType) {
		seatClassTypeDao.update(seatClassType);
	}

	@Override
	@Transactional
	public void deleteSeatClasses(Integer screenId) {
		seatClassTypeDao.deleteSeatClasses(screenId);
	}

	@Override
	public void delete(SeatClassType seatClassType) {
		if (seatClassType != null) {
			seatClassTypeDao.delete(seatClassType);
		}

	}

	@Override
	public SeatClassType load(Integer seatClassTypeId) {
		return seatClassTypeDao.load(SeatClassType.class, seatClassTypeId);
	}
}
