package com.likelion.ecommerce.controller;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.likelion.ecommerce.service.CloudinaryService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
@Validated
public class UploadFileController {
	
	@Autowired
	private CloudinaryService cloudinaryService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/image")
	public ResponseEntity<LinkedHashMap> uploadImage(
			@RequestParam("data") MultipartFile data, 
			@RequestParam("title") String title) throws IOException {
	    String url = cloudinaryService.uploadFile(data);
	    LinkedHashMap response = new LinkedHashMap<>();
	    response.put("title", title);
	    response.put("url", url);
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
