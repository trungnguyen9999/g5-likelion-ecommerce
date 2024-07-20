package com.likelion.ecommerce.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoResponse {
	private Integer id;
	private String email;
	private List<String> roles;
	private String token;

}
