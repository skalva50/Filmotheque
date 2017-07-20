package com.skalvasociety.skalva.enumeration;

public enum RealisateurOrderBy implements OrderBy {
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
