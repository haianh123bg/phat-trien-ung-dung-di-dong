package com.nguyenngochaianh.btl.model;

public class Car {
    private int carId;
    private int userId;
    private String licensePlate;
    private String model;
    private int year;

    // Constructor mặc định
    public Car() {
    }

    // Constructor với tất cả các tham số
    public Car(int carId, int userId, String licensePlate, String model, int year) {
        this.carId = carId;
        this.userId = userId;
        this.licensePlate = licensePlate;
        this.model = model;
        this.year = year;
    }

    // Getter và Setter
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // Builder Pattern
    public static class Builder {
        private int carId;
        private int userId;
        private String licensePlate;
        private String model;
        private int year;

        public Builder carId(int carId) {
            this.carId = carId;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder licensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        // Build method to return the final Car object
        public Car build() {
            return new Car(carId, userId, licensePlate, model, year);
        }
    }
}
