package com.skalvasociety.skalva.tmdbObject;

import java.util.List;

import com.skalvasociety.skalva.bean.Film;

public class SearchMovie {
	private Integer page;
	private Integer total_results;
	private Integer total_pages;
	private List<ResultsSearchMovie> results;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotal_results() {
		return total_results;
	}
	public void setTotal_results(Integer total_results) {
		this.total_results = total_results;
	}
	public Integer getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(Integer total_pages) {
		this.total_pages = total_pages;
	}
	public List<ResultsSearchMovie> getResults() {
		return results;
	}
	public void setResults(List<ResultsSearchMovie> results) {
		this.results = results;
	}
	
	public Film toFilm(){
		Film film = null; 
		if (this !=  null && !this.getResults().isEmpty()){
			film = new Film();
			film.setIdTMDB(this.getResults().get(0).getId());			
		}	
		return film;
		
	}
}
