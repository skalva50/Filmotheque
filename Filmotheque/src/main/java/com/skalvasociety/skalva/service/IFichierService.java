package com.skalvasociety.skalva.service;

import java.util.List;

import com.skalvasociety.skalva.bean.Fichier;

public interface IFichierService {
	Fichier findByID(int id);
	List<Fichier> findAllFichiers();
	void saveFichier (Fichier fichier);
	boolean isFichierCheminUnique(String chemin);
	
	void majFichier();
}
