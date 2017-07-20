package com.skalvasociety.skalva.dao;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Pays;

public interface IPaysDao extends IDao<Serializable, Pays> {	
	Pays getPaysbyIdIso (String idIso);
}
