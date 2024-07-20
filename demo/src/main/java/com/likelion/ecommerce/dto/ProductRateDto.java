package com.likelion.ecommerce.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductRateDto {
	private Integer id;
	
	private Integer productId;
	
	private Integer accountId;
	
	private String userFirstName;

	private String userLastName;
	
	private String avatar;
	
	private Integer score;
	
	private String comment;
	
	private Date rateTime;

}
