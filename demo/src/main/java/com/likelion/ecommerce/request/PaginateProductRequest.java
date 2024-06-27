package com.likelion.ecommerce.request;

public class PaginateProductRequest {
	
	private Integer accountId;
	
	private Boolean inWishList;
	
	private Integer page;
	
	private Integer pageSize;

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

	/**
	 * @return the inWishList
	 */
	public Boolean getInWishList() {
		return inWishList;
	}

	/**
	 * @param inWishList the inWishList to set
	 */
	public void setInWishList(Boolean inWishList) {
		this.inWishList = inWishList;
	}
	
	
}
