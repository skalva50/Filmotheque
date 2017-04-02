package com.skalvasociety.skalva.dao;

import com.skalvasociety.skalva.bean.Pays;

public interface IPaysDao {
	void savePays(Pays pays);
	Pays getPaysbyIdIso (String idIso);
	Pays getPaysbyId (Integer id);

}
