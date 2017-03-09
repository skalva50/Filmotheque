package com.skalvasociety.skalva.tmdbObject;

import java.util.List;

import com.skalvasociety.skalva.bean.Saison;

public class SerieSaisonDetails {
	private String _id;
	private String air_date;
	private List<Episodes> episodes;
	private String name;
	private String overview;
	private Integer id;
	private String poster_path;
	private Integer season_number;
	
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getAir_date() {
		return air_date;
	}
	public void setAir_date(String air_date) {
		this.air_date = air_date;
	}
	public List<Episodes> getEpisodes() {
		return episodes;
	}
	public void setEpisodes(List<Episodes> episodes) {
		this.episodes = episodes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public Integer getSeason_number() {
		return season_number;
	}
	public void setSeason_number(Integer season_number) {
		this.season_number = season_number;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	public Saison toSaison(){
		Saison saison = new Saison();
		saison.setAffiche(this.getPoster_path());
		saison.setResume(this.getOverview());
		saison.setNumero(this.getSeason_number());	
		saison.setDateSortie(this.getAir_date());
		return saison;
	}
	
	

}
