package com.likelion.ecommerce.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
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
}
