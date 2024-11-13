package com.nguyenngochaianh.btl.dto.response;

public class PaymentResponse {
    private String qrCode;
    private String bankName;
    private String accountNumber;
    private String accountName;
    private Double amount;
    private String des;

    
    public PaymentResponse(String qrCode, String bankName, String accountNumber, String accountName, Double amount, String des) {
        this.qrCode = qrCode;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.amount = amount;
        this.des = des;
    }
}
