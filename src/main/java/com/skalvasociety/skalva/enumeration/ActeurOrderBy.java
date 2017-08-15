package com.skalvasociety.skalva.enumeration;

public enum ActeurOrderBy implements OrderBy {
	nom("Nom"),
	dateNaissance("Date de naissance"),
	popularite("Popularité");
	
	private String displayName;	

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	ActeurOrderBy(String displayName){
		this.setDisplayName(displayName);
	}

	public SortDirection getSortDirectionDefaut() {
		switch (this) {
		case nom:
			return SortDirection.ASC;
		case dateNaissance:
			return SortDirection.DESC;
		case popularite:
			return SortDirection.DESC;
		default:
			return SortDirection.ASC;
		}
	}

}
