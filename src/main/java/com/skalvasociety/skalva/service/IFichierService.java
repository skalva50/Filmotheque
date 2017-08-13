package com.skalvasociety.skalva.service;

import java.io.Serializable;
import com.skalvasociety.skalva.bean.Fichier;

public interface IFichierService extends IService<Serializable, Fichier> {	
	boolean isFichierCheminUnique(String chemin);	
	void majFichier();
}
