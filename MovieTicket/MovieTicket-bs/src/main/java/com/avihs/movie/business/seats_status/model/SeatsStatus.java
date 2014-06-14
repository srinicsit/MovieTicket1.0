package com.avihs.movie.business.seats_status.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.model.SeatStatus;
import com.avihs.movie.business.seats_layout.model.SeatsLayout;

@Entity
@Table(name = "SEATS_STATUS")
public class SeatsStatus extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "SEAT_STATUS")
	private SeatStatus seatStatus;

	@JoinColumn(name = "SEATS_LAYOUT_ID")
	@ManyToOne
	private SeatsLayout seatsLayout;

	@Column(name = "DATE")
	private Date date;

	public SeatsLayout getSeatsLayout() {
		return seatsLayout;
	}

	public void setSeatsLayout(SeatsLayout seatsLayout) {
		this.seatsLayout = seatsLayout;
	}

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}

}
