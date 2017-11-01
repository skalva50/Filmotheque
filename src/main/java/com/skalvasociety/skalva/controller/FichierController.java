package com.skalvasociety.skalva.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skalvasociety.skalva.bean.Fichier;
import com.skalvasociety.skalva.service.IFichierService;

@Controller
public class FichierController {
	
	private Logger logger = Logger.getLogger(FichierController.class);
	
    @Autowired
    IFichierService service;
    
	@Autowired
    private Environment environment;
    
    @RequestMapping(value = { "/administration/listFichiers" }, method = RequestMethod.GET)
    public String listFichiers(ModelMap model) { 
        List<Fichier> fichiers = service.getAll();
        model.addAttribute("fichiers", fichiers);
        return "allfichiers";
    }
    
	 @RequestMapping(value="/download", method = RequestMethod.GET)
	    public void downloadFile(
	    		HttpServletResponse response,	    	
	    		@RequestParam("typeFolder") String typeFolder,
	    		@RequestParam("pathFile") String pathFile
	    ) throws IOException {
		 
		 String pathFolder ="";
		 if(typeFolder.equalsIgnoreCase("film")){
			 pathFolder = environment.getProperty("film.path");
		 }else if(typeFolder.equalsIgnoreCase("serie")){
			 pathFolder = environment.getProperty("serie.path");
		 }		 
		 
		 File file = new File(pathFolder+"/"+pathFile);
		 
		 if(!file.exists()){
	            String errorMessage = "Désolé, le fichier demandé n'existe pas" + pathFolder+"/"+pathFile;	            
	            OutputStream outputStream = response.getOutputStream();
	            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
	            outputStream.close();
	            logger.error(errorMessage);
	            return;
	     }
	        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
	        if(mimeType==null){
	        	logger.error("mimetype is not detectable, will take default");	            
	            mimeType = "application/octet-stream";
	        }         
	        response.setContentType(mimeType);
	        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));       
	        response.setContentLength((int)file.length());
	        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	        //Copy bytes from source to destination(outputstream in this example), closes both streams.
	        FileCopyUtils.copy(inputStream, response.getOutputStream());		 
	 }
}
