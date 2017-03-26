package com.skalvasociety.skalva.tmdbObject;

import java.util.List;
import com.skalvasociety.skalva.bean.MediaTMDB;


public class Video {
	private Integer id;
	private List<ResultsVideos> results;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<ResultsVideos> getResults() {
		return results;
	}
	public void setResults(List<ResultsVideos> results) {
		this.results = results;
	}

	
	public void toMedia(MediaTMDB media){

	}
	
	
	

}
