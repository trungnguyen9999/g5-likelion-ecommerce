package com.likelion.ecommerce.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "accounts",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username")
		})
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_account_id_seq")
    @SequenceGenerator(name = "accounts_account_id_seq", sequenceName = "accounts_account_id_seq", allocationSize = 1)
	private Integer accountId;

	@NotBlank
	@Size(max = 20)
	@Column(name = "username")
	private String username;

	@NotBlank
	@Size(max = 120)
	@Column(name = "password")
	private String password;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "created_at")
	private Date createdAt;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "account_roles",
			joinColumns = @JoinColumn(name = "account_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public Account(String username, String password, Integer type, Integer status, Date createdAt) {
		this.username = username;
		this.password = password;
		this.type = type;
		this.status = status;
		this.createdAt = createdAt;
	}
}
