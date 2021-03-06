package com.skalvasociety.skalva.tmdbObject;

import java.util.List;

public class ResultsSearchSerie {
	private String poster_path;	
	private String overview;	
	private List<Integer> genre_ids;	
	private Integer id;	
	private String original_language;	
	private String backdrop_path;
	private Double popularity;
	private Integer vote_count;	
	private Double vote_average;
	private String first_air_date;
	private List<String> origin_country;
	private String name;
	private String original_name;
	
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}

	public List<Integer> getGenre_ids() {
		return genre_ids;
	}
	public void setGenre_ids(List<Integer> genre_ids) {
		this.genre_ids = genre_ids;
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
	
	public String getBackdrop_path() {
		return backdrop_path;
	}
	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}
	public Double getPopularity() {
		return popularity;
	}
	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}
	public Integer getVote_count() {
		return vote_count;
	}
	public void setVote_count(Integer vote_count) {
		this.vote_count = vote_count;
	}
	
	public Double getVote_average() {
		return vote_average;
	}
	public void setVote_average(Double vote_average) {
		this.vote_average = vote_average;
	}
	public String getFirst_air_date() {
		return first_air_date;
	}
	public void setFirst_air_date(String first_air_date) {
		this.first_air_date = first_air_date;
	}
	public List<String> getOrigin_country() {
		return origin_country;
	}
	public void setOrigin_country(List<String> origin_country) {
		this.origin_country = origin_country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriginal_name() {
		return original_name;
	}
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	

}
