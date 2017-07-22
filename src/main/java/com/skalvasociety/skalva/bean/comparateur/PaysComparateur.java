package com.skalvasociety.skalva.bean.comparateur;

import java.util.Comparator;

import com.skalvasociety.skalva.bean.Pays;

public class PaysComparateur implements Comparator<Pays> {

	public int compare(Pays p0, Pays p1) {
		if(p0 != null && p1 != null && p0.getNom() != null && p1.getNom() != null)
			return p0.getNom().compareToIgnoreCase(p1.getNom());
		else
			return 0;
	}

}
