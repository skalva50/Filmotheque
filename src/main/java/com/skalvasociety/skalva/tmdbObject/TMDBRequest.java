package com.skalvasociety.skalva.tmdbObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.MediaTMDB;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Episode;


public class TMDBRequest {
	
	private Logger logger = Logger.getLogger(TMDBRequest.class);
    
	public TMDBRequest(String API_KEY){
		this.setApi_key(API_KEY);
	}
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	private String api_key;	
	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	
	public SearchMovie searchMovie(String nameMovie) throws IOException{			
		
		// Au minimum le nom doit contenir 1 caractères + l'extension
		if(nameMovie.length()<=4)
			return null;
		
		// Suppression extension
		String nameMovieFormat = nameMovie.substring(0, nameMovie.length()-4);
		
		// Remplacement . et _ par espace
		nameMovieFormat = nameMovieFormat.replace('.', ' ');
		nameMovieFormat = nameMovieFormat.replace('_', ' ');
		
		// formatage des espaces pour requetes		
		nameMovieFormat = nameMovieFormat.replaceAll(" ", "%20");				
		
		
		String url = "https://api.themoviedb.org/3/search/movie";
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=fr-FR";
		// Query
		url += "&query="+nameMovieFormat;
		// Options
		url += "&page=1&include_adult=false";
		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();		
		// optional default is GET
		con.setRequestMethod("GET");		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = con.getResponseCode();
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();				
				// optional default is GET
				con.setRequestMethod("GET");				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);
				responseCode = con.getResponseCode();						
			} catch (InterruptedException e) {				
				logger.error(e.getMessage(),e.getCause());
			}
		}
		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();			
			ObjectMapper objectMapper = new ObjectMapper(); 
			SearchMovie movie = objectMapper.readValue(response.toString(),SearchMovie.class );
			return movie;
		}else if (responseCode == 400){
			logger.error("Erreur connexion à TMDB, APIKEY = "+ api_key);
			return null;
		}else{
			return null;
		}
	}
	
	public MovieDetails getMovieByID (int id) throws IOException{
		
		String url = "https://api.themoviedb.org/3/movie/"+ id;
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=fr-FR";		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();		
		// optional default is GET
		con.setRequestMethod("GET");		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();				
				// optional default is GET
				con.setRequestMethod("GET");				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);
				responseCode = con.getResponseCode();			
				
			} catch (InterruptedException e) {
				logger.error(e.getMessage(),e.getCause());
			}
		}		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();			
			ObjectMapper objectMapper = new ObjectMapper(); 
			MovieDetails movie = objectMapper.readValue(response.toString(),MovieDetails.class );
			return movie;
		}else if (responseCode == 400){
			logger.error("Erreur connexion à TMDB, APIKEY = "+ api_key);
			return null;
		}else{
			return null;
		}
	}
	
	public List<com.skalvasociety.skalva.bean.Video> getVideoByID (MediaTMDB media) throws IOException{		
		
		String url = "";
		if (media.getClass() == Film.class){
			url = "https://api.themoviedb.org/3/movie/"+  ((Film)media).getIdTMDB()+ "/videos";
		}else if (media.getClass() == Serie.class){
			url = "https://api.themoviedb.org/3/tv/"+  ((Serie)media).getIdTMDB()+ "/videos";
		}else if (media.getClass() == Saison.class){
			url = "https://api.themoviedb.org/3/tv/"+  ((Saison)media).getSerie().getIdTMDB()+ "/season/"+ ((Saison)media).getNumero() +"/videos";
		}else if (media.getClass() == Episode.class){
			url = "https://api.themoviedb.org/3/tv/"+
						((Episode)media).getSaison().getSerie().getIdTMDB()+
						"/season/"+ ((Episode)media).getSaison().getNumero() +
						"/episode/" + ((Episode)media).getNumero() +
						"/videos";
		}else{
			return null;
		}
		
		
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=en-US";
				
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();		
		// optional default is GET
		con.setRequestMethod("GET");		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();				
				// optional default is GET
				con.setRequestMethod("GET");				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);
				responseCode = con.getResponseCode();	
			} catch (InterruptedException e) {
				logger.error(e.getMessage(),e.getCause());
			}
		}
				
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			ObjectMapper objectMapper = new ObjectMapper(); 
			Video videoTMDB = objectMapper.readValue(response.toString(),Video.class );
			
			if (videoTMDB.getResults() != null && !videoTMDB.getResults().isEmpty()){
				List<ResultsVideos> listResultsVideos = videoTMDB.getResults();
				List<com.skalvasociety.skalva.bean.Video> listVideos = new LinkedList<com.skalvasociety.skalva.bean.Video>();
				for (ResultsVideos resultsVideos : listResultsVideos){
					com.skalvasociety.skalva.bean.Video video = new com.skalvasociety.skalva.bean.Video();
					video.setCleVideo(resultsVideos.getKey());
					video.setSiteVideo(resultsVideos.getSite());
					video.setNom(resultsVideos.getName());
					video.setMedia(media);				
					listVideos.add(video);
				}				
				return listVideos;
			}		
		}else if (responseCode == 400){
			logger.error("Erreur connexion à TMDB, APIKEY = "+ api_key);
			return null;
		}
		return null;
	}
	
	public SearchSerie searchSerie (String name) throws JsonParseException, JsonMappingException, IOException{
		
		String nameSerieFormat = name.replaceAll(" ", "%20");		
		String url = "https://api.themoviedb.org/3/search/tv";
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=fr-FR";
		// Query
		url += "&query="+nameSerieFormat;
		// Options
		url += "&page=1";		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();		
		// optional default is GET
		con.setRequestMethod("GET");		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();				
				// optional default is GET
				con.setRequestMethod("GET");				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);
				responseCode = con.getResponseCode();	
				
			} catch (InterruptedException e) {
				logger.error(e.getMessage(),e.getCause());
			}
		}		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();			
			ObjectMapper objectMapper = new ObjectMapper(); 
			SearchSerie serie = objectMapper.readValue(response.toString(),SearchSerie.class );
			return serie;
		}else if (responseCode == 400){
			logger.error("Erreur connexion à TMDB, APIKEY = "+ api_key);
			return null;
		}else{
			return null;
		}
	}
	
	public SerieDetails getSerieByID (int id) throws IOException{	
		
		String url = "https://api.themoviedb.org/3/tv/"+ id;
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=fr-FR";		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();		
		// optional default is GET
		con.setRequestMethod("GET");		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();				
				// optional default is GET
				con.setRequestMethod("GET");				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);
				responseCode = con.getResponseCode();				
			} catch (InterruptedException e) {				
				logger.error(e.getMessage(),e.getCause());
			}
		}
		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();			
			ObjectMapper objectMapper = new ObjectMapper(); 
			SerieDetails serie = objectMapper.readValue(response.toString(),SerieDetails.class );
			return serie;
		}else if (responseCode == 400){
			logger.error("Erreur connexion à TMDB, APIKEY = "+ api_key);
			return null;
		}else{
			return null;
		}
	}

	
	public SerieSaisonDetails getSerieSaison (int id, String sNumSaison) throws IOException{
		// Au minimum le nom doit contenir saison + espace + numSaison soit 8  caractètres
		if(sNumSaison.length()<=7)
			return null;
		
		// Suppression extension
		String numSaisonFormat = sNumSaison.substring(7, sNumSaison.length());
		int numSaison;
		
		try {
			numSaison = Integer.parseInt(numSaisonFormat);
		} catch (Exception e) {
			logger.error("Erreur formatage du numero de saison: "+sNumSaison+ " - "+ e.getMessage(),e.getCause());
			return null;
		}	
		String url = "https://api.themoviedb.org/3/tv/"+ id;
		// numero saison
		url += "/season/"+numSaison;
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=fr-FR";		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();		
		// optional default is GET
		con.setRequestMethod("GET");		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();				
				// optional default is GET
				con.setRequestMethod("GET");				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);
				responseCode = con.getResponseCode();
			
			} catch (InterruptedException e) {				
				logger.error(e.getMessage(),e.getCause());
			}
		}
			
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			ObjectMapper objectMapper = new ObjectMapper(); 
			SerieSaisonDetails serieSaison = objectMapper.readValue(response.toString(),SerieSaisonDetails.class );
			return serieSaison;
		}else if (responseCode == 400){
			logger.error("Erreur connexion à TMDB, APIKEY = "+ api_key);
			return null;
		}else{
			return null;
		}
	}
	
	
	public EpisodeTMDB getEpisode(Integer idSerie, Integer numSaison, String nomFichier) throws JsonParseException, JsonMappingException, IOException{
		// Au minimum le nom doit contenir 1 caractères + l'extension
		if(nomFichier.length()<=4)
			return null;
		
		// Suppression extension
		String sNumEpisode = nomFichier.substring(0, nomFichier.length()-4);
		// Recuperation dernier caractère
		if (sNumEpisode.length()<=1){
			return null;
		}
		sNumEpisode = sNumEpisode.substring(sNumEpisode.length()-2, sNumEpisode.length());		
		int numEpisode = 0;
		try {
			numEpisode = Integer.parseInt(sNumEpisode);
		} catch (Exception e) {
			logger.error("Erreur formatage du numero de saison: "+sNumEpisode+ " - "+ e.getMessage(),e.getCause());
			return null;
		}		
		
		String url = "https://api.themoviedb.org/3/tv/"+ idSerie;
		// numero saison
		url += "/season/"+numSaison;
		// numero episode
		url += "/episode/"+numEpisode;
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=fr-FR";	
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();		
		// optional default is GET
		con.setRequestMethod("GET");		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();		
				con.setRequestMethod("GET");
				con.setRequestProperty("User-Agent", USER_AGENT);
				responseCode = con.getResponseCode();								
			} catch (InterruptedException e) {				
				logger.error(e.getMessage(),e.getCause());
			}
		}	
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();			
			ObjectMapper objectMapper = new ObjectMapper(); 
			EpisodeTMDB episodeTMDB = objectMapper.readValue(response.toString(),EpisodeTMDB.class );
			return episodeTMDB;
		}else if (responseCode == 400){
			logger.error("Erreur connexion à TMDB, APIKEY = "+ api_key);
			return null;
		}else{
			return null;
		}							
	}
	
	public List<Cast> getCastbyMedia (MediaTMDB media) throws IOException{
		
		String url = "";
		if (media.getClass() == Film.class){
			url = "https://api.themoviedb.org/3/movie/"+  ((Film)media).getIdTMDB()+ "/credits";
		}else if (media.getClass() == Serie.class){
			url = "https://api.themoviedb.org/3/tv/"+  ((Serie)media).getIdTMDB()+ "/credits";	
		}else if (media.getClass() == Saison.class){			
			url = "https://api.themoviedb.org/3/tv/"+  ((Saison)media).getSerie().getIdTMDB()+ "/season/"+((Saison)media).getNumero()+"/credits";			
		}else{
			return null;
		}
				
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=en-US";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();		
		// optional default is GET
		con.setRequestMethod("GET");		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();				
				// optional default is GET
				con.setRequestMethod("GET");				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);
				responseCode = con.getResponseCode();
				
			} catch (InterruptedException e) {
				logger.error(e.getMessage(),e.getCause());
			}
		}
		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			ObjectMapper objectMapper = new ObjectMapper(); 
			Credit credit = objectMapper.readValue(response.toString(),Credit.class );
			
			if (credit != null) {
				return credit.getCast();
			}else{
				return null;
			}
		}else if (responseCode == 400){
			logger.error("Erreur connexion à TMDB, APIKEY = "+ api_key);
			return null;
		}else{
			return null;
		}
	}
	
	public List<Crew> getCrewbyMedia (MediaTMDB media) throws IOException{		
		
		String url = "";
		if (media.getClass() == Film.class){
			url = "https://api.themoviedb.org/3/movie/"+  ((Film)media).getIdTMDB()+ "/credits";
		}else if (media.getClass() == Serie.class){
			url = "https://api.themoviedb.org/3/tv/"+  ((Serie)media).getIdTMDB()+ "/credits";		
		}else{
			return null;
		}	
		
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=en-US";		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();		
		// optional default is GET
		con.setRequestMethod("GET");		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();				
				// optional default is GET
				con.setRequestMethod("GET");				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);
				responseCode = con.getResponseCode();				
			} catch (InterruptedException e) {
				logger.error(e.getMessage(),e.getCause());
			}
		}
		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			ObjectMapper objectMapper = new ObjectMapper(); 
			Credit credit = objectMapper.readValue(response.toString(),Credit.class );
			
			if (credit != null) {
				return credit.getCrew();
			}else{
				return null;
			}
		}else if (responseCode == 400){
			logger.error("Erreur connexion à TMDB, APIKEY = "+ api_key);
			return null;
		}else{
			return null;
		}
	}

	public Genres getGenresSeries() throws IOException {		
		
		String url = "https://api.themoviedb.org/3/genre/tv/list";
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=fr-FR";		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();		
		// optional default is GET
		con.setRequestMethod("GET");		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();
				// optional default is GET
				con.setRequestMethod("GET");				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);
				responseCode = con.getResponseCode();				
				
			} catch (InterruptedException e) {				
				logger.error(e.getMessage(),e.getCause());
			}
		}
		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			ObjectMapper objectMapper = new ObjectMapper(); 
			Genres genres = objectMapper.readValue(response.toString(),Genres.class );
			return genres;
		}else if (responseCode == 400){
			logger.error("Erreur connexion à TMDB, APIKEY = "+ api_key);
			return null;
		}else{
			return null;
		}
	}
	
	public Genres getGenresFilms() throws IOException {
		
		String url = "https://api.themoviedb.org/3/genre/movie/list";
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=fr-FR";
		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();		
		// optional default is GET
		con.setRequestMethod("GET");		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();				
				// optional default is GET
				con.setRequestMethod("GET");				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);
				responseCode = con.getResponseCode();				
				
			} catch (InterruptedException e) {				
				logger.error(e.getMessage(),e.getCause());
			}
		}
		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			ObjectMapper objectMapper = new ObjectMapper(); 
			Genres genres = objectMapper.readValue(response.toString(),Genres.class );
			return genres;
		}else if (responseCode == 400){
			logger.error("Erreur connexion à TMDB, APIKEY = "+ api_key);
			return null;
		}else{
			return null;
		}
	}
	
	public People getPeopleDetail(Integer idTMDB) throws IOException {
	
		String url = "https://api.themoviedb.org/3/person/";
		// id
		url += idTMDB;
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=en-US";		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();		
		// optional default is GET
		con.setRequestMethod("GET");		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();				
				// optional default is GET
				con.setRequestMethod("GET");				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);
				responseCode = con.getResponseCode();				
			} catch (InterruptedException e) {				
				logger.error(e.getMessage(),e.getCause());
			}
		}		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			ObjectMapper objectMapper = new ObjectMapper(); 
			People people = objectMapper.readValue(response.toString(),People.class );
			return people;
		}else if (responseCode == 400){
			logger.error("Erreur connexion à TMDB, APIKEY = "+ api_key);
			return null;
		}else{
			return null;
		}
	}


}
