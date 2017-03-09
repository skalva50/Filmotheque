package com.skalvasociety.skalva.bean;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntiteTmdb extends Entite {
	private int idTMDB;	
	
	public int getIdTMDB() {
		return idTMDB;
	}

	public void setIdTMDB(int idTMDB) {
		this.idTMDB = idTMDB;
	}
}
