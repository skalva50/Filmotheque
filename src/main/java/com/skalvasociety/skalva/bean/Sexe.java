package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sexe")
public class Sexe extends Entite {
	private String typeSexe;
	
	private List<Acteur> personnes = new LinkedList<Acteur>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sexe")
	public List<Acteur> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(List<Acteur> personnes) {
		this.personnes = personnes;
	}

	public String getTypeSexe() {
		return typeSexe;
	}

	public void setTypeSexe(String typeSexe) {
		this.typeSexe = typeSexe;
	}
	
}
