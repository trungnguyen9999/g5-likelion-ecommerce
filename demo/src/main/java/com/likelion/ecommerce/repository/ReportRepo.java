package com.likelion.ecommerce.repository;

import java.math.BigDecimal;
import java.util.Date;

public interface ReportRepo {
	
	Integer getReportOfOrder(Date from, Date to);
	
	BigDecimal getReportOfRevenue(Date from, Date to);
}
