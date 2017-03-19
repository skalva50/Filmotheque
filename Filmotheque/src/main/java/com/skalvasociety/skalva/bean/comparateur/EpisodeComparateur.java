package com.skalvasociety.skalva.bean.comparateur;

import java.util.Comparator;

import com.skalvasociety.skalva.bean.Episode;

public class EpisodeComparateur implements Comparator<Episode> {

	public int compare(Episode e1, Episode e2) {
		if(e1.getNumero() > e2.getNumero()){
            return 1;
        } else {
            return -1;
        }
	}

}
