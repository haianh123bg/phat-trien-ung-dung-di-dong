package com.haianh123.carcare.controller.user;

import com.haianh123.carcare.dto.response.ApiResponse;
import com.haianh123.carcare.dto.response.PaymentSlipResponse;
import com.haianh123.carcare.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {
    PaymentService paymentService;

    @PostMapping("/create-payment/{course_id}")
    public ApiResponse<PaymentSlipResponse> createPayment(
        @PathVariable(value = "course_id") Integer course_id,
        @RequestParam(value = "user_name") String name,
        @RequestParam(value = "email") String email

    ) {

        return ApiResponse.<PaymentSlipResponse>builder()
                .code(200)
                .result(paymentService.createPayment(course_id, name, email))
                .build();
    }
}
