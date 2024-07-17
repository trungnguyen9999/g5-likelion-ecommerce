package com.likelion.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "order_status")
public class OrderStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_status_id_seq")
    @SequenceGenerator(name = "order_status_id_seq", sequenceName = "order_status_id_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "name")
	private String name;

}
