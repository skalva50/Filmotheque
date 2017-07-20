package com.skalvasociety.skalva.bean.comparateur;

import java.util.Comparator;

import com.skalvasociety.skalva.bean.Personne;

public class PersonneComparateur implements Comparator<Personne> {

	public int compare(Personne p0, Personne p1) {
		return p0.getNom().compareToIgnoreCase(p1.getNom());
	}

}
