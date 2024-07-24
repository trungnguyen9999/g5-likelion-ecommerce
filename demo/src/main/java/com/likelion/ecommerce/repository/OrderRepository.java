package com.likelion.ecommerce.repository;

import com.likelion.ecommerce.entities.Order;
import com.likelion.ecommerce.response.OrderStatusCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o ORDER BY o.orderId DESC")
    Optional<Order> findTopByOrderByIdDesc();

    List<Order> findOrdersByAccountId(Integer accountId);

    @Query(value = "SELECT " +
            "   SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) AS status1, " +
            "   SUM(CASE WHEN status_id = 2 THEN 1 ELSE 0 END) AS status2, " +
            "   SUM(CASE WHEN status_id = 3 THEN 1 ELSE 0 END) AS status3, " +
            "   SUM(CASE WHEN status_id = 4 THEN 1 ELSE 0 END) AS status4, " +
            "   SUM(CASE WHEN status_id = 5 THEN 1 ELSE 0 END) AS status5, " +
            "   SUM(CASE WHEN status_id = 6 THEN 1 ELSE 0 END) AS status6, " +
            "   SUM(CASE WHEN status_id = 7 THEN 1 ELSE 0 END) AS status7 " +
            " FROM orders " +
            " WHERE order_time BETWEEN :from AND :to", nativeQuery = true)
    Map getReportOfOrderStatus(Date from, Date to);

    @Query("SELECT CASE " +
            "WHEN o.status.id = 1 THEN 'Processing' " +
            "WHEN o.status.id = 2 THEN 'Completed' " +
            "WHEN o.status.id = 3 THEN 'Cancelled' " +
            "END as orderStatusName, COUNT(o) as orderStatusCount " +
            "FROM Order o " +
            "GROUP BY o.status.id ORDER BY o.status.id")
    List<OrderStatusCount> countOrdersByStatus();
}
