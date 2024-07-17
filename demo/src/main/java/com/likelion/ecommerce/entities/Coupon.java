package com.likelion.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupon_coupon_id_seq")
    @SequenceGenerator(name = "coupon_coupon_id_seq", sequenceName = "coupon_coupon_id_seq", allocationSize = 1)
    @Column(name = "coupon_id")
    private Integer couponId;

    @Column(name = "code")
    private String code;

    @Column(name = "value")
    private Integer value;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "quantity_used")
    private Integer quantityUsed;

    @Column(name = "created_at")
    private Date createdAt;
}
