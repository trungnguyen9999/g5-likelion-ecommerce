package com.likelion.ecommerce.request;

import lombok.Data;

@Data
public class PaginateRequest {
	
	private Integer accountId;
	
	private Integer page;
	
	private Integer pageSize;
}
