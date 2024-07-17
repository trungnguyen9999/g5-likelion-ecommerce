package com.likelion.ecommerce.dto;

import java.util.Date;

public interface UserDetailsDto {
    public Integer getUserId();

    public Integer getAccountId();

    public String getUsername();

    public String getPassword();

    public Integer getType();

    public Integer getStatus();

    public Date getCreatedAt();

    public String getFullName();

    public String getPhoneNumber();

    public String getEmail();

    public Date getBirthdate();

    public String getAddressLine1();

    public String getAddressLine2();

    public String getApartment();

    public String getSuburb();

    public String getCity();

    public String getRegion();

    public String getAvatar();

}
