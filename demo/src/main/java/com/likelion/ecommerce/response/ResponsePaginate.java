package com.likelion.ecommerce.response;

import java.util.List;

public class ResponsePaginate {
	private Integer curentPage;
	
	private Integer totalPages;
	
	private Integer pageSize;
	
	private Integer totalElements;
	
	private Object items;

	public Integer getCurentPage() {
		return curentPage;
	}

	public void setCurentPage(Integer curentPage) {
		this.curentPage = curentPage;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}
	
}
