package com.skalvasociety.skalva.daoTools;

import com.skalvasociety.skalva.enumeration.Sort;
import com.skalvasociety.skalva.enumeration.SortBy;

public class PageRequest {
	private int pageNumber;
	private int pageSize;
	private Sort sort;
	private SortBy sortBy;

	
	public PageRequest(int pageNumber, int pageSize, Sort sort, SortBy sortBy) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.sort = sort;
		this.sortBy = sortBy;	
	}

	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public SortBy getSortBy() {
		return sortBy;
	}

	public void setSortBy(SortBy sortBy) {
		this.sortBy = sortBy;
	}
	
}
