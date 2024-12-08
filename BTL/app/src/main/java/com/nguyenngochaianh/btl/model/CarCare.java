package com.nguyenngochaianh.btl.model;

public class CarCare {

    private int carCareId;
    private String carId;
    private String receivingLocation;
    private String receiptDate;
    private String returnDate;
    private String status;
    private String registrationService;

    public CarCare(int carCareId, String carId, String receivingLocation, String receiptDate, String returnDate, String status, String registrationService) {
        this.carCareId = carCareId;
        this.carId = carId;
        this.receivingLocation = receivingLocation;
        this.receiptDate = receiptDate;
        this.returnDate = returnDate;
        this.status = status;
        this.registrationService = registrationService;
    }

    public CarCare(String carId, String receivingLocation, String receiptDate, String returnDate, String status, String registrationService) {
        this.carId = carId;
        this.receivingLocation = receivingLocation;
        this.receiptDate = receiptDate;
        this.returnDate = returnDate;
        this.status = status;
        this.registrationService = registrationService;
    }

    public CarCare() {
    }

    public int getCarCareId() {
        return carCareId;
    }

    public void setCarCareId(int carCareId) {
        this.carCareId = carCareId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegistrationService() {
        return registrationService;
    }

    public void setRegistrationService(String registrationService) {
        this.registrationService = registrationService;
    }

    public String getReceivingLocation() {
        return receivingLocation;
    }

    public void setReceivingLocation(String receivingLocation) {
        this.receivingLocation = receivingLocation;
    }
}
