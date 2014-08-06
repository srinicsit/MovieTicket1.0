package com.avihs.movie.web.ticket.payment.form;

public class PaymentForm {

	private Integer transactionId;

	private Long creditCardNumer;

	private Integer ccv;

	private Integer expMonth;

	private Integer expYear;

	private String nameOnCC;

	private String ccType;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Long getCreditCardNumer() {
		return creditCardNumer;
	}

	public void setCreditCardNumer(Long creditCardNumer) {
		this.creditCardNumer = creditCardNumer;
	}

	public Integer getCcv() {
		return ccv;
	}

	public void setCcv(Integer ccv) {
		this.ccv = ccv;
	}

	public Integer getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(Integer expMonth) {
		this.expMonth = expMonth;
	}

	public Integer getExpYear() {
		return expYear;
	}

	public void setExpYear(Integer expYear) {
		this.expYear = expYear;
	}

	public String getNameOnCC() {
		return nameOnCC;
	}

	public void setNameOnCC(String nameOnCC) {
		this.nameOnCC = nameOnCC;
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

}
