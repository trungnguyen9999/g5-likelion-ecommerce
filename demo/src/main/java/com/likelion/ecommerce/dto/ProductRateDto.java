package com.likelion.ecommerce.dto;

import java.util.Date;

public class ProductRateDto {
	private Integer id;
	
	private Integer productId;
	
	private Integer accountId;
	
	private String userFullname;
	
	private String avatar;
	
	private Integer score;
	
	private String comment;
	
	private Date rateTime;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the accountId
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the userFullname
	 */
	public String getUserFullname() {
		return userFullname;
	}

	/**
	 * @param userFullname the userFullname to set
	 */
	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the rateTime
	 */
	public Date getRateTime() {
		return rateTime;
	}

	/**
	 * @param rateTime the rateTime to set
	 */
	public void setRateTime(Date rateTime) {
		this.rateTime = rateTime;
	}
	
	
}
