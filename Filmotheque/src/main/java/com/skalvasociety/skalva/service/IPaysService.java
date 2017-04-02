package com.skalvasociety.skalva.service;

import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.tmdbObject.Country;

public interface IPaysService {
	void savePays(Pays pays);
	Pays getPaysbyIdIso (String idIso);
	Pays getPaysbyId (Integer id);
	Pays countryToPays (Country country);
	Pays countrySerieToPays (String country);
	void majCountrytoPays( Country country, Pays pays);
}
