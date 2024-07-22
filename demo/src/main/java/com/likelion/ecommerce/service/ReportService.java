package com.likelion.ecommerce.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.likelion.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likelion.ecommerce.repository.ReportRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

	private final ReportRepo repo;

	private final OrderService orderService;

	@SuppressWarnings("unchecked")
	public Object getReportOfOrder() 
	{
		Date now = new Date();
		//Thống kê This Year - This Month - This Week - To Day
		Map thongKe = new HashMap();
		thongKe.put("thisYear", repo.getReportOfOrder(this.getTimePeriodStart("YEAR"), now));
		thongKe.put("thisMonth", repo.getReportOfOrder(this.getTimePeriodStart("MONTH"), now));
		thongKe.put("thisWeek", repo.getReportOfOrder(this.getTimePeriodStart("WEEK"), now));
		thongKe.put("toDay", repo.getReportOfOrder(this.getTimePeriodStart("DAY"), now));
		
		return thongKe;
	}

	@SuppressWarnings("unchecked")
	public Object getReportOfRevenue() 
	{
		Date now = new Date();
		//Thống kê This Year - This Month - This Week - To Day
		Map thongKe = new HashMap();
		thongKe.put("thisYear", repo.getReportOfRevenue(this.getTimePeriodStart("YEAR"), now));
		thongKe.put("thisMonth", repo.getReportOfRevenue(this.getTimePeriodStart("MONTH"), now));
		thongKe.put("thisWeek", repo.getReportOfRevenue(this.getTimePeriodStart("WEEK"), now));
		thongKe.put("toDay", repo.getReportOfRevenue(this.getTimePeriodStart("DAY"), now));
		
		return thongKe;
	}
	
	private Date getTimePeriodStart(String period) 
	{
        LocalDate startDate;

        switch (period.toUpperCase()) {
            case "YEAR":
                startDate = Year.now().atDay(1);
                break;
            case "MONTH":
                startDate = YearMonth.now().atDay(1);
                break;
            case "WEEK":
                startDate = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                break;
            case "DAY":
                startDate = LocalDate.now();
                break;
            default:
                throw new IllegalArgumentException("Invalid period: " + period);
        }

        return Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

	public Map getReportOfOrderStatus(Date from, Date to) {
		return orderService.getReportOfOrderStatus(from, to);
	}
}
