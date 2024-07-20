package com.likelion.ecommerce.request;

import lombok.Data;

@Data
public class ForgotPassRequest {
    String email;
    String newPassword;
}
