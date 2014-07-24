package com.avihs.movie.business.model;

public class TicketSummary {

	private String bookingId;

	private String movieName;

	private String theaterName;

	private String screenName;

	private String address;

	private String seatClass;

	private String seatNumbers;

	private String city;

	private String showDate;

	private String showStartTime;

	private Long confirmationId;

	private String tpinOrKioskCode;

	private String bookingTime;

	private String paymentMode;

	private String panNumber;

	private String helpMailId;

	private String helpContactNumber;

	private OrderSummary orderSummary = new OrderSummary();

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public String getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(String seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	public String getShowStartTime() {
		return showStartTime;
	}

	public void setShowStartTime(String showStartTime) {
		this.showStartTime = showStartTime;
	}

	public String getTpinOrKioskCode() {
		return tpinOrKioskCode;
	}

	public void setTpinOrKioskCode(String tpinOrKioskCode) {
		this.tpinOrKioskCode = tpinOrKioskCode;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public OrderSummary getOrderSummary() {
		return orderSummary;
	}

	public String getHelpMailId() {
		return helpMailId;
	}

	public void setHelpMailId(String helpMailId) {
		this.helpMailId = helpMailId;
	}

	public String getHelpContactNumber() {
		return helpContactNumber;
	}

	public void setHelpContactNumber(String helpContactNumber) {
		this.helpContactNumber = helpContactNumber;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public class OrderSummary {

		private float ticketCost;

		private float discount;

		private float additionalCharge;

		private float otherCharge;

		private float internetHandlingFee;

		public float getTicketCost() {
			return ticketCost;
		}

		public void setTicketCost(float ticketCost) {
			this.ticketCost = ticketCost;
		}

		public float getDiscount() {
			return discount;
		}

		public void setDiscount(float discount) {
			this.discount = discount;
		}

		public float getAdditionalCharge() {
			return additionalCharge;
		}

		public void setAdditionalCharge(float additionalCharge) {
			this.additionalCharge = additionalCharge;
		}

		public float getOtherCharge() {
			return otherCharge;
		}

		public void setOtherCharge(float otherCharge) {
			this.otherCharge = otherCharge;
		}

		public float getInternetHandlingFee() {
			return internetHandlingFee;
		}

		public void setInternetHandlingFee(float internetHandlingFee) {
			this.internetHandlingFee = internetHandlingFee;
		}

		public float getTotalCost() {
			return (getTicketCost() + getInternetHandlingFee()
					+ getAdditionalCharge() + getOtherCharge())
					- getDiscount();
		}

	}

	public Long getConfirmationId() {
		return confirmationId;
	}

	public void setConfirmationId(Long confirmationId) {
		this.confirmationId = confirmationId;
	}

}
