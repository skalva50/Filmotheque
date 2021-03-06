package com.skalvasociety.skalva.tools;

import java.io.File;


import javax.activation.MimetypesFileTypeMap;

import org.apache.log4j.Logger;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Acces {
	private static Logger logger = Logger.getLogger(Acces.class);
	
	/**
	 * Lit les dossiers d'un dossier (ne lit pas dans les sous-dossiers)
	 * @param path
	 * @return Liste des noms de dossier
	 */
	public List<String> listDossier(String path){		
		List<String> listDossier = new LinkedList<String>();
		File file = new File(path);
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {				
						listDossier.add(files[i].getName());					
				}
			}
		}   	
		return listDossier;
	}
	
	/**
	 * Lit les fichiers d'un dossier (ne lit pas dans les sous-dossiers)
	 * @param path
	 * @return Liste des noms de fichiers
	 */
	public List<FileMetaData> listFichierVideo(String path){		
		List<FileMetaData> listFichier = new LinkedList<FileMetaData>();
		File file = new File(path);
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isDirectory()) {					
					MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
					fileTypeMap.addMimeTypes("video/x-matroska mkv");
					fileTypeMap.addMimeTypes("video/m4v m4v");
					fileTypeMap.addMimeTypes("video/mp4 mp4");
					if(fileTypeMap.getContentType(files[i].getName()).startsWith("video")){	
						FileMetaData fileMetaData = new FileMetaData();
						fileMetaData.setNom(files[i].getName());						
						fileMetaData.setDateModification(new Date(files[i].lastModified()));						
						listFichier.add(fileMetaData);						
					}else{
						logger.error("Probleme type Fichier, Nom du fichier: " +files[i].getName() + " - type fichier: " + fileTypeMap.getContentType(files[i].getName()));
					}
				}
			}
		}   	
		return listFichier;
	}

}
