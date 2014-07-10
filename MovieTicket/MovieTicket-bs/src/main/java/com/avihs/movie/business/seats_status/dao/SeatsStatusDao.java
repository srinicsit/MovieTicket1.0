package com.avihs.movie.business.seats_status.dao;

import java.util.List;

import com.avihs.movie.business.dao.CommonDao;
import com.avihs.movie.business.model.SeatStatus;
import com.avihs.movie.business.model.SeatsStatusCount;
import com.avihs.movie.business.seats_status.model.SeatsStatus;

public interface SeatsStatusDao extends CommonDao {

	public void save(List<SeatsStatus> seatsStatusList);

	public List<SeatsStatusCount> getSeatsStatusCount(Integer movieScreenId,
			SeatStatus seatsStatus);

	public List<SeatsStatus> getSeatsStatus(Integer movieScreenId);

}
