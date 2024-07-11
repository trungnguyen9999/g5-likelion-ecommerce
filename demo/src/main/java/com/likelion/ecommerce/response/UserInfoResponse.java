package com.likelion.ecommerce.response;

import java.util.List;

public class UserInfoResponse {
	private Integer id;
	private String username;
	private List<String> roles;

	public UserInfoResponse(Integer id, String username, List<String> roles) {
		this.id = id;
		this.username = username;
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}
