package com.nguyenngochaianh.btl.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PaymentResponse {
    private String qrCode;
    private String bankName;
    private String accountNumber;
    private String accountName;
    private Double amount;
    private String des;

    public PaymentResponse() {
    }

    public PaymentResponse(String qrCode, String bankName, String accountNumber, String accountName, Double amount, String des) {
        this.qrCode = qrCode;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.amount = amount;
        this.des = des;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    // Builder Pattern
    public static class Builder {
        private String qrCode;
        private String bankName;
        private String accountNumber;
        private String accountName;
        private Double amount;
        private String des;

        // Phương thức thiết lập cho `qrCode`
        public Builder qrCode(String qrCode) {
            this.qrCode = qrCode;
            return this;
        }

        // Phương thức thiết lập cho `bankName`
        public Builder bankName(String bankName) {
            this.bankName = bankName;
            return this;
        }

        // Phương thức thiết lập cho `accountNumber`
        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        // Phương thức thiết lập cho `accountName`
        public Builder accountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        // Phương thức thiết lập cho `amount`
        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        // Phương thức thiết lập cho `des`
        public Builder des(String des) {
            this.des = des;
            return this;
        }

        // Phương thức `build` để tạo ra đối tượng `PaymentResponse`
        public PaymentResponse build() {
            return new PaymentResponse(qrCode, bankName, accountNumber, accountName, amount, des);
        }
    }

    // Phương thức tĩnh để khởi tạo builder
    public static Builder builder() {
        return new Builder();
    }
}
