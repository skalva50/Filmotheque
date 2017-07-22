package com.skalvasociety.skalva.tmdbObject;

import com.skalvasociety.skalva.bean.Genre;

public class GenreTmdb {
	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Genre toGenre(){
		Genre genre = new Genre();
		genre.setIdTMDB(getId());
		genre.setLibelle(getName());		
		return genre;		
	}
	
}
