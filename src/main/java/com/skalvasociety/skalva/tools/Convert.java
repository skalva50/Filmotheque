package com.skalvasociety.skalva.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


public class Convert {
	private static Logger logger = Logger.getLogger(Convert.class);

	public Date stringToDate(String sDate) {
		if(sDate == null || sDate.equals("")){
			return null;
		}
		SimpleDateFormat formatter;
		// Pour certaines date, seul l'année est donnée
		if(sDate.length() == 4){
			formatter = new SimpleDateFormat("yyyy");
		}else{
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		}		 
	     Date date = null; 
		 try {
			date = formatter.parse(sDate);
		} catch (java.text.ParseException e) {		
			logger.error(e.getMessage(),e.getCause());
		}	
	    return date;
	}
}
