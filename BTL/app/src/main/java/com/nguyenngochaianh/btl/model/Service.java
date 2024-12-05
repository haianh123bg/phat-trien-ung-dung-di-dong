package com.nguyenngochaianh.btl.model;

public class Service {
    private int serviceId;
    private String name;
    private String description;
    private double price;

    // Constructor mặc định
    public Service() {
    }

    // Constructor với tất cả các tham số
    public Service(int serviceId, String name, String description, double price) {
        this.serviceId = serviceId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getter và Setter
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Builder Pattern
    public static class Builder {
        private int serviceId;
        private String name;
        private String description;
        private double price;

        public Builder serviceId(int serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        // Build method to return the final Service object
        public Service build() {
            return new Service(serviceId, name, description, price);
        }
    }
}
