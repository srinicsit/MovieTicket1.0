package com.avihs.movie.business.seats_status.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.model.SeatStatus;
import com.avihs.movie.business.seats.model.Seats;

@Entity
@Table(name = "SEATS_STATUS")
@DynamicUpdate
@DynamicInsert
public class SeatsStatus extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "SEAT_STATUS")
	private SeatStatus seatStatus;

	@JoinColumn(name = "SEAT_ID")
	@ManyToOne
	private Seats seat;

	@Column(name = "DATE")
	private Date date;

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}

	public Seats getSeat() {
		return seat;
	}

	public void setSeat(Seats seat) {
		this.seat = seat;
	}

}
