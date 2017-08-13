package com.skalvasociety.skalva.tmdbObject;

import java.util.List;

public class Credit {
	private Integer id;
	private List<Crew> crew;
	private List<Cast> cast;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Crew> getCrew() {
		return crew;
	}
	public void setCrew(List<Crew> crew) {
		this.crew = crew;
	}
	public List<Cast> getCast() {
		return cast;
	}
	public void setCast(List<Cast> cast) {
		this.cast = cast;
	}
	
	
}
