package com.avihs.movie.business.seat_class_type.service;

import com.avihs.movie.business.seat_class_type.model.SeatClassType;

public interface SeatClassTypeService {

	public void deleteSeatClasses(Integer screenId);

	public void save(SeatClassType seatClassType);

	public void update(SeatClassType seatClassType);

	public void delete(SeatClassType seatClassType);

	public SeatClassType load(Integer seatClassTypeId);
}
