package com.skalvasociety.skalva.enumeration;

public enum ActeurOrderBy implements OrderBy {
	nom("nom");
	
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
		default:
			return SortDirection.ASC;
		}
	}

}
