package com.nguyenngochaianh.btl.model;

import java.util.Date;

public class ServiceHistory {
    private int serviceHistoryId;
    private int carId;
    private int serviceId;
    private Date serviceDate;
    private String notes;

    // Constructor mặc định
    public ServiceHistory() {
    }

    // Constructor với tất cả các tham số
    public ServiceHistory(int serviceHistoryId, int carId, int serviceId, Date serviceDate, String notes) {
        this.serviceHistoryId = serviceHistoryId;
        this.carId = carId;
        this.serviceId = serviceId;
        this.serviceDate = serviceDate;
        this.notes = notes;
    }

    // Getter và Setter
    public int getServiceHistoryId() {
        return serviceHistoryId;
    }

    public void setServiceHistoryId(int serviceHistoryId) {
        this.serviceHistoryId = serviceHistoryId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Builder Pattern
    public static class Builder {
        private int serviceHistoryId;
        private int carId;
        private int serviceId;
        private Date serviceDate;
        private String notes;

        public Builder serviceHistoryId(int serviceHistoryId) {
            this.serviceHistoryId = serviceHistoryId;
            return this;
        }

        public Builder carId(int carId) {
            this.carId = carId;
            return this;
        }

        public Builder serviceId(int serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder serviceDate(Date serviceDate) {
            this.serviceDate = serviceDate;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        // Build method to return the final ServiceHistory object
        public ServiceHistory build() {
            return new ServiceHistory(serviceHistoryId, carId, serviceId, serviceDate, notes);
        }
    }
}
