package com.skalvasociety.skalva.bean.comparateur;

import java.util.Comparator;

import com.skalvasociety.skalva.bean.Saison;

public class SaisonComparateur implements Comparator<Saison> {

	public int compare(Saison s1, Saison s2) {
		if(s1.getNumero() > s2.getNumero()){
            return 1;
        } else {
            return -1;
        }
	}

}
