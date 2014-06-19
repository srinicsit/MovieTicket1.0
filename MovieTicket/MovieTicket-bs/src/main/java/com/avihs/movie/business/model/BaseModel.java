package com.avihs.movie.business.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.avihs.movie.business.user.model.User;

@MappedSuperclass
public class BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;

	@Column(name = "IS_ACTIVE", length = 1)
	private Character isActive = 'Y';

	@JoinColumn(name = "MODIFIED_USER_PK_ID")
	@ManyToOne
	private User modifiedUser;

	public BaseModel() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Character getIsActive() {
		return isActive;
	}

	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}

	public User getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(User modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

}
