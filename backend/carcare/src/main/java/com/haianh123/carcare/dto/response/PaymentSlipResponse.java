package com.haianh123.carcare.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSlipResponse {
    private String qrUrl;
    private String bankName;
    private String bankAccount;
    private String accountName;
    private Double total;
    private String des;
}
