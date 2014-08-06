package com.avihs.movie.web.movie_screen_price.form;

public class MovieScreenPriceForm {

	private Integer movieScreenId;

	private Integer seatClsName;

	private Float price;

	private Integer movieScreenPriceId;

	public Integer getMovieScreenId() {
		return movieScreenId;
	}

	public void setMovieScreenId(Integer movieScreenId) {
		this.movieScreenId = movieScreenId;
	}

	public Integer getSeatClsName() {
		return seatClsName;
	}

	public void setSeatClsName(Integer seatClsName) {
		this.seatClsName = seatClsName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getMovieScreenPriceId() {
		return movieScreenPriceId;
	}

	public void setMovieScreenPriceId(Integer movieScreenPriceId) {
		this.movieScreenPriceId = movieScreenPriceId;
	}
}
