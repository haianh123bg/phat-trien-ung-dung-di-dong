package com.nguyenngochaianh.btl.dto.request;

public class PaymentRequest {
    private String orderId;
    private Double amount;
    private String type;

    // Constructor mặc định
    public PaymentRequest() {
    }

    // Constructor với tất cả các tham số
    public PaymentRequest(String orderId, Double amount, String type) {
        this.orderId = orderId;
        this.amount = amount;
        this.type = type;
    }

    // Getter và Setter
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Builder Pattern
    public static class Builder {
        private String orderId;
        private Double amount;
        private String type;

        // Phương thức thiết lập cho `orderId`
        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        // Phương thức thiết lập cho `amount`
        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        // Phương thức thiết lập cho `type`
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        // Phương thức `build` để tạo ra đối tượng `PaymentRequest`
        public PaymentRequest build() {
            return new PaymentRequest(orderId, amount, type);
        }
    }

    // Phương thức tĩnh để khởi tạo builder
    public static Builder builder() {
        return new Builder();
    }
}
