package com.haianh123.carcare.service;

import com.haianh123.carcare.dto.response.PaymentSlipResponse;

public interface PaymentService {
    PaymentSlipResponse createPayment(Integer courseId, String name, String email);
}
