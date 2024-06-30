package com.likelion.ecommerce;

import org.springframework.http.HttpHeaders;

public class HeaderClass {
	public static HttpHeaders getHeader()	{
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "https://g5-likelion-ecommerce.onrender.com");
        return headers;
	}
}
