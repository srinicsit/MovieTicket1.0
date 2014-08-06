package com.avihs.movie.business.seats_status.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.model.SeatStatus;
import com.avihs.movie.business.movie_screen.model.MovieScreen;
import com.avihs.movie.business.seats.model.Seats;

@Entity
@Table(name = "SEATS_STATUS")
@DynamicUpdate
@DynamicInsert
@NamedQueries({
		@NamedQuery(name = "getSeatsStatusCount", query = "select count(seatsStatus.seat.row.seatClassType) as count,seatsStatus.seat.row.seatClassType.seatType.name as classType,"
				+ "seatsStatus.seat.row.seatClassType.seatType.id as classTypeId from "
				+ "SeatsStatus seatsStatus inner join seatsStatus.seat inner join seatsStatus.seat.row inner join seatsStatus.seat.row.seatClassType  "
				+ "where seatsStatus.seat.status='show' and seatsStatus.movieScreen=:movieScreenId and seatsStatus.seatStatus =:seatStatus group by seatsStatus.seat.row.seatClassType"),
		@NamedQuery(name = "getMovieScreenSeats", query = "from SeatsStatus ss where movieScreen=:movieScreenId") })
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

	@ManyToOne
	@JoinColumn(name = "MOVIE_SCREEN_ID")
	@JsonIgnore
	private MovieScreen movieScreen;

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

	public MovieScreen getMovieScreen() {
		return movieScreen;
	}

	public void setMovieScreen(MovieScreen movieScreen) {
		this.movieScreen = movieScreen;
	}

}
