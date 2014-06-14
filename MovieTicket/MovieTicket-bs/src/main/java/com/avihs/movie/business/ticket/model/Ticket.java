package com.avihs.movie.business.ticket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.movie_screen.model.MovieScreen;

@Entity
@Table(name = "TICKET")
public class Ticket extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "MOVIE_SCREEN_ID")
	@ManyToOne
	private MovieScreen movieScreen;

	@Column(name="PRICE")
	private Float price;
	
	@Column(name = "COMMISSION")
	private Float commission;

	public MovieScreen getMovieScreen() {
		return movieScreen;
	}

	public void setMovieScreen(MovieScreen movieScreen) {
		this.movieScreen = movieScreen;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getCommission() {
		return commission;
	}

	public void setCommission(Float commission) {
		this.commission = commission;
	}

}
