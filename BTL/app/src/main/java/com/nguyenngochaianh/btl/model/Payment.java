package com.nguyenngochaianh.btl.model;

import java.util.Date;

public class Payment {
    private int paymentId;
    private int userId;
    private double amount;
    private Date paymentDate;
    private String status;

    // Constructor mặc định
    public Payment() {
    }

    // Constructor với tất cả các tham số
    public Payment(int paymentId, int userId, double amount, Date paymentDate, String status) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    // Getter và Setter
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Builder Pattern
    public static class Builder {
        private int paymentId;
        private int userId;
        private double amount;
        private Date paymentDate;
        private String status;

        public Builder paymentId(int paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder paymentDate(Date paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        // Build method to return the final Payment object
        public Payment build() {
            return new Payment(paymentId, userId, amount, paymentDate, status);
        }
    }
}
