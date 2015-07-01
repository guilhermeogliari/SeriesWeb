package com.seriesweb.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Serie extends Program{

	@Column
	private Integer numbersSeasons;
	
	@Column
	private String originalName;
	
	@Lob
	@Column(columnDefinition = "mediumblob")
	private byte[] image;
	
	@Enumerated(EnumType.STRING)
	private ParentalGuidance parentalGuidance;
	
	@Enumerated(EnumType.STRING)
	private Set<Genre> genres;
	
	@ManyToMany
	private Set<Actor> actors;

	@OneToMany(mappedBy = "serie")
	private Set<Episode> episodes;

	public Serie() {
		super();
	}

	public Integer getNumbersSeasons() {
		return numbersSeasons;
	}

	public void setNumbersSeasons(Integer numbersSeasons) {
		this.numbersSeasons = numbersSeasons;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public ParentalGuidance getParentalGuidance() {
		return parentalGuidance;
	}

	public void setParentalGuidance(ParentalGuidance parentalGuidance) {
		this.parentalGuidance = parentalGuidance;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public Set<Actor> getActors() {
		if(this.actors == null) {
			this.actors = new HashSet<Actor>();
		}
		return (Set<Actor>) this.actors;	
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public Set<Episode> getEpisodes() {
		if(this.episodes == null) {
			this.episodes = new HashSet<Episode>();
		}
		return (Set<Episode>) this.episodes;	
	}

	public void setEpisodes(Set<Episode> episodes) {
		this.episodes = episodes;
	}
	
	public void addAllActor(Set<Actor> newAtor) {
		if (this.actors == null) {
			this.actors = new HashSet<Actor>();
		}
		for (Actor tmp : newAtor)
			tmp.addSerie(this);	
	}	
	
	public void addAllEpisode(Set<Episode> newEpisodio) {
		if (this.episodes == null) {
			this.episodes = new HashSet<Episode>();
		}
		for (Episode tmp : newEpisodio)
			tmp.setSerie(this);
	}
	
	public void removeAllActor(Set<Actor> newAtor) {
		if(this.actors == null) {
			return;
		}
		
		this.actors.removeAll(newAtor);	
	}
	
	public void removeAllEpisode(Set<Episode> newEpisodio) {
		if(this.episodes == null) {
			return;
		}
		
		this.episodes.removeAll(newEpisodio);	
	}

	public void addActor(Actor newAtor) {
		if(this.actors == null) {
			this.actors = new HashSet<Actor>();
		}
		
		if (this.actors.add(newAtor))
			newAtor.addSerie(this);	
	}
	
	public void addEpisode(Episode newEpisodio) {
		if(this.episodes == null) {
			this.episodes = new HashSet<Episode>();
		}
		
		if (this.episodes.add(newEpisodio))
			newEpisodio.basicSetSerie(this);	
	}
	
	public void removeActor(Actor oldAtor) {
		if(this.actors == null)
			return;
		
		if (this.actors.remove(oldAtor))
			oldAtor.removeSerie(this);	
	}
	
	public void removeEpisode(Episode oldEpisodio) {
		if(this.episodes == null)
			return;
		
		if (this.episodes.remove(oldEpisodio))
			oldEpisodio.unsetSerie();	
	}
	
}
