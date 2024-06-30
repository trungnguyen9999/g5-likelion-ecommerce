package com.likelion.ecommerce.config;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CorsFilter extends OncePerRequestFilter {


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Specify the allowed origin domains 
		response.setHeader("Access-Control-Allow-Origin", "https://g5-likelion-ecommerce.onrender.com");
		// Specify the allowed HTTP methods 
		 response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); 

		// Specify the allowed headers 
		 response.setHeader("Access-Control-Allow-Headers", "*"); 
		// Enable support for credentials (e.g., cookies)
		 response.setHeader("Access-Control-Allow-Credentials", "true"); 

		 
        filterChain.doFilter(request, response);		
	}
}
