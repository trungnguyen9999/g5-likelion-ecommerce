package com.likelion.ecommerce.controller;

import com.likelion.ecommerce.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/payment")
public class PaypalController {

    @Value("${application.backend.url}")
    private String backendUrl;

    @Value("${application.frontend.url}")
    private String frontendUrl;

    private final PaypalService paypalService;

    @PostMapping("/create")
    public Map<String, String> createPayment(@RequestBody Map<String, String> paymentData) {
        Map<String, String> response = new HashMap<>();
        try {
            String method = paymentData.get("method");
            Double amount = Double.valueOf(paymentData.get("amount"));
            String currency = paymentData.get("currency");
            String description = paymentData.get("description");
            String cancelUrl = backendUrl + "/api/payment/public/cancel";
            String successUrl = backendUrl + "/api/payment/public/success";
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

    @GetMapping("/public/success")
    public RedirectView paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
    ) {
        RedirectView redirectView = new RedirectView();
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                redirectView.setUrl(frontendUrl + "/cart/checkout/payment/success");
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred:: ", e);
            redirectView.setUrl(frontendUrl + "/cart/checkout/payment/cancel");
        }
        return redirectView;
    }

    @GetMapping("/public/cancel")
    public RedirectView paymentSuccess() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(frontendUrl + "/cart/checkout/payment/cancel");
        return redirectView;
    }

}
