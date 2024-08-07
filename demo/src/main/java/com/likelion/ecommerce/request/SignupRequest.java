package com.likelion.ecommerce.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Data
public class SignupRequest {

    private String username;

    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private Integer type;

    private Integer status;

    private Date createdAt;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;

    private Date birthdate;

    private String addressLine1;

    private String addressLine2;

    private String apartment;

    private String suburb;

    private String city;

    private String region;

    private String avatar;

    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}
