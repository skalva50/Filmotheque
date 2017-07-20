package com.skalvasociety.skalva.bean.comparateur;

import java.util.Comparator;

import com.skalvasociety.skalva.bean.Realisateur;

public class RealisateurComparateurNbFilms implements Comparator<Realisateur> {

	public int compare(Realisateur r1, Realisateur r2) {
		if (r1.getFilms() == null || r2.getFilms() == null) {
			return 0;
		}
		if(r1.getFilms().size() > r2.getFilms().size()){
            return 1;
        } else {
            return -1;

        }

	}
}
