package com.skalvasociety.skalva.dao;

import java.util.List;

import com.skalvasociety.skalva.bean.Fichier;

public interface IFichierDao {
	
	Fichier findById(int id);	
	List<Fichier> findAllFichiers();
	void saveFichier(Fichier fichier);
	Fichier findFichierByChemin (String chemin);

}
