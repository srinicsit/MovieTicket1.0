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

}
