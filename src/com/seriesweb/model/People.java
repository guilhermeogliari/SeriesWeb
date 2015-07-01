package com.seriesweb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@MappedSuperclass
public class People{
	
	@Id
	@Column(nullable = false)
	@GeneratedValue
	protected Long id = 0L;
	
	@Column
	private String name;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date birth;
	
	public People() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {   
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
}
