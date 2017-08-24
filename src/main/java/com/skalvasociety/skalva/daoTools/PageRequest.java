package com.skalvasociety.skalva.daoTools;

import java.util.List;

import com.skalvasociety.skalva.enumeration.SortDirection;
import com.skalvasociety.skalva.enumeration.IOrderBy;

public class PageRequest<T> {
	private int pageNumber;
	private int pageSize;
	private SortDirection sort;
	private IOrderBy sortBy;
	private int totalPage;

	
	public PageRequest(int pageNumber, int pageSize, SortDirection sort, IOrderBy sortBy) {
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
	public SortDirection getSort() {
		return sort;
	}
	public void setSort(SortDirection sort) {
		this.sort = sort;
	}

	public IOrderBy getSortBy() {
		return sortBy;
	}

	public void setSortBy(IOrderBy sortBy) {
		this.sortBy = sortBy;
	}

	public int getTotalPage() {    		
    	return totalPage;

	}

	public void setTotalPage(List<T> list) {
    	int totalPage = 0;
    	if (this.getPageSize() > 0){
    		totalPage= list.size() / this.getPageSize()+1;
    	}    		
    	this.totalPage = totalPage;
	}
	
	
}
