package com.skalvasociety.skalva.dao;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Fichier;

public interface IFichierDao extends IDao<Serializable, Fichier>{
	Fichier findFichierByChemin (String chemin);
}
