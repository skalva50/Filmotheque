package com.skalvasociety.skalva.enumeration;

public enum RealisateurOrderBy implements OrderBy {
	nom("Nom"),
	dateNaissance("Date de naissance");	
	
	private String displayName;	

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	RealisateurOrderBy(String displayName){
		this.setDisplayName(displayName);
	}
	
	public SortDirection getSortDirectionDefaut() {
		switch (this) {
		case nom:
			return SortDirection.ASC;
		case dateNaissance:
			return SortDirection.DESC;
		default:
			return SortDirection.ASC;
		}
	}
}
