package com.avihs.movie.business.theater.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.user.model.User;

@NamedQueries({
		@NamedQuery(name = "findByTheaterName", query = "select name from Theater t where t.name = :name and t.location = :location"),
		@NamedQuery(name = "findByTheaterNameAndNotID", query = "select name from Theater t where t.name = :name  and t.location = :location and t.id=:id"),
		@NamedQuery(name = "findTheatersByLocation", query = "from Theater t where t.location = :location"),
		@NamedQuery(name = "findTheatersByUser", query = "from Theater t where t.user = :user") })
@Entity
@Table(name = "THEATER")
@DynamicUpdate(value = true)
@DynamicInsert(value = true)
// @SelectBeforeUpdate(value = true)
public class Theater extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LOCATION")
	private String location;

	@JoinColumn(name = "user_pk_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
