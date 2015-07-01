package com.seriesweb.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@MappedSuperclass
public class Program{
	
	@Id
	@Column
	@GeneratedValue
	protected Long id = 0L;
	
	@Column
	private String name;
	
	@Column
	private double ratingGeneral;
	
	@Column(columnDefinition="text")
	private String description;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@OneToMany(mappedBy = "program")
	private Set<Iteration> iterations;

	public Program() {
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

	public double getRatingGeneral() {
		return ratingGeneral;
	}

	public void setRatingGeneral(double ratingGeneral) {
		this.ratingGeneral = ratingGeneral;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Iteration> getIterations() {
		if(this.iterations == null) {
			this.iterations = new HashSet<Iteration>();
		}
		return (Set<Iteration>) this.iterations;	
	}

	public void setIterations(Set<Iteration> iterations) {
		this.iterations = iterations;
	}
	
	public void addAllIteration(Set<Iteration> newIteracao) {
		if (this.iterations == null) {
			this.iterations = new HashSet<Iteration>();
		}
		for (Iteration tmp : newIteracao)
			tmp.setProgram(this);
			
	}
	
	public void removeAllIteration(Set<Iteration> newIteracao) {
		if(this.iterations == null) {
			return;
		}
		
		this.iterations.removeAll(newIteracao);	
	}
	
	public void addIteration(Iteration newIteracao) {
		if(this.iterations == null) {
			this.iterations = new HashSet<Iteration>();
		}
		
		if (this.iterations.add(newIteracao))
			newIteracao.basicSetProgram(this);	
	}
	
	public void removeIteration(Iteration oldIteracao) {
		if(this.iterations == null)
			return;
		
		if (this.iterations.remove(oldIteracao))
			oldIteracao.unsetProgram();
			
	}
	
}
