package com.skalvasociety.skalva.bean.comparateur;

import java.util.Comparator;

import com.skalvasociety.skalva.bean.Genre;

public class GenreComparateur implements Comparator<Genre> {

	public int compare(Genre g0, Genre g1) {
		if(g0 != null && g1 != null && g0.getLibelle() != null && g1.getLibelle() != null)
			return g0.getLibelle().compareToIgnoreCase(g1.getLibelle());
		else
			return 0;
	}

}
