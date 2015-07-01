package com.seriesweb.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Actor extends People{
	
	@Column(columnDefinition="text")
	private String description;
	
	@ManyToMany(mappedBy="actors")
	private Set<Serie> series;

	public Actor() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Serie> getSeries() {
		if(this.series == null){
			this.series = new HashSet<Serie>();
		}
		return (Set<Serie>) this.series;
	}

	public void addAllSerie(Set<Serie> newSerie) {
		if (this.series == null) {
			this.series = new HashSet<Serie>();
		}
		for (Serie tmp : newSerie)
			tmp.addActor(this);
	}
	
	public void removeAllSerie(Set<Serie> newSerie) {
		if(this.series == null) {
			return;
		}
		
		this.series.removeAll(newSerie);	
	}
	
	public void addSerie(Serie newSerie) {
		if(this.series == null) {
			this.series = new HashSet<Serie>();
		}
		
		if (this.series.add(newSerie))
			newSerie.addActor(this);	
	}
	
	public void removeSerie(Serie oldSerie) {
		if(this.series == null)
			return;
		
		if (this.series.remove(oldSerie))
			oldSerie.removeActor(this);
			
	}
	
}
