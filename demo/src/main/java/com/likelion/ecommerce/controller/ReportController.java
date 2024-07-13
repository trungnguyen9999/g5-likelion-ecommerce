package com.likelion.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likelion.ecommerce.service.ReportService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/report-dashboard")
@RequiredArgsConstructor
@Validated
public class ReportController {

	private final ReportService reportService;

	@GetMapping("/order")
    public ResponseEntity<?> getReportOfOrder()
    {
        return ResponseEntity.ok()
        		.body(reportService.getReportOfOrder());
    }
	
	@GetMapping("/revenue")
    public ResponseEntity<?> getReportOfRevenue()
    {		
        return ResponseEntity.ok()
        		.body(reportService.getReportOfRevenue());
    }
	
}
