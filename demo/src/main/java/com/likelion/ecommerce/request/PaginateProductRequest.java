package com.likelion.ecommerce.request;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaginateProductRequest {
	
	private Integer accountId;
	
	private Integer page;
	
	private Integer pageSize;
	
	

	public PaginateProductRequest(Integer accountId, Integer page, Integer pageSize) {
		super();
		this.accountId = accountId;
		this.page = page;
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
}
