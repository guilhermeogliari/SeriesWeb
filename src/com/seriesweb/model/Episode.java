package com.seriesweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Episode extends Program{
	
	@Column
	private Long duration;
	
	@Column
	private Integer season;
	
	@ManyToOne
	@JoinColumn
	private Serie serie;

	public Episode() {
		super();
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie mySerie) {
		this.basicSetSerie(mySerie);
		mySerie.addEpisode(this);	
	}

	public void basicSetSerie(Serie mySerie) {
		if (this.serie != mySerie) {
			if (mySerie != null){
				if (this.serie != mySerie) {
					Serie oldserie = this.serie;
					this.serie = mySerie;
					if (oldserie != null)
						oldserie.removeEpisode(this);
				}
			}
		}	
	}
	
	public void unsetSerie() {
		if (this.serie == null)
			return;
		Serie oldserie = this.serie;
		this.serie = null;
		oldserie.removeEpisode(this);	
	}
	
}
