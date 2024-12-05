package com.nguyenngochaianh.btl.model;

import java.util.Date;

public class Notification {
    private int notificationId;
    private int userId;
    private String message;
    private Date notificationDate;
    private String status;

    // Constructor mặc định
    public Notification() {
    }

    // Constructor với tất cả các tham số
    public Notification(int notificationId, int userId, String message, Date notificationDate, String status) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.notificationDate = notificationDate;
        this.status = status;
    }

    // Getter và Setter
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Builder Pattern
    public static class Builder {
        private int notificationId;
        private int userId;
        private String message;
        private Date notificationDate;
        private String status;

        public Builder notificationId(int notificationId) {
            this.notificationId = notificationId;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder notificationDate(Date notificationDate) {
            this.notificationDate = notificationDate;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        // Build method to return the final Notification object
        public Notification build() {
            return new Notification(notificationId, userId, message, notificationDate, status);
        }
    }
}
