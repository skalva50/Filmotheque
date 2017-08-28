package com.skalvasociety.skalva.enumeration;

public enum GenreOrderBy implements IOrderBy {
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
