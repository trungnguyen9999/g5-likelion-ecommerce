package com.likelion.ecommerce.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_account_id_seq")
    @SequenceGenerator(name = "accounts_account_id_seq", sequenceName = "accounts_account_id_seq", allocationSize = 1)
	private Integer accountId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "created_at")
	private Date createdAt;
}
