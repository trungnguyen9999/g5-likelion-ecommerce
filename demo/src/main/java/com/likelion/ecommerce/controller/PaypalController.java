package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/payment")
public class PaypalController {

    @Value("${application.backend.url}")
    private String backendUrl;

    private final PaypalService paypalService;

    @PostMapping("/create")
    public Map<String, String> createPayment(@RequestBody Map<String, String> paymentData) {
        Map<String, String> response = new HashMap<>();
        try {
            String method = paymentData.get("method");
            Double amount = Double.valueOf(paymentData.get("amount"));
            String currency = paymentData.get("currency");
            String description = paymentData.get("description");
            String cancelUrl = backendUrl + "/payment/cancel";
            String successUrl = backendUrl + "/payment/success";
            Payment payment = paypalService.createPayment(
                    amount,
                    currency,
                    method,
                    "sale",
                    description,
                    cancelUrl,
                    successUrl
            );

            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    response.put("approvalUrl", links.getHref());
                    return response;
                }
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred:: ", e);
        }
        response.put("error", "Payment creation failed");
        return response;
    }

}
