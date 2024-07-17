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
@Table(name = "password_reset_tokens")
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false, name = "expiry_date")
    private Date expiryDate;

    @OneToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "account_id")
    private Account account;

    public PasswordResetToken(String token, Date date, Account account) {
        this.token = token;
        this.expiryDate = date;
        this.account = account;
    }
}
