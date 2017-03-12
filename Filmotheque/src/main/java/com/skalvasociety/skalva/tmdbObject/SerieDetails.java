package com.skalvasociety.skalva.tmdbObject;

import java.util.List;

import com.skalvasociety.skalva.bean.Serie;

public class SerieDetails {
	private Boolean in_production;
	private String backdrop_path;		
	private List<Genre> genres;
	private String homepage;
	private Integer id;	
	private String original_language;
	private String original_name;
	private String overview;
	private Double popularity;
	private String poster_path;
	private List<Compagny> production_companies;
	private String status;	
	private Double vote_average;
	private Integer vote_count;	
	private List<Createur> created_by;
	private List<Integer> episode_run_time;
	private String first_air_date;
	private List<String> languages;
	private String last_air_date;
	private String name;
	private List<Network> networks;
	private Integer number_of_episodes;
	private Integer number_of_seasons;
	private List<String> origin_country;
	private List<Season> seasons;	
	private String type;

	public String getBackdrop_path() {
		return backdrop_path;
	}
	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}
	
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getOriginal_language() {
		return original_language;
	}
	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}
	
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public Double getPopularity() {
		return popularity;
	}
	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public List<Compagny> getProduction_companies() {
		return production_companies;
	}
	public void setProduction_companies(List<Compagny> production_companies) {
		this.production_companies = production_companies;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Double getVote_average() {
		return vote_average;
	}
	public void setVote_average(Double vote_average) {
		this.vote_average = vote_average;
	}
	public Integer getVote_count() {
		return vote_count;
	}
	public void setVote_count(Integer vote_count) {
		this.vote_count = vote_count;
	}
	public Boolean getIn_production() {
		return in_production;
	}
	public void setIn_production(Boolean in_production) {
		this.in_production = in_production;
	}
	public String getOriginal_name() {
		return original_name;
	}
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	public List<Createur> getCreated_by() {
		return created_by;
	}
	public void setCreated_by(List<Createur> created_by) {
		this.created_by = created_by;
	}
	public List<Integer> getEpisode_run_time() {
		return episode_run_time;
	}
	public void setEpisode_run_time(List<Integer> episode_run_time) {
		this.episode_run_time = episode_run_time;
	}
	public String getFirst_air_date() {
		return first_air_date;
	}
	public void setFirst_air_date(String first_air_date) {
		this.first_air_date = first_air_date;
	}
	public List<String> getLanguages() {
		return languages;
	}
	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}
	public String getLast_air_date() {
		return last_air_date;
	}
	public void setLast_air_date(String last_air_date) {
		this.last_air_date = last_air_date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Network> getNetworks() {
		return networks;
	}
	public void setNetworks(List<Network> networks) {
		this.networks = networks;
	}
	public Integer getNumber_of_episodes() {
		return number_of_episodes;
	}
	public void setNumber_of_episodes(Integer number_of_episodes) {
		this.number_of_episodes = number_of_episodes;
	}
	public Integer getNumber_of_seasons() {
		return number_of_seasons;
	}
	public void setNumber_of_seasons(Integer number_of_seasons) {
		this.number_of_seasons = number_of_seasons;
	}
	public List<String> getOrigin_country() {
		return origin_country;
	}
	public void setOrigin_country(List<String> origin_country) {
		this.origin_country = origin_country;
	}
	public List<Season> getSeasons() {
		return seasons;
	}
	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	
	public void toSerie(Serie serie){
		serie.setResume(this.getOverview());
		serie.setAffiche(this.getPoster_path());
		serie.setTitre(this.getName());
		serie.setTitreOriginal(this.getOriginal_name());
		serie.setPopularite(getPopularity());
		serie.setNote(getVote_average());
		serie.setResumeCourt(serie.getResume());
		
	}
}
