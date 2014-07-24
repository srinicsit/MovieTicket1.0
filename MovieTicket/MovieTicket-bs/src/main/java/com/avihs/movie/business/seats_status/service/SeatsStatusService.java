package com.avihs.movie.business.seats_status.service;

import java.util.List;

import com.avihs.movie.business.model.SeatStatus;
import com.avihs.movie.business.model.SeatsStatusCount;
import com.avihs.movie.business.seats_status.model.SeatsStatus;

public interface SeatsStatusService {
	public void save(List<SeatsStatus> seatsStatusList);

	public void save(SeatsStatus seatsStatus);
	
	public void update(SeatsStatus seatsStatus);

	public List<SeatsStatusCount> getSeatsStatusCount(Integer movieScreenId,
			SeatStatus seatsStatus);

	public List<SeatsStatus> getSeatsStatus(Integer movieScreenId);
}
