package com.seriesweb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Iteration{
	
	@Id
	@Column
	@GeneratedValue
	protected Long id = 0L;
	
	@Column
	private Boolean assisted;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date schedule;
	
	@Column
	private String comment;
	
	@Column
	private Double rating;
	
	@ManyToOne
	@JoinColumn
	private User user;
	
	@ManyToOne
	@JoinColumn
	private Program program;
	
	public Iteration() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAssisted() {
		return assisted;
	}

	public void setAssisted(Boolean assisted) {
		this.assisted = assisted;
	}

	public Date getSchedule() {
		return schedule;
	}

	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.basicSetUser(user);
		user.addIteration(this);
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.basicSetProgram(program);
		program.addIteration(this);
	}
	
	public void basicSetUser(User myUsuario) {
		if (this.user != myUsuario) {
			if (myUsuario != null){
				if (this.user != myUsuario) {
					User oldusuario = this.user;
					this.user = myUsuario;
					if (oldusuario != null)
						oldusuario.removeIteration(this);
				}
			}
		}	
	}
	
	public void basicSetProgram(Program myPrograma) {
		if (this.program != myPrograma) {
			if (myPrograma != null){
				if (this.program != myPrograma) {
					Program oldprograma = this.program;
					this.program = myPrograma;
					if (oldprograma != null)
						oldprograma.removeIteration(this);
				}
			}
		}	
	}
	
	public void unsetUser() {
		if (this.user == null)
			return;
		User oldusuario = this.user;
		this.user = null;
		oldusuario.removeIteration(this);	
	}

	public void unsetProgram() {
		if (this.program == null)
			return;
		Program oldprograma = this.program;
		this.program = null;
		oldprograma.removeIteration(this);	
	}
	
}
