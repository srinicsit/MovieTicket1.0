package com.avihs.movie.business.transaction.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;

@Entity
@Table(name = "TRANSACTION")
@DynamicUpdate
@DynamicInsert
public class Transaction extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long creditCardNumer;

	private Integer ccv;

	private Integer expMonth;

	private Integer expYear;

	private String nameOnCC;

	private String ccType;

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
