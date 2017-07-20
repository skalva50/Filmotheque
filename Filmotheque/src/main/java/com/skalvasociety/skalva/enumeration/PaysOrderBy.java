package com.skalvasociety.skalva.enumeration;

public enum PaysOrderBy implements OrderBy {
	nom;

	public SortDirection getSortDirectionDefaut() {
		switch (this) {
		case nom:
			return SortDirection.ASC;
		default:
			return SortDirection.ASC;
		}
	}
}
