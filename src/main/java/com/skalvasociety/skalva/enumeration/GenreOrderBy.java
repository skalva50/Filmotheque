package com.skalvasociety.skalva.enumeration;

public enum GenreOrderBy implements OrderBy {
	libelle;

	public SortDirection getSortDirectionDefaut() {
		switch (this) {
		case libelle:
			return SortDirection.ASC;
		default:
			return SortDirection.ASC;
		}
	}
}
