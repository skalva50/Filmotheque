package com.skalvasociety.skalva.enumeration;

public enum SerieOrderBy implements OrderBy {
	titre("Titre"),
	dateSortie("Date de sortie"),
	dateAjout("Date d'ajout"),
	note ("Note");
	
	private String displayName;
	
	SerieOrderBy(String displayName){
		this.setDisplayName(displayName);
	}	


	public SortDirection getSortDirectionDefaut() {
		switch (this) {
		case titre:
			return SortDirection.ASC;
		case dateSortie:
			return SortDirection.DESC;
		case dateAjout:
			return SortDirection.DESC;
		case note:
			return SortDirection.DESC;
		default:
			return SortDirection.ASC;
		}
	}


	public String getDisplayName() {
		return displayName;
	}


	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
