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
@Table(name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "email")
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_user_id_seq")
    @SequenceGenerator(name = "users_user_id_seq", sequenceName = "users_user_id_seq", allocationSize = 1)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "account_id")
	private Integer accountId;
	
	@Column(name = "lastname")
	private String lastName;

	@Column(name = "firstname ")
	private String firstName;

	@Column(name = "phonenumber")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "birthdate")
	private Date birthdate;

	@Column(name = "address_line1")
	private String addressLine1;

	@Column(name = "address_line2")
	private String addressLine2;

	@Column(name = "apartment")
	private String apartment;

	@Column(name = "suburb")
	private String suburb;

	@Column(name = "city")
	private String city;

	@Column(name = "region")
	private String region;

	@Column(name = "avatar")
	private String avatar;

	public User(String phoneNumber, String firstName, String lastName, String email, Date birthdate, String addressLine1, String addressLine2, String apartment,
			String suburb, String city, String region, String avatar) {
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthdate = birthdate;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.apartment = apartment;
		this.suburb = suburb;
		this.city = city;
		this.region = region;
		this.avatar = avatar;
	}
}
