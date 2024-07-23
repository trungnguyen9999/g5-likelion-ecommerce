package com.likelion.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.likelion.ecommerce.service.ReportService;

import lombok.RequiredArgsConstructor;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api/report-dashboard")
@RequiredArgsConstructor
@Validated
public class ReportController {

	private final ReportService reportService;

    @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/order")
    public ResponseEntity<?> getReportOfOrder()
    {
        return ResponseEntity.ok()
        		.body(reportService.getReportOfOrder());
    }

    @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/revenue")
    public ResponseEntity<?> getReportOfRevenue()
    {		
        return ResponseEntity.ok()
        		.body(reportService.getReportOfRevenue());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/order-status")
    public ResponseEntity<?> getReportOfOrderStatus(
            @RequestParam Date from,
            @RequestParam Date to
    )
    {
        return ResponseEntity.ok()
                .body(reportService.getReportOfOrderStatus(from, to));
    }
}
