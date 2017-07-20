package com.skalvasociety.skalva.service;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.tmdbObject.Country;

public interface IPaysService extends IService<Serializable, Pays>{	
	Pays getPaysbyIdIso (String idIso);
	Pays countryToPays (Country country);
	Pays countrySerieToPays (String country);
	void majCountrytoPays( Country country, Pays pays);
}
